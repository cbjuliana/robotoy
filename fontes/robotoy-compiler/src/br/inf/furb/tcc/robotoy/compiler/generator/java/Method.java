package br.inf.furb.tcc.robotoy.compiler.generator.java;

public final class Method {

    private String name;
    private final StringBuilder content = new StringBuilder();

    private Class<?> returnType;
    private boolean isStatic;
    private boolean throwsException;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void appendContent(String content) {
        this.content.append(content);
    }

    public String getMethodContent() {
        return content.toString();
    }

    public StringBuilder getContent() {
        return content;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void makeStatic() {
        isStatic = true;
    }

    public boolean throwsException() {
        return throwsException;
    }

    public void makeThrowsException() {
        throwsException = true;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

}
