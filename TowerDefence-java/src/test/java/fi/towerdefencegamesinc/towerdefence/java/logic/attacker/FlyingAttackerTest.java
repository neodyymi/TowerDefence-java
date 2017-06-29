/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.Type;
import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.PoisonModifier;
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
public class FlyingAttackerTest {

    public FlyingAttackerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateFlyingAttacker() {
        Tile tile = new Tile(0, 0, Type.Spawn, false);
        Attacker flyingAttacker = new FlyingAttacker(tile, 1, 1);
        assertTrue(flyingAttacker.getSpeed() == 1);
        assertEquals(true, flyingAttacker.canFly());
        assertEquals(1, flyingAttacker.attack());
    }

    @Test
    public void testToString() {

        Tile tile = new Tile(0, 0, Type.Spawn, false);
        Attacker flyingAttacker = new FlyingAttacker(tile, 1, 1);
        flyingAttacker.addModifier(new PoisonModifier(100_000_000L, 1));
        String expResult = "Basic flying, speed: 1.0, damage: 1.0, health: 100/100, modifiers: 1666:40 Poison(1 hp/sec)";
        assertEquals(expResult, flyingAttacker.toString());
    }

}
