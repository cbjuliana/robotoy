import br.inf.furb.tcc.robotoy.generic.robot.IRobot;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program088 {

	private static Timer _end;
	private static final IRobot _ROBOT = new RobotMock();

	private static double tempo = (double) -5;

	public static void main(String[] args) {
		try {
			_end = LocalDateTime.now().plusMillis(Math.abs((int) (tempo * 1000)));
			while ((_start = LocalDateTime.now()).isBefore(_end)) {
			}
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}