/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

/**
 *
 * @author vrsaari
 */
public class Map {
    Tile[][] tiles;
    ScoreBoard scoreBoard;

    public Map(int width, int height) {
        this.tiles = new Tile[width][height];
        this.scoreBoard = new ScoreBoard();
    }

    public Map() {
        this(10, 10);
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
    
    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }
    
    
}
