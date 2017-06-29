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

    private static final int DEFAULT_LOOT = 200;
    private static final int DEFAULT_HEALTH = 100;

    /**
     * Creates a flying attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed of the attacker.
     * @param damage The base damage of the attacker.
     * @param health Base hitpoints for the attacker.
     * @param loot Amount of currency player gets for killing this attacker.
     */
    public FlyingAttacker(Tile spawnLocation, int speed, int damage, int health, int loot) {
        super(spawnLocation, speed, damage, true, health, loot);
    }

    /**
     * Creates a flying attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed of the attacker.
     * @param damage The base damage of the attacker.
     */
    public FlyingAttacker(Tile spawnLocation, int speed, int damage) {
        super(spawnLocation, speed, damage, true, DEFAULT_HEALTH, DEFAULT_LOOT);
    }
}
