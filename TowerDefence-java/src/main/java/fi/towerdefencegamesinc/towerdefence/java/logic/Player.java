/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;

/**
 * The player-class keeps information on a player.
 *
 * @author vrsaari
 */
public class Player {

    private String name;
    private int currency;
    private int health;
    private final int baseHealth;

    /**
     * Create a player object.
     *
     * @param name The name of the player.
     * @param currency Amount of game currency.
     * @param health Health of players base.
     */
    public Player(String name, int currency, int health) {
        this.name = name;
        this.currency = currency;
        this.health = health;
        this.baseHealth = health;
    }

    /**
     * Create a player object.
     *
     * @param name The name of the player.
     * @param currency Amount of game currency.
     */
    public Player(String name, int currency) {
        this(name, currency, 100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    /**
     * Make player take damage.
     *
     * @param damageToBase Damage to be taken.
     */
    public void takeDamage(int damageToBase) {
        this.health -= damageToBase;
        System.out.println("Player took " + damageToBase + " damage.");
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nCurrency: " + this.currency
                + "\nBase: " + this.health + "/" + this.baseHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Is the game over?
     *
     * @return True if player has lost.
     */
    public boolean gameOver() {
        return this.health <= 0;
    }

    /**
     * Attempt to buy a tower.
     *
     * @param tower The tower to be bought.
     * @return True if the tower was bought.
     */
    public boolean buy(Tower tower) {
        int cost = tower.getBuildCost();
        if (this.currency - cost >= 0) {
            if (tower.getTile().addTower(tower)) {
                this.currency -= cost;
                return true;
            }
        }
        return false;
    }

    /**
     * Attempt to sell a tower.
     *
     * @param tower The tower to be sold.
     * @return True if the tower was sold.
     */
    public boolean sell(Tower tower) {
        if (tower.getTile().removeTower(tower)) {
            int worth = tower.getWorth();
            this.currency += worth;
            return true;
        }
        return false;
    }

    /**
     * Attempt to upgrade a tower.
     *
     * @param tower The tower to be upgraded.
     * @return True if the tower was upgraded.
     */
    public boolean upgradeTower(Tower tower) {
        if (this.currency - tower.getUpgradeCost() < 0) {
            return false;
        }
        this.currency -= tower.getUpgradeCost();
        tower.upgrade();
        return true;
    }

    /**
     * Attempt to loot an attacker.
     * @param target The attacker to be looted.
     */
    public void loot(Attacker target) {
        this.currency += target.loot();
    }
}
