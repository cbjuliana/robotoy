package br.furb.tcc.robotoy.common.arduino;

public enum ConfigurationPropertyName {
	
	DIRECTORY_ARDUINO_IDE("directory.arduino.ide"),
	
	MODEL_ARDUINO_BOARD("model.arduino.board"),
	
	PORT_ARDUINO_BOARD("port.arduino.board"),
	
	COLOR_SENSOR_S0_PORT("color.sensor.s0.port"),
	
	COLOR_SENSOR_S1_PORT("color.sensor.s1.port"),
	
	COLOR_SENSOR_S2_PORT("color.sensor.s2.port"),
	
	COLOR_SENSOR_S3_PORT("color.sensor.s3.port"),
	
	COLOR_SENSOR_OUT_PORT("color.sensor.out.port"),
	
	DISTANCE_SENSOR_TRIGGER_PORT("distance.sensor.trigger.port"),
	
	DISTANCE_SENSOR_ECHO_PORT("distance.sensor.echo.port"),
	
	BUZZER_PORT("buzzer.port"),
	
	LCD_PIN1_PORT("lcd.pin1.port"),
	
	LCD_PIN2_PORT("lcd.pin2.port"),
	
	LCD_PIN3_PORT("lcd.pin3.port"),
	
	LCD_PIN4_PORT("lcd.pin4.port"),
	
	LCD_PIN5_PORT("lcd.pin5.port"),
	
	LCD_PIN6_PORT("lcd.pin6.port"),
	
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
