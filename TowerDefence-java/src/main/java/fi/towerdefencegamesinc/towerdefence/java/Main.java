/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java;

import fi.towerdefencegamesinc.towerdefence.java.logic.tower.BasicTower;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.FreezeTower;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import fi.towerdefencegamesinc.towerdefence.java.ui.GraphicalUI;
import fi.towerdefencegamesinc.towerdefence.java.ui.TextUI;
import java.util.Scanner;

/**
 *
 * @author vrsaari
 */
public class Main {

    public static void main(String[] args) {
        Tower t1 = new BasicTower();
        Tower t2 = new FreezeTower();

        System.out.println(t1.getWorth());
        System.out.println(t2.getWorth());

//        TextUI ui = new TextUI(new Scanner(System.in));
        GraphicalUI ui = new GraphicalUI();
        
        ui.run();

        
    }
}
