import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program024 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double number;

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			number = (double) 1 + 2 - (3 * (4 / (5 % 6)));
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}