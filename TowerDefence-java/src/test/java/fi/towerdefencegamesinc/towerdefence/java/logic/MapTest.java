/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
        GameMap instance = new GameMap();
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
        GameMap instance = new GameMap();
        Tile expResult = null;
        Tile result = instance.getTile(x, y);
        assertEquals(expResult, result);
    }

    /**
     * Test of reading a file into a map
     */
    @Test
    public void testReadExternalMapFromFile() {
        char[][] tmpTiles = {
            {'X', ' ', ' ', '#'},
            {'#', '@', ' ', '@'},
            {'#', '@', ' ', '@'},
            {'#', '@', ' ', '$'},
            {'#', '#', '#', '#'}
        };

        File tmpF;
        StringBuilder sb = new StringBuilder();
        Stream.of(tmpTiles).forEach(chars -> {
            sb.append(new String(chars));
            sb.append("\n");
        });
        sb.append("\n");

        
        GameMap testMap = GameMap.loadMapFromFile("testMap", true);
        assertEquals(testMap.toString(), sb.toString());
    }

    @Test
    public void testReadMapFromFile() {
        char[][] tmpTiles = {
            {'#', '#', '#'},
            {'X', ' ', '$'},
            {'@', '@', '@'},
        };

        File tmpF;
        StringBuilder sb = new StringBuilder();
        Stream.of(tmpTiles).forEach(chars -> {
            sb.append(new String(chars));
            sb.append("\n");
        });
        sb.append("\n");

        
        GameMap testMap = GameMap.loadMapFromFile("testMap1", false);
        assertEquals(testMap.toString(), sb.toString());
    }

}
