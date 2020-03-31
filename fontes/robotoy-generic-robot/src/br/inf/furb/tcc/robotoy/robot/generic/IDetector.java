package br.inf.furb.tcc.robotoy.robot.generic;

import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;

/**
 * Detecções que o robô poderá fazer.
 * 
 * @author Maria Gabriela Torrens
 */
public interface IDetector {

    /**
     * Detecta uma cor.
     * 
     * @return cor detectada ou preto caso não consiga detectar a cor
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    ColorDefinition detectColor() throws Exception;

    /**
     * Verifica se há um obstáculo.
     * 
     * @return {@code true} se houver um obstáculo, caso contrário {@code false}
     * @throws Exception caso ocorra algum problema ao executar o comando
     */
    public boolean hasObstacle() throws Exception;

}
