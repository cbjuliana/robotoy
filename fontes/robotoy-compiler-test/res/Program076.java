import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program076 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static String fruta;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	fruta = "p�ra";
			while (!fruta.equals("ma��") && "ma��".equals(fruta)) {
			}
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}