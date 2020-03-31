package br.inf.furb.tcc.robotoy.compiler.generator.java;

import br.inf.furb.tcc.robotoy.robot.generic.Color;

/**
 * Defini��o dos tipos de atributo suportados pela linguagem.
 * 
 * @author Maria Gabriela Torrens
 */
public enum Type {

    /** N�mero, que em Java � correspondente ao {@link Double} primitivo. */
    NUMBER("n�mero", "double"),

    /** Texto, que em Java � correspondente � {@link String}. */
    TEXT("texto", "String"),

    /** Cor, que em Java � correspondente � {@link Color}. */
    COLOR("cor", "ColorDefinition");

    private final String robotoyType;
    private final String correspondingClassInJava;

    private Type(String robotoyType, String correspondigClassInJava) {
        this.robotoyType = robotoyType;
        this.correspondingClassInJava = correspondigClassInJava;
    }

    /**
     * Retorna a descri��o do tipo na linguagem Robotoy.
     * 
     * @return descri��o do tipo na linguagem Robotoy
     */
    public String getRobotoyType() {
        return robotoyType;
    }

    /**
     * Retorna a classe correspondente ao tipo em Java.
     * 
     * @return classe correspondente ao tipo em Java
     */
    public String getCorrespondingClassInJava() {
        return correspondingClassInJava;
    }

}
