/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;

/**
 * A template for a special kind of attacker. A stronger single unit attacking.
 *
 * @author vrsaari
 */
public class BossAttacker extends BasicAttacker {    
    
    private static final int DEFAULT_LOOT = 1000;
    private static final int DEFAULT_HEALTH = 1000;
    
    /**
     * Creates a boss attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed of the boss attacker.
     * @param damage The base damage of the boss attacker.
     */
    public BossAttacker(Tile spawnLocation, int speed, int damage) {
        super(spawnLocation, speed, damage, false, DEFAULT_HEALTH, DEFAULT_LOOT);
    }

    /**
     * Creates a boss attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed of the boss attacker.
     * @param damage The base damage of the boss attacker.
     * @param health The base health for the boss attacker.
     * @param loot Amount of currency player gets for killing this attacker.
     */
    public BossAttacker(Tile spawnLocation, int speed, int damage, int health, int loot) {
        super(spawnLocation, speed, damage, false, health, loot);
    }

}
