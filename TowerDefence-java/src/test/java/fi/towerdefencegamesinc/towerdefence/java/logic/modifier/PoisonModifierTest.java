/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.modifier;

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
public class PoisonModifierTest {
    
    public PoisonModifierTest() {
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
     * Test of toString method, of class PoisonModifier.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PoisonModifier instance = new PoisonModifier(1_000_000L, "TestName", 1);
        String expResult = "16:40 TestName(1 hp/sec)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
