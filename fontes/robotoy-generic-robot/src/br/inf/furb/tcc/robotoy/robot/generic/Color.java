package br.inf.furb.tcc.robotoy.robot.generic;

/**
 * Cores suportadas pela linguagem Robotoy.
 * 
 * @author Maria Gabriela Torrens
 */
public enum Color {

    BLACK("preto"),

    WHITE("branco"),

    GREEN("verde"),

    RED("vermelho"),

    YELLOW("amarelo"),

    BLUE("azul");

    private final String name;

    private Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
