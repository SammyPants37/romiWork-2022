package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.RomiDrivetrain;

public class turnToAnAngle extends CommandBase{
    
    private double angle;
    private RomiDrivetrain drive;

    public turnToAnAngle(double turnAngle, RomiDrivetrain drivetrain) {
        angle = turnAngle;
        drive = drivetrain;
        addRequirements(drive);
    }

    public void execute() {
        drive.arcadeDrive(0.0, 0.7);
    }

    public boolean isFinished() {
        double inchPerDegree = Math.PI * 5.551 / 360;
        // Compare distance travelled from start to distance based on degree turn
        return getAverageTurningDistance() >= (inchPerDegree * angle);
    }

    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }

    private double getAverageTurningDistance() {
        double leftDistance = Math.abs(drive.getLeftDistanceInch());
        double rightDistance = Math.abs(drive.getRightDistanceInch());
        return (leftDistance + rightDistance) / 2.0;
    }
}
