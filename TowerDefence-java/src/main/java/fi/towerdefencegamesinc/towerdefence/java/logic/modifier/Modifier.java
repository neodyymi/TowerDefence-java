/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.modifier;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import java.util.Date;

/**
 * Interface for all modifiers.
 * @author vrsaari
 */
public interface Modifier {

    /**
     * Getter for duration.
     * @return The duration of the modifier.
     */
    Long getDuration();

    /**
     * How long is the modifier still active?
     * @return Duration left on the modifier in milliseconds.
     */
    Long getDurationLeft();

    /**
     * Getter for name.
     * @return The name of the modifier.
     */
    String getName();

    /**
     * Getter for startTime.
     * @return The start time for the modifier.
     */
    Date getStartTime();

    /**
     * Getter for strength.
     * @return The strength of the modifier.
     */
    int getStrength();

    /**
     * Setter for duration.
     * @param duration The new duration of the modifier.
     */
    void setDuration(Long duration);

    /**
     * Setter for name.
     * @param name The new name of the modifier.
     */
    void setName(String name);

    /**
     * Setter for start time.
     * @param startTime The new start time of the modifier.
     */
    void setStartTime(Date startTime);

    /**
     * Setter for strength.
     * @param strength The new strength of the modifier.
     */
    void setStrength(int strength);

    @Override
    String toString();

    /**
     * Update stats of the attacker the modifier is attached to.
     *
     * @param attacker The attacker the modifier is on.
     */
    void update(Attacker attacker);

}
