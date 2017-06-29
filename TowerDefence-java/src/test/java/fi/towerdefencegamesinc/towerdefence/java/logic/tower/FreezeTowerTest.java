/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.tower;

import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.Type;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.BasicAttacker;
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
    private Tile tile;
    private BasicAttacker attacker;
    
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
        this.tile = new Tile(0, 0, Type.Spawn, false);
        this.attacker = new BasicAttacker(this.tile, 1, 1);
        this.freezeTower = new FreezeTower(this.tile);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of shoot method, of class FreezeTower.
     */
    @Test
    public void testShoot() {
        assertEquals(5, this.freezeTower.shoot(this.attacker));
    }

    /**
     * Test of getCharRepr method, of class FreezeTower.
     */
    @Test
    public void testGetCharRepr() {
        assertTrue('F' == this.freezeTower.getCharRepr());
    }
    
}
