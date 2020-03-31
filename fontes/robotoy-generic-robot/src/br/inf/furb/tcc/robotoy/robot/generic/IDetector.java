package br.inf.furb.tcc.robotoy.robot.generic;

import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;

/**
 * Detec��es que o rob� poder� fazer.
 * 
 * @author Maria Gabriela Torrens
 */
public interface IDetector {

    /**
     * Detecta uma cor.
     * 
     * @return cor detectada ou preto caso n�o consiga detectar a cor
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    ColorDefinition detectColor() throws Exception;

    /**
     * Verifica se h� um obst�culo.
     * 
     * @return {@code true} se houver um obst�culo, caso contr�rio {@code false}
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public boolean hasObstacle() throws Exception;

}
