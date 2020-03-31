package br.inf.furb.tcc.robotoy.robot.generic;

/**
 * Formas de comunica��o que o rob� poder� exercer.
 * 
 * @author Maria Gabriela Torrens
 */
public interface ICommunicator {

    /**
     * O rob� exibe um texto na tela.
     * 
     * @param text texto a ser escrito na tela
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void write(String text) throws Exception;

    /**
     * O rob� emite um som.
     * 
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public void beep();

}
