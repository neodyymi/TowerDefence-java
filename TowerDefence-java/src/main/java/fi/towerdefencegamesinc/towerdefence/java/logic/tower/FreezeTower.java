/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

/**
 * A tower with capability to freeze or slow enemies.
 *
 * @author vrsaari
 */
public class FreezeTower extends BasicTower {

    /**
     * Create a freeze tower with the given parameters.
     *
     * @param power The power of the tower.
     * @param speed The firingspeed of the tower.
     * @param upgradeCost Array of costs to upgrade the tower.
     */
    public FreezeTower(int power, int speed, int[] upgradeCost) {
        super(power, speed, upgradeCost);
    }

    /**
     * Create a freeze tower with set parameters.
     */
    public FreezeTower() {
        this(1, 1000, new int[]{2, 4, 6, 8, 10});
    }

    @Override
    public int shoot() {
        return super.shoot();
    }

    @Override
    public char getCharRepr() {
        return 'F';
    }

}
