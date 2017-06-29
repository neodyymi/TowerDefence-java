/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.BasicAttacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.BasicTower;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
            {'@', '@', '@'},};

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

    @Test
    public void testGetAllAttackersAndTowers() {
        GameMap testMap = GameMap.loadMapFromFile("testMap1", false);
        Tower tower = new BasicTower(testMap.getTile(2, 2));
        testMap.getTile(2, 2).addTower(tower);
        Tile spawn = testMap.getRandomSpawn();
        Attacker attacker = new BasicAttacker(spawn);
        spawn.addAttacker(attacker);
        assertEquals("Basic speed: 0.5, damage: 1.0, health: 100/100, modifiers: none", testMap.getAllAttackers().get(0).toString());
        assertEquals("Basic Tower\n"
                + "Level: 0\n"
                + "Damage: 5.0\n"
                + "Range: 3.0\n"
                + "Speed: 1000.0", testMap.getAllTowers().get(0).toString());
        assertEquals(1, testMap.getAllAttackers().size());
        assertEquals(1, testMap.getAllTowers().size());
    }

    @Test
    public void testToStringExtended() {
        GameMap testMap = GameMap.loadMapFromFile("testMap1", false);
        Tower tower = new BasicTower(testMap.getTile(2, 2));
        testMap.getTile(2, 2).addTower(tower);
        Tile spawn = testMap.getRandomSpawn();
        Attacker attacker = new BasicAttacker(spawn);
        spawn.addAttacker(attacker);
        assertEquals("###\n"
                + "1 $\n"
                + "@@B\n"
                + "\n"
                + "X (0, 1) Unbuildable\n"
                + "\n"
                + "Basic speed: 0.5, damage: 1.0, health: 100/100, modifiers: none\n", testMap.toString());
    }
    
    @Test
    public void testExternalMapFiles() {
        List<String> files = null;
        try {
            files = GameMap.externalMapFiles();
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals("[Ext: samplemap1.map]", files.toString());
    }
}
