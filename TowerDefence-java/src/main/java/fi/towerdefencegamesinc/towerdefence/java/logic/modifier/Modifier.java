/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.modifier;

import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import java.util.Date;

/**
 *
 * @author vrsaari
 */
public interface Modifier {

    Long getDuration();

    /**
     *
     * @return Duration left on the modifier in milliseconds.
     */
    Long getDurationLeft();

    String getName();

    Date getStartTime();

    int getStrength();

    void setDuration(Long duration);

    void setName(String name);

    void setStartTime(Date startTime);

    void setStrength(int strength);

    String toString();

    /**
     * Update stats of the attacker the modifier is attached to.
     *
     * @param attacker
     */
    void update(Attacker attacker);
    
}
