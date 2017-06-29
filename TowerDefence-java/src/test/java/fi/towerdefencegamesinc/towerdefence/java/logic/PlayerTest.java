/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.BasicAttacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.BasicTower;
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
public class PlayerTest {

    private GameMap testMap;
    private Tile tile;
    private BasicAttacker basicAttacker;
    private BasicTower tower;

    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.testMap = GameMap.loadMapFromFile("testMap1", false);
        this.tile = testMap.getRandomSpawn();
        Tile towerTile = this.testMap.getTile(2, 2);
        this.basicAttacker = new BasicAttacker(this.tile, 1, 1000);
        this.tower = new BasicTower(towerTile);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void playerIsCreated() {
        Player player = new Player("Uolevi", 1000);
        assertEquals("Uolevi", player.getName());
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance = new Player("Uolevi", 1000);
        String expResult = "Uolevi";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Test";
        Player instance = new Player("Pertti", 1000);
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    @Test
    public void testSetCurrency() {
        Player player = new Player("Uolevi", 1000);
        int before = player.getCurrency();
        player.setCurrency(1);
        int after = player.getCurrency();
        assertEquals(1, after);
        assertEquals(1000, before);
    }

    @Test
    public void testTakeDamage() {
        Player player = new Player("Uolevi", 1000);
        int before = player.getHealth();
        assertEquals(100, before);
        player.takeDamage(99);
        assertEquals(1, player.getHealth());
        player.takeDamage(1);
        assertTrue(player.gameOver());
        assertEquals(100, player.getBaseHealth());
    }

    @Test
    public void testToString() {
        Player player = new Player("Uolevi", 1000);
        System.out.println(player.toString());
        assertEquals("Name: Uolevi\n"
                + "Currency: 1000\n"
                + "Base: 100/100", player.toString());
    }

    @Test
    public void testBuyUpgradeAndSell() {
        Player player = new Player("Uolevi", 1000);
        assertTrue(player.buy(this.tower));
        assertEquals(900, player.getCurrency());
        assertTrue(player.upgradeTower(this.tower));
        assertEquals(800, player.getCurrency());
        assertTrue(player.sell(this.tower));
        assertEquals(1000, player.getCurrency());
    }

    @Test
    public void testBuyUpgradeAndSellFail() {
        Player player = new Player("Uolevi", 1);
        assertFalse(player.buy(this.tower));
        assertEquals(1, player.getCurrency());
        assertFalse(player.upgradeTower(this.tower));
        assertEquals(1, player.getCurrency());
    }
    
    @Test
    public void testLootAttacker() {
        Player player = new Player("Uolevi", 0);
        this.basicAttacker.takeDamage(1000);
        player.loot(basicAttacker);
        assertEquals(100, player.getCurrency());
    }
}
