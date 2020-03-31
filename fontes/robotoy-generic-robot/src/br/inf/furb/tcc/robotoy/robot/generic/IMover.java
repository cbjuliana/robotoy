package br.inf.furb.tcc.robotoy.robot.generic;

/**
 * Ações de movimentação que o robô deverá suportar.
 * 
 * @author Maria Gabriela Torrens
 */
public interface IMover {

    /**
     * O robô anda para frente constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void walkForward() throws Exception;

    /**
     * O robô anda para frente <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o robô andará para frente
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void walkForward(double n) throws Exception;

    /**
     * O robô anda para trás constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void walkBackward() throws Exception;

    /**
     * O robô anda para trás <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o robô andará para trás
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void walkBackward(double n) throws Exception;

    /**
     * O robô para de andar.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void stopWalking() throws Exception;

    /**
     * O robô vira para a esquerda constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnLeft() throws Exception;

    /**
     * O robô vira para a esquerda <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o robô virará para a esquerda
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnLeft(double n) throws Exception;

    /**
     * O robô vira para a direita constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnRight() throws Exception;

    /**
     * O robô vira para a direita <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o robô virará para a direita
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnRight(double n) throws Exception;

    /**
     * O robô para de virar.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void stopTurning() throws Exception;

    /**
     * Vira o motor para a esquerda <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o motor virará para a esquerda
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnMultiuseMotorLeft(double n) throws Exception;

    /**
     * Vira o motor para a direita <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o motor virará para a direita
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnMultiuseMotorRight(double n) throws Exception;

    /**
     * Gira a roda esquerda para frente constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void spinLeftWheelForward() throws Exception;

    /**
     * Gira a roda esquerda para trás constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void spinLeftWheelBackward() throws Exception;

    /**
     * Gira a roda direita para frente constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void spinRightWheelForward() throws Exception;

    /**
     * Gira a roda direita para trás constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void spinRightWheelBackward() throws Exception;

    /**
     * O robô para de girar.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void stopSpinning() throws Exception;

}
