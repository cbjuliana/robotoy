import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program022 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static String TeXt;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	TeXt = "TeXtO QuAlQuEr";
        	_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}