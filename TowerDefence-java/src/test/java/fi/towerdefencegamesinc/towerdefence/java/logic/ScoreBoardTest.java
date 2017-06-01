/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author vrsaari
 */
public class ScoreBoardTest {

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
     * Test of getScores method, of class ScoreBoard.
     */
    @Test
    public void testGetScores_int() {
        System.out.println("getScores");
        int n = 0;
        ScoreBoard instance = new ScoreBoard();
        List<Score> result = instance.getScores(n);
        assertEquals(0, result.size());
    }

    /**
     * Test of getScores method, of class ScoreBoard.
     */
    @Test
    public void testGetScores() {
        System.out.println("getScores");
        ScoreBoard instance = new ScoreBoard();
        List<Score> result = instance.getScores();
        assertEquals(0, result.size());
    }

}
