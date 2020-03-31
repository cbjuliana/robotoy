import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program092 {

	private static final RobotMock _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			Rotina1();
			Rotina2();
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

	private static void Rotina1() throws Exception {
		_ROBOT.write("Rotina 1");
	}

	private static void Rotina2() throws Exception {
		_ROBOT.write("Rotina 2");
	}

}