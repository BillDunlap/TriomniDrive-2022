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
import frc.robot.commands.GoStraight;
import frc.robot.commands.RumbleController;
import frc.robot.commands.Spin;
import frc.robot.commands.SpinNativeUnits;
import frc.robot.subsystems.ControllerRumbler;
// import frc.robot.subsystems.OmniWheel;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import com.kauailabs.navx.frc.AHRS; // for NaxX
import edu.wpi.first.wpilibj.SPI; // for MXP port on top of Roborio (that NavX is attached to)

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
  public final ControllerRumbler m_rumbler = new ControllerRumbler(m_controller);
  private final DriveTrain m_driveTrain = new DriveTrain();
  public static final AHRS ahrs = new AHRS(SPI.Port.kMXP); 
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    SmartDashboard.putData(m_driveTrain); // show commands controlling the drive train
    configureButtonBindings();
    m_driveTrain.setDefaultCommand(new SpinUsingJoystick(m_driveTrain, m_controller));
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
    leftBumperButton.whenPressed(new SpinNativeUnits(m_driveTrain, 200).until(() -> !leftBumperButton.get()));
    // right bumper causes counterclockwise spin until released
    JoystickButton rightBumperButton = new JoystickButton(m_controller,XboxController.Button.kRightBumper.value);
    rightBumperButton.whenPressed(new SpinNativeUnits(m_driveTrain, -150).until(() -> !rightBumperButton.get()));
    // Y button makes robot go forward at 1.5 feet/second, until button is released
    JoystickButton yButton = new JoystickButton(m_controller, XboxController.Button.kY.value);
    yButton.whenPressed(new GoStraight(m_driveTrain, 1.5, 0.0).until(() -> !yButton.get()));
    // A button makes robot go left at 1.3 feet/second, until button is released
    JoystickButton aButton = new JoystickButton(m_controller, XboxController.Button.kA.value);
    aButton.whenPressed(new GoStraight(m_driveTrain, 1.3, -90.).until(() -> !aButton.get()));
    JoystickButton backButton = new JoystickButton(m_controller, XboxController.Button.kBack.value);
    backButton.whenPressed(new RumbleController(m_rumbler, 1.0, 1.0).withTimeout(3.0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // very slowly spin for 5 seconds
    return new Spin(m_driveTrain, 0.4).withTimeout(5.0);
  }
}
