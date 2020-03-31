import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;
import br.inf.furb.tcc.robotoy.robot.generic.Color;
import java.lang.Double;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public final class Program019 {

	private static final RobotMock _ROBOT = new RobotMock();

	private static double qtdPassos;
	private static ColorDefinition color;
	private static String text;

	public static void main(String[] args) {
		try {
			_ROBOT.onStart();
			qtdPassos = (double) 10;
			color = new ColorDefinition("azul", Color.BLUE);
			text = "Qtd. de passos: " + Double.toString(qtdPassos) + " na cor " + color.getName() + ".";
			text = Double.toString(qtdPassos) + "/" + color.getName();
			_ROBOT.onEnd();
		} catch (Exception e) {
			_ROBOT.manageException(e);
		}
	}

}