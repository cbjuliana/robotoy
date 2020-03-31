import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program027 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double number1;
	private static double number2;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	number1 = (double) 25;
        	number2 = (double) -(number1);
			number2 = (double) +(-number2 * 3) - -(+number2 * 3);
			number1 = (double) number1 + number2;
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}