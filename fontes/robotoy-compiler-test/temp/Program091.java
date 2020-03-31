import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program091 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			andarParaFrente();
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

	private static void andarParaFrente() throws Exception {
		_ROBOT.walkForward(Math.abs(2));
	}

}