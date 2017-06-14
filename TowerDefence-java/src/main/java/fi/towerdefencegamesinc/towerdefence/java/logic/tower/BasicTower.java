/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

import fi.towerdefencegamesinc.towerdefence.java.logic.Location;
import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A basic type of tower.
 *
 * @author vrsaari
 */
public class BasicTower implements Tower {

    private int level;
    private int power;
    private double speed;
    private long lastShot;
    private int[] upgradeCost;
    private Tile tile;
    private Attacker target;
    private double range;
    private double baseDamage;

    /**
     * Create a basic tower with given parameters.
     *
     * @param tile The tile the tower is located in.
     * @param power The power of the tower.
     * @param speed The firingspeed of the tower.
     * @param range
     * @param upgradeCost Array of costs to upgrade the tower.
     */
    public BasicTower(Tile tile, int power, int speed, double range, int[] upgradeCost) {
        this.power = power;
        this.speed = speed;
        this.level = 0;
        this.lastShot = 0;
        this.upgradeCost = upgradeCost;
        this.tile = tile;
        this.baseDamage = 5.0;
        this.target = null;
        this.range = range;
    }

    /**
     * Create a basic tower.
     *
     * @param tile The tile the tower is located in.
     */
    public BasicTower(Tile tile) {
        this(tile, 1, 1000, 3.0, new int[]{1, 2, 3, 4, 5});
    }
    
    public BasicTower() {
        this(null);
    }

    @Override
    public int shoot(Attacker target) {
        if(!this.readyToShoot()) {
            System.out.println(this.tile.getLocation().toString() + " tower is not ready.");
            return 0;
        }
        if(Location.getDistance(this.tile.getLocation(), target.getTile().getLocation()) > this.range) {
            System.out.println(this.tile.getLocation().toString() + " tower not in range of any attacker.");
            return 0;
        }
        this.target = target;
        double damage = this.baseDamage * this.power * this.level;
        this.target.takeDamage((int) damage);
        this.lastShot = new Date().getTime();
        System.out.println(this.tile.getLocation().toString() + " tower shot " 
                + target.toString() + " for " + damage + " damage.");
        return (int) damage;
    }

    @Override
    public int upgrade() {
        this.level++;
        return this.level;
    }

    @Override
    public int getUpgradeCost() {
        return this.upgradeCost[this.level];
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public boolean readyToShoot() {
        return new Date().getTime() - this.lastShot > this.speed;
    }

    @Override
    public char getCharRepr() {
        return 'B';
    }

    @Override
    public long getLastShot() {
        return lastShot;
    }

    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public double getRange() {
        return this.range;
    }

    @Override
    public Attacker getTarget(List<Attacker> attackers) {
        if(this.tile == null) {
            //System.out.println("TILE NULL");
            return null;
        }
        if(target != null && Location.getDistance(this.getTile().getLocation(),
                                this.target.getTile().getLocation()) <= this.range && !target.isDead()) {
            //System.out.println("CURRENT TARGET IS FINE");
            return this.target;
        }
        final Comparator<Attacker> comp = (a1, a2)
                -> Double.compare(
                        Location.getDistance(this.getTile().getLocation(),
                                a1.getTile().getLocation()),
                        Location.getDistance(this.getTile().getLocation(),
                                a2.getTile().getLocation()));
        Attacker closest = attackers.stream().min(comp).orElse(null);
        if(closest == null) return null;
        //System.out.println("Closest target: " + closest.toString());
        //System.out.println("Distance: " + Location.getDistance(
        //        this.getTile().getLocation(), closest.getTile().getLocation()
        //));
        if(Location.getDistance(this.getTile().getLocation(),
                                closest.getTile().getLocation()) <= this.range) {
            //System.out.println("Closest is close enough");
            return closest;
        } else {
            
            //System.out.println("Closest is too far. " + this.range + " vs " + Location.getDistance(this.getTile().getLocation(), closest.getTile().getLocation()));
        }
        return null;
    }
}
