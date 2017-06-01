/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

/**
 *
 * @author vrsaari
 */
public class BasicTower implements Tower {

    private int level;
    private int power;
    private int speed;
    private int lastShot;
    private int[] upgradeCost;

    public BasicTower() {
        this.level = 0;
        this.lastShot = 0;
        this.upgradeCost = new int[]{1, 2, 3, 4, 5};
    }

    @Override
    public int shoot() {
        return 1;
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
        return true;
    }

    @Override
    public char getCharRepr() {
        return 'B';
    }

}
