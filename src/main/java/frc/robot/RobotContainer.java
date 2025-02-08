package frc.robot;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.AUTONOMUS.GoStraigt;
import frc.robot.AUTONOMUS.CoralOut;

import frc.robot.commands.ClimbDOWNCom;
import frc.robot.commands.ClimbUPCom;
import frc.robot.commands.CoralOutCom;
import frc.robot.commands.CoralStackCom;
import frc.robot.commands.DriveCom;
import frc.robot.commands.AlgieOUTCom;
import frc.robot.commands.ArmDOWNCom;
import frc.robot.commands.AlgieINCom;

import frc.robot.commands.ArmUPCom;
import frc.robot.subsystems.ARM;
import frc.robot.subsystems.CLimb;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Roller;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.DRIVECON);
  
  private final CommandXboxController m_operatorController = 
      new CommandXboxController(OperatorConstants.OPCON);

  // The autonomous chooser
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public final Roller m_roller = new Roller();
  public final Drive m_drive = new Drive();
  public final CLimb m_climber = new CLimb();
  public final ARM m_arm = new ARM();

  public final CoralOut m_simpleCoralAuto = new CoralOut(m_drive, m_roller);
  public final GoStraigt m_driveForwardAuto = new GoStraigt(m_drive);

  public RobotContainer() {
    // Set up command bindings
    configureBindings();
    m_chooser.setDefaultOption("Coral Auto", m_simpleCoralAuto);
    m_chooser.addOption("Drive Forward Auto", m_driveForwardAuto);
    SmartDashboard.putData(m_chooser);
  }


  private void configureBindings() {

    m_drive.setDefaultCommand(new DriveCom(m_drive,
        () -> -m_driverController.getLeftY(),
        () -> -m_driverController.getRightX(),
        () -> true));

    /
    m_driverController.leftBumper().whileTrue(new DriveCom(m_drive, 
        () -> -m_driverController.getLeftY() * DriveConstants.SLOW_MODE_MOVE,  
        () -> -m_driverController.getRightX() * DriveConstants.SLOW_MODE_TURN,
        () -> true));

    m_operatorController.rightBumper().whileTrue(new AlgieINCom(m_roller));
    m_operatorController.rightTrigger(.2).whileTrue(new AlgieOUTCom(m_roller));

    m_operatorController.leftBumper().whileTrue(new ArmUPCom(m_arm));
    m_operatorController.leftTrigger(.2).whileTrue(new ArmDOWNCom(m_arm));

    m_operatorController.x().whileTrue(new CoralOutCom(m_roller));
    m_operatorController.y().whileTrue(new CoralStackCom(m_roller));

    m_operatorController.pov(0).whileTrue(new ClimbUPCom(m_climber));
    m_operatorController.pov(180).whileTrue(new ClimbDOWNCom(m_climber));
  }
    public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
}
