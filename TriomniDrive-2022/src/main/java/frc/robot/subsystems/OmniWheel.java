// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class OmniWheel extends SubsystemBase {
  private TalonSRX talonsrx;
  private double percent;
  /** Creates a new OmniWheel. You have to give the talon SRX for that wheel,
     eventually write diameter of wheel. */
  public OmniWheel(TalonSRX t) {
    this.talonsrx = t;
    this.percent = 0;
  }
 
  // this will set the speed of the wheel from a scale of-1 to 1. 
  //-1 is maximum speed in reverse, 1 is maximum speed forward.
  // 0 means stopped.
  public void setPercent(double percent) {
    this.percent = percent;
  }

  @Override
  public void periodic() {
    talonsrx.set(ControlMode.PercentOutput,percent);
  }
}
