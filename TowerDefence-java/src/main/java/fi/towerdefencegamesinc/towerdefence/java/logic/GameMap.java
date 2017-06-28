/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * The map is used to track objects in game and locations available for
 * building.
 *
 * @author vrsaari
 */
public class GameMap {

    private Tile[][] tiles;
    private ScoreBoard scoreBoard;
    private List<Tile> spawns;
    
    private static final String MAPS_PATH = "./maps/";

    /**
     * Create a new Map object with given width and height.
     *
     * @param width The width of the map in tiles.
     * @param height The height of the map in tiles.
     */
    public GameMap(int height, int width) {
        this.tiles = new Tile[height][width];
        this.spawns = new ArrayList();
        this.scoreBoard = new ScoreBoard();
    }

    /**
     * Create a new map object with fixed width and height.
     *
     */
    public GameMap() {
        this(10, 10);
    }

    /**
     * Read information from a file to generate a map.
     *
     * @param fileName File to read map information from.
     * @return Generated map object.
     */
    public static GameMap loadMapFromFile(String fileName) {
        char[][] tmpTiles;
        try (Stream<String> stream = Files.lines(Paths.get(MAPS_PATH + fileName))) {
            tmpTiles = stream.map(s -> s.toCharArray()).toArray(char[][]::new);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            return null;
        }
        GameMap map = loadMapFromCharArray(tmpTiles);

        return map;
    }

    /**
     * Mostly a assisting method for generating a map from a file.
     *
     * @param tmpTiles 2d char-array used to generate a map from.
     * @return Generated map object.
     */
    public static GameMap loadMapFromCharArray(char[][] tmpTiles) {
        int width = tmpTiles[0].length;
        int height = tmpTiles.length;
        GameMap map = new GameMap(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (tmpTiles[i][j]) {
                    case ' ':
                        map.tiles[i][j] = new Tile(j, i, Type.Road, false);
                        break;
                    case '#':
                        map.tiles[i][j] = new Tile(j, i, Type.Unbuildable, false);
                        break;
                    case '@':
                        map.tiles[i][j] = new Tile(j, i, Type.Buildable, true);
                        break;
                    case 'X':
                        map.tiles[i][j] = new Tile(j, i, Type.Spawn, false);
                        map.addSpawn(map.tiles[i][j]);
                        break;
                    case '$':
                        map.tiles[i][j] = new Tile(j, i, Type.Base, false);
                        break;
                    default:
                        break;
                }

            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i > 0) {
                    map.tiles[i][j].setNorth(map.tiles[i - 1][j]);
                }
                if (i < map.tiles.length - 1) {
                    map.tiles[i][j].setSouth(map.tiles[i + 1][j]);
                }
                if (j < map.tiles[i].length - 1) {
                    map.tiles[i][j].setEast(map.tiles[i][j + 1]);
                }
                if (j > 0) {
                    map.tiles[i][j].setWest(map.tiles[i][j - 1]);
                }
            }
        }
        return map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder attackerTiles = new StringBuilder();
        Stream.of(tiles).forEach(x -> {
            Stream.of(x).forEach(tile -> {
                Tile t = (Tile) tile;
                if (t.getTower() == null && t.getAttackers().isEmpty()) {
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
                            case Base:
                                sb.append(Type.Base.toString());
                            default:
                                break;
                        }
                    }
                } else if (t.getTower() != null) {
                    sb.append(t.getTower().getCharRepr());
                } else {
                    int count = t.getAttackers().size();
                    if (count < 10) {
                        sb.append(count);
                    } else {
                        sb.append("*");
                    }
                    attackerTiles.append(t.toString()).append("\n");
                }
            });
            sb.append("\n");
        });
        sb.append("\n").append(attackerTiles);
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

    /**
     * Get random spawn point.
     *
     * @return a random spawn point.
     */
    public Tile getRandomSpawn() {
        System.out.println("Spawns: " + this.spawns.size());
        return this.spawns.get(new Random().nextInt(this.spawns.size()));
    }

    public List<Attacker> getAllAttackers() {
        List<Attacker> attackers = new ArrayList();
        Arrays.stream(this.tiles).forEach(row -> Arrays.stream(row).forEach(t -> {
            attackers.addAll(t.getAttackers());
        }));
        return attackers;
    }

    public List<Tower> getAllTowers() {
        List<Tower> towers = new ArrayList();
        Arrays.stream(this.tiles).forEach(row -> Arrays.stream(row).forEach(t -> {
            Tower tower = t.getTower();
            if (tower != null) {
                towers.add(t.getTower());
            }
        }));
        return towers;
    }

    public void removeAttackers(List<Attacker> attackers) {
        attackers.stream().forEach(a -> {
            a.getTile().removeAttacker(a);
        });
    }

    public void addSpawn(Tile tile) {
        this.spawns.add(tile);
    }

    public int getWidth() {
        return this.tiles[0].length;
    }

    public int getHeight() {
        return this.tiles.length;
    }

    public static ArrayList<String> mapFiles() throws IOException {
        File dir = new File(MAPS_PATH);
        ArrayList<String> files = new ArrayList(Arrays.asList(dir.list((d, name) -> name.endsWith(".map"))));

        return files;
    }
}
