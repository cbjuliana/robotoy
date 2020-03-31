package br.inf.furb.tcc.robotoy.compiler.generator.java;

public final class InvokedMethod {

    private final int line;
    private final String name;

    public InvokedMethod(int line, String name) {
        this.line = line;
        this.name = name;
    }

    public int getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

}
