// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Preferences;

/** These things are like variables in Constants,
 * but can be set via the Preferences class (e.g.,
 * via Shuffleboard) and will be stored in flash
 * memory on the roborio.
 * 
 * A current limitation is that the turning variables
 * must be doubles.
 */
public enum TuningVariables {
    // To add a new value, just enter its name and default value to the following list
    defaultSpinRate_DegreesPerSecond (40.0),
    defaultTravelRate_FeetPerSecond (2.0);

    private TuningVariables(double defaultValue){
        m_defaultValue = defaultValue;
        if (!Preferences.containsKey(name())) {
          Preferences.setDouble(name(), m_defaultValue);
        }
    }
    private double m_defaultValue;
    public double get() {
        return Preferences.getDouble(name(), m_defaultValue);
    }
    void remove(){
        Preferences.remove(name());
    }
    static void removeAllKnown() {
        for(TuningVariables tv : TuningVariables.values()) {
            tv.remove();
        }
    }
}
