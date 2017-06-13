/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.Type;
import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.Modifier;
import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.SlowModifier;
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
public class BasicAttackerTest {
    
    private Attacker basicAttacker;
    private Tile tile;
    
    public BasicAttackerTest() {
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
        this.basicAttacker = new BasicAttacker(this.tile, 10, 1000);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of move method, of class BasicAttacker.
     */
    @Test
    public void testMove() {
        assertTrue(true);
    }

    /**
     * Test of attack method, of class BasicAttacker.
     */
    @Test
    public void testAttack() {
        assertEquals(1000, this.basicAttacker.attack());
    }

    /**
     * Test of canFly method, of class BasicAttacker.
     */
    @Test
    public void testCanFly() {
        assertEquals(false, this.basicAttacker.canFly());
    }

    /**
     * Test of addModifier method, of class BasicAttacker.
     */
    @Test
    public void testAddModifier() {
        this.basicAttacker.addModifier(new SlowModifier(1000L, "Slowness", 50));
        assertTrue(1000L == this.basicAttacker.getModifiers().get(0).getDuration());
        assertEquals("Slowness", this.basicAttacker.getModifiers().get(0).getName());
    }

    /**
     * Test of updateModifiers method, of class BasicAttacker.
     */
    @Test
    public void testUpdateModifiers() {
        assertTrue(true); // TODO
    }

    /**
     * Test of getSpeed method, of class BasicAttacker.
     */
    @Test
    public void testGetSpeed() {
        assertEquals(10, this.basicAttacker.getSpeed());
    }
    
}
