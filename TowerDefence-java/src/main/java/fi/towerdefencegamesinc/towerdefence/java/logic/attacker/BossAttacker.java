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

    /**
     * Creates a boss attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed of the boss attacker.
     * @param damage The base damage of the boss attacker.
     */
    public BossAttacker(Tile spawnLocation, int speed, int damage) {
        super(spawnLocation, speed, damage, false, 1000);
    }

    /**
     * Creates a boss attacker.
     *
     * @param spawnLocation The location the attacker spawned in.
     * @param speed The base speed of the boss attacker.
     * @param damage The base damage of the boss attacker.
     * @param health The base health for the boss attacker.
     */
    public BossAttacker(Tile spawnLocation, int speed, int damage, int health) {
        super(spawnLocation, speed, damage, false, health);
    }

}
