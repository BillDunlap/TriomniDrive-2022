// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;// HID stands for Human interface device.
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.commands.DefaultOmniWheel;
// import frc.robot.commands.RunWheel;
import frc.robot.commands.SpinUsingJoystick;
import frc.robot.commands.Spin;
// import frc.robot.subsystems.OmniWheel;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final OmniWheel m_ow = new OmniWheel(new TalonSRX(3), "default"); // the 2 was 1 -> this change does change motor controller lights
  // private final RunWheel m_rw = new RunWheel(m_ow, -0.5, 5.0); // the -0.5 was 1.0
  private final XboxController m_controller = new XboxController(0);
  private final DriveTrain m_driveTrain = new DriveTrain();
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    SmartDashboard.putData(m_driveTrain); // show on Shuffleboard which command is using subsystem m_ow
    // Configure the button bindings
    configureButtonBindings();
    m_driveTrain.setDefaultCommand(new SpinUsingJoystick(m_driveTrain, m_controller));
    // m_ow.setDefaultCommand(new RunViaJoystick());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // pressing left bumper will make it spin slowly clockwise until button is released
    JoystickButton leftBumperButton = new JoystickButton(m_controller,XboxController.Button.kLeftBumper.value);
    leftBumperButton.whenPressed(new Spin(m_driveTrain, 0.2).until(() -> !leftBumperButton.get()));
    // right bumper causes counterclockwise spin until released
    JoystickButton rightBumperButton = new JoystickButton(m_controller,XboxController.Button.kRightBumper.value);
    rightBumperButton.whenPressed(new Spin(m_driveTrain, -0.2).until(() -> !rightBumperButton.get()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // very slowly spin for 5 seconds
    return new Spin(m_driveTrain, 0.1).withTimeout(5.0);
  }
}
