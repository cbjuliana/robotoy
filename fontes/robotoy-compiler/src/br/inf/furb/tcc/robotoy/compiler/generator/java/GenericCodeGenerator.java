package br.inf.furb.tcc.robotoy.compiler.generator.java;

import java.io.File;
import java.io.IOException;
import java.util.List;

import br.inf.furb.tcc.robotoy.robot.generic.IRobot;

public interface GenericCodeGenerator {

    Method newRobotoyMethod();

    void removeRobotoyVariableInEditing();

    List<RobotoyVariable> getRobotoyVariables();

    List<Method> getRobotoyMethods();

    RobotoyVariable getRobotoyVariableInEditing();

    void setRobotoyVariableInEditing(String variableName);

    void setRobotoyVariableInEditing(RobotoyVariable variable);

    RobotoyVariable getRobotoytVariable(String variableName);

    boolean hasRobotoyVariable(String variableName);

    boolean containsRobotoyVariableWithName(String name);

    boolean containsRobotoyMethodWithName(String name);

    void addImport(String clazz);

    RobotoyVariable newRobotoyVariable();

    void removeRobotoyMethodInEditing();

    void appendContent(String content);

    void openBlock();

    void openBlock(boolean breakLine);

    void closeBlock();

    void closeBlock(boolean breakLine);

    void addDoubleImport();

    void breakLine();

    void indentLine();

    void finalizeInitialConfiguration();

}
