package br.inf.furb.tcc.robotoy.compiler.analyzer.message;

public class SemanticValidationMessage {

    public static final String VARIABLE_NOT_DECLARED = "A vari�vel com nome \"%s\" n�o foi declarada.";
    public static final String METHOD_NOT_DECLARED = "A rotina com nome \"%s\" n�o foi declarada.";
    public static final String INCOMPATIBLE_TYPES = "O tipo da vari�vel \"%s\" (%s) n�o � compat�vel com o tipo da vari�vel \"%s\" (%s).";
    public static final String CANNOT_ATTRIBUTE_NUMBER = "N�o � poss�vel atribuir um n�mero � vari�vel \"%s\", pois ela � do tipo %s.";
    public static final String CANNOT_ATTRIBUTE_COLOR = "N�o � poss�vel atribuir uma cor � vari�vel \"%s\", pois ela � do tipo %s.";
    public static final String CANNOT_ATTRIBUTE_TEXT = "N�o � poss�vel atribuir um texto � vari�vel \"%s\", pois ela � do tipo %s.";
    public static final String CANNOT_ADD_SIGNAL = "Os sinais de positivo e negativo s� podem ser adicionados em n�meros ou vari�veis num�ricas.";
    public static final String SIGNAL_ALREADY_EXISTS = "N�o � poss�vel adicionar mais de um sinal para um n�mero ou vari�vel num�rica.";
    public static final String VARIABLE_ALREADY_EXISTS = "J� existe uma vari�vel com o nome \"%s\".";
    public static final String METHOD_ALREADY_EXISTS = "J� existe uma rotina com o nome \"%s\".";
    public static final String CANNOT_ADD_CONCATENATION_OPERATOR = "O operador de concatena��o \".\" s� pode ser utilizado para concatenar textos.";
    public static final String CANNOT_ADD_ARITHMETIC_OPERATOR = "O operador aritm�tico \"%s\" s� pode ser utilizado entre n�meros ou vari�veis num�ricas.";
    public static final String INVALID_COMPARISION = "N�o � poss�vel comparar tipos diferentes: %s e %s.";
    public static final String INCOMPATIBLE_OPERATOR_FOR_COMPARISON = "O tipo %s s� pode ser comparado com os operadores \"=\" ou \"=/\".";
    public static final String INVALID_ELEMENTS_RELATION = "Tipos incompat�veis no %s elemento da compara��o, n�o � poss�vel relacionar um(a) %s a um(a) %s.";
    public static final String INVALID_PARAMETER_TYPE = "O par�metro deve ser do tipo %s.";

    private SemanticValidationMessage() {
        // Classe n�o instanci�vel.
    }

}
