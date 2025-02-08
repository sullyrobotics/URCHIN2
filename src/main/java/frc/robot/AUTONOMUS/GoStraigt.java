package frc.robot.AUTONOMUS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class GoStraigt extends Command {
    private Drive m_drive;
    private Timer timer;
    private double drive_seconds = 3.25;

      
    public GoStraigt(Drive drive)
    {
        m_drive = drive;
        
        timer = new Timer();

        addRequirements(m_drive);
    }

    @Override
  public void initialize() {
    
    timer.restart();
  }

  @Override
  public void execute() {
    // drive at 30% speed
    if(timer.get() < drive_seconds)
    {
        m_drive.driveArcade(0.3, 0.0,false);
    }
  }

  
  @Override
  public void end(boolean isInterrupted) {
    // stop drive motors
    m_drive.driveArcade(0.0, 0.0, false);
    timer.stop();
  }

  
  @Override
  public boolean isFinished() {
    return timer.get() >= drive_seconds;
  }
}