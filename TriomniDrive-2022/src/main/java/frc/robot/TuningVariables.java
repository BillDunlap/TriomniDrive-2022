// Author: Bill Dunlap <williamwdunlap@gmail.com>, FRC 4173 (Team IMVERT), January 2023.
// This software is free to use by anyone with no restrictions.

package frc.robot;

import edu.wpi.first.wpilibj.Preferences;

/** These things are like variables in Constants,
 * but can be set via the Preferences class (e.g.,
 * via Shuffleboard) and will be stored in flash
 * memory on the roborio.  The Preferences class
 * stores values in flash (persistent) memory on
 * the Roborio.  The TuningVariables class also
 * stores default values in Java (nonpersistent)
 * memory.
 * 
 * To get a tuning variable's value use the syntax
 *   TuningVariables.variableName.get()
 * 
 * A current limitation is that the turning variables
 * must be doubles.
 */
public enum TuningVariables {
    // To add a new value, just enter its name and default value to the following list
    defaultSpinRate_DegreesPerSecond (40.0),
    defaultTravelRate_FeetPerSecond (2.0);

    private double m_defaultValue;
    /** Users cannot call an enum constructor directly;
     * Java will call it for each variable listed above.
     * Note the constructor will not change pre-existing
     * values in flash memory.  Use setToDefaultValue or
     * setAllToDefaultValues for that.
     */
    private TuningVariables(double defaultValue){
        m_defaultValue = defaultValue;
        if (!Preferences.containsKey(name())) {
          Preferences.setDouble(name(), m_defaultValue);
        }
    }
    
    public double get() {
        return Preferences.getDouble(name(), m_defaultValue);
    }
    void set(double value) {
        Preferences.setDouble(name(), value);
    }
    void setToDefaultValue() {
        set(m_defaultValue);
    }
    static void setAllToDefaultValues() {
        for(TuningVariables tv: TuningVariables.values()) {
            tv.setToDefaultValue();
        }
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
