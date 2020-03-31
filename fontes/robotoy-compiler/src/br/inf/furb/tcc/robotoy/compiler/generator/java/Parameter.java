package br.inf.furb.tcc.robotoy.compiler.generator.java;

import br.inf.furb.tcc.robotoy.compiler.analyzer.SemanticError;
import br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage;

public final class Parameter {

    private final Type type;
    private final StringBuilder content;

    public Parameter(Type type) {
        this.type = type;
        this.content = new StringBuilder();
    }

    public void assertContentTypeToAppend(Type type, int line) throws SemanticError {
        if (type != this.type) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.INVALID_PARAMETER_TYPE, this.type.getRobotoyType()));
        }
    }

    public void appendContent(String content) {
        this.content.append(content);
    }

    public String getParameter(boolean abs) {
    	String format;
    	
        if (type == Type.NUMBER) {
        	if (abs)
        	    format = "Math.abs(%s)";
        	format = "%s";
            return String.format(format, content.toString());
        }
        return content.toString();
    }

    public Type getType() {
        return type;
    }

}
