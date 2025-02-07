package frc.robot.AUTONOMUS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.RollerConstants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Roller;

public class CoralOut extends Command {
    private Drive m_drive;
    private Roller m_roller;
    private Timer timer;
    private double drive_seconds = 3.25;
    private double exjest_seconds = 4.5;

    public CoralOut(Drive drive, Roller roller)
    {
        m_drive = drive;
        m_roller = roller;
               
        timer = new Timer();

        addRequirements(m_drive);
        addRequirements(m_roller);
        
    }

    @Override
  public void initialize() {
    
    timer.restart();
  }

  @Override
  public void execute() {
      
    if(timer.get() < drive_seconds)
    {
        m_drive.driveArcade(0.3, 0.0,false);
    }
    
    else if(timer.get() > drive_seconds && timer.get() < exjest_seconds)
    {
        m_drive.driveArcade(0.0, 0.0,false);
        m_roller.runRoller(RollerConstants.OUTCRL);
    }
  }

  
  @Override
  public void end(boolean isInterrupted) {
    // stop drive motors
    m_drive.driveArcade(0.0, 0.0, false);
    m_roller.runRoller(0);
    timer.stop();
  }
  
  @Override
  public boolean isFinished() {
    // check if timer exceeds seconds, when it has this will return true indicating
    // this command is finished
    return timer.get() >= exjest_seconds;
  }
}