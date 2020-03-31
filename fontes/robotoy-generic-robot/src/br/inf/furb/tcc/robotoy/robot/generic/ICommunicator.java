package br.inf.furb.tcc.robotoy.robot.generic;

/**
 * Formas de comunicação que o robô poderá exercer.
 * 
 * @author Maria Gabriela Torrens
 */
public interface ICommunicator {

    /**
     * O robô exibe um texto na tela.
     * 
     * @param text texto a ser escrito na tela
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void write(String text) throws Exception;

    /**
     * O robô emite um som.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void beep();

}
