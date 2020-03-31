import br.inf.furb.tcc.robotoy.robot.generic.Color;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program071 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static String fruta;
	private static double qtd;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	fruta = "maçã verde";
        	qtd = (double) 10;
			if (_ROBOT.detectColor().getColor() == Color.GREEN && !fruta.equals("pêra") && qtd <= 10) {
			}
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}