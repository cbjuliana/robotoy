import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;
import br.inf.furb.tcc.robotoy.robot.generic.Color;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program010 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static ColorDefinition color;

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			color = new ColorDefinition("amarela", Color.YELLOW);
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}