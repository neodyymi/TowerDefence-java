/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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

    private static final String MAPS_PATH = "maps/";
    public static final List<String> DEFAULT_MAPS = Arrays.asList("samplemap1(15x17).map", "samplemap2(11x13).map");
    public static final String EXTERNAL_MAP_PREFIX = "Ext: ";

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
     * @param external Is the map file an external file?
     * @return Generated map object.
     */
    public static GameMap loadMapFromFile(String fileName, boolean external) {
        char[][] tmpTiles;
        if (external) {
            try (Stream<String> stream = Files.lines(Paths.get(MAPS_PATH + fileName))) {
                tmpTiles = stream.map(s -> s.toCharArray()).toArray(char[][]::new);
            } catch (IOException e) {
                e.printStackTrace(System.out);
                return null;
            }
        } else {
            try (InputStream is = GameMap.class.getClassLoader().getResourceAsStream(MAPS_PATH + fileName);
                    final Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
                    final BufferedReader br = new BufferedReader(r);
                    final Stream<String> stream = br.lines()) {
                tmpTiles = stream.map(s -> s.toCharArray()).toArray(char[][]::new);
                is.close();
            } catch (IOException e) {
                e.printStackTrace(System.out);
                return null;
            }
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
     * Getter for scoreBoard.
     *
     * @return The scoreboard for the map.
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    /**
     * Getter for getting tile from certain coordinates.
     *
     * @param x x-coordinate for a tile on the map.
     * @param y y-coordinate for a tile on the map.
     * @return The map tile corresponding to the x,y given.
     */
    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    /**
     * Setter for setting a tile into certain coordinates.
     *
     * @param x x-coordinate for a tile on the map.
     * @param y y-coordinate for a tile on the map.
     * @param t The tile object used to replace the tile in the given location.
     */
    public void setTile(int x, int y, Tile t) {
        this.tiles[x][y] = t;
    }

    /**
     * Setter for tiles.
     *
     * @param tiles Array of tiles to replace current tiles.
     */
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * Getter for tiles.
     *
     * @return 2d array of tiles.
     */
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

    /**
     * List all attackers in map.
     *
     * @return List of attackers.
     */
    public List<Attacker> getAllAttackers() {
        List<Attacker> attackers = new ArrayList();
        Arrays.stream(this.tiles).forEach(row -> Arrays.stream(row).forEach(t -> {
            attackers.addAll(t.getAttackers());
        }));
        return attackers;
    }

    /**
     * List all towers in map.
     *
     * @return List of towers.
     */
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

    /**
     * Remove multiple attackers.
     *
     * @param attackers List of attackers to be removed.
     */
    public void removeAttackers(List<Attacker> attackers) {
        attackers.stream().forEach(a -> {
            a.getTile().removeAttacker(a);
        });
    }

    /**
     * Add a tile into the list of spawnlocations.
     *
     * @param tile Tile to be added.
     */
    public void addSpawn(Tile tile) {
        this.spawns.add(tile);
    }

    /**
     * Find out the width of the map.
     *
     * @return Width of the map.
     */
    public int getWidth() {
        return this.tiles[0].length;
    }

    /**
     * Find out the height of the map.
     *
     * @return Height of the map.
     */
    public int getHeight() {
        return this.tiles.length;
    }

    /**
     * What external map files are available in the /maps/ folder.
     *
     * @return List of external maps available.
     * @throws IOException
     */
    public static List<String> externalMapFiles() throws IOException {
        if (!Files.isDirectory(Paths.get(MAPS_PATH))) {
            return new ArrayList<>();
        }
        File dir = new File(MAPS_PATH);
        if (!dir.exists() || !dir.isDirectory()) {
            return null;
        }
        ArrayList<String> files = new ArrayList(Arrays.asList(dir.list((d, name) -> name.endsWith(".map"))));

        return files.stream().map(m -> EXTERNAL_MAP_PREFIX + m).collect(Collectors.toList());
    }
}
