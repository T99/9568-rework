package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.LauncherConstants;

public class Launcher extends SubsystemBase {
	
	protected final PWMSparkMax launcherMotorController;
	
	protected final PWMSparkMax feederMotorController;
	
	public final Launcher.Commands commands;
	
	public Launcher() {
		
		this.launcherMotorController = new PWMSparkMax(LauncherConstants.kLauncherID);
		this.feederMotorController = new PWMSparkMax(LauncherConstants.kFeederID);
		this.commands = new Launcher.Commands();
		
	}
	
	public class Commands {
		
		protected Command runLauncher(double speed) {
			
			return Launcher.this.startEnd(
				() -> Launcher.this.launcherMotorController.set(speed),
				Launcher.this.launcherMotorController::stopMotor
			);
			
		}
		
		protected Command runFeeder(double speed) {
			
			return Launcher.this.startEnd(
				() -> Launcher.this.feederMotorController.set(speed),
				Launcher.this.feederMotorController::stopMotor
			);
			
		}
		
		public Command intake() {
			
			return this.runLauncher(LauncherConstants.kIntakeLauncherSpeed)
				.alongWith(this.runFeeder(LauncherConstants.kIntakeFeederSpeed));
			
		}
		
		public Command launch() {
			
			return this.runLauncher(LauncherConstants.kLauncherSpeed)
				.alongWith(new WaitCommand(3).andThen(
						this.runFeeder(LauncherConstants.kLaunchFeederSpeed)
				)).withTimeout(5);
		
		}
		
	}
	
}
