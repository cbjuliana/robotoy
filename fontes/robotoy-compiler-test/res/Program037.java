import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program037 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
			_ROBOT.stopWalking();
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}