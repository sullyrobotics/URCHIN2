package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.RollerConstants;
import frc.robot.subsystems.Roller;

public class AlgieINCom extends Command{
    private final Roller m_roller;

    public AlgieINCom(Roller roller) {
        m_roller = roller;
        addRequirements(roller);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_roller.runRoller(RollerConstants.INAGL);
    }

    @Override
    public void end(boolean interrupted) {
        m_roller.runRoller(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
