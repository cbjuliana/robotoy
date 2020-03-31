package br.inf.furb.tcc.robotoy.compiler.generator.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.inf.furb.tcc.robotoy.robot.generic.IRobot;

public final class JavaClassGenerator implements GenericCodeGenerator {

    private static final String TAB = "\t";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private String javaFileName;

    private final List<String> imports;
    private final StringBuilder initialConfigurationContent;
    private final StringBuilder content;
    private final StringBuilder mainContent;
    private final List<Attribute> attributes;
    private final List<RobotoyVariable> robotoyVariables;
    private final List<Method> methods;
    private StringBuilder contentInEditing;

    private RobotoyVariable robotoyVariable;
    private Method robotoyMethod;

    private int tabsAmount;

    public JavaClassGenerator() {
        tabsAmount = 3;
        imports = new ArrayList<String>();
        initialConfigurationContent = new StringBuilder();
        content = new StringBuilder();
        mainContent = new StringBuilder();
        contentInEditing = initialConfigurationContent;
        attributes = new ArrayList<Attribute>();
        robotoyVariables = new ArrayList<RobotoyVariable>();
        methods = new ArrayList<Method>();
    }

    public void createFile(File javaFile, Class<? extends IRobot> clazz) throws IOException {
        // TODO Validar se é arquivo Java.
        javaFileName = javaFile.getName().replace(".java", "");

        content.setLength(0);
        tabsAmount = 0;

        addRobotImport(clazz);
        generateContent();

        try (PrintStream stream = new PrintStream(javaFile)) {
            stream.print(content.toString());
        }
    }

    private void generateContent() {
        addClassDeclaration();
    }

    public Method newRobotoyMethod() {
        if (contentInEditing == mainContent) {
            tabsAmount = 2;
        }

        robotoyMethod = new Method();
        robotoyMethod.makeThrowsException();
        contentInEditing = robotoyMethod.getContent();
        return robotoyMethod;
    }
    
    public void removeRobotoyVariableInEditing() {
        if (robotoyVariable.getStatus() == VariableStatus.IN_DECLARATION) {
        	robotoyVariable.setStatus(VariableStatus.NONE);
        	robotoyVariables.add(robotoyVariable);
        }
        robotoyVariable = null;
    }

    public List<RobotoyVariable> getRobotoyVariables() {
        return Collections.unmodifiableList(robotoyVariables);
    }

    public List<Method> getRobotoyMethods() {
        return Collections.unmodifiableList(methods);
    }

    public RobotoyVariable getRobotoyVariableInEditing() {
        return robotoyVariable;
    }

    public void setRobotoyVariableInEditing(String variableName) {
        RobotoyVariable variable = getRobotoytVariable(variableName);
        setRobotoyVariableInEditing(variable);
    }

    public void setRobotoyVariableInEditing(RobotoyVariable variable) {
        this.robotoyVariable = variable;
        robotoyVariable.setStatus(VariableStatus.IN_UPDATE);
    }

    public RobotoyVariable getRobotoytVariable(String variableName) {
        for (RobotoyVariable variable : robotoyVariables) {
            if (variable.getName().equals(variableName)) {
                return variable;
            }
        }
        return null;
    }

    public boolean hasRobotoyVariable(String variableName) {
        for (RobotoyVariable variable : robotoyVariables) {
            if (variable.getName().equals(variableName)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRobotoyVariableWithName(String name) {
        for (RobotoyVariable variable : robotoyVariables) {
            if (variable.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRobotoyMethodWithName(String name) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private void addClassDeclaration() {
        addImports();
        content.append("public final class ").append(javaFileName).append(" ");
        openBlock(content, true);
        breakLine(content);
        addAttributes();
        addMain();
        addMethods();
        closeBlock(content, false);
    }

    public void addImport(String clazz) {
        if (!imports.contains(clazz)) {
            imports.add(clazz);
        }
    }

    private void addImports() {
        for (String toImport : imports) {
            content.append("import ").append(toImport).append(";");
            breakLine(content);
        }
        breakLine(content);
    }

    private void addAttributes() {
        for (Attribute attribute : attributes) {
            String type = attribute.getType();
            String name = attribute.getName();
            String value = attribute.getValue();

            indentLine(content);

            content.append("private ");
            if (attribute.isStatic()) {
                content.append("static ");
            }

            if (attribute.isFinal()) {
                content.append("final ");
            }

            content.append(type).append(" ").append(name);

            if (attribute.getValue() != null) {
                content.append(" = ").append(value);
            }

            content.append(";");

            breakLine(content);
        }
        
        if (!robotoyVariables.isEmpty()) {
            breakLine(content);
        }
        
        for (RobotoyVariable variable : robotoyVariables) {
            indentLine(content);
            content.append("private static ").append(variable.getType().getCorrespondingClassInJava()).append(" ").append(variable.getName()).append(";");
            breakLine(content);
        }

        if (!attributes.isEmpty()) {
            breakLine(content);
        }
    }

    private void addMethods() {
        for (Method method : methods) {
            String name = method.getName();

            indentLine(content);
            content.append("private static ");
            Class<?> returnType = method.getReturnType();
            if (returnType != null) {
                content.append(returnType.getSimpleName());
            } else {
                content.append("void");
            }
            content.append(" ");
            content.append(name).append("()");
            if (method.throwsException()) {
                content.append(" throws Exception");
            }
            content.append(" ");
            openBlock(content, true);
            content.append(method.getMethodContent());
            closeBlock(content);
            breakLine(content);
        }
    }

    public RobotoyVariable newRobotoyVariable() {
        robotoyVariable = new RobotoyVariable();
        robotoyVariable.setStatus(VariableStatus.IN_DECLARATION);
        return robotoyVariable;
    }

    public void removeRobotoyMethodInEditing() {
        this.methods.add(robotoyMethod);
        robotoyMethod = null;
    }

    private void addMain() {
        indentLine(content);
        content.append("public static void main(String[] args) ");
        openBlock(content, true);
        addExceptionTreatmentBlock();
        closeBlock(content);
        breakLine(content);
    }

    private void addInitialConfiguration() {
        content.append(initialConfigurationContent.toString());
    }

    public void appendContent(String content) {
        contentInEditing.append(content);
    }
    
    private void addExceptionTreatmentBlock() {
        indentLine(content);
        content.append("try ");
        openBlock(content, true);
        indentLine(content);
        addInitialConfiguration();
        content.append("_ROBOT.onStart();");
        breakLine(content);
        addMainContent();
        indentLine(content);
        content.append("_ROBOT.onEnd();");
        breakLine(content);
        closeBlock(content, false);
        content.append(" catch (Exception e) ");
        openBlock(content, true);
        indentLine(content);
        content.append("_ROBOT.manageException(e);");
        breakLine(content);
        closeBlock(content);
    }

    private void addMainContent() {
        content.append(mainContent.toString());
    }

    public void openBlock() {
        openBlock(true);
    }

    public void openBlock(boolean breakLine) {
        openBlock(contentInEditing, breakLine);
    }

    private void openBlock(StringBuilder content, boolean breakLine) {
        content.append("{");

        if (breakLine) {
            breakLine(content);
        }

        tabsAmount++;
    }

    public void closeBlock() {
        closeBlock(true);
    }

    public void closeBlock(boolean breakLine) {
        closeBlock(contentInEditing, breakLine);
    }

    private void closeBlock(StringBuilder content) {
        closeBlock(content, true);
    }

    private void closeBlock(StringBuilder content, boolean breakLine) {
        tabsAmount--;
        indentLine(content);
        content.append("}");
        if (breakLine) {
            breakLine(content);
        }
    }

    public void addDoubleImport() {
        if (!imports.contains(Double.class)) {
            addImport("java.lang.Double");
        }
    }
    
    private boolean containsAttributeWithName(String attributeName) {
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(attributeName)) {
                return true;
            }
        }
        return false;
    }

    public void breakLine() {
        breakLine(contentInEditing);
    }

    private void breakLine(StringBuilder content) {
        content.append(LINE_SEPARATOR);
    }

    public void indentLine() {
        indentLine(contentInEditing);
    }

    private void indentLine(StringBuilder content) {
        for (int i = 0; i < tabsAmount; i++) {
            content.append(TAB);
        }
    }

    private void addRobotImport(Class<? extends IRobot> clazz) {
        String attributeName = "_ROBOT";
        if (!containsAttributeWithName(attributeName)) {
            addImport("br.inf.furb.tcc.robotoy.robot.lejos.LejosRobot");
            Attribute attribute = new Attribute(clazz.getSimpleName(), attributeName, "new ".concat(clazz.getSimpleName()).concat("()"));
            attribute.makeStatic();
            attribute.makeFinal();
            attributes.add(attribute);
        }
    }

    public void finalizeInitialConfiguration() {
        contentInEditing = mainContent;
    }

}
