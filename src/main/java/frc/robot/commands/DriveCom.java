package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

// Command to drive the robot with joystick inputs
public class DriveCom extends Command {
  private final DoubleSupplier m_xSpeed;
  private final DoubleSupplier m_zRotation;
  private final Drive m_drive;
  private final BooleanSupplier m_squared;

  
  public DriveCom(Drive driveSubsystem, 
      DoubleSupplier xSpeed, DoubleSupplier zRotation, BooleanSupplier squareInputs) {
    // Save parameters to local variables for use later
    m_xSpeed = xSpeed;
    m_zRotation = zRotation;
    m_drive = driveSubsystem;
    m_squared = squareInputs;

   
    addRequirements(m_drive);
  }

  
  @Override
  public void initialize() {
  }

  
  @Override
  public void execute() {
    m_drive.driveArcade(m_xSpeed.getAsDouble(), m_zRotation.getAsDouble(), m_squared.getAsBoolean());
  }

  @Override
  public void end(boolean isInterrupted) {
  }

  
  @Override
  public boolean isFinished() {
  
    return false;
  }
}