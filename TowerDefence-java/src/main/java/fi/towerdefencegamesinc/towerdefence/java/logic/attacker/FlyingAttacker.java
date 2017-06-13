/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;

/**
 * A template for a flying attacker. It can move more freely than non-flying
 * ones.
 *
 * @author vrsaari
 */
public class FlyingAttacker extends BasicAttacker {

    /**
     * Creates a flying attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed of the attacker.
     * @param damage The base damage of the attacker.
     * @param health Base hitpoints for the attacker.
     */
    public FlyingAttacker(Tile spawnLocation, int speed, int damage, int health) {
        super(spawnLocation, speed, damage, true, health);
    }

    /**
     * Creates a flying attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed of the attacker.
     * @param damage The base damage of the attacker.
     */
    public FlyingAttacker(Tile spawnLocation, int speed, int damage) {
        super(spawnLocation, speed, damage, true, 100);
    }
}
