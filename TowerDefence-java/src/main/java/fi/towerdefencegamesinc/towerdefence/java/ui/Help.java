/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.ui;

/**
 *
 * @author vrsaari
 */
public class Help {

    public static void help() {

    }

    public static void commands() {
        String response = "Available commands: tower, score, continue. Enter to update.";
        System.out.println(response);
    }

    public static void tower() {
        String response = "Command 'tower' requires more arguments.\n"
                + "'tower add <x-coordinate> <y-coordinate> <type of tower>\n"
                + "Types of tower available ('basic', 'freeze')\n";
        System.out.println(response);
    }

    public static void score() {
        String response = "Command 'score' requires more arguments.\n"
                + "'score <number of scores>'\n";
        System.out.println(response);
    }
}
