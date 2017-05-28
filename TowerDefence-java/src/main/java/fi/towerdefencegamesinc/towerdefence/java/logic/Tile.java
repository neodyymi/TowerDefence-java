/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

/**
 *
 * @author vrsaari
 */
public class Tile {
    
    private Type type;
    private Tower tower;
    private final boolean buildable;

    /**
     *
     * @param type
     * @param buildable
     */
    public Tile(Type type, boolean buildable) {
        this.type = type;
        this.buildable = buildable;
    }
    
    
}
