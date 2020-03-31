import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program021 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static String text;

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			text = "A cor atual é: " + _ROBOT.detectColor().getName();
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}