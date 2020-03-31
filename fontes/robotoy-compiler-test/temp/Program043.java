import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program043 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			_ROBOT.turnRight(Math.abs(52.7256));
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}