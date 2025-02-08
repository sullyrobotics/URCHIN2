package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class CLimb extends SubsystemBase {

    private final SparkMax climbMotor;

   
    public CLimb () {
        
    climbMotor = new SparkMax(ClimberConstants.M_CL, MotorType.kBrushless);

    climbMotor.setCANTimeout(250);

    
    SparkMaxConfig climbConfig = new SparkMaxConfig();
    climbConfig.voltageCompensation(ClimberConstants.ROB_Vol);
    climbConfig.smartCurrentLimit(ClimberConstants.CurrentLimit);
    climbConfig.idleMode(IdleMode.kBrake);
    climbMotor.configure(climbConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void periodic() {
    }

    public void runClimber(double speed){
        climbMotor.set(speed);
    }

}