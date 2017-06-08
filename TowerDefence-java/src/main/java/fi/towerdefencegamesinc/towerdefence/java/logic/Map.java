/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The map is used to track objects in game and locations available for building.
 * @author vrsaari
 */
public class Map {

    Tile[][] tiles;
    ScoreBoard scoreBoard;

    /**
     * Create a new Map object with given width and height.
     * 
     * @param width The width of the map in tiles.
     * @param height The height of the map in tiles.
     */
    public Map(int width, int height) {
        this.tiles = new Tile[width][height];
        this.scoreBoard = new ScoreBoard();
    }

    /**
     * Create a new map object with fixed width and height.
     * 
     */
    public Map() {
        this(10, 10);
    }

    /**
     * Read information from a file to generate a map.
     * 
     * @param fileName File to read map information from.
     * @return Generated map object.
     */
    public static Map loadMapFromFile(String fileName) {
        char[][] tmpTiles;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            tmpTiles = stream.map(s -> s.toCharArray()).toArray(char[][]::new);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return null;
        }
        Map map = loadMapFromCharArray(tmpTiles);

        return map;
    }

    /**
     * Mostly a assisting method for generating a map from a file.
     * @param tmpTiles 2d char-array used to generate a map from.
     * @return Generated map object.
     */
    public static Map loadMapFromCharArray(char[][] tmpTiles) {
        int width = tmpTiles[0].length;
        int height = tmpTiles.length;
        Map map = new Map(width, height);
        IntStream.range(0, height).forEach(i -> {
            IntStream.range(0, width).forEach(j -> {
                switch (tmpTiles[i][j]) {
                    case ' ':
                        map.tiles[i][j] = new Tile(Type.Road, false);
                        break;
                    case '#':
                        map.tiles[i][j] = new Tile(Type.Unbuildable, false);
                        break;
                    case '@':
                        map.tiles[i][j] = new Tile(Type.Buildable, true);
                        break;
                    case 'X':
                        map.tiles[i][j] = new Tile(Type.Spawn, false);
                        break;
                    default:
                        break;
                }

            });
        });
        return map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stream.of(tiles).forEach(x -> {
            Stream.of(x).forEach(tile -> {
                Tile t = (Tile) tile;
                if (t.getTower() == null) {
                    if (null != t.getType()) {
                        switch (t.getType()) {
                            case Road:
                                sb.append(Type.Road.toString());
                                break;
                            case Unbuildable:
                                sb.append(Type.Unbuildable.toString());
                                break;
                            case Buildable:
                                sb.append(Type.Buildable.toString());
                                break;
                            case Spawn:
                                sb.append(Type.Spawn.toString());
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    sb.append(t.getTower().getCharRepr());
                }
            });
            sb.append("\n");
        });
        return sb.toString();
    }

    /**
     *
     * @return The scoreboard for the map.
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    /**
     *
     * @param x x-coordinate for a tile on the map.
     * @param y y-coordinate for a tile on the map.
     * @return The map tile corresponding to the x,y given.
     */
    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    /**
     *
     * @param x x-coordinate for a tile on the map.
     * @param y y-coordinate for a tile on the map.
     * @param t The tile object used to replace the tile in the given location.
     */
    public void setTile(int x, int y, Tile t) {
        this.tiles[x][y] = t;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

}
