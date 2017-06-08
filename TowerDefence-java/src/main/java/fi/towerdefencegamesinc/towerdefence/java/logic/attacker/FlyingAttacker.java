/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

/**
 * A template for a flying attacker. It can move more freely than non-flying ones.
 * @author vrsaari
 */
public class FlyingAttacker extends BasicAttacker {

    /**
     * Creates a flying attacker.
     * @param speed The base speed of the attacker.
     * @param damage The base damage of the attacker.
     */
    public FlyingAttacker(int speed, int damage) {
        super(speed, damage, true);
    }

}
