import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program014 {

	private static final RobotMock _ROBOT = new RobotMock();

    private static ColorDefinition color1;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	color1 = _ROBOT.detectColor();
        	_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}