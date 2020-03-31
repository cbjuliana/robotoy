import br.inf.furb.tcc.robotoy.generic.robot.IRobot;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program089 {

	private static Timer _end;
	private static Timer _end;
	private static final IRobot _ROBOT = new RobotMock();

	public static void main(String[] args) {
		try {
			_end = LocalDateTime.now().plusMillis(Math.abs((int) (1 * 1000)));
			while ((_start = LocalDateTime.now()).isBefore(_end)) {
			}
			_end = LocalDateTime.now().plusMillis(Math.abs((int) (5 * 1000)));
			while ((_start = LocalDateTime.now()).isBefore(_end)) {
			}
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}