/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.BasicModifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A basic type of attacker which serves as both a basic minion and a
 * buildingblock for other types of attackers.
 *
 * @author vrsaari
 */
public class BasicAttacker implements Attacker {

    private double speed;
    private double damage;
    private Set<BasicModifier> modifiers;
    private boolean flying;
    private int health;
    private final int baseHealth;

    private Tile current;
    private Tile previous;
    private double movementsLeft;
    private final double baseSpeed;
    private final int baseDamage;
    private int loot;

    private static final int DEFAULT_LOOT = 100;
    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_DAMAGE = 1;
    private static final double DEFAULT_SPEED = 0.5;

    /**
     * Creates an attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed for the attacker.
     * @param damage The base damage for the attacker.
     * @param flying Does the attacker fly?
     * @param health Base hitpoints for the attacker.
     * @param loot Amount of currency player gets for killing this attacker.
     */
    public BasicAttacker(Tile spawnLocation, double speed, int damage, boolean flying, int health, int loot) {
        this.speed = speed;
        this.baseSpeed = speed;
        this.damage = damage;
        this.baseDamage = damage;
        this.flying = flying;
        this.health = health;
        this.baseHealth = health;
        this.movementsLeft = 0;

        this.previous = null;
        this.current = spawnLocation;

        this.modifiers = new HashSet();

        this.loot = loot;
    }

    /**
     * Creates an attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed for the attacker.
     * @param damage The base damage for the attacker.
     * @param flying Does the attacker fly?
     */
    public BasicAttacker(Tile spawnLocation, double speed, int damage, boolean flying) {
        this(spawnLocation, speed, damage, flying, DEFAULT_HEALTH, DEFAULT_LOOT);

        this.modifiers = new HashSet();
    }

    /**
     * Simplified constructor for a non-flying attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed for the attacker.
     * @param damage The base damage for the attacker.
     */
    public BasicAttacker(Tile spawnLocation, double speed, int damage) {
        this(spawnLocation, speed, damage, false);
    }

    /**
     * Simplified constructor for a non-flying attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     */
    public BasicAttacker(Tile spawnLocation) {
        this(spawnLocation, DEFAULT_SPEED, DEFAULT_DAMAGE);
    }

    @Override
    public void move() {
        if (this.current.isBase()) {
            return;
        }
        this.movementsLeft += this.speed;
        while (this.movementsLeft >= 1) {
            Tile next = this.current.nextRoad(this.previous);
            if (next != null) {
                this.current.removeAttacker(this);
                this.previous = this.current;
                this.current = next;
                this.current.addAttacker(this);
                this.movementsLeft--;
            } else {
                break;
            }
        }
    }

    @Override
    public int attack() {
        return (int) this.damage;
    }

    @Override
    public boolean canFly() {
        return this.flying;
    }

    @Override
    public void addModifier(BasicModifier mod) {
        this.modifiers.add(mod);
    }

    @Override
    public List<BasicModifier> getModifiers() {
        return new ArrayList(this.modifiers);
    }

    @Override
    public void updateModifiers() {
        this.modifiers.forEach(m -> m.update(this));
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String flyingStr = "";
        if (this.flying) {
            flyingStr = "flying, ";
        }
        String modString = "none";
        if (!this.modifiers.isEmpty()) {
            List<BasicModifier> modList = getModifiers();
            StringBuilder mods = new StringBuilder(modList.get(0).toString());
            modList.stream().skip(1).forEach(x -> {
                mods.append(", ");
                mods.append(x.toString());
            });
            modString = mods.toString();

        }
        sb.append("Basic ")
                .append(flyingStr)
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

    @Override
    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.isDead()) {
            this.current.removeAttacker(this);
        }
    }

    @Override
    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public int loot() {
        if (!isDead()) {
            return 0;
        }
        int looted = this.loot;
        this.loot = 0;
        return looted;
    }

    @Override
    public double getHealthPct() {
        return (double) this.health / this.baseHealth;
    }

    @Override
    public int getHealth() {
        return health;
    }
    
    

}
