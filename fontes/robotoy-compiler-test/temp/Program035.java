import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program035 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			_ROBOT.walkBackward(Math.abs(5 + (10 / 5) * 100 - (-10 % (5))));
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}