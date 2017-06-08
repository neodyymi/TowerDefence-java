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
 * Scoreboards keep a number of scores together. It's related to a map and that
 * is the common relation between the scores.
 *
 * @author vrsaari
 */
public class ScoreBoard {

    PriorityQueue<Score> scores;

    /**
     * Creates an empty ScoreBoard-objects to store a number of scores together.
     * It keeps them in order by scores with a heap-style datastructure.
     */
    public ScoreBoard() {
        this.scores = new PriorityQueue<>(10, Collections.reverseOrder());
    }

    /**
     * Retrieve a number of scores from the scoreboard. Ordered by score.
     *
     * @param n Number of scores to be returned.
     * @return A list of scores.
     */
    public List<Score> getScores(int n) {
        return scores.stream().limit(n).collect(Collectors.toList());
    }

    /**
     * Retrieve the top 10 scores from the scoreboard.
     *
     * @return A list of scores.
     */
    public List<Score> getScores() {
        return getScores(10);
    }

}
