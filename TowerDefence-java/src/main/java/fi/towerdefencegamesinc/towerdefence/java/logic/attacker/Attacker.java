/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.attacker;

import fi.towerdefencegamesinc.towerdefence.java.logic.modifier.Modifier;

/**
 *
 * @author vrsaari
 */
public interface Attacker {

    public void move();

    public int attack();

    public boolean canFly();

    public void addModifier(Modifier mod);

    public void updateModifiers();

    public void getSpeed();
}
