package br.inf.furb.tcc.robotoy.compiler.generator.java;

public class ConditionPart {

    private final StringBuilder content = new StringBuilder();

    public void appendValue(String content) {
        this.content.append(content);
    }

    public String getCondition() {
        return content.toString();
    }

}
