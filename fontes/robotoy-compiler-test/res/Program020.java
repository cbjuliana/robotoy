import java.lang.Double;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program020 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double valor;
	private static String adição;
	private static String subtração;
	private static String divisão;
	private static String multiplicação;
	private static String restoDaDivisão;

	public static void main(String[] args) {
		try {
        	_ROBOT.onStart();
        	valor = (double) -5;
        	adição = "Resultado: " + Double.toString(5 + valor);
        	subtração = "Resultado: " + Double.toString(5 - valor);
        	divisão = "Resultado: " + Double.toString(5 / valor);
        	multiplicação = "Resultado: " + Double.toString(5 * valor);
        	restoDaDivisão = "Resultado: " + Double.toString(5 % valor);
        	_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}