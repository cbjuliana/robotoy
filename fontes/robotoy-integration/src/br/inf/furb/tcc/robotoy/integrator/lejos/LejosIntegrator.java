package br.inf.furb.tcc.robotoy.integrator.lejos;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import br.inf.furb.tcc.robotoy.common.lejos.ConfigurationPropertyName;
import br.inf.furb.tcc.robotoy.common.util.Util;
import br.inf.furb.tcc.robotoy.compiler.generator.java.CodeGeneratorJava;
import br.inf.furb.tcc.robotoy.compiler.generator.java.JavaClassGenerator;
import br.inf.furb.tcc.robotoy.integrator.IRobotIntegrator;
import br.inf.furb.tcc.robotoy.robot.lejos.LejosRobot;

public class LejosIntegrator implements IRobotIntegrator {

	private static final String MESSAGE_INVALID_SENSOR_PORT = "A porta configurada para o sensor de %s é inválida: \"%s\", deveria ser 1, 2, 3 ou 4."; 
	private static final String MESSAGE_INVALID_WHEEL_PORT = "A porta configurada para a roda %s é inválida: \"%s\", deveria ser A, B ou C."; 
	private static final String MESSAGE_INVALID_MULTIUSE_MOTOR_PORT = "A porta configurada para o motor multiuso é inválida: \"%s\", deveria ser A, B ou C."; 
	
	@Override
	public void generateFile(String program, File propertiesFile, File javaFile) throws Exception {
		CodeGeneratorJava generator = new CodeGeneratorJava(program, LejosRobot.class);
		JavaClassGenerator javaClassGenerator = generator.getJavaClassGenerator();
		
		Properties properties; 
		try {
			properties = Util.loadProperties(propertiesFile);
		} catch (IOException e) {
			throw new IllegalStateException("Os sensores e motores do robô LEGO Mindstorms não foram configurados.");
		}
		
		String propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_PORT.getName());
		if (propertyValue != null) {
			javaClassGenerator.addImport("lejos.nxt.SensorPort");
			
			javaClassGenerator.indentLine();
			javaClassGenerator.appendContent(String.format("_ROBOT.setColorSensorPort(%s);", getSensorPort(propertyValue, String.format(MESSAGE_INVALID_SENSOR_PORT, "cor", propertyValue))));
			javaClassGenerator.breakLine();
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.DISTANCE_SENSOR_PORT.getName());
		if (propertyValue != null) {
			javaClassGenerator.addImport("lejos.nxt.SensorPort");

			javaClassGenerator.indentLine();
			javaClassGenerator.appendContent(String.format("_ROBOT.setUltrasonicSensorPort(%s);", getSensorPort(propertyValue, String.format(MESSAGE_INVALID_SENSOR_PORT, "distância", propertyValue))));
			javaClassGenerator.breakLine();
		}
		
		javaClassGenerator.addImport("lejos.nxt.MotorPort");

		propertyValue = properties.getProperty(ConfigurationPropertyName.MULTIUSE_MOTOR_PORT.getName());
		if (propertyValue != null) {
			javaClassGenerator.indentLine();
			javaClassGenerator.appendContent(String.format("_ROBOT.setMultiuseMotorPort(%s);", getMotorPort(propertyValue, String.format(MESSAGE_INVALID_MULTIUSE_MOTOR_PORT, propertyValue))));
			javaClassGenerator.breakLine();
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LEFT_WHEEL_PORT.getName());
		if (propertyValue != null) {
			javaClassGenerator.indentLine();
			javaClassGenerator.appendContent(String.format("_ROBOT.setLeftWheelPort(%s);", getMotorPort(propertyValue, String.format(MESSAGE_INVALID_WHEEL_PORT, "esquerda", propertyValue))));
			javaClassGenerator.breakLine();
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.RIGHT_WHEEL_PORT.getName());
		if (propertyValue != null) {
			javaClassGenerator.indentLine();
			javaClassGenerator.appendContent(String.format("_ROBOT.setRightWheelPort(%s);", getMotorPort(propertyValue, String.format(MESSAGE_INVALID_WHEEL_PORT, "direita", propertyValue))));
			javaClassGenerator.breakLine();
		}
		
		generator.generateCode(javaFile);
	}
	
	private String getSensorPort(String value, String message) {
		switch (value) {
			case "1":
				return "SensorPort.S1";
			
			case "2":
				return "SensorPort.S2";
			
			case "3":
				return "SensorPort.S3";
			
			case "4":
				return "SensorPort.S4";
			
			default:
				throw new IllegalStateException(message);
		}
	}
	
	private String getMotorPort(String value, String message) {
		switch (value) {
			case "A":
				return "MotorPort.A";
			
			case "B":
				return "MotorPort.B";
			
			case "C":
				return "MotorPort.C";
				
			default:
				throw new IllegalStateException(message);
		}
	}
	
}
