/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

/**
 * Enumerator for dealing with game difficulty. It holds some settings 
 * for the difficulty level
 * @author vrsaari
 */
public enum Difficulty {
    EASY("Easy", 0),
    NORMAL("Normal", 1),
    HARD("Hard", 2);

    private final String name;
    private final int difficulty;

    private Difficulty(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
