/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

/**
 * Interface for all towers to be placed by the player.
 * @author vrsaari
 */
public interface Tower {

    /**
     * Attempt to shoot.
     * @return Damage to be dealt.
     */
    int shoot();

    /**
     * Upgrade the tower level.
     * @return The new level of the tower.
     */
    int upgrade();

    /**
     * Find out how much it would cost to upgrade the tower.
     * @return The cost to upgrade the tower to the next level.
     */
    int getUpgradeCost();

    /**
     * 
     * @return The current level of the tower.
     */
    int getLevel();

    /**
     * Find out if the tower is ready to shoot again.
     * @return Is the tower ready to shoot?
     */
    boolean readyToShoot();

    /**
     * 
     * @return Single character representation for the tower.
     */
    public char getCharRepr();
}
