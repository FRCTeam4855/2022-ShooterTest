// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;


public class Robot extends TimedRobot {


  private Joystick controller;
  private static final int deviceID = 5;
  private CANSparkMax m_motor;
  private Spark n_motor;
  private double deadZone = .02; 
  DoubleSolenoid exampleDouble = new DoubleSolenoid(1, PneumaticsModuleType.REVPH, 0, 1);
  Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
  boolean compenabled = phCompressor.enabled();
  boolean pressureSwitch = phCompressor.getPressureSwitchValue();

  
  @Override
  public void robotInit() {
    SmartDashboard.putNumber("Spark Max Limit 0-1", .0);
    SmartDashboard.putNumber("Spark Limit 0-1", .0);
    
    m_motor = new CANSparkMax(deviceID, MotorType.kBrushless);

    n_motor = new Spark(1);


    controller = new Joystick(0); //Left Joystick fore/aft

    phCompressor.enableDigital();

}

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Spark_Max Current Speed", m_motor.get());
    SmartDashboard.putNumber("Spark Current Speed", n_motor.get());


      if (controller.getRawButtonPressed(3)) { 
      exampleDouble.set(kForward);
    }
      if (controller.getRawButtonPressed(4)) { 
      exampleDouble.set(kReverse);
    }
    SmartDashboard.putBoolean("Compressor on?", compenabled);
    SmartDashboard.putBoolean("Compressor switch?",  pressureSwitch);

    //SmartDashboard.setDefaultNumber("Motor M Speed", leftSpeedLimit);
    //leftSpeedLimit = SmartDashboard.getDefaultNumber("Motor M", leftSpeedLimit);
    //playType = SmartDashboard.getString("Match Mode", "MATCH");
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
    double motorSparkLimit = SmartDashboard.getNumber("Spark Limit 0-1", .1);
    double motorSparkMaxLimit = SmartDashboard.getNumber("Spark Max Limit 0-1", .1);
    if (Math.abs(controller.getRawAxis(1)) < deadZone) m_motor.set(0); //Sets the dead zone so that the motor stops
    if (Math.abs(controller.getRawAxis(5)) < deadZone) n_motor.set(0);
    m_motor.set(controller.getRawAxis(1)*motorSparkMaxLimit); //Set SparkMax to left joystick input
    n_motor.set(controller.getRawAxis(5)*motorSparkLimit);
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

