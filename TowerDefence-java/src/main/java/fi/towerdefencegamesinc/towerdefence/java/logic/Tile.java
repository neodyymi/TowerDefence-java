/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;

/**
 *
 * @author vrsaari
 */
public class Tile {

    private final Type type;
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
        this.tower = null;
    }

    public Tower getTower() {
        return tower;
    }

    public Type getType() {
        return type;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public boolean addTower(Tower tower) {
        if (this.tower == null && this.buildable) {
            this.tower = tower;
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public boolean equals(Object obj) {
//        Tile other = (Tile) obj;
//        return type == other.getType() && tower.equals(other.getTower()) 
//                && buildable == other.isBuildable();
//    }
}
