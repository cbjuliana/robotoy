package br.inf.furb.tcc.robotoy.compiler.analyzer;

import br.inf.furb.tcc.robotoy.compiler.generator.java.Method;

public class DeclaredMethod {

    private final Method method;
    private final int line;

    public DeclaredMethod(int line, Method method) {
        this.method = method;
        this.line = line;
    }

    public Method getMethod() {
        return method;
    }

    public int getLine() {
        return line;
    }

}
