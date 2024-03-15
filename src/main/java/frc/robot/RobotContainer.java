// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.RollerClaw;
import frc.robot.subsystems.TankDriveTrain;

public class RobotContainer {
  
  protected final TankDriveTrain driveTrain;
  protected final Launcher launcher;
  protected final RollerClaw rollerClaw;
  
  private final CommandXboxController driverController;
  private final CommandXboxController operatorController;
  
  public final RobotContainer.Commands commands;

  public RobotContainer() {
    
    this.driveTrain = new TankDriveTrain();
    this.launcher = new Launcher();
    this.rollerClaw = new RollerClaw();
    
    this.driverController = new CommandXboxController(
        OperatorConstants.kDriverControllerPort
    );
    
    this.operatorController = new CommandXboxController(
        OperatorConstants.kOperatorControllerPort
    );
    
    this.commands = new RobotContainer.Commands();
    
    this.configureBindings();
  }

  private void configureBindings() {
    
    this.driveTrain.setDefaultCommand(this.driveTrain.commands.arcadeDrive(
        () -> -driverController.getLeftY(),
        () -> -driverController.getRightX()
    ).withName("Default Drive Command"));
    
    operatorController.a().onTrue(this.launcher.commands.launch());
    operatorController.leftBumper().whileTrue(this.launcher.commands.intake());
    operatorController.rightBumper().whileTrue(this.rollerClaw.commands.intake());
    operatorController.b().whileTrue(this.rollerClaw.commands.launch());
    
  }
  
  public Command getAutonomousCommand() {
    
    return this.commands.exampleAuto();
    
  }
  
  public class Commands {
    
    public Command exampleAuto() {
      
      return RobotContainer.this.driveTrain.commands.timedArcadeDrive(
          -0.5,
          0,
          3
      );
      
      
    }
    
  }
  
}
