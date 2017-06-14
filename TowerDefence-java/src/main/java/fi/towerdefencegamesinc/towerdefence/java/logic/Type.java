/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic;

/**
 * Enumerator for different types of tiles available.
 *
 * @author vrsaari
 */
public enum Type {
    Road(' '),
    Buildable('@'),
    Unbuildable('#'),
    Spawn('X'),
    Base('$');

    private final char representation;

    private Type(char t) {
        representation = t;
    }

    @Override
    public String toString() {
        return "" + representation;
    }

}
