/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 *
 * @author vrsaari
 */
public class ScoreBoard {
    PriorityQueue<Score> scores;

    public ScoreBoard() {
        this.scores = new PriorityQueue<>(10, Collections.reverseOrder());
    }

    /**
     *
     * @param n
     * @return
     */
    public List<Score> getScores(int n) {
        return scores.stream().limit(n).collect(Collectors.toList());
    }
    
    /**
     *
     * @return
     */
    public List<Score> getScores() {
        return getScores(10);
    }
    
    
    
}
