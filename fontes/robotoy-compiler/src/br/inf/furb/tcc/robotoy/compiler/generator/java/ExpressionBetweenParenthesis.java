package br.inf.furb.tcc.robotoy.compiler.generator.java;

public final class ExpressionBetweenParenthesis {

    private final String decimalFormat;

    private StringBuilder expression = new StringBuilder();

    private boolean appendDecimalFormat;
    private boolean firstOpeningParenthesisAdded;
    private boolean firstClosingParenthesisAdded;

    private int amountParenthesisToClose;

    public ExpressionBetweenParenthesis(String decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public void appendContent(String content) {
        expression.append(content);
    }

    public void appendOpeningParenthesis() {
        amountParenthesisToClose++;

        if (!firstOpeningParenthesisAdded) {
            firstOpeningParenthesisAdded = true;
            return;
        }

        expression.append("(");
    }

    public void appendEndingParenthesis() {
        amountParenthesisToClose--;

        if (!firstClosingParenthesisAdded) {
            firstClosingParenthesisAdded = true;
            return;
        }

        expression.append(")");
    }

    public boolean isComplete() {
        return amountParenthesisToClose == 0;
    }

    public String getExpression() {
        if (appendDecimalFormat) {
            return String.format(decimalFormat, expression.toString());
        }
        return expression.toString();
    }

    public void appendDecimalFormat() {
        appendDecimalFormat = true;
    }

}
