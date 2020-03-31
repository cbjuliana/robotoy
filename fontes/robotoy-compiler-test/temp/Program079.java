import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program079 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double valor;

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			valor = (double) 10 / 2;
			while (10 / 2 == valor || valor != 5) {
			}
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}