// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControllerRumbler;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;

public class DefaultTeleop extends CommandBase {
  /** Creates a new DefaultTeleop. */
  private final DriveTrain m_driveTrain;
  private final XboxController m_controller;
  private final ControllerRumbler m_controllerRumbler;
  
  /** Creates a new SpinUsingJoystick. */
  public DefaultTeleop(DriveTrain driveTrain, XboxController controller, ControllerRumbler controllerRumbler) {
    m_driveTrain = driveTrain;
    m_controller = controller;
    m_controllerRumbler = controllerRumbler;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.beStill();
    m_controllerRumbler.beQuiet();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // TODO: turn off rumbler if not too fast
    double z;
    boolean rumbleNeeded = false;
    if ((z = m_controller.getLeftTriggerAxis()) > 0.0) {
      rumbleNeeded = z > .90;
      m_driveTrain.spinDegreesPerSecond(-z * 90.0); // I think z is in [-1,1]
    } else if ((z = m_controller.getRightTriggerAxis()) > 0.0) {
      rumbleNeeded = z > 0.90;
      m_driveTrain.spinDegreesPerSecond(+z * 90.0);
    } else if (m_controller.getLeftBumper()) {
      m_driveTrain.spinDegreesPerSecond(-40.0); // -> -TuningVariables.m_spinRate
    } else if (m_controller.getRightBumper()) {
      m_driveTrain.spinDegreesPerSecond(+40.0); // -> +TuningVariables.m_spinRate
    } else if ((z = m_controller.getPOV()) != -1) {
      m_driveTrain.setVelocityFpsDegrees(2.0, z); // -> TuningVariables.m_fps
    } else { // if nothing else pressed, go according to left joystick
      double x = m_controller.getLeftX();
      double y = m_controller.getLeftY();
      if (x == 0.0 && y == 0.0) {
        m_driveTrain.beStill();
        m_controllerRumbler.beQuiet();
      } else {
        double radians = Math.atan2(-y, x) - Math.PI/2 ;
        double fps = Math.hypot(x, y) * 4.0 ; // 4 ft/s is easy walking speed
        rumbleNeeded = fps >= 4.0;
        m_driveTrain.setVelocityFpsRadians(fps, radians);
      }
    }
    if (rumbleNeeded) {
      m_controllerRumbler.setRumble(.5); // gentle rumble if speeding
    } else {
      m_controllerRumbler.beQuiet();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.beStill();
    m_controllerRumbler.beQuiet();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
