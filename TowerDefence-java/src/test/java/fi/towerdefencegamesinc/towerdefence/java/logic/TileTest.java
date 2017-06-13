/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import fi.towerdefencegamesinc.towerdefence.java.logic.tower.BasicTower;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
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
public class TileTest {

    private Tile buildable;
    private Tile unbuildable;

    public TileTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        buildable = new Tile(1, 1, Type.Buildable, true);
        unbuildable = new Tile(0, 0, Type.Unbuildable, false);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTower method, of class Tile.
     */
    @Test
    public void testGetTower() {
        System.out.println("getTower");
        Tower expResult = null;
        Tower result = buildable.getTower();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Tile.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Type expResult = Type.Buildable;
        Type result = buildable.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of isBuildable method, of class Tile.
     */
    @Test
    public void buildableTileIsBuildable() {
        System.out.println("isBuildable");
        boolean expResult = true;
        boolean result = buildable.isBuildable();
        assertEquals(expResult, result);
    }

    @Test
    public void unbuildableTileIsUnbuildable() {
        System.out.println("isUnbuildable");
        boolean expResult = false;
        boolean result = unbuildable.isBuildable();
        assertEquals(expResult, result);
    }

    @Test
    public void addTowerToTile() {
        Tower t = new BasicTower();
        buildable.addTower(t);
        assertEquals(buildable.getTower().getCharRepr(), 'B');
    }

}
