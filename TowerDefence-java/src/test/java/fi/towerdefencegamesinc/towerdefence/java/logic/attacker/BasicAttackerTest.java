/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.Modifier;
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
        this.basicAttacker = new BasicAttacker(10, 1000);
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
        this.basicAttacker.addModifier(new Modifier(1000L));
        assertTrue(1000L == this.basicAttacker.getModifiers().get(0).getDuration());
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
