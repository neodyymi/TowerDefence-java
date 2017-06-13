/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java;

import fi.towerdefencegamesinc.towerdefence.java.ui.TextUI;
import java.util.Scanner;

/**
 *
 * @author vrsaari
 */
public class Main { 
    public static void main(String[] args) {
        TextUI ui = new TextUI(new Scanner(System.in));
        while(true) {
            ui.update();
        }
        
    }
}
