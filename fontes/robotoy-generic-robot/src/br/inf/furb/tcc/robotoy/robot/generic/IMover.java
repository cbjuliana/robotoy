package br.inf.furb.tcc.robotoy.robot.generic;

/**
 * A��es de movimenta��o que o rob� dever� suportar.
 * 
 * @author Maria Gabriela Torrens
 */
public interface IMover {

    /**
     * O rob� anda para frente constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void walkForward() throws Exception;

    /**
     * O rob� anda para frente <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o rob� andar� para frente
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void walkForward(double n) throws Exception;

    /**
     * O rob� anda para tr�s constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void walkBackward() throws Exception;

    /**
     * O rob� anda para tr�s <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o rob� andar� para tr�s
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void walkBackward(double n) throws Exception;

    /**
     * O rob� para de andar.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void stopWalking() throws Exception;

    /**
     * O rob� vira para a esquerda constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnLeft() throws Exception;

    /**
     * O rob� vira para a esquerda <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o rob� virar� para a esquerda
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnLeft(double n) throws Exception;

    /**
     * O rob� vira para a direita constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnRight() throws Exception;

    /**
     * O rob� vira para a direita <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o rob� virar� para a direita
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnRight(double n) throws Exception;

    /**
     * O rob� para de virar.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void stopTurning() throws Exception;

    /**
     * Vira o motor para a esquerda <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o motor virar� para a esquerda
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void turnMultiuseMotorLeft(double n) throws Exception;

    /**
     * Vira o motor para a direita <b>n</b> vezes.
     * 
     * @param n quantidade de vezes que o motor virar� para a direita
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
     * Gira a roda esquerda para tr�s constantemente.
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
     * Gira a roda direita para tr�s constantemente.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void spinRightWheelBackward() throws Exception;

    /**
     * O rob� para de girar.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void stopSpinning() throws Exception;

}
