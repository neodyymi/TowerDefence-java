/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.Modifier;
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

    private int speed;
    private int damage;
    private Set<Modifier> modifiers;
    private boolean flying;

    /**
     * Creates an attacker.
     *
     * @param speed The base speed for the attacker.
     * @param damage The base damage for the attacker.
     * @param flying Does the attacker fly?
     */
    public BasicAttacker(int speed, int damage, boolean flying) {
        this.speed = speed;
        this.damage = damage;
        this.flying = flying;

        this.modifiers = new HashSet();
    }

    /**
     * Simplified constructor for a non-flying attacker.
     *
     * @param speed The base speed for the attacker.
     * @param damage The base damage for the attacker.
     */
    public BasicAttacker(int speed, int damage) {
        this(speed, damage, false);
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
