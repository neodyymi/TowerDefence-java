/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.modifier;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import java.util.Date;

/**
 * A time limited modifier for a target. It can modify any stats on the target.
 *
 * @author vrsaari
 */
public abstract class Modifier {

    private Date startTime;
    private Long duration;
    private String name;
    private int strength;

    /**
     * Creates a new modifier.
     *
     * @param duration The duration of the modifier in milliseconds.
     * @param name
     * @param strength
     */
    public Modifier(Long duration, String name, int strength) {
        this.startTime = new Date();
        this.duration = duration;
        this.strength = strength;
    }

    /**
     *
     * @return Duration left on the modifier in milliseconds.
     */
    public Long getDurationLeft() {
        return duration - (this.startTime.getTime() - new Date().getTime());
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * Update stats of the attacker the modifier is attached to.
     *
     * @param attacker
     */
    public void update(Attacker attacker) {
        // Do somethign to the attacker.
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public abstract String toString();
}
