import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program018 {

	private static final RobotMock _ROBOT = new RobotMock();

    private static String text;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	text = "Cor: " + "verde";
        	_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}