/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.BasicModifier;
import java.util.List;

/**
 * Interface for all types of attackers trying to destroy the player.
 *
 * @author vrsaari
 */
public interface Attacker {

    /**
     * Move the attacker as it is designed to.
     * @param start The tile the movement starts in.
     */
    public void move();

    /**
     * Attack as designed to.
     *
     * @return Amount of damage dealt.
     */
    public int attack();

    /**
     * Can this attacker fly?
     *
     * @return Can this attacker fly?
     */
    public boolean canFly();

    /**
     * Add a modifier to the attacker.
     *
     * @param mod Modifier to be added.
     */
    public void addModifier(BasicModifier mod);

    /**
     * Get all modifiers modifying the attacker.
     *
     * @return List of modifiers.
     */
    public List<BasicModifier> getModifiers();

    /**
     * Make the modifiers tick forward in effect and duration.
     */
    public void updateModifiers();

    /**
     * How fast the attacker is.
     *
     * @return The speed of the attacker.
     */
    public int getSpeed();

    @Override
    public String toString();
    
    /**
     *
     * @return The tile the attacker is currently located in.
     */
    public Tile getTile();
    
    /**
     * Inform the attacker it has been hit with an amount of damage.
     * @param amount Amount of damage taken.
     */
    public void takeDamage(int amount);
    
    /**
     *
     * @return True if the attacker is dead.
     */
    public boolean isDead();
    
    /**
     *
     * @return Amount of loot gained from dead attacker.
     */
    public int loot();
    
    /**
     *
     * @return Percentage of health left.
     */
    public double getHealthPct();
}
