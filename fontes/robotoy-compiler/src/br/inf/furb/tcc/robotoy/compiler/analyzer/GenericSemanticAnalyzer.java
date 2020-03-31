package br.inf.furb.tcc.robotoy.compiler.analyzer;

public interface GenericSemanticAnalyzer {

    void executeAction(int action, Token token) throws SemanticError;

}
