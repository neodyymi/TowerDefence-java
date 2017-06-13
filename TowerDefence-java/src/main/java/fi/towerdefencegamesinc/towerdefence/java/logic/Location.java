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
public class Location {

    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param x1 x-coordinate for first tile.
     * @param y1 y-coordinate for first tile.
     * @param x2 x-coordinate for second tile.
     * @param y2 y-coordinate for second tile.
     * @return distance between coordinates.
     */
    public static double getDistance(int x1, int y1, int x2, int y2) {
        int deltaX = Math.abs(x1 - x2);
        int deltaY = Math.abs(y1 - y2);
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    /**
     *
     * @param location First location.
     * @param other Other location.
     * @return distance between locations.
     */
    public static double getDistance(Location location, Location other) {
        return Location.getDistance(location.x, location.y, other.x, other.y);
    }

    @Override
    public String toString() {
        return ("(" + this.x + ", " + this.y + ")");
    }
    
    

}
