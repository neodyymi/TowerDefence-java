/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java;

import fi.towerdefencegamesinc.towerdefence.java.logic.Score;

/**
 *
 * @author vrsaari
 */
public class Main { 
    public static void main(String[] args) {
        Score score = new Score("Testi", 12345);
        System.out.println(score);
    }
}
