// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.OmniWheel;

public class DriveTrain extends SubsystemBase {
  private final OmniWheel m_front;
  private final OmniWheel m_right;
  private final OmniWheel m_left;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    m_front = new OmniWheel(1, "front");
    m_right = new OmniWheel(2, "right");
    m_left = new OmniWheel(3, "left");
    setFractionPower(0.0, 0.0, 0.0);
  }

  public void setFractionPower(double fFront, double fRight, double fLeft){
    m_front.setFractionPower(fFront);
    m_right.setFractionPower(fRight);
    m_left.setFractionPower(fLeft);
  }

  /** spin robot using same power to all omniwheels
   *  @param fractionPower: number in [-1,1] saying what fraction of maximum power
   * to give each wheel.  Positive for clockwise, negative counterclockwise.
   */
  public void spinFractionPower(double fractionPower) {
    setFractionPower(fractionPower, fractionPower, fractionPower);
  }

  public void beStill() {
    setFractionPower(0.0, 0.0, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
