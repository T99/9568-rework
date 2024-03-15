package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.RollerClawConstants;

public class RollerClaw extends SubsystemBase {
	
	protected final PWMSparkMax motorController;
	
	public final RollerClaw.Commands commands;
	
	public RollerClaw() {
		
		this.motorController = new PWMSparkMax(
			RollerClawConstants.kRollerClawID
		);
		this.commands = new RollerClaw.Commands();
		
	}
	
	public class Commands {
		
		protected Command runRollerClaw(double speed) {
			
			return RollerClaw.this.startEnd(
				() -> RollerClaw.this.motorController.set(speed),
				RollerClaw.this.motorController::stopMotor
			);
			
		}
		
		public Command intake() {
			
			return this.runRollerClaw(
				RollerClawConstants.kRollerClawOutputPower
			);
			
		}
		
		public Command launch() {
			
			return this.runRollerClaw(
				-RollerClawConstants.kRollerClawOutputPower
			);
			
		}
		
	}
	
}
