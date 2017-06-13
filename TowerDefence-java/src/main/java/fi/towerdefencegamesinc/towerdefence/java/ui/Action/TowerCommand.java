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
public class TowerCommand implements Action{
    
    Map<String, Supplier<Tower>> towers = new HashMap();

    public TowerCommand() {
        towers.put("BASIC", BasicTower::new);
        towers.put("FREEZE", FreezeTower::new);
    }
    
    

    @Override
    public void run(String command, Game game) {
        String[] args = command.split("\\s+");
        if(args.length < 3) {
            Help.tower();
            return;
        }
        String action = args[0].toLowerCase();
        int xCoord = Integer.parseInt(args[1]);
        int yCoord = Integer.parseInt(args[2]);
        switch (action) {
            case "add": this.add(xCoord, yCoord, args[3], game);
                        break;
            case "upgrade": this.upgrade(xCoord, yCoord, game);
            default: 
        }
        
        
        
        
    }
    
    public boolean add(int x, int y, String type, Game game) {
        Supplier<Tower> tower = towers.get(type.toUpperCase());
        if(tower == null) {
            return false;
        }
        Tower t = tower.get();
        t.setTile(game.getMap().getTile(x, y));
        game.getMap().getTile(x, y).addTower(tower.get());
        return true;
    }

    private boolean upgrade(int x, int y, Game game) {
        Tower tower = game.getMap().getTile(x, y).getTower();
        if(tower == null) {
            return false;
        }
        int oldLevel = tower.getLevel();
        return tower.upgrade() > oldLevel ;
    }
    
}
