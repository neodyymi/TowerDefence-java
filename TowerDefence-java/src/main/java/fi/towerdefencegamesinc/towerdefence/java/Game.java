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

    public Game(String mapFile, String playerName, Difficulty difficulty) {
        int startCurrency = 1000 - difficulty.getDifficulty() * 250;
        this.map = GameMap.loadMapFromFile(mapFile);
        this.player = new Player(playerName, startCurrency);
        this.score = 0;
        this.nextWave = 0;
        this.spawnedCurrentWave = 0;
        this.wavesOfAttackers = new int[]{5,10,15,20,25};
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public GameMap getMap() {
        return map;
    }

    public void update() {
        if (player.gameOver()) {
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
            } else {
                System.out.println("Target is null");
            }
        });
        if (new Random().nextInt(3) == 0) {
            if(!this.spawnAttacker() && this.getMap().getAllAttackers().isEmpty()) {
                this.currentWaveFinished = true;
            }
        }
    }

    public boolean spawnAttacker() {
        if(this.spawnedCurrentWave >= this.wavesOfAttackers[this.nextWave-1]) {
            return false;
        }
        this.spawnedCurrentWave++;
        Tile spawn = this.map.getRandomSpawn();
        System.out.println("Spawnpoint : " + spawn.toString());
        Attacker attacker = new BasicAttacker(spawn);
        spawn.addAttacker(attacker);
        return true;
    }

    public int getNextWave() {
        return this.nextWave;
    }
    
    public void startNextWave() {
        this.nextWave++;
        this.currentWaveFinished = false;
        this.spawnedCurrentWave = 0;
    }

    public boolean currentWaveFinished() {
        return this.currentWaveFinished;
    }

}
