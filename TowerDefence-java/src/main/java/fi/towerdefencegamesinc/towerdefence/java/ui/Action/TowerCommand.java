/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.ui.Action;

import fi.towerdefencegamesinc.towerdefence.java.Game;
import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.BasicTower;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.FreezeTower;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import fi.towerdefencegamesinc.towerdefence.java.ui.Help;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author vrsaari
 */
public class TowerCommand implements Action {

    Map<String, Supplier<Tower>> towers;

    public TowerCommand() {
        towers = new HashMap();
        towers.put("BASIC", BasicTower::new);
        towers.put("FREEZE", FreezeTower::new);
    }

    @Override
    public void run(String command, Game game) {
        String[] args = command.split("\\s+");
        if (args.length < 3) {
            Help.tower();
            return;
        }
        String action = args[1].toLowerCase();

        int xCoord;
        int yCoord;
        try {
            xCoord = Integer.parseInt(args[2]);
            yCoord = Integer.parseInt(args[3]);
        } catch (Exception e) {
            Help.tower();
            return;
        }
        switch (action) {
            case "add":
                if(args.length < 5) {
                    Help.tower();
                    break;
                }
                System.out.println("Adding tower");
                this.add(xCoord, yCoord, args[4], game);
                break;
            case "upgrade":
                //System.out.println("Upgrading tower");
                int level = this.upgrade(xCoord, yCoord, game);
                System.out.println("Tower upgraded, now level " + level);
                break;
            default:
                Help.tower();
        }

    }

    public boolean add(int x, int y, String type, Game game) {
        Supplier<Tower> tower = towers.get(type.toUpperCase());
        if (tower == null) {
            System.out.println("No such tower type.");
            return false;
        }
        Tower t = tower.get();
        t.setTile(game.getMap().getTile(x, y));
        game.getMap().getTile(x, y).addTower(t);
        return true;
    }

    private int upgrade(int x, int y, Game game) {
        Tower tower = game.getMap().getTile(x, y).getTower();
        if (tower == null) {
            return -1;
        }
        return tower.upgrade();
    }

}
