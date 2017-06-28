/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Enumerator for different types of tiles available.
 *
 * @author vrsaari
 */
public enum Type {
    Road(' ', new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY))),
    Buildable('@', new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))),
    Unbuildable('#', new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY))),
    Spawn('X', new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))),
    Base('$', new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));

    private final char representation;
    private Background background;

    private Type(char t, Background bg) {
        representation = t;
        background = bg;
    }
    
    public Background getBackground() {
        return this.background;
    }

    @Override
    public String toString() {
        return "" + representation;
    }

}
