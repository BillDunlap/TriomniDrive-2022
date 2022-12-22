// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;

public class SpinUsingJoystick extends CommandBase {
  private final DriveTrain m_driveTrain;
  private final XboxController m_controller;
  /** Creates a new SpinUsingJoystick. */
  public SpinUsingJoystick(DriveTrain driveTrain, XboxController controller) {
    m_driveTrain = driveTrain;
    m_controller = controller;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.beStill();;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double y = m_controller.getLeftY();
    m_driveTrain.spinFractionPower(y);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.beStill();;
  }

  // This is expected to default method for DriveTrain objects, so it never finishes
  @Override
  public boolean isFinished() {
    return false;
  }
}
