package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.CLimb;

public class ClimbDOWNCom extends Command{
    private final CLimb m_climber;

    public ClimbDOWNCom(CLimb climber) {
        m_climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_climber.runClimber(ClimberConstants.CLIMBER_SPEED_DOWN);
    }

    @Override
    public void end(boolean interrupted) {
        m_climber.runClimber(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
