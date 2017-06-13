/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;

/**
 * A tower with capability to freeze or slow enemies.
 *
 * @author vrsaari
 */
public class FreezeTower extends BasicTower {

    /**
     * Create a freeze tower with the given parameters.
     *
     * @param tile The tile the tower is located in.
     * @param power The power of the tower.
     * @param speed The firingspeed of the tower.
     * @param upgradeCost Array of costs to upgrade the tower.
     */
    public FreezeTower(Tile tile, int power, int speed, double range, int[] upgradeCost) {
        super(tile, power, speed, range, upgradeCost);
    }

    /**
     * Create a freeze tower with set parameters.
     * @param tile The tile the tower is located in.
     */
    public FreezeTower(Tile tile) {
        this(tile, 1, 1000, 2, new int[]{2, 4, 6, 8, 10});
    }
    
    public FreezeTower() {
        this(null);
    }

    @Override
    public int shoot(Attacker target) {
        return super.shoot(target);
    }

    @Override
    public char getCharRepr() {
        return 'F';
    }

}
