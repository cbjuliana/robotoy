import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program041 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double valor;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	valor = (double) 0.50;
			_ROBOT.turnLeft(Math.abs(valor));
			_ROBOT.turnLeft(Math.abs(-valor));
			_ROBOT.turnLeft(Math.abs(+valor));
			_ROBOT.turnLeft(Math.abs((valor) * 2));
			_ROBOT.turnLeft(Math.abs(valor + valor));
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}