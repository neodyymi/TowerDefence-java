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
public abstract class BasicModifier implements Modifier {

    private Date startTime;
    private Long duration;
    private String name;
    private int strength;

    /**
     * Creates a new modifier.
     *
     * @param duration The duration of the modifier in milliseconds.
     * @param name The name of the modifier.
     * @param strength The strength of the modifier.
     */
    public BasicModifier(Long duration, String name, int strength) {
        this.startTime = new Date();
        this.duration = duration;
        this.strength = strength;
        this.name = name;
    }

    @Override
    public Long getDurationLeft() {
        return duration - (this.startTime.getTime() - new Date().getTime());
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Long getDuration() {
        return duration;
    }

    @Override
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * Update stats of the attacker the modifier is attached to.
     *
     * @param attacker Attacker to update modifier on.
     */
    @Override
    public void update(Attacker attacker) {
        // Do somethign to the attacker.
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

}
