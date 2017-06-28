/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.ui;

import fi.towerdefencegamesinc.towerdefence.java.Game;
import fi.towerdefencegamesinc.towerdefence.java.logic.Difficulty;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.Action;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.ContinueCommand;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.HelpCommand;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.ScoreCommand;
import fi.towerdefencegamesinc.towerdefence.java.ui.Action.TowerCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @author vrsaari
 */
public class TextUI {
    private final Game game;
    private final Scanner scanner;
    
    private Map<String, Supplier<Action>> commands;
    

    public TextUI(Scanner scanner) {
        this.commands = new HashMap();
        this.scanner = scanner;
        System.out.print("Name? ");
        String playerName = this.scanner.nextLine();
        System.out.print("Difficulty? (" + Stream.of(Difficulty.values()).map(Difficulty::toString).reduce(", ", String::concat));
        Difficulty difficulty = Difficulty.valueOf(this.scanner.nextLine().toUpperCase());
        System.out.print("Name of mapfile? ");
        String mapFileName = this.scanner.nextLine();
        this.game = new Game(mapFileName, playerName, difficulty);
        
        commands.put("TOWER", TowerCommand::new);
        commands.put("SCORE", ScoreCommand::new);
        commands.put("CONTINUE", ContinueCommand::new);
        commands.put("HELP", HelpCommand::new);
        
    }
    
    public void run() {
        while (true) {
            this.update();
        }
    }
    
    public void update() {
        printPlayer();
        printMap();
        Help.commands();
        String cmd = scanner.nextLine();
        if(cmd.isEmpty()) {
            cmd = "CONTINUE";
        }
        System.out.println(cmd);
        commands.getOrDefault(cmd.split("\\s+")[0].toUpperCase(), commands.get("HELP"))
                .get().run(cmd, game);
    }
    
    public void printScores(int n) {
        System.out.println(this.game.getMap().getScoreBoard().getScores(n));
    }
    
    public void printScores() {
        this.printScores(10);
    }
    
    public void printMap() {
        System.out.println(this.game.getMap().toString());
    }    
    public void printPlayer() {
        System.out.println(this.game.getPlayer().toString());
    }
}
