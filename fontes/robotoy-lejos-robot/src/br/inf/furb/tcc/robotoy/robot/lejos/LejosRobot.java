package br.inf.furb.tcc.robotoy.robot.lejos;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import br.inf.furb.tcc.robotoy.robot.generic.Color;
import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;
import br.inf.furb.tcc.robotoy.robot.generic.IRobot;

/**
 * Representação de um robô montado com o <i>kit</i> <b>LEGO Mindstorms NXT
 * 2.0</b>. Essa representação determina a estrutura mínima que um robô deve
 * possuir para ser programável na linguagem <b>Robotoy</b>:
 * 
 * <ul>
 * <li>Um motor que represente a roda esquerda;</li>
 * <li>Um motor que represente a roda direita;</li>
 * <li>Um sensor de cor;</li>
 * <li>Um sensor ultrassônico, no intuito de identificar obstáculos;</li>
 * <li>Um motor extra que permite anexar o sensor de cor/ultrassônico,
 * proporcionando uma melhor mobilidade ao robô.</li>
 * </ul>
 * 
 * @author Maria Gabriela Torrens
 */
public final class LejosRobot implements IRobot {

	private static final List<lejos.nxt.ColorSensor.Color> supportedColors = getSupportedColors();
	private NXTRegulatedMotor leftWheel;
	private NXTRegulatedMotor rightWheel;
	private ColorSensor colorSensor;
	private NXTRegulatedMotor multiuseMotor;
	private UltrasonicSensor distanceSensor;

	@Override
	public void onStart() throws Exception {
		if (leftWheel != null) {
			leftWheel.setSpeed(300);
		}
		if (rightWheel != null) {
			rightWheel.setSpeed(300);
		}
		System.out.println("Pressione o botao para iniciar");
		Button.waitForAnyPress();
	}

	@Override
	public void onEnd() throws Exception {
		System.out.println("Fim do programa");
		Button.waitForAnyPress();
	}

	@Override
	public void walkForward() throws Exception {
		checkLeftWheelConnected();
		checkRightWheelConnected();
		leftWheel.forward();
		rightWheel.forward();
	}

	@Override
	public void walkForward(double n) throws Exception {
		checkLeftWheelConnected();
		checkRightWheelConnected();
		n = n * Measures.ONE_FOOTSTEP;
		leftWheel.rotate((int) n, true);
		rightWheel.rotate((int) n);
	}

	@Override
	public void walkBackward() throws Exception {
		checkLeftWheelConnected();
		checkRightWheelConnected();
		leftWheel.backward();
		rightWheel.backward();
	}

	@Override
	public void walkBackward(double n) throws Exception {
		checkLeftWheelConnected();
		checkRightWheelConnected();
		n = n * Measures.ONE_FOOTSTEP;
		leftWheel.rotate((int) -n, true);
		rightWheel.rotate((int) -n);
	}

	@Override
	public void stopWalking() throws Exception {
		stop();
	}

	@Override
	public void turnLeft() throws Exception {
		checkLeftWheelConnected();
		leftWheel.backward();
		rightWheel.forward();
	}

	@Override
	public void turnLeft(double n) throws Exception {
		checkLeftWheelConnected();
		n = n * Measures.WHEEL_NINETY_DEGREES;
		leftWheel.rotate((int) -n, true);
		rightWheel.rotate((int) n);
	}

	@Override
	public void turnRight() throws Exception {
		checkRightWheelConnected();
		rightWheel.backward();
		leftWheel.forward();
	}

	@Override
	public void turnRight(double n) throws Exception {
		checkRightWheelConnected();
		n = n * Measures.WHEEL_NINETY_DEGREES;
		rightWheel.rotate((int) -n, true);
		leftWheel.rotate((int) n);
	}

	@Override
	public void stopTurning() throws Exception {
		stop();
	}

	@Override
	public void turnMultiuseMotorLeft(double n) throws Exception {
		checkMultiuseMotorConnected();
		n = n * Measures.MOTOR_NINETY_DEGREES;
		multiuseMotor.rotate((int) -n);
	}

	@Override
	public void turnMultiuseMotorRight(double n) throws Exception {
		checkMultiuseMotorConnected();
		n = n * Measures.MOTOR_NINETY_DEGREES;
		multiuseMotor.rotate((int) n);
	}

	@Override
	public void spinLeftWheelForward() throws Exception {
		checkLeftWheelConnected();
		leftWheel.forward();
	}

	@Override
	public void spinLeftWheelBackward() throws Exception {
		checkLeftWheelConnected();
		leftWheel.backward();
	}

	@Override
	public void spinRightWheelForward() throws Exception {
		checkRightWheelConnected();
		rightWheel.forward();
	}

	@Override
	public void spinRightWheelBackward() throws Exception {
		checkRightWheelConnected();
		rightWheel.backward();

	}

	@Override
	public void stopSpinning() throws Exception {
		stop();
	}

	private void stop() throws Exception {
		checkLeftWheelConnected();
		checkRightWheelConnected();
		leftWheel.stop(true);
		rightWheel.stop();
	}

	@Override
	public void write(String text) {
		System.out.println(text);
	}

	@Override
	public void beep() {
		Sound.beep();
	}

	@Override
	public boolean hasObstacle() throws Exception {
		checkDistanceSensorConnected();
		return distanceSensor != null && distanceSensor.getDistance() < 20;
	}

	@Override
	public ColorDefinition detectColor() throws Exception {
		checkColorSensorConnected();

		lejos.nxt.ColorSensor.Color colorReaded = colorSensor.getColor();
		ColorDefinition color = matchColor(colorReaded);

		if (color == null) {
			color = getApproximatedColor(colorReaded);
		}

		assert color != null;

		return color;
	}

	private ColorDefinition matchColor(lejos.nxt.ColorSensor.Color color) {
		switch (color.getColor()) {
		case lejos.nxt.ColorSensor.Color.WHITE:
			return new ColorDefinition(Color.WHITE);

		case lejos.nxt.ColorSensor.Color.GREEN:
			return new ColorDefinition(Color.GREEN);

		case lejos.nxt.ColorSensor.Color.BLUE:
			return new ColorDefinition(Color.BLUE);

		case lejos.nxt.ColorSensor.Color.RED:
			return new ColorDefinition(Color.RED);

		case lejos.nxt.ColorSensor.Color.YELLOW:
			return new ColorDefinition(Color.YELLOW);
			
		case lejos.nxt.ColorSensor.Color.BLACK:
			return new ColorDefinition(Color.BLACK);
		}
		
		return null;
	}

	private ColorDefinition getApproximatedColor(lejos.nxt.ColorSensor.Color color) {
		int closestValue = Integer.MAX_VALUE;
		lejos.nxt.ColorSensor.Color approximatedColor = null;
		for (lejos.nxt.ColorSensor.Color otherColor : supportedColors) {
			int value = Math.abs(color.getRed() - otherColor.getRed()) + Math.abs(color.getGreen() - otherColor.getGreen()) + Math.abs(color.getBlue() - otherColor.getBlue());
			if (value < closestValue) {
				closestValue = value;
				approximatedColor = otherColor;
			}
		}

		assert approximatedColor != null;

		return matchColor(approximatedColor);
	}

	private static List<lejos.nxt.ColorSensor.Color> getSupportedColors() {
		lejos.nxt.ColorSensor.Color red = new lejos.nxt.ColorSensor.Color(255, 0, 0, 0, lejos.nxt.ColorSensor.Color.RED);
		lejos.nxt.ColorSensor.Color green = new lejos.nxt.ColorSensor.Color(255, 0, 0, 0, lejos.nxt.ColorSensor.Color.GREEN);
		lejos.nxt.ColorSensor.Color blue = new lejos.nxt.ColorSensor.Color(255,	0, 0, 0, lejos.nxt.ColorSensor.Color.BLUE);
		lejos.nxt.ColorSensor.Color yellow = new lejos.nxt.ColorSensor.Color(255, 255, 0, 0, lejos.nxt.ColorSensor.Color.YELLOW);
		lejos.nxt.ColorSensor.Color white = new lejos.nxt.ColorSensor.Color(255, 255, 255, 0, lejos.nxt.ColorSensor.Color.WHITE);
		lejos.nxt.ColorSensor.Color black = new lejos.nxt.ColorSensor.Color(0, 0, 0, 0, lejos.nxt.ColorSensor.Color.BLACK);

		List<lejos.nxt.ColorSensor.Color> supportedColors = new ArrayList<lejos.nxt.ColorSensor.Color>();
		supportedColors.add(red);
		supportedColors.add(green);
		supportedColors.add(blue);
		supportedColors.add(yellow);
		supportedColors.add(white);
		supportedColors.add(black);

		return supportedColors;
	}

	/** Valida se o sensor de cor está conectado.
	 * 
	 * @throws Exception caso o sensor de cor não esteja conectado
	 */
	private void checkColorSensorConnected() throws Exception {
		if (colorSensor == null) {
			throw new Exception("Sensor de cor nao conectado");
		}
	}

	/** Valida se a roda esquerda está conectada.
	 * 
	 * @throws Exception caso a roda esquerda não esteja conectada
	 */
	private void checkLeftWheelConnected() throws Exception {
		if (leftWheel == null) {
			throw new Exception("Roda esquerda nao conectada");
		}
	}

	/** Valida se a roda direita está conectada.
	 * 
	 * @throws Exception caso a roda direita não esteja conectada
	 */
	private void checkRightWheelConnected() throws Exception {
		if (leftWheel == null) {
			throw new Exception("Roda direita nao conectada");
		}
	}

	/** Valida se o motor multiúso está conectado.
	 * 
	 * @throws Exception caso o motor multiúsonão esteja conectado
	 */
	private void checkMultiuseMotorConnected() throws Exception {
		if (leftWheel == null) {
			throw new Exception("Motor multiuso nao conectado");
		}
	}

	/**
	 * Valida se o sensor de distância está conectado.
	 * 
	 * @throws Exception caso o sensor de distância não esteja conectado
	 */
	private void checkDistanceSensorConnected() throws Exception {
		if (leftWheel == null) {
			throw new Exception("Sensor de distancia nao conectado");
		}
	}

	@Override
	public void manageException(Exception exception) {
		System.out.println(exception.getMessage());
		Button.waitForAnyPress();
	}

	public void setUltrasonicSensorPort(SensorPort port) {
		this.distanceSensor = new UltrasonicSensor(port);
	}

	public void setMultiuseMotorPort(MotorPort port) {
		this.multiuseMotor = new NXTRegulatedMotor(port);
	}

	public void setColorSensorPort(SensorPort port) {
		this.colorSensor = new ColorSensor(port);
	}

	public void setRightWheelPort(MotorPort port) {
		this.rightWheel = new NXTRegulatedMotor(port);
	}

	public void setLeftWheelPort(MotorPort port) {
		this.leftWheel = new NXTRegulatedMotor(port);
	}

}
