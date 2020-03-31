import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program032 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double valor;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	valor = (double) 5;
			_ROBOT.walkForward(Math.abs(valor));
			_ROBOT.walkForward(Math.abs(-valor));
			_ROBOT.walkForward(Math.abs(+valor));
			_ROBOT.walkForward(Math.abs((valor) * 2));
			_ROBOT.walkForward(Math.abs(valor + valor));
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}