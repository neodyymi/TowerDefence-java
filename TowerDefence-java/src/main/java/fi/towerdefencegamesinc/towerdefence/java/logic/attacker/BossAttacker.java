/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

/**
 * A template for a special kind of attacker. A stronger single unit attacking.
 * @author vrsaari
 */
public class BossAttacker extends BasicAttacker {

    /**
     * Creates a boss attacker.
     * @param speed The base speed of the boss attacker.
     * @param damage The base damage of the boss attacker.
     */
    public BossAttacker(int speed, int damage) {
        super(speed, damage);
    }

}
