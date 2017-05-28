/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author vrsaari
 */
public class MapTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getScoreBoard method, of class Map.
     */
    @Test
    public void testGetScoreBoard() {
        System.out.println("getScoreBoard");
        Map instance = new Map();
        ScoreBoard expResult = new ScoreBoard();
        ScoreBoard result = instance.getScoreBoard();
        assertEquals(expResult.getClass(), result.getClass());
    }

    /**
     * Test of getTile method, of class Map.
     */
    @Test
    public void testGetTile() {
        System.out.println("getTile");
        int x = 0;
        int y = 0;
        Map instance = new Map();
        Tile expResult = null;
        Tile result = instance.getTile(x, y);
        assertEquals(expResult, result);
    }
    
}
