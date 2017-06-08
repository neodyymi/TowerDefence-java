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

    /**
     * Create a player object.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
