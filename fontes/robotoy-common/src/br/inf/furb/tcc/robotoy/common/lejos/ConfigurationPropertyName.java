package br.inf.furb.tcc.robotoy.common.lejos;

public enum ConfigurationPropertyName {
	
	COLOR_SENSOR_PORT("color.sensor.port"),
	
	DISTANCE_SENSOR_PORT("distance.sensor.port"),
	
	LEFT_WHEEL_PORT("left.wheel.port"),
	
	RIGHT_WHEEL_PORT("right.wheel.port"),

	MULTIUSE_MOTOR_PORT("multiuse.motor.port");
	
	private final String name;
	
	private ConfigurationPropertyName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
