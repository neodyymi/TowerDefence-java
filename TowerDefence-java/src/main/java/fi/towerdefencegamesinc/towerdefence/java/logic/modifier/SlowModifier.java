/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.logic.modifier;

/**
 * Slow modifier applied on attackers.
 *
 * @author vrsaari
 */
public class SlowModifier extends BasicModifier {

    /**
     * Create a slow modifier.
     *
     * @param duration The duration of the modifier.
     * @param name The name of the modifier.
     * @param strength The strength of the modifier.
     */
    public SlowModifier(Long duration, String name, int strength) {
        super(duration, name, strength);
    }

    /**
     * Create a slow modifier.
     *
     * @param duration The duration of the modifier.
     * @param strength The strength of the modifier.
     */
    public SlowModifier(Long duration, int strength) {
        super(duration, "Slowness", strength);
    }

    @Override
    public String toString() {
        int minutes = (int) ((this.getDurationLeft() / (1000 * 60)));
        int seconds = (int) ((this.getDurationLeft() / (1000)) % 60);
        StringBuilder sb = new StringBuilder();
        sb.append(minutes).append(":").append(seconds).append(" ").append(super.getName())
                .append("(").append(super.getStrength()).append("%)");
        return sb.toString();
    }

}
