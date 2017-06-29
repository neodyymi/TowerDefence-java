/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author vrsaari
 */
public class DifficultyTest {

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
    
    @Test
    public void easyAvailable() {
        Difficulty dif = Difficulty.EASY;
        assertEquals(0, dif.getDifficulty());
        assertEquals("Easy", dif.getName());
    }
    
    @Test
    public void normalAvailable() {
        Difficulty dif = Difficulty.NORMAL;
        assertEquals(1, dif.getDifficulty());
        assertEquals("Normal", dif.getName());
    }
    
    @Test
    public void hardAvailable() {
        Difficulty dif = Difficulty.HARD;
        assertEquals(2, dif.getDifficulty());
        assertEquals("Hard", dif.getName());
        assertEquals("Hard", dif.toString());
    }
}
