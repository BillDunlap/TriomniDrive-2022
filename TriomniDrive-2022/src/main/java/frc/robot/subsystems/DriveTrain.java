// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.subsystems.OmniWheel; // no need to import class in same directory
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {
  private final OmniWheel m_front;
  private final OmniWheel m_right;
  private final OmniWheel m_left;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    m_front = new OmniWheel(1, "front", Constants.kWheelDiameterFt);
    m_right = new OmniWheel(2, "right", Constants.kWheelDiameterFt);
    m_left = new OmniWheel(3, "left", Constants.kWheelDiameterFt);
    beStill();
  }

  public void setFractionPower(double front, double right, double left){
    m_front.setFractionPower(front);
    m_right.setFractionPower(right);
    m_left.setFractionPower(left);
  }

  public void setVelocityNativeUnits(double front, double right, double left) {
    m_front.setVelocityNativeUnits(front);
    m_right.setVelocityNativeUnits(right);
    m_left.setVelocityNativeUnits(left);
  }

  /** spin robot using same power to all omniwheels
   *  @param fractionPower: number in [-1,1] saying what fraction of maximum power
   * to give each wheel.  Positive for clockwise, negative counterclockwise.
   */
  public void spinFractionPower(double fractionPower) {
    setFractionPower(fractionPower, fractionPower, fractionPower);
  }

  public void spinNativeUnits(double velocity) {
    setVelocityNativeUnits(velocity, velocity, velocity);
  }

  public void beStill() {
    setFractionPower(0.0, 0.0, 0.0);
  }

  public void setVelocityFpsRadians(double feetPerSecond, double radians){
    double front_feetPerSecond = feetPerSecond * Math.cos( radians - 9./6. * Math.PI);
    double right_feetPerSecond = feetPerSecond * Math.cos( radians - 5./6. * Math.PI);
    double left_feetPerSecond = feetPerSecond * Math.cos( radians - 1./6. * Math.PI);
    m_front.setVelocityFeetPerSecond(front_feetPerSecond);
    m_right.setVelocityFeetPerSecond(right_feetPerSecond);
    m_left.setVelocityFeetPerSecond(left_feetPerSecond); 
  }

  public void setVelocityFpsDegrees(double feetPerSecond, double degrees){
    setVelocityFpsRadians(feetPerSecond, -degrees / 180. * Math.PI);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double angle = RobotContainer.ahrs.getAngle();
    SmartDashboard.putNumber("Angle", angle);
  }
}
