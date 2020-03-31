import java.lang.Double;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program020 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double valor;
	private static String adi��o;
	private static String subtra��o;
	private static String divis�o;
	private static String multiplica��o;
	private static String restoDaDivis�o;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	valor = (double) -5;
        	adi��o = "Resultado: " + Double.toString(5 + valor);
        	subtra��o = "Resultado: " + Double.toString(5 - valor);
        	divis�o = "Resultado: " + Double.toString(5 / valor);
        	multiplica��o = "Resultado: " + Double.toString(5 * valor);
        	restoDaDivis�o = "Resultado: " + Double.toString(5 % valor);
        	_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}