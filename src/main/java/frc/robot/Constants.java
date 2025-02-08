
package frc.robot;

public final class Constants {
  public static final class DriveConstants {
    public static final int M_L1 = 1;
    public static final int M_L2 = 2;
    public static final int M_R1 = 3;
    public static final int M_R2 = 4;

    public static final int CurrentLimit = 50;
    public static final double ROB_Vol = 12;
    public static final double SLOW_MODE_MOVE = 0.5;
    public static final double SLOW_MODE_TURN = 0.6;
  }

  public static final class RollerConstants {
    public static final int M_RL = 5;
    public static final int CurrentLimit = 50;
    public static final double ROB_Vol = 12;
    public static final double OUTCRL = -.4;
    public static final double INCRL = -0.8;
    public static final double INAGL = -0.8;
    public static final double OUTAGL = 0.4;
  }

  
  
  public static final class ArmConstants {
    public static final int M_ARM = 6;
    public static final int CurrentLimit = 60;
    public static final double ROB_Vol = 10;
    public static final double ARM_SPEED_DOWN = 0.4;
    public static final double ARM_SPEED_UP = -0.4;
    public static final double ARM_HOLD_DOWN = 0.1;
    public static final double ARM_HOLD_UP = -0.15;
  }

  
  public static final class ClimberConstants {
    public static final int M_CL = 7;
    public static final int CurrentLimit = 50;
    public static final double ROB_Vol = 12;
    public static final double CLIMBER_SPEED_DOWN = -0.5;
    public static final double CLIMBER_SPEED_UP = 0.5;
  }

  public static final class OperatorConstants {
    public static final int DRIVECON = 0;
    public static final int OPCON = 1;
  }
}