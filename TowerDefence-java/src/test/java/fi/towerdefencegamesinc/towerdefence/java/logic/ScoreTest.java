/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import java.util.Calendar;
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
public class ScoreTest {

    private Score score;

    public ScoreTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.score = new Score("Pekka", 2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Score.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = score.toString();
        assertTrue(result.contains("Pekka") && result.contains("2"));
    }

    /**
     * Test of getDate method, of class Score.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Calendar expResult = Calendar.getInstance();
        expResult.setTime(new Date());
        Calendar result = Calendar.getInstance();
        result.setTime(score.getDate());

        assertEquals(expResult.get(Calendar.YEAR), result.get(Calendar.YEAR));
        assertEquals(expResult.get(Calendar.MONTH), result.get(Calendar.MONTH));
        assertEquals(expResult.get(Calendar.DAY_OF_MONTH), result.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Test of getPlayer method, of class Score.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        String expResult = "Pekka";
        String result = score.getPlayer().getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Score.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        long expResult = 2L;
        long result = score.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Score, when scores are equal.
     */
    @Test
    public void testCompareToEqual() {
        System.out.println("compareTo");
        Score other = new Score("Pekka", 2);
        int expResult = 0;
        int result = score.compareTo(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Score, when other score is better.
     */
    @Test
    public void testCompareToBetter() {
        System.out.println("compareTo");
        Score other = new Score("Pekka", 4);
        int result = score.compareTo(other);
        assertTrue(result < 0);
    }

    /**
     * Test of compareTo method, of class Score, when other score is lesser.
     */
    @Test
    public void testCompareToLesser() {
        System.out.println("compareTo");
        Score other = new Score("Pekka", 0);
        int result = score.compareTo(other);
        assertTrue(result > 0);
    }

}
