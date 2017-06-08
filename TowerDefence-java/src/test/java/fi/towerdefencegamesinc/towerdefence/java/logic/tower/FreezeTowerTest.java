/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

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
public class FreezeTowerTest {
    private Tower freezeTower;
    
    public FreezeTowerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.freezeTower = new FreezeTower();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of shoot method, of class FreezeTower.
     */
    @Test
    public void testShoot() {
        assertEquals(1, this.freezeTower.shoot());
    }

    /**
     * Test of getCharRepr method, of class FreezeTower.
     */
    @Test
    public void testGetCharRepr() {
        assertTrue('F' == this.freezeTower.getCharRepr());
    }
    
}
