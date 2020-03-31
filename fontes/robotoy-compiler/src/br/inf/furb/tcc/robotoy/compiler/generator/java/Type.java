package br.inf.furb.tcc.robotoy.compiler.generator.java;

import br.inf.furb.tcc.robotoy.robot.generic.Color;

/**
 * Definição dos tipos de atributo suportados pela linguagem.
 * 
 * @author Maria Gabriela Torrens
 */
public enum Type {

    /** Número, que em Java é correspondente ao {@link Double} primitivo. */
    NUMBER("número", "double"),

    /** Texto, que em Java é correspondente à {@link String}. */
    TEXT("texto", "String"),

    /** Cor, que em Java é correspondente à {@link Color}. */
    COLOR("cor", "ColorDefinition");

    private final String robotoyType;
    private final String correspondingClassInJava;

    private Type(String robotoyType, String correspondigClassInJava) {
        this.robotoyType = robotoyType;
        this.correspondingClassInJava = correspondigClassInJava;
    }

    /**
     * Retorna a descrição do tipo na linguagem Robotoy.
     * 
     * @return descrição do tipo na linguagem Robotoy
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
