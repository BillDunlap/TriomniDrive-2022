// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;
public class OmniWheel extends SubsystemBase {
  private TalonSRX m_talonsrx;
  private double m_fractionPower;
  private String m_name;
  /** Creates a new OmniWheel, controlled by a TalonSRX motor controller
   *  @param canID:  CAN bus identifier for the motor controller
   *  @param name: a name for the wheel to be used in SmartDashboard displays
   */ 
    // eventually will need to specify diameter of wheel.
  public OmniWheel(int canId, String name) {
    m_talonsrx = new TalonSRX(canId);
    m_fractionPower = 0;
    m_name = name;
    m_talonsrx.clearStickyFaults();
    SmartDashboard.putNumber("Omniwheel power" + "(" + m_name + ")", m_fractionPower);
  }
 
  /** Set the speed of the wheel on a scale of-1 to 1. 
      @param fractionPower: -1 is maximum power in reverse, 1 is maximum power forward.
        0 means stopped.
      */
  public void setFractionPower(double fractionPower) {
    m_fractionPower = fractionPower;
    SmartDashboard.putNumber("Omniwheel power" + "(" + m_name + ")", m_fractionPower);
    m_talonsrx.set(ControlMode.PercentOutput,m_fractionPower);
  }

  @Override
  public void periodic() {

  }
}
