import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program030 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
			_ROBOT.walkForward(Math.abs(10.5));
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}