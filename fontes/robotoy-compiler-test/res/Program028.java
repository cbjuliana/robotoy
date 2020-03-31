import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program028 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double NuMbEr;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	NuMbEr = (double) 10;
			NuMbEr = (double) 125.00;
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}