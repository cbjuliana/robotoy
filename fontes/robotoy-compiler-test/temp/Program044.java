import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program044 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			_ROBOT.turnRight(Math.abs((5 + 1 * (-5 - -4))));
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}