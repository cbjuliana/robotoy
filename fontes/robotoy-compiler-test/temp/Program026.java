import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program026 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double number;

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			number = (double) +20;
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}