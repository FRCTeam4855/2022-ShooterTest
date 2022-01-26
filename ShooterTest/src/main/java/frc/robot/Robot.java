// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {
  private Joystick controller;
  private static final int deviceID = 5;
  private CANSparkMax m_motor;
  private Spark n_motor;
  private double leftSpeedLimit = .25;
  private double rightSpeedLimit = .57;
 private double deadZone = .02;

  public String kEnable;
  public String kDisable;

  @Override
  public void robotInit() {
    
    m_motor = new CANSparkMax(deviceID, MotorType.kBrushless);
    n_motor = new Spark(1);

    controller = new Joystick(0); //Left Joystick fore/aft

  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Spark_Max", m_motor.get());
    SmartDashboard.putNumber("Spark", n_motor.get());
  }

  @Override
  public void autonomousInit() {
    
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    if (Math.abs(controller.getRawAxis(1)) < deadZone) m_motor.set(0); //Sets the dead zone so that the motor stops
    if (Math.abs(controller.getRawAxis(5)) < deadZone) n_motor.set(0);
    m_motor.set(controller.getRawAxis(1)*leftSpeedLimit); //Set SparkMax to left joystick input
    n_motor.set(controller.getRawAxis(5)*rightSpeedLimit);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
