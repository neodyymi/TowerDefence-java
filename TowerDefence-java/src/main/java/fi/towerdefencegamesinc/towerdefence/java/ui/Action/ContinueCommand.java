/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.ui.Action;

import fi.towerdefencegamesinc.towerdefence.java.Game;

/**
 *
 * @author vrsaari
 */
public class ContinueCommand implements Action {

    @Override
    public void run(String command, Game game) {
        game.update();
    }

}
