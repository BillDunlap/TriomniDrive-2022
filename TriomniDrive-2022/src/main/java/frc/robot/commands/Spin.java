// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Spin extends CommandBase {
  private final DriveTrain m_driveTrain;
  private double m_percent;
  /** Creates a new Spin. */
  public Spin(DriveTrain driveTrain, double percent) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_percent = percent;
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.setFractionPower(m_percent, m_percent, m_percent);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setFractionPower(0.0, 0.0, 0.0);
  }

  // We expect this command to be decorated with withTimeout() or until()
  @Override
  public boolean isFinished() {
    return false; // we expect user will add a decorator for a timeout or wait until button is released
  }
}
