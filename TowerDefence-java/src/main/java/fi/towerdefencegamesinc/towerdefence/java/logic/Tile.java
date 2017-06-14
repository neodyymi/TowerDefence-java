/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tiles are the buildingblocks of the maps.
 *
 * @author vrsaari
 */
public class Tile {

    private final Type type;
    private Tower tower;
    private final boolean buildable;
    private Tile west;
    private Tile east;
    private Tile north;
    private Tile south;
    private Location location;
    private List<Attacker> attackers;

    /**
     * Create a tile object.
     *
     * @param location Coordinates for the tile.
     * @param type Type of the tile.
     * @param buildable Is the player allowed to build on the tile?
     */
    public Tile(Location location, Type type, boolean buildable) {
        this.location = location;
        this.type = type;
        this.buildable = buildable;
        this.tower = null;
        this.attackers = new ArrayList();
    }

    /**
     *
     * @param x x-coordinate for tile.
     * @param y y-coordinate for tile.
     * @param type Type of the tile.
     * @param buildable Is the player allowed to build on the tile?
     */
    public Tile(int x, int y, Type type, boolean buildable) {
        this(new Location(x, y), type, buildable);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Tile getWest() {
        return west;
    }

    public void setWest(Tile west) {
        this.west = west;
    }

    public Tile getEast() {
        return east;
    }

    public void setEast(Tile east) {
        this.east = east;
    }

    public Tile getNorth() {
        return north;
    }

    public void setNorth(Tile north) {
        this.north = north;
    }

    public Tile getSouth() {
        return south;
    }

    public void setSouth(Tile south) {
        this.south = south;
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

    /**
     * Attempt to add a tower on the tile.
     *
     * @param tower The tower to be added to the tile.
     * @return Was the tower added successfully?
     */
    public boolean addTower(Tower tower) {
        if (this.tower == null && this.buildable) {
            this.tower = tower;
            return true;
        } else {
            return false;
        }
    }

    public boolean addAttacker(Attacker attacker) {
        return this.attackers.add(attacker);
    }

    public boolean removeAttacker(Attacker attacker) {
        return this.attackers.remove(attacker);
    }

    public List<Attacker> getAttackers() {
        return attackers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.toString());
        sb.append(" ");
        sb.append(this.location);
        sb.append(" ");
        if (buildable) {
            sb.append("Buildable\n");
        } else {
            sb.append("Unbuildable\n");
        }

        if (this.tower != null) {
            sb.append(this.tower.toString());
        }
        if (!this.attackers.isEmpty()) {
            this.attackers.stream().forEach(x -> sb.append(x.toString()));
        }

        return sb.toString();
    }

//    @Override
//    public boolean equals(Object obj) {
//        Tile other = (Tile) obj;
//        return type == other.getType() && tower.equals(other.getTower()) 
//                && buildable == other.isBuildable();
//    }
    public boolean isSpawn() {
        return this.type == Type.Spawn;
    }

    public Tile nextRoad(Tile previous) {
        Tile next = null;
        List<Tile> options = new ArrayList();
        if (this.east != null) {
            options.add(this.east);
            //System.out.println("East: " + this.east.toString());
        }
        if (this.south != null) {
            options.add(this.south);
            //System.out.println("South: " + this.south.toString());
        }
        if (this.north != null) {
            options.add(this.north);
            //System.out.println("North: " + this.north.toString());
        }
        if (this.west != null) {
            options.add(this.west);
            //System.out.println("West: " + this.west.toString());
        }
        Collections.shuffle(options);
        while (next == null) {
            if (options.isEmpty()) {
                return null;
            }
            next = options.remove(0);

            if (!next.canMoveTo()) {
                next = null;
            } else if(next == previous) {
                next = null;
            } else {
                break;
            }
        }
        return next;
    }

    private boolean canMoveTo() {
        return this.type == Type.Road || this.type == Type.Spawn || this.type == Type.Base;
    }

    public boolean isBase() {
        return this.type == Type.Base;
    }
}
