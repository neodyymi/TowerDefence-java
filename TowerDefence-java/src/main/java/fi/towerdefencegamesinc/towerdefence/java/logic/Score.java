/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import java.util.Date;

/**
 * A score object with information related to a single score.
 *
 * @author vrsaari
 */
public class Score implements Comparable {

    private final Long score;
    private final Date date;
    private final Player player;

    /**
     * Create a score object. It uses the current time as a timestamp for it.
     *
     * @param name Name of the player performing the score.
     * @param score The score to be recorded.
     */
    public Score(String name, long score) {
        this.player = new Player(name);
        this.score = score;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return String.format("%1$s10 (%3$te2.%3$tm2.%3$tY4): %2$s10", player.getName(), score, date);
    }

    public Date getDate() {
        return date;
    }

    public Player getPlayer() {
        return player;
    }

    public long getScore() {
        return score;
    }

    @Override
    public int compareTo(Object o) {
        Score other = (Score) o;
        return this.score.compareTo(other.score);
    }

}
