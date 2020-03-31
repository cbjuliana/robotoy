package br.inf.furb.tcc.robotoy.integrator.arduino;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import br.furb.tcc.robotoy.common.arduino.ConfigurationPropertyName;
import br.inf.furb.tcc.robotoy.common.util.Util;
import br.inf.furb.tcc.robotoy.compiler.generator.java.ArduinoClassGenerator;
import br.inf.furb.tcc.robotoy.compiler.generator.java.CodeGeneratorArduino;
import br.inf.furb.tcc.robotoy.compiler.generator.java.JavaClassGenerator;
import br.inf.furb.tcc.robotoy.integrator.IRobotIntegrator;
import br.inf.furb.tcc.robotoy.robot.lejos.LejosRobot;

public class ArduinoIntegrator implements IRobotIntegrator {

	private static final String MESSAGE_INVALID_SENSOR_PORT = "A porta configurada para o sensor de %s é inválida: \"%s\", deveria ser 1, 2, 3 ou 4."; 
	private static final String MESSAGE_INVALID_WHEEL_PORT = "A porta configurada para a roda %s é inválida: \"%s\", deveria ser A, B ou C."; 
	private static final String MESSAGE_INVALID_MULTIUSE_MOTOR_PORT = "A porta configurada para o motor multiuso é inválida: \"%s\", deveria ser A, B ou C."; 
	
	@Override
	public void generateFile(String program, File propertiesFile, File file) throws Exception {
		CodeGeneratorArduino generator = new CodeGeneratorArduino(program);
		ArduinoClassGenerator arduinoClassGenerator = generator.getArduinoClassGenerator();
		
		Properties properties; 
		try {
			properties = Util.loadProperties(propertiesFile);
		} catch (IOException e) {
			throw new IllegalStateException("Os sensores e motores do robô Arduino não foram configurados.");
		}
		
		String propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S0_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("const int _s0 = %s;", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("const int _s0 = 0;");
		}	
		arduinoClassGenerator.addSetup("pinMode(_s0, OUTPUT);");
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S1_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("const int _s1 = %s;", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("const int _s1 = 0;");
		}
		arduinoClassGenerator.addSetup("pinMode(_s1, OUTPUT);");
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S2_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("const int _s2 = %s;", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("const int _s2 = 0;");
		}	
		arduinoClassGenerator.addSetup("pinMode(_s2, OUTPUT);");
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S3_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("const int _s3 = %s;", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("const int _s3 = 0;");
		}	
		arduinoClassGenerator.addSetup("pinMode(_s3, OUTPUT);");
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_OUT_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("const int _out = %s;", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("const int _out = 0;");
		}		
		arduinoClassGenerator.addGlobalVariable("const String vermelho = \"vermelho\";");
		arduinoClassGenerator.addGlobalVariable("const String verde = \"verde\";");
		arduinoClassGenerator.addGlobalVariable("const String azul = \"azul\";");
		arduinoClassGenerator.addGlobalVariable("const String amarelo = \"amarelo\";");
		arduinoClassGenerator.addGlobalVariable("const String branco = \"branco\";");
		arduinoClassGenerator.addGlobalVariable("const String preto = \"preto\";");
		
		arduinoClassGenerator.addSetup("pinMode(_out, INPUT);");
		
		if (properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S0_PORT.getName()) != null
				&& properties.getProperty(ConfigurationPropertyName.COLOR_SENSOR_S1_PORT.getName()) != null) {
			arduinoClassGenerator.addSetup("digitalWrite(_s0, HIGH);");
			arduinoClassGenerator.addSetup("digitalWrite(_s1, HIGH);");
		}
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.DISTANCE_SENSOR_TRIGGER_PORT.getName());
		if (propertyValue != null) {
			arduinoClassGenerator.addGlobalVariable(String.format("#define _HC_SR04_TRIGGER %s", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("#define _HC_SR04_TRIGGER A2");
		}
		arduinoClassGenerator.addImport("Ultrasonic.h");
		
		arduinoClassGenerator.addSetup("pinMode(_HC_SR04_TRIGGER, OUTPUT);");
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.DISTANCE_SENSOR_ECHO_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("#define _HC_SR04_ECHO %s", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("#define _HC_SR04_ECHO A3");
		}
		arduinoClassGenerator.addImport("Ultrasonic.h");
		
		arduinoClassGenerator.addSetup("pinMode(_HC_SR04_ECHO, INPUT);");
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.BUZZER_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("#define _BUZZER %s", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("#define _BUZZER A0");
		}
		arduinoClassGenerator.addSetup("pinMode(_BUZZER, OUTPUT);");		

		propertyValue = properties.getProperty(ConfigurationPropertyName.MULTIUSE_MOTOR_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addSetup(String.format("_motorMultiuso.attach(%s);", propertyValue));	
		} else {
			arduinoClassGenerator.addSetup("_motorMultiuso.attach(10);");
		}
		arduinoClassGenerator.addImport("Servo.h");
		
		arduinoClassGenerator.addGlobalVariable("Servo _motorMultiuso;");
		
		arduinoClassGenerator.addGlobalVariable("int _anguloSensor = 90;");
		
		arduinoClassGenerator.addSetup("_motorMultiuso.write(_anguloSensor);");		
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.LEFT_WHEEL_PORT.getName());
		if (propertyValue != null) {
			arduinoClassGenerator.addGlobalVariable(String.format("AF_DCMotor _motorEsquerdo(%s);", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("AF_DCMotor _motorEsquerdo(4);");
		}
		arduinoClassGenerator.addImport("AFMotor.h");
		
		propertyValue = properties.getProperty(ConfigurationPropertyName.RIGHT_WHEEL_PORT.getName());
		if (propertyValue != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("AF_DCMotor _motorDireito(%s);", propertyValue));
		} else {
			arduinoClassGenerator.addGlobalVariable("AF_DCMotor _motorDireito(1);");
		}
		arduinoClassGenerator.addImport("AFMotor.h");
		
		if (properties.getProperty(ConfigurationPropertyName.LEFT_WHEEL_PORT.getName()) != null
				&& properties.getProperty(ConfigurationPropertyName.RIGHT_WHEEL_PORT.getName()) != null) {
			arduinoClassGenerator.addSetup("_setarVelocidadeDeslocamento();");
		}
		
		String propertyValuePin1 = properties.getProperty(ConfigurationPropertyName.LCD_PIN1_PORT.getName());
		String propertyValuePin2 = properties.getProperty(ConfigurationPropertyName.LCD_PIN2_PORT.getName());
		String propertyValuePin3 = properties.getProperty(ConfigurationPropertyName.LCD_PIN3_PORT.getName());
		String propertyValuePin4 = properties.getProperty(ConfigurationPropertyName.LCD_PIN4_PORT.getName());
		String propertyValuePin5 = properties.getProperty(ConfigurationPropertyName.LCD_PIN5_PORT.getName());
		String propertyValuePin6 = properties.getProperty(ConfigurationPropertyName.LCD_PIN6_PORT.getName());
		
		if (propertyValuePin1 != null
				&& propertyValuePin2 != null
				&& propertyValuePin3 != null
				&& propertyValuePin4 != null
				&& propertyValuePin5 != null
				&& propertyValuePin6 != null) {			
			arduinoClassGenerator.addGlobalVariable(String.format("LiquidCrystal _lcd(%s, %s, %s, %s, %s, %s);", 
					propertyValuePin1, propertyValuePin2, propertyValuePin3, propertyValuePin4, propertyValuePin5, propertyValuePin6));
		} else {
			arduinoClassGenerator.addGlobalVariable("LiquidCrystal _lcd(43, 44, 45, 42, 46, 41);");
		}
		arduinoClassGenerator.addImport("LiquidCrystal.h");
		
		arduinoClassGenerator.addSetup("_lcd.begin(16, 2);");
		
		arduinoClassGenerator.addGlobalVariable("boolean _ligado = true;");
		
		generator.generateCode(file);
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
