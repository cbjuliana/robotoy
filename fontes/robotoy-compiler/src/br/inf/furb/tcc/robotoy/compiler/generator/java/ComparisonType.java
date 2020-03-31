package br.inf.furb.tcc.robotoy.compiler.generator.java;

public enum ComparisonType {

    EQUAL("=", "=="),

    DIFFERENT("=/", "!="),

    LESSER("<", "<"),

    LESSER_OR_EQUAL("<=", "<="),

    GREATER(">", ">"),

    GREATER_OR_EQUAL(">=", ">=");

    private final String robotoyOperator;
    private final String javaOperator;

    private ComparisonType(String robotoyOperator, String javaOperator) {
        this.robotoyOperator = robotoyOperator;
        this.javaOperator = javaOperator;
    }

    public String getRobotoyOperator() {
        return robotoyOperator;
    }

    public String getJavaOperator() {
        return javaOperator;
    }

    public static ComparisonType getJavaOperatorByRobotoyOperator(String robotoyOperator) {
        ComparisonType[] types = ComparisonType.values();
        for (ComparisonType comparisonType : types) {
            if (comparisonType.getRobotoyOperator().equals(robotoyOperator)) {
                return comparisonType;
            }
        }
        throw new RuntimeException("Operador de comparação não reconhecido.");
    }

}
