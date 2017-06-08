/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

import java.util.Date;

/**
 * A basic type of tower.
 *
 * @author vrsaari
 */
public class BasicTower implements Tower {

    private int level;
    private int power;
    private int speed;
    private long lastShot;
    private int[] upgradeCost;

    /**
     * Create a basic tower with given parameters.
     * @param power The power of the tower.
     * @param speed The firingspeed of the tower.
     * @param upgradeCost Array of costs to upgrade the tower.
     */
    public BasicTower(int power, int speed, int[] upgradeCost) {
        this.power = power;
        this.speed = speed;
        this.level = 0;
        this.lastShot = 0;
        this.upgradeCost = upgradeCost;
    }

    /**
     * Create a basic tower.
     */
    public BasicTower() {
        this(1, 1000, new int[]{1, 2, 3, 4, 5});
    }

    @Override
    public int shoot() {
        return 1 * power;
    }

    @Override
    public int upgrade() {
        this.level++;
        return this.level;
    }

    @Override
    public int getUpgradeCost() {
        return this.upgradeCost[this.level];
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public boolean readyToShoot() {
        return this.lastShot - new Date().getTime() < this.speed;
    }

    @Override
    public char getCharRepr() {
        return 'B';
    }

}
