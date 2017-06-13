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

    /**
     * Create a player object.
     *
     * @param name The name of the player.
     * @param currency Amount of game currency.
     */
    public Player(String name, int currency) {
        this.name = name;
        this.currency = currency;
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
}
