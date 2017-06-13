/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.ui.Action;

import fi.towerdefencegamesinc.towerdefence.java.Game;
import fi.towerdefencegamesinc.towerdefence.java.ui.Help;

/**
 *
 * @author vrsaari
 */
public class ScoreCommand implements Action {

    @Override
    public void run(String command, Game game) {
        String[] args = command.split("\\s+");
        if (args.length < 1) {
            System.out.println(game.getMap().getScoreBoard().getScores());
            return;
        }
        try {
            System.out.println(game.getMap().getScoreBoard().getScores(Integer.parseInt(args[1])));
        } catch (Exception e) {
            Help.score();
        }
    }

}
