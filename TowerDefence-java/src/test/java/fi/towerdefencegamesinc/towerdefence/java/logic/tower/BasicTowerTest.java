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
import java.util.Date;
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
    private BasicAttacker attacker;
    
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
        this.attacker = new BasicAttacker(this.tile, 1, 1);
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
        assertEquals(1, this.basicTower.shoot(this.attacker));
    }

    /**
     * Test of upgrade method, of class BasicTower.
     */
    @Test
    public void testUpgrade() {
        int start = this.basicTower.getLevel();
        int newLevel = this.basicTower.upgrade();
        assertEquals(start+1, this.basicTower.getLevel());
        assertEquals(start+1, newLevel);
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
    
}
