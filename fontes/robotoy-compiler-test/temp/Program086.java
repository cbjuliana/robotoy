import br.inf.furb.tcc.robotoy.generic.robot.IRobot;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program086 {

	private static Timer _end;
	private static final IRobot _ROBOT = new RobotMock();

	private static double tempo = (double) 2;

	public static void main(String[] args) {
		try {
			_end = LocalDateTime.now().plusMillis(Math.abs((int) (tempo * 60000)));
			while ((_start = LocalDateTime.now()).isBefore(_end)) {
				_ROBOT.walkForward(Math.abs(1));
				_ROBOT.turnLeft();
			}
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}