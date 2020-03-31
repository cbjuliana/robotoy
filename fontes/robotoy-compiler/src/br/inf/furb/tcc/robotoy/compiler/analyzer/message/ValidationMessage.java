package br.inf.furb.tcc.robotoy.compiler.analyzer.message;

public final class ValidationMessage {

    public static final String INVALID_EXTENSION_CLASS = "A classe \"%s\" deve estender de \"%s\".";
    public static final String INVALID_CONSTRUCTORS_AMOUNT = "A classe %s deve possuir apenas um construtor sem parâmetros.";
    public static final String CONSTRUCTOR_WITH_PARAMETER = "O construtor da classe %s possui parâmetro.";

    private ValidationMessage() {
        // Classe não instanciável.
    }

}
