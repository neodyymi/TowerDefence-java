/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.modifier;

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
public class BasicModifierTest {

    public BasicModifierTest() {
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

    /**
     * Test of getDurationLeft method, of class SlowModifier.
     */
    @Test
    public void testGetDurationLeftSlow() {
        System.out.println("getDurationLeftSlow");
        BasicModifier instance = new SlowModifier(1_000_000L, 1);
        Long expResult = 1_000_000L;
        Long result = instance.getDurationLeft();
        System.out.println(expResult + " - " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDurationLeft method, of class SlowModifier.
     */
    @Test
    public void testGetDurationLeftPoison() {
        System.out.println("getDurationLeftPoison");
        BasicModifier instance = new PoisonModifier(1_000_000L, 1);
        Long expResult = 1_000_000L;
        Long result = instance.getDurationLeft();
        System.out.println(expResult + " - " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartTime method, of class SlowModifier.
     */
    @Test
    public void testGetStartTimeSlow() {
        Date expResult = new Date();
        System.out.println("getStartTime");
        BasicModifier instance = new SlowModifier(1_000_000L, 1);
        Date result = instance.getStartTime();
        assertTrue(expResult.equals(result) || expResult.before(result));
    }

    /**
     * Test of getStartTime method, of class PoisonModifier.
     */
    @Test
    public void testGetStartTimePoison() {
        Date expResult = new Date();
        System.out.println("getStartTime");
        BasicModifier instance = new PoisonModifier(1_000_000L, 1);
        Date result = instance.getStartTime();
        assertTrue(expResult.equals(result) || expResult.before(result));
    }

    /**
     * Test of setStartTime method, of class SlowModifier.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        Date startTime = new Date();
        BasicModifier instance = new SlowModifier(1_000_000L, 1);
        instance.setStartTime(startTime);
        assertEquals(startTime, instance.getStartTime());

    }

    /**
     * Test of getDuration method, of class SlowModifier.
     */
    @Test
    public void testGetDurationSlow() {
        System.out.println("getDuration");
        BasicModifier instance = new SlowModifier(1_000_000L, 1);
        Long expResult = 1_000_000L;
        Long result = instance.getDuration();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDuration method, of class BasicModifier.
     */
    @Test
    public void testSetDuration() {
        System.out.println("setDuration");
        Long duration = 10_000_000L;
        BasicModifier instance = new SlowModifier(1_000_000L, 1);
        instance.setDuration(duration);
        Long result = instance.getDuration();
        Long expResult = 10_000_000L;
        assertEquals(expResult, result);

    }

    /**
     * Test of update method, of class BasicModifier.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Attacker attacker = new BasicAttacker(new Tile(0, 0, Type.Spawn, false));
        BasicModifier instance = new SlowModifier(1_000_000L, 1);
        instance.update(attacker);
        assertTrue(instance.getDuration() != null);
    }

    /**
     * Test of setName method, of class BasicModifier.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "NewName";
        BasicModifier instance = new SlowModifier(1_000_000L, "OldName", 1);
        assertEquals("OldName", instance.getName());
        instance.setName(name);
        assertEquals("NewName", instance.getName());
    }

    /**
     * Test of getStrength method, of class BasicModifier.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        BasicModifier instance = new SlowModifier(1_000_000L, "OldName", 1);
        int expResult = 1;
        int result = instance.getStrength();
        assertEquals(expResult, result);
        instance.setStrength(2);
        int expResultNow = 2;
        result = instance.getStrength();
        assertEquals(expResultNow, result);
    }

}
