/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java;

import fi.towerdefencegamesinc.towerdefence.java.logic.GameMap;
import fi.towerdefencegamesinc.towerdefence.java.logic.Location;
import fi.towerdefencegamesinc.towerdefence.java.logic.Player;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import java.util.List;

/**
 *
 * @author vrsaari
 */
public class Game {

    private final GameMap map;
    private final Player player;
    private int score;

    public Game(String mapFile, String playerName, int startCurrency) {
        this.map = GameMap.loadMapFromFile(mapFile);
        this.player = new Player(playerName, startCurrency);
        this.score = 0;
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
        List<Attacker> attackers = map.getAllAttackers();
        attackers.stream().forEach(Attacker::move);
        List<Tower> towers = map.getAllTowers();
        towers.stream().forEach(t -> {
            Tower tower = (Tower) t;
            Attacker target = tower.getTarget(attackers);
            if (target != null) {
                tower.shoot(target);
            }
        });
    }

}
