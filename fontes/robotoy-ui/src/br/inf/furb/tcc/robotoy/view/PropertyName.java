package br.inf.furb.tcc.robotoy.view;

public enum PropertyName {
	
	ROBOT_TYPE("robot.type"),

	ROBOT_INTEGRATOR("robot.integrator"),
	
	ROBOT_CONFIGURATION_FILE("robot.configuration.file");
	
	private final String name;
	
	private PropertyName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
