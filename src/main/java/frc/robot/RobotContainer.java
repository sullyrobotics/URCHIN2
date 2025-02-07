package frc.robot;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.AUTONOMUS.GoStraigt;
import frc.robot.AUTONOMUS.CoralOut;
import frc.robot.commands.ClimberDownCommand;
import frc.robot.commands.ClimberUpCommand;
import frc.robot.commands.CoralOutCommand;
import frc.robot.commands.CoralStackCommand;
import frc.robot.commands.DriveCom;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CLimb;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Roller;
import frc.robot.subsystems.RollerSubsystem;
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

  public final CoralOut m_simpleCoralAuto = new CoralOut(m_drive, m_roller);
  public final GoStraigt m_driveForwardAuto = new GoStraigt(m_drive);

  public RobotContainer() {
    
    configureBindings();
    m_chooser.setDefaultOption("Coral Auto", m_simpleCoralAuto);
    m_chooser.addOption("Drive Forward Auto", m_driveForwardAuto);
    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    m_drive.setDefaultCommand(new DriveCom(m_drive,
        () -> -m_driverController.getLeftY(),
        () -> -m_driverController.getRightX(),
        () -> true));

    
    m_driverController.leftBumper().whileTrue(new DriveCom(m_drive, 
        () -> -m_driverController.getLeftY() * DriveConstants.SLOW_MODE_MOVE,  
        () -> -m_driverController.getRightX() * DriveConstants.SLOW_MODE_TURN,
        () -> true));

    
    m_operatorController.rightBumper().whileTrue(new AlgieInCommand(m_roller));
    
    
    m_operatorController.rightTrigger(.2).whileTrue(new AlgieOutCommand(m_roller));


    m_operatorController.leftBumper().whileTrue(new ArmUpCommand(m_arm));
    m_operatorController.leftTrigger(.2).whileTrue(new ArmDownCommand(m_arm));

    
    m_operatorController.x().whileTrue(new CoralOutCommand(m_roller));
    m_operatorController.y().whileTrue(new CoralStackCommand(m_roller));

    
    m_operatorController.pov(0).whileTrue(new ClimberUpCommand(m_climber));
    m_operatorController.pov(180).whileTrue(new ClimberDownCommand(m_climber));
  }

    public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
}