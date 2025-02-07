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
    // start timer, uses restart to clear the timer as well in case this command has
    // already been run before
    timer.restart();
  }

  // Runs every cycle while the command is scheduled (~50 times per second), here we will just drive forwards
  @Override
  public void execute() {
    // drive at 30% speed
    if(timer.get() < drive_seconds)
    {
        m_drive.driveArcade(0.3, 0.0,false);
    }
  }

  // Runs each time the command ends via isFinished or being interrupted.
  @Override
  public void end(boolean isInterrupted) {
    // stop drive motors
    m_drive.driveArcade(0.0, 0.0, false);
    timer.stop();
  }

  // Runs every cycle while the command is scheduled to check if the command is
  // finished
  @Override
  public boolean isFinished() {
    // check if timer exceeds seconds, when it has this will return true indicating
    // this command is finished
    return timer.get() >= drive_seconds;
  }
}