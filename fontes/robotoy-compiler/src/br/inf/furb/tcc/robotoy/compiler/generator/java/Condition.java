package br.inf.furb.tcc.robotoy.compiler.generator.java;

import br.inf.furb.tcc.robotoy.compiler.analyzer.SemanticError;

public class Condition {

    private StringBuilder condition = new StringBuilder();

    public void appendConditionPart(ConditionPart conditionPart) {
        condition.append(conditionPart.getCondition());
    }

    public void appendComparisonConditionPart(ComparisonConditionPart conditionPart, int line) throws SemanticError {
        condition.append(conditionPart.getCondition(line));
    }

    public void appendAnd() {
        condition.append(" && ");
    }

    public void appendOr() {
        condition.append(" || ");
    }

    public String getCondition() {
        return condition.toString();
    }
}
