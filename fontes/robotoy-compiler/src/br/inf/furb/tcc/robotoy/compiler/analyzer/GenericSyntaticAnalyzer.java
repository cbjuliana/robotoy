package br.inf.furb.tcc.robotoy.compiler.analyzer;

public interface GenericSyntaticAnalyzer {

    void parse(LexicalAnalyzer scanner, GenericSemanticAnalyzer semanticAnalyser) throws AnalysisError;

}
