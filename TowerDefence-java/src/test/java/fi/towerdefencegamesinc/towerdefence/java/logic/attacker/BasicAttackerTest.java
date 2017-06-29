/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.GameMap;
import fi.towerdefencegamesinc.towerdefence.java.logic.Location;
import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.Type;
import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.BasicModifier;
import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.PoisonModifier;
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
    private GameMap testMap;
    
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
        testMap = GameMap.loadMapFromFile("testMap1", false);
        this.tile = testMap.getRandomSpawn();
//        this.tile = new Tile(0, 0, Type.Spawn, false);
        this.basicAttacker = new BasicAttacker(this.tile, 1, 1000);
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of move method, of class BasicAttacker.
     */
    @Test
    public void testMove() {
        Location start = this.basicAttacker.getTile().getLocation();
        String expStart = "(0, 1)";
        this.basicAttacker.move();
        Location end = this.basicAttacker.getTile().getLocation();
        String expEnd = "(1, 1)";
        assertEquals(expStart, start.toString());
        assertEquals(expEnd, end.toString());
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
        assertTrue(this.basicAttacker.getSpeed() == 1);
    }
    
    @Test
    public void testGetHealthPct() {
        assertTrue(this.basicAttacker.getHealthPct() == 1);
    }
    
    @Test
    public void testLoot() {
        assertEquals(0, this.basicAttacker.loot());
    }
    
    @Test
    public void testTakeDamage() {
        this.basicAttacker.takeDamage(99);
        assertEquals(1, this.basicAttacker.getHealth());
        this.basicAttacker.takeDamage(1);
        assertEquals(100, this.basicAttacker.loot());
    }
    
    @Test
    public void testToString() {
        this.basicAttacker.addModifier(new PoisonModifier(100_000_000L, 1));
        String expResult = "Basic speed: 1.0, damage: 1000.0, health: 100/100, modifiers: 1666:40 Poison(1 hp/sec)";
        assertEquals(expResult, this.basicAttacker.toString());
    }
    
}
