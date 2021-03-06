/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.Type;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.BasicAttacker;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vrsaari
 */
public class BasicTowerTest {

    private Tower basicTower;
    private Tile tile;
    private Tile other;
    private BasicAttacker attacker;
    private BasicAttacker farAttacker;

    public BasicTowerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.tile = new Tile(0, 0, Type.Spawn, false);
        this.other = new Tile(100, 100, Type.Road, false);
        this.attacker = new BasicAttacker(this.tile, 1, 1);
        this.farAttacker = new BasicAttacker(this.other, 1, 1);
        this.basicTower = new BasicTower(this.tile, 1, 10000, 3, new int[]{1, 2, 3, 4, 5});
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of shoot method, of class BasicTower.
     */
    @Test
    public void testShoot() {
        assertEquals(5, this.basicTower.shoot(this.attacker));
        assertEquals(0, this.basicTower.shoot(farAttacker));
    }

    /**
     * Test of upgrade method, of class BasicTower.
     */
    @Test
    public void testUpgrade() {
        int start = this.basicTower.getLevel();
        int newLevel = this.basicTower.upgrade();
        assertEquals(start + 1, this.basicTower.getLevel());
        assertEquals(start + 1, newLevel);
    }

    /**
     * Test of getUpgradeCost method, of class BasicTower.
     */
    @Test
    public void testGetUpgradeCost() {
        int start = this.basicTower.getLevel();
        int cost = this.basicTower.getUpgradeCost();
        assertEquals(1, cost);
        int newLevel = this.basicTower.upgrade();
        cost = this.basicTower.getUpgradeCost();
        assertEquals(2, cost);
    }

    /**
     * Test of getLevel method, of class BasicTower.
     */
    @Test
    public void testGetLevel() {
        int start = this.basicTower.getLevel();
        assertEquals(0, this.basicTower.getLevel());
        int newLevel = this.basicTower.upgrade();
        assertEquals(1, this.basicTower.getLevel());
        assertEquals(1, newLevel);
    }

    /**
     * Test of readyToShoot method, of class BasicTower.
     */
    @Test
    public void testReadyToShoot() {
        assertTrue(this.basicTower.readyToShoot());
        this.basicTower.shoot(this.attacker);
        System.out.println("Last shot: " + this.basicTower.getLastShot());
        System.out.println("Current time in ms: " + new Date().getTime());
        assertFalse(this.basicTower.readyToShoot());
    }

    /**
     * Test of getCharRepr method, of class BasicTower.
     */
    @Test
    public void testGetCharRepr() {
        assertTrue('B' == this.basicTower.getCharRepr());
    }

    @Test
    public void testGetLastShot() {
        Long before = this.basicTower.getLastShot();
        this.basicTower.shoot(attacker);
        Long after = this.basicTower.getLastShot();
        assertNotEquals(before, after);
    }

    @Test
    public void testGetTile() {
        String expResult = "X (0, 0) Unbuildable\n";
        String towerTileString = this.basicTower.getTile().toString();
        assertEquals(expResult, towerTileString);
    }

    @Test
    public void testToString() {
        String expResult = "Basic Tower\n"
                + "Level: 0\n"
                + "Damage: 5.0\n"
                + "Range: 3.0\n"
                + "Speed: 10000.0";
        String towerString = this.basicTower.toString();
        assertEquals(expResult, towerString);
    }

    @Test
    public void testWorth() {
        this.basicTower.upgrade();
        int worth = this.basicTower.getWorth();
        int expResult = 101;
        assertEquals(expResult, worth);
    }

    @Test
    public void testGetTargetNear() {
        List<Attacker> l = new ArrayList();
        l.add(attacker);
        l.add(farAttacker);
        Attacker nearest = this.basicTower.getTarget(l);
        assertEquals(attacker, nearest);
    }

    @Test
    public void testGetTargetFar() {
        List<Attacker> l = new ArrayList();
        l.add(farAttacker);
        Attacker nearest = this.basicTower.getTarget(l);
        assertNull(nearest);
    }

}
