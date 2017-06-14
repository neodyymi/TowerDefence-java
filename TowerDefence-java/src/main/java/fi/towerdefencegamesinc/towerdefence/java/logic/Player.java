/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

/**
 * The player-class keeps information on a player.
 *
 * @author vrsaari
 */
public class Player {

    private String name;
    private int currency;
    private int health;

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
    }
    
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

    public void takeDamage(int damageToBase) {
        this.health -= damageToBase;
        System.out.println("Player took " + damageToBase + " damage.");
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", currency: " + this.currency 
                + ", base: " + this.health;
    }

    public boolean gameOver() {
        return this.health <= 0;
    }
    
    
}
