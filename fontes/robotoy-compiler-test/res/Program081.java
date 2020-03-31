import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program081 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double valor;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	valor = (double) 10;
			while (valor < 20 && valor <= 20 || valor > -5 && valor >= -15.5 && _ROBOT.hasObstacle()) {
			}
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}