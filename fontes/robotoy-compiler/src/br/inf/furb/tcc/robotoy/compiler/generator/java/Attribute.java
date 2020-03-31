package br.inf.furb.tcc.robotoy.compiler.generator.java;

public final class Attribute {

    private final String type;
    private final String name;
    private final String value;

    private boolean isStatic;
    private boolean isFinal;

    public Attribute(String type, String name) {
        this(type, name, null);
    }

    public Attribute(String type, String name, String value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void makeStatic() {
        isStatic = true;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void makeFinal() {
        isFinal = true;
    }

    public boolean isConstant() {
        return isStatic && isFinal;
    }

    public void makeConstant() {
        isStatic = true;
        isFinal = true;
    }

}
