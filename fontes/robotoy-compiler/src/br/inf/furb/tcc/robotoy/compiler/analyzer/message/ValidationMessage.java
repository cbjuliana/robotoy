package br.inf.furb.tcc.robotoy.compiler.analyzer.message;

public final class ValidationMessage {

    public static final String INVALID_EXTENSION_CLASS = "A classe \"%s\" deve estender de \"%s\".";
    public static final String INVALID_CONSTRUCTORS_AMOUNT = "A classe %s deve possuir apenas um construtor sem par�metros.";
    public static final String CONSTRUCTOR_WITH_PARAMETER = "O construtor da classe %s possui par�metro.";

    private ValidationMessage() {
        // Classe n�o instanci�vel.
    }

}
