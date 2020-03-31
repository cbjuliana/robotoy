import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program048 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			_ROBOT.turnMultiuseMotorLeft(Math.abs(1 * 2));
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}