/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java;

import fi.towerdefencegamesinc.towerdefence.java.logic.Difficulty;
import fi.towerdefencegamesinc.towerdefence.java.logic.GameMap;
import fi.towerdefencegamesinc.towerdefence.java.logic.Player;
import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.BasicAttacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This is the main class that generates the game environment.
 *
 * @author vrsaari
 */
public class Game {

    private final GameMap map;
    private final Player player;
    private int score;

    private int[] wavesOfAttackers;
    private int nextWave;
    private int spawnedCurrentWave;
    private boolean currentWaveFinished;
    private boolean gameOver;
    private boolean gameWon;

    /**
     * Creates a Game object.
     *
     * @param mapFile Name of the map file to be used.
     * @param playerName Name of the player.
     * @param difficulty Difficulty for the game.
     * @param external If the map to be used is an external map.
     */
    public Game(String mapFile, String playerName, Difficulty difficulty, boolean external) {
        int startCurrency = 1000 - difficulty.getDifficulty() * 250;
        this.map = GameMap.loadMapFromFile(mapFile, external);
        this.player = new Player(playerName, startCurrency);
        this.score = 0;
        this.nextWave = 0;
        this.spawnedCurrentWave = 0;
        this.wavesOfAttackers = new int[]{5};
    }

    /**
     * Creates a Game object.
     *
     * @param mapFile Name of the map file to be used.
     * @param playerName Name of the player.
     * @param difficulty Difficulty for the game.
     */
    public Game(String mapFile, String playerName, Difficulty difficulty) {
        this(mapFile, playerName, difficulty, false);
    }

    /**
     * Getter for score.
     *
     * @return Returns current score for the game.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for score.
     *
     * @param score Integer to be set as score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter for player.
     *
     * @return Returns the player playing the game.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter for map.
     *
     * @return Returns current map.
     */
    public GameMap getMap() {
        return map;
    }

    /**
     * This is a game updating method. It makes the game tick forwards one tick.
     */
    public void update() {
        if (this.currentWaveFinished && this.getNextWave() == -1) {
            this.gameWon = true;
        }
        if (player.gameOver()) {
            this.gameOver = true;
            if (player.getHealth() <= 0) {
                this.gameWon = false;
            }
            System.out.println("------------\n\n\n\n\n\nGAME OVER\n\n\n\n\n\n------------");
            return;
        }

        List<Attacker> attackers = map.getAllAttackers();
        List<Attacker> attackersInBase = attackers.stream()
                .filter(x -> x.getTile().isBase()).collect(Collectors.toList());
        int damageToBase = attackersInBase.stream()
                .mapToInt(Attacker::attack).sum();
        if (damageToBase > 0) {
            this.player.takeDamage(damageToBase);
        }
        attackers.removeAll(attackersInBase);
        System.out.println("Attackers: " + attackers.size());
        attackers.stream().forEach(a -> a.move());
        List<Tower> towers = map.getAllTowers();
        System.out.println("Towers: " + towers.size());
        towers.stream().forEach(t -> {
            Tower tower = (Tower) t;
            System.out.println("Checking tower range: " + tower.getCharRepr() + " " + tower.toString());
            Attacker target = tower.getTarget(attackers);
            if (target != null) {
                System.out.println("shooting at target");
                tower.shoot(target);
                if (target.isDead()) {
                    this.player.loot(target);
                }
            } else {
                System.out.println("Target is null");
            }
        });
        if (new Random().nextInt(3) == 0) {
            if (!this.spawnAttacker() && this.getMap().getAllAttackers().isEmpty()) {
                this.currentWaveFinished = true;
            }
        }
    }

    /**
     * Attempts to spawn an attacker at a random spawn.
     *
     * @return True, if an attacker was spawned.
     */
    public boolean spawnAttacker() {
        if (this.spawnedCurrentWave >= this.wavesOfAttackers[this.nextWave - 1]) {
            return false;
        }
        this.spawnedCurrentWave++;
        Tile spawn = this.map.getRandomSpawn();
        System.out.println("Spawnpoint : " + spawn.toString());
        Attacker attacker = new BasicAttacker(spawn);
        spawn.addAttacker(attacker);
        return true;
    }

    /**
     * Getter for nextWave.
     *
     * @return Returns number of next wave.
     */
    public int getNextWave() {
        if (this.nextWave >= this.wavesOfAttackers.length) {
            return -1;
        }
        return this.nextWave;
    }

    /**
     * Begins a new wave.
     *
     * @return Can next wave be started?
     */
    public void startNextWave() {
        this.nextWave++;
        this.currentWaveFinished = false;
        this.spawnedCurrentWave = 0;
    }

    /**
     * Is current wave finished?
     *
     * @return True if current wave is finished.
     */
    public boolean currentWaveFinished() {
        return this.currentWaveFinished;
    }

    /**
     * Is the game over?
     *
     * @return True if game is over.
     */
    public boolean gameOver() {
        return this.gameOver;
    }

    /**
     * Has the player won?
     *
     * @return True if player has beat the game.
     */
    public boolean gameWon() {
        return this.gameWon;
    }

}
