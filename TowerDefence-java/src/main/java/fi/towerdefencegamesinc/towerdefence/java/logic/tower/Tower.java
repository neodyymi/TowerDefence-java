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
public interface Tower {

    int shoot();

    int upgrade();

    int getUpgradeCost();

    int getLevel();

    boolean readyToShoot();

    public char getCharRepr();
}
