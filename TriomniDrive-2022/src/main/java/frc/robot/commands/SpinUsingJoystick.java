// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinUsingJoystick extends CommandBase {
  private final DriveTrain m_driveTrain;
  private final XboxController m_controller;
  private double m_basePower;
  /** Creates a new SpinUsingJoystick. */
  public SpinUsingJoystick(DriveTrain driveTrain, XboxController controller) {
    m_driveTrain = driveTrain;
    m_controller = controller;
    m_basePower = 0;
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
    if (m_controller.getXButtonPressed()) {
      m_basePower -= 0.05;
      SmartDashboard.putNumber("Base Power", m_basePower);
    }
    if (m_controller.getBButtonPressed()) {
      m_basePower += 0.05;
      SmartDashboard.putNumber("Base Power", m_basePower);
    }
    double y = m_controller.getLeftY();
    m_driveTrain.spinFractionPower(y + m_basePower);
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
