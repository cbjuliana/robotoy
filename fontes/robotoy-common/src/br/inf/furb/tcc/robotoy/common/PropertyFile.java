package br.inf.furb.tcc.robotoy.common;

import java.nio.file.Paths;

public class PropertyFile {

	private static final String ROBOTOY_FOLDER = Paths.get(System.getProperty("user.dir"), "..").toString();

	public static final String GEN_FOLDER = Paths.get(ROBOTOY_FOLDER, "gen").toString();;

	public static final String LIB_FOLDER = Paths.get(ROBOTOY_FOLDER, "lib").toString();;

	public static final String LEJOS_ROBOT_CONFIGURATION_FILE = Paths.get(GEN_FOLDER, "lejos-robot.properties").toString();
	
	public static final String ARDUINO_MEGA_ROBOT_CONFIGURATION_FILE = Paths.get(GEN_FOLDER, "arduino-mega-robot.properties").toString();

	public static final String ROBOT_CONFIGURATION_FILE = Paths.get(GEN_FOLDER, "robot.properties").toString();

	private PropertyFile() {
		// Classe não instanciável.
	}

}
