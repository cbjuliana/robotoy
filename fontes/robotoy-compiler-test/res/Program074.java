import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program074 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
			while (!_ROBOT.hasObstacle()) {
				_ROBOT.walkForward();
			}
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}