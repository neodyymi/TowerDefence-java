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

    private static final int COST = 200;
    private static final int[] UPGRADE_COST = new int[]{200, 400, 600, 800, 1000};

    /**
     * Create a freeze tower with the given parameters.
     *
     * @param tile The tile the tower is located in.
     * @param power The power of the tower.
     * @param speed The firingspeed of the tower.
     * @param range The range of the tower.
     * @param upgradeCost Array of costs to upgrade the tower.
     */
    public FreezeTower(Tile tile, int power, int speed, double range, int[] upgradeCost) {
        super(tile, power, speed, range, upgradeCost, COST);
    }

    /**
     * Create a freeze tower with set parameters.
     *
     * @param tile The tile the tower is located in.
     */
    public FreezeTower(Tile tile) {
        this(tile, 1, 1000, 2, UPGRADE_COST);
    }

    /**
     * Create a freeze tower with set parameters.
     */
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
