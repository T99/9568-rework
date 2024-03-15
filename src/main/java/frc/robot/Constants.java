// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public static class DrivetrainConstants {
    public static final int kLeftRearID = 2;
    public static final int kLeftFrontID = 3;
    public static final int kRightRearID = 0;
    public static final int kRightFrontID = 1;
    public static final int kCurrentLimit = 40;
  }

  public static class LauncherConstants {
    public static final int kFeederID = 4;
    public static final int kLauncherID = 8;
    public static final int kLauncherCurrentLimit = 80;
    public static final int kFeedCurrentLimit = 80;
    public static final double kLauncherSpeed = -1;
    public static final double kLaunchFeederSpeed = -1;
    public static final double kIntakeLauncherSpeed = 1; 
    public static final double kIntakeFeederSpeed = 0.2;
    public static final double kLauncherDelay = 3;
  }
  public static class RollerClawConstants  {
    public static final int kRollerClawID = 6;
    public static final int kRollerClawCurrentLimit = 80;
    public static final double kRollerClawOutputPower = .5;
    public static final double kRollerClawStallPower = 0.1;
  }
}
