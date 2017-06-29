/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.ui.action;

import fi.towerdefencegamesinc.towerdefence.java.Game;
import fi.towerdefencegamesinc.towerdefence.java.ui.Help;

/**
 *
 * @author vrsaari
 */
public class HelpCommand implements Action {

    @Override
    public void run(String command, Game game) {
        Help.commands();
    }

}
