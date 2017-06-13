/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.ui;

import fi.towerdefencegamesinc.towerdefence.java.Game;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.Action;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.ContinueCommand;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.ScoreCommand;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.TowerCommand;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 *
 * @author vrsaari
 */
public class TextUI {
    private final Game game;
    private final Scanner scanner;
    
    private Map<String, Supplier<Action>> commands;
    

    public TextUI(Scanner scanner) {
        this.scanner = scanner;
        System.out.print("Name? ");
        String playerName = this.scanner.nextLine();
        System.out.print("Start currency? ");
        int startCurrency = Integer.parseInt(this.scanner.nextLine());
        System.out.println("Name of mapfile? ");
        String mapFileName = this.scanner.nextLine();
        this.game = new Game(mapFileName, playerName, startCurrency);
        
        commands.put("TOWER", TowerCommand::new);
        commands.put("SCORE", ScoreCommand::new);
        commands.put("CONTINUE", ContinueCommand::new);
        
    }
    
    public void update() {
        printMap();
        Help.help();
        String cmd = scanner.nextLine();
        if(!cmd.isEmpty()) {
            cmd = "CONTINUE";
        }
        commands.get(cmd).get().run(cmd, game);
    }
    
    public void printScores(int n) {
        this.game.getMap().getScoreBoard().getScores(n);
    }
    
    public void printScores() {
        this.printScores(10);
    }
    
    public void printMap() {
        this.game.getMap().toString();
    }    
}
