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
     * Initial cost to build the tower type.
     */
    private static final int COST = 100;
    private static final int[] UPGRADE_COST = new int[]{100, 200, 300, 400, 500};

    private int buildCost;

    /**
     * Create a basic tower with given parameters.
     *
     * @param tile The tile the tower is located in.
     * @param power The power of the tower.
     * @param speed The firingspeed of the tower.
     * @param range The range of the tower.
     * @param upgradeCost Array of costs to upgrade the tower.
     * @param buildCost The cost to build the tower.
     */
    public BasicTower(Tile tile, int power, int speed, double range, int[] upgradeCost, int buildCost) {
        this.power = power;
        this.speed = speed;
        this.level = 0;
        this.lastShot = 0;
        this.upgradeCost = upgradeCost;
        this.tile = tile;
        this.baseDamage = 5.0;
        this.target = null;
        this.range = range;
        this.buildCost = buildCost;
    }

    /**
     * Create a basic tower with given parameters.
     *
     * @param tile The tile the tower is located in.
     * @param power The power of the tower.
     * @param speed The firingspeed of the tower.
     * @param range The range of the tower.
     * @param upgradeCost Array of costs to upgrade the tower.
     */
    public BasicTower(Tile tile, int power, int speed, double range, int[] upgradeCost) {
        this(tile, power, speed, range, upgradeCost, COST);
    }

    /**
     * Create a basic tower.
     *
     * @param tile The tile the tower is located in.
     */
    public BasicTower(Tile tile) {
        this(tile, 1, 1000, 3.0, UPGRADE_COST);
    }

    /**
     * Create a basic tower.
     */
    public BasicTower() {
        this(null);
    }

    @Override
    public int shoot(Attacker target) {
        if (!this.readyToShoot()) {
            return 0;
        }
        if (Location.getDistance(this.tile.getLocation(), target.getTile().getLocation()) > this.range) {
            return 0;
        }
        this.target = target;
        double damage = this.baseDamage * this.power * (this.level + 1);
        this.target.takeDamage((int) damage);
        this.lastShot = new Date().getTime();
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
        if (this.tile == null) {
            return null;
        }
        if (target != null && Location.getDistance(this.getTile().getLocation(),
                this.target.getTile().getLocation()) <= this.range && !target.isDead()) {
            return this.target;
        }
        final Comparator<Attacker> comp = (a1, a2)
                -> Double.compare(
                        Location.getDistance(this.getTile().getLocation(),
                                a1.getTile().getLocation()),
                        Location.getDistance(this.getTile().getLocation(),
                                a2.getTile().getLocation()));
        Attacker closest = attackers.stream().min(comp).orElse(null);
        if (closest == null) {
            return null;
        }

        if (Location.getDistance(this.getTile().getLocation(),
                closest.getTile().getLocation()) <= this.range) {
            return closest;
        }
        return null;
    }

    @Override
    public int getWorth() {
        int worth = this.buildCost;
        for (int i = 0; i < this.level; i++) {
            worth += this.upgradeCost[i];
        }
        return worth;
    }

    @Override
    public int getBuildCost() {
        return this.buildCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Basic Tower").append("\nLevel: ").append(this.level)
                .append("\nDamage: ").append(this.baseDamage * this.power * (this.level + 1))
                .append("\nRange: ").append(this.range)
                .append("\nSpeed: ").append(this.speed);
        return sb.toString();
    }
}
