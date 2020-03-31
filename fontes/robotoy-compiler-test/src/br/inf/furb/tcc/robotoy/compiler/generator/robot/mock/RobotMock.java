package br.inf.furb.tcc.robotoy.compiler.generator.robot.mock;

import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;
import br.inf.furb.tcc.robotoy.robot.generic.IRobot;

public class RobotMock implements IRobot {

    @Override
    public void walkForward() {
    }

    @Override
    public void walkForward(double n) {
    }

    @Override
    public void walkBackward() {
    }

    @Override
    public void walkBackward(double n) {
    }

    @Override
    public void stopWalking() {
    }

    @Override
    public void turnLeft() {
    }

    @Override
    public void turnLeft(double n) {
    }

    @Override
    public void turnRight() {
    }

    @Override
    public void turnRight(double n) {
    }

    @Override
    public void stopTurning() {
    }

    @Override
    public void turnMultiuseMotorLeft(double n) {
    }

    @Override
    public void turnMultiuseMotorRight(double n) {
    }

    @Override
    public void spinLeftWheelForward() {
    }

    @Override
    public void spinLeftWheelBackward() {
    }

    @Override
    public void spinRightWheelForward() {
    }

    @Override
    public void spinRightWheelBackward() {
    }

    @Override
    public void stopSpinning() {
    }

    @Override
    public void write(String text) {
    }

    @Override
    public void beep() {
    }

    @Override
    public boolean hasObstacle() {
        return false;
    }

    @Override
    public ColorDefinition detectColor() {
        return null;
    }

    @Override
    public void manageException(Exception exception) {
    }

	@Override
	public void onStart() throws Exception {
	}

	@Override
	public void onEnd() throws Exception {
	}

}
