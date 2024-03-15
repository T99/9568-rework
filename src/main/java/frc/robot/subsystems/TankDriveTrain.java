package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

import java.util.function.DoubleSupplier;
import java.util.stream.Stream;

public class TankDriveTrain extends SubsystemBase {
	
	protected final PWMSparkMax frontLeftMotorController;
	
	protected final PWMSparkMax frontRightMotorController;
	
	protected final PWMSparkMax rearLeftMotorController;
	
	protected final PWMSparkMax rearRightMotorController;
	
	protected final DifferentialDrive differentialDrive;
	
	public final TankDriveTrain.Commands commands;
	
	public TankDriveTrain() {
		
		this.frontLeftMotorController = new PWMSparkMax(DrivetrainConstants.kLeftFrontID);
		this.frontRightMotorController = new PWMSparkMax(DrivetrainConstants.kRightFrontID);
		this.rearLeftMotorController = new PWMSparkMax(DrivetrainConstants.kLeftRearID);
		this.rearRightMotorController = new PWMSparkMax(DrivetrainConstants.kRightRearID);
		this.differentialDrive = new DifferentialDrive(
			this.frontLeftMotorController,
			this.frontRightMotorController
		);
		this.commands = new TankDriveTrain.Commands();
		
		this.frontLeftMotorController.addFollower(
			this.rearLeftMotorController
		);
		
		this.frontRightMotorController.addFollower(
			this.rearRightMotorController
		);
		
		this.frontLeftMotorController.setInverted(true);
		this.rearLeftMotorController.setInverted(true);
		this.frontRightMotorController.setInverted(false);
		this.rearLeftMotorController.setInverted(false);
		
	}
	
	protected Stream<PWMSparkMax> getMotorControllerStream() {
		
		return Stream.of(
			this.frontLeftMotorController,
			this.frontRightMotorController,
			this.rearLeftMotorController,
			this.rearRightMotorController
		);
		
	}
	
	public class Commands {
	
		public Command arcadeDrive(
			DoubleSupplier speed,
			DoubleSupplier rotation
		) {
			
			return TankDriveTrain.this.run(
				() -> TankDriveTrain.this.differentialDrive.arcadeDrive(
					speed.getAsDouble(),
					rotation.getAsDouble()
				)
			);
			
		}
		
		public Command timedArcadeDrive(
			double speed,
			double rotation,
			double seconds
		) {
			
			return TankDriveTrain.this.run(
				() -> TankDriveTrain.this.differentialDrive.arcadeDrive(
					speed,
					rotation
				)
			).withTimeout(seconds);
			
		}
	
	}
	
}
