// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OmniWheel;
import edu.wpi.first.wpilibj.Timer;

public class RunWheel extends CommandBase {
  private OmniWheel m_ow;
  double m_speed;
  double m_seconds;
  Timer m_timer;

  /** Creates a new RunWheel. */
  // run OmniWHeel for 5 seconds
  public RunWheel(OmniWheel ow, double seconds, double speed){
    m_ow = ow;
    m_speed = speed;
    m_seconds = seconds;
    m_timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  m_timer.reset();
  m_timer.start();
  m_ow.setPercent(m_speed);



  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ow.setPercent(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.get() >= m_seconds;

  }
}
