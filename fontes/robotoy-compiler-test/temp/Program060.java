import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program060 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static String text;

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			text = "Olá";
			_ROBOT.write(text);
			_ROBOT.write(text + " mundo!");
			text = "mundo!";
			_ROBOT.write("Olá " + text);
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}