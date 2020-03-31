import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;
import br.inf.furb.tcc.robotoy.robot.generic.Color;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program015 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static ColorDefinition color1;
	private static ColorDefinition Color1;

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			color1 = new ColorDefinition("verde", Color.GREEN);
			Color1 = color1;
			color1 = _ROBOT.detectColor();
			Color1 = color1;
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}