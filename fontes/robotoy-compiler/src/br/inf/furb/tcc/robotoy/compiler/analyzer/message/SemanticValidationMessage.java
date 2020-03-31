package br.inf.furb.tcc.robotoy.compiler.analyzer.message;

public class SemanticValidationMessage {

    public static final String VARIABLE_NOT_DECLARED = "A variável com nome \"%s\" não foi declarada.";
    public static final String METHOD_NOT_DECLARED = "A rotina com nome \"%s\" não foi declarada.";
    public static final String INCOMPATIBLE_TYPES = "O tipo da variável \"%s\" (%s) não é compatível com o tipo da variável \"%s\" (%s).";
    public static final String CANNOT_ATTRIBUTE_NUMBER = "Não é possível atribuir um número à variável \"%s\", pois ela é do tipo %s.";
    public static final String CANNOT_ATTRIBUTE_COLOR = "Não é possível atribuir uma cor à variável \"%s\", pois ela é do tipo %s.";
    public static final String CANNOT_ATTRIBUTE_TEXT = "Não é possível atribuir um texto à variável \"%s\", pois ela é do tipo %s.";
    public static final String CANNOT_ADD_SIGNAL = "Os sinais de positivo e negativo só podem ser adicionados em números ou variáveis numéricas.";
    public static final String SIGNAL_ALREADY_EXISTS = "Não é possível adicionar mais de um sinal para um número ou variável numérica.";
    public static final String VARIABLE_ALREADY_EXISTS = "Já existe uma variável com o nome \"%s\".";
    public static final String METHOD_ALREADY_EXISTS = "Já existe uma rotina com o nome \"%s\".";
    public static final String CANNOT_ADD_CONCATENATION_OPERATOR = "O operador de concatenação \".\" só pode ser utilizado para concatenar textos.";
    public static final String CANNOT_ADD_ARITHMETIC_OPERATOR = "O operador aritmético \"%s\" só pode ser utilizado entre números ou variáveis numéricas.";
    public static final String INVALID_COMPARISION = "Não é possível comparar tipos diferentes: %s e %s.";
    public static final String INCOMPATIBLE_OPERATOR_FOR_COMPARISON = "O tipo %s só pode ser comparado com os operadores \"=\" ou \"=/\".";
    public static final String INVALID_ELEMENTS_RELATION = "Tipos incompatíveis no %s elemento da comparação, não é possível relacionar um(a) %s a um(a) %s.";
    public static final String INVALID_PARAMETER_TYPE = "O parâmetro deve ser do tipo %s.";

    private SemanticValidationMessage() {
        // Classe não instanciável.
    }

}
