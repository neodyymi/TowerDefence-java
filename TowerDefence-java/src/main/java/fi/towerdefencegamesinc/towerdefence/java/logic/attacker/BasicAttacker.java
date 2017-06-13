/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.Location;
import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * A basic type of attacker which serves as both a basic minion and a
 * buildingblock for other types of attackers.
 *
 * @author vrsaari
 */
public class BasicAttacker implements Attacker {

    private int speed;
    private int damage;
    private Set<Modifier> modifiers;
    private boolean flying;
    private int health;
    private final int baseHealth;
    
    private Tile current;
    private Tile previous;

    /**
     * Creates an attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed for the attacker.
     * @param damage The base damage for the attacker.
     * @param flying Does the attacker fly?
     * @param health Base hitpoints for the attacker.
     */
    public BasicAttacker(Tile spawnLocation, int speed, int damage, boolean flying, int health) {
        this.speed = speed;
        this.damage = damage;
        this.flying = flying;
        this.health = health;
        this.baseHealth = health;
        
        this.previous = null;
        this.current = spawnLocation;

        this.modifiers = new HashSet();
    }

    /**
     * Creates an attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed for the attacker.
     * @param damage The base damage for the attacker.
     * @param flying Does the attacker fly?
     */
    public BasicAttacker(Tile spawnLocation, int speed, int damage, boolean flying) {
        this(spawnLocation, speed, damage, flying, 100);

        this.modifiers = new HashSet();
    }

    /**
     * Simplified constructor for a non-flying attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed for the attacker.
     * @param damage The base damage for the attacker.
     */
    public BasicAttacker(Tile spawnLocation, int speed, int damage) {
        this(spawnLocation, speed, damage, false, 100);
    }

    @Override
    public void move() {
        IntStream.range(0, this.speed).forEach((int x) -> {
            Tile next = this.current.nextRoad(this.previous);
            if(next != null) {
                this.current.removeAttacker(this);
                this.current = next;
                this.current.addAttacker(this);
            }
            
        });
    }

    @Override
    public int attack() {
        return this.damage;
    }

    @Override
    public boolean canFly() {
        return this.flying;
    }

    @Override
    public void addModifier(Modifier mod) {
        this.modifiers.add(mod);
    }

    @Override
    public List<Modifier> getModifiers() {
        return new ArrayList(this.modifiers);
    }

    @Override
    public void updateModifiers() {
        this.modifiers.forEach(m -> m.update(this));
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String flying = "";
        if (this.flying) {
            flying = "flying ";
        }
        String modString = "none";
        if (!this.modifiers.isEmpty()) {
            List<Modifier> modList = new ArrayList(this.modifiers);
            StringBuilder mods = new StringBuilder(modList.get(0).toString());
            modList.stream().skip(1).forEach(x -> {
                mods.append(", ");
                mods.append(x.toString());
            });

        }
        sb.append("Basic ")
                .append(flying)
                .append("speed: ").append(this.speed)
                .append(", damage: ").append(this.damage)
                .append(", health: ").append(this.health).append("/").append(this.baseHealth)
                .append(", modifiers: ").append(modString);

        return sb.toString();
    }

    @Override
    public Tile getTile() {
        return this.current;    
    }

}
