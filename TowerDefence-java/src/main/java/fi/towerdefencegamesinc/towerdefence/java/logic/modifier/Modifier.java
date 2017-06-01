/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.modifier;

import java.util.Date;

/**
 *
 * @author vrsaari
 */
public class Modifier {

    private Date startTime;
    private Long duration;

    public Modifier(Long duration) {
        this.startTime = new Date();
        this.duration = duration;
    }

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

}
