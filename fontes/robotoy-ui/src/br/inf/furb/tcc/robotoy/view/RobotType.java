package br.inf.furb.tcc.robotoy.view;

public enum RobotType {

	LEGO_MINDSTORMS("lego mindstorms"),
	ARDUINO_MEGA("arduino");

	private final String type;

	private RobotType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static RobotType getRobotByType(String type) {
		RobotType[] types = RobotType.values();
		for (RobotType robotType : types) {
			if (robotType.getType().equals(type)) {
				return robotType;
			}
		}
		return null;
	}
	
}
