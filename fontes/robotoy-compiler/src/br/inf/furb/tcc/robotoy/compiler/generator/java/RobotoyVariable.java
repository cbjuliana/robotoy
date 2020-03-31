package br.inf.furb.tcc.robotoy.compiler.generator.java;

public class RobotoyVariable {
    
    private Type type;
    private String name;
    private VariableStatus status;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public VariableStatus getStatus() {
        return status;
    }

    public void setStatus(VariableStatus status) {
        this.status = status;
    }

}
