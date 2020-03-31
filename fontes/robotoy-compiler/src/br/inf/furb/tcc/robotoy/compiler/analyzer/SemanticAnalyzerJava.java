package br.inf.furb.tcc.robotoy.compiler.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage;
import br.inf.furb.tcc.robotoy.compiler.generator.java.ComparisonConditionPart;
import br.inf.furb.tcc.robotoy.compiler.generator.java.ComparisonType;
import br.inf.furb.tcc.robotoy.compiler.generator.java.Condition;
import br.inf.furb.tcc.robotoy.compiler.generator.java.ConditionPart;
import br.inf.furb.tcc.robotoy.compiler.generator.java.ExpressionBetweenParenthesis;
import br.inf.furb.tcc.robotoy.compiler.generator.java.GenericCodeGenerator;
import br.inf.furb.tcc.robotoy.compiler.generator.java.InvokedMethod;
import br.inf.furb.tcc.robotoy.compiler.generator.java.JavaClassGenerator;
import br.inf.furb.tcc.robotoy.compiler.generator.java.Method;
import br.inf.furb.tcc.robotoy.compiler.generator.java.Parameter;
import br.inf.furb.tcc.robotoy.compiler.generator.java.RobotoyVariable;
import br.inf.furb.tcc.robotoy.compiler.generator.java.Type;
import br.inf.furb.tcc.robotoy.compiler.generator.java.VariableStatus;
import br.inf.furb.tcc.robotoy.robot.generic.Color;
import br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition;

public class SemanticAnalyzerJava implements Constants, GenericSemanticAnalyzer {

    private final GenericCodeGenerator generator;

    private static final String DOUBLE_CONVERSION = "Double.toString(%s)";

    private int line;

    private boolean hasSignalToAppend;
    private String signal;

    private boolean hasExpressionBetweenParenthesis;
    private ExpressionBetweenParenthesis expressionBetweenParenthesis;

    private Parameter parameter;

    private Condition condition;
    private ConditionPart conditionPart;
    private ComparisonConditionPart comparisonConditionPart;

    private Token token;

    private List<InvokedMethod> invokedMethods;
    private List<DeclaredMethod> declaredMethods;

    public SemanticAnalyzerJava(GenericCodeGenerator generator) {
        this.generator = generator;
        this.invokedMethods = new ArrayList<InvokedMethod>();
        this.declaredMethods = new ArrayList<DeclaredMethod>();
        this.line = 1;
    }

    /* (non-Javadoc)
     * @see br.inf.furb.tcc.robotoy.compiler.analyzer.GenericSemanticAnalyzer#executeAction(int, br.inf.furb.tcc.robotoy.compiler.analyzer.Token)
     */
    @Override
    public void executeAction(int action, Token token) throws SemanticError {
        switch (action) {
            case 1:
                defineVariableType(token);
                break;
            case 2:
                appendVariableName(token);
                break;
            case 3:
                endCommand();
                break;
            case 4:
                appendVariable(token);
                break;
            case 5:
                appendNumber(token);
                break;
            case 6:
                appendColor(token);
                break;
            case 7:
                appendIdentifyColor(token);
                break;
            case 8:
                appendText(token);
                break;
            case 9:
                appendOpeningParenthesis();
                break;
            case 10:
                appendClosingParenthesis();
                break;
            case 11:
                prepareSignalAppend(token);
                break;
            case 12:
                appendConcatenationOperator(token);
                break;
            case 13:
                appendArithmeticOperator(token);
                break;
            case 18:
                closeBlock();
                break;
            case 19:
                appendIf();
                break;
            case 20:
                appendOpenBlock();
                break;
            case 21:
                appendElse();
                break;
            case 22:
                startNewCondition();
                break;
            case 23:
                appendNegation();
                break;
            case 24:
                appendHasObstacle();
                break;
            case 25:
                appendAnd();
                break;
            case 26:
                appendOr();
                break;
            case 27:
                appendWhile();
                break;
            case 28:
                appendComparisonOperator(token);
                break;
            case 29:
                appendRobotCall();
                break;
            case 30:
                appendVerticalDirection(token);
                break;
            case 31:
                appendHorizontalDirection(token);
                break;
            case 32:
                appendMultiuseMotor();
                break;
            case 33:
                appendWalk();
                break;
            case 34:
                appendTurn();
                break;
            case 35:
                appendWheel();
                break;
            case 36:
                appendSpin();
                break;
            case 37:
                appendStop();
                break;
            case 38:
                appendWalking();
                break;
            case 39:
                appendTurning();
                break;
            case 40:
                appendSpinning();
                break;
            case 41:
                appendWrite();
                break;
            case 42:
                appendBeep();
                break;
            case 43:
                appendParameter();
                break;
            case 44:
                defineMethodName(token);
                break;
            case 45:
                endMethodDeclaration();
                break;
            case 46:
                assertMethodsDeclaration();
                break;
            case 47:
                storeToken(token);
                break;
            case 48:
                appendMethodInvoked();
                break;
            case 49:
                appendVariableName(this.token);
                break;
            case 50:
                prepareParameterTypeNumberAppend();
                break;
            case 51:
                prepareParameterTypeTextAppend();
                break;
            case 52:
                increaseLine(token);
                break;
            default:
                break;
        }
    }

    private void increaseLine(Token token) {
        String lexeme = token.getLexeme();
        assert lexeme.matches("^(\\n)+$");

        int amount = StringUtils.countMatches(lexeme, "\n");
        line = line + amount;
    }

    private void prepareParameterTypeNumberAppend() {
        parameter = new Parameter(Type.NUMBER);
    }

    private void prepareParameterTypeTextAppend() {
        parameter = new Parameter(Type.TEXT);
    }

    private void appendMethodInvoked() {
        String methodName = token.getLexeme();
        invokedMethods.add(new InvokedMethod(line, methodName));
        generator.indentLine();
        appendContent(methodName.concat("()"));
    }

    private void storeToken(Token token) {
        this.token = token;
    }

    private void endMethodDeclaration() {
        generator.removeRobotoyMethodInEditing();
    }

    private void defineMethodName(Token token) {
        Method method = generator.newRobotoyMethod();
        method.setName(token.getLexeme());
        declaredMethods.add(new DeclaredMethod(line, method));
    }

    private void assertMethodsDeclaration() throws SemanticError {
        assertInvokedMethodsDeclaration();
        assertDuplicateMethodsNotExists();
    }

    private void assertDuplicateMethodsNotExists() throws SemanticError {
        List<DeclaredMethod> otherDeclaredMethods = new ArrayList<DeclaredMethod>(declaredMethods);
        for (DeclaredMethod declaredMethod : declaredMethods) {
            otherDeclaredMethods.remove(0);
            for (DeclaredMethod otherDeclaredMethod : otherDeclaredMethods) {
                String name = declaredMethod.getMethod().getName();
                if (name.equals(otherDeclaredMethod.getMethod().getName())) {
                    throw new SemanticError(otherDeclaredMethod.getLine(), String.format(SemanticValidationMessage.METHOD_ALREADY_EXISTS, name));
                }
            }
        }
    }

    private void assertInvokedMethodsDeclaration() throws SemanticError {
        for (InvokedMethod invokedMethod : invokedMethods) {
            if (!generator.containsRobotoyMethodWithName(invokedMethod.getName())) {
                throw new SemanticError(invokedMethod.getLine(), String.format(SemanticValidationMessage.METHOD_NOT_DECLARED, invokedMethod.getName()));
            }
        }
    }

    private void appendParameter() {
        String content = parameter.getParameter(true);
        parameter = null;
        appendContent(content);
    }

    private void appendBeep() {
        appendContent("beep");
    }

    private void appendWrite() {
        appendContent("write");
    }

    private void appendSpinning() {
        appendContent("Spinning");
    }

    private void appendTurning() {
        appendContent("Turning");
    }

    private void appendWalking() {
        appendContent("Walking");
    }

    private void appendStop() {
        appendContent("stop");
    }

    private void appendSpin() {
        appendContent("spin");
    }

    private void appendWheel() {
        appendContent("Wheel");
    }

    private void appendTurn() {
        appendContent("turn");
    }

    private void appendWalk() {
        appendContent("walk");
    }

    private void appendMultiuseMotor() {
        appendContent("MultiuseMotor");
    }

    private void appendRobotCall() {
        generator.indentLine();
        appendContent("_ROBOT.");
    }

    private void appendHorizontalDirection(Token token) {
        String direction = token.getLexeme();
        assert "esquerda".equalsIgnoreCase(direction) || "direita".equalsIgnoreCase(direction);

        String content = "";
        if ("esquerda".equalsIgnoreCase(direction)) {
            content = content.concat("Left");
        } else if ("direita".equalsIgnoreCase(direction)) {
            content = content.concat("Right");
        }

        appendContent(content);
    }

    private void appendVerticalDirection(Token token) {
        String direction = token.getLexeme();
        assert "frente".equalsIgnoreCase(direction) || "trás".equalsIgnoreCase(direction);

        String content = "";
        if ("frente".equalsIgnoreCase(direction)) {
            content = content.concat("Forward");
        } else if ("trás".equalsIgnoreCase(direction)) {
            content = content.concat("Backward");
        }

        appendContent(content);
    }

    private void appendComparisonOperator(Token token) {
        String operator = token.getLexeme();
        assert operator.equals("=") || operator.equals("=/") || operator.equals("<") || operator.equals("<=") || operator.equals(">") || operator.equals(">=");

        assert comparisonConditionPart != null;
        comparisonConditionPart.setComparisonType(ComparisonType.getJavaOperatorByRobotoyOperator(operator));
    }

    private void appendOr() throws SemanticError {
        assert condition != null;
        appendConditionPart();
        condition.appendOr();
    }

    private void appendAnd() throws SemanticError {
        assert condition != null;
        appendConditionPart();
        condition.appendAnd();
    }

    private void appendConditionPart() throws SemanticError {
        if (conditionPart != null) {
            condition.appendConditionPart(conditionPart);
            conditionPart = null;
            return;
        }

        if (comparisonConditionPart != null) {
            condition.appendComparisonConditionPart(comparisonConditionPart, line);
            comparisonConditionPart = null;
        }
    }

    private void startNewCondition() {
        if (condition == null) {
            condition = new Condition();
        }
    }

    private void appendNegation() {
        conditionPart = new ConditionPart();
        conditionPart.appendValue("!");
    }

    private void appendHasObstacle() {
        if (conditionPart == null) {
            conditionPart = new ConditionPart();
        }
        conditionPart.appendValue("_ROBOT.hasObstacle()");
    }

    private void appendElse() {
        generator.closeBlock(false);
        appendContent(" else ");
        generator.openBlock();
    }

    private void appendIf() {
        generator.indentLine();
        appendContent("if (");
    }

    private void appendWhile() {
        generator.indentLine();
        appendContent("while (");
    }

    private void appendOpenBlock() throws SemanticError {
        appendConditionPart();
        appendCondition();

        appendContent(") ");
        generator.openBlock();
    }

    private void appendCondition() throws SemanticError {
        assert condition != null;
        appendContent(condition.getCondition());
        condition = null;
    }

    private void closeBlock() {
        generator.closeBlock();
    }

    private void prepareSignalAppend(Token token) throws SemanticError {
        if (hasSignalToAppend) {
            throw new SemanticError(line, SemanticValidationMessage.SIGNAL_ALREADY_EXISTS);
        }
        signal = token.getLexeme();
        hasSignalToAppend = true;
        assert signal.equals("+") || signal.equals("-");
    }

    private void appendOpeningParenthesis() {
        String openingParenthesis = "(";

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            if (variable.getType() == Type.TEXT) {
                hasExpressionBetweenParenthesis = true;
                if (expressionBetweenParenthesis == null) {
                    expressionBetweenParenthesis = new ExpressionBetweenParenthesis(DOUBLE_CONVERSION);
                }
                expressionBetweenParenthesis.appendOpeningParenthesis();
                return;
            }
        }

        if (condition != null) {
            initializeComparisonConditionIfNecessary();
            appendContent(openingParenthesis);
            return;
        }

        if (parameter != null) {
            appendContent(openingParenthesis);
            return;
        }

        appendContent(openingParenthesis);
    }

    private void appendClosingParenthesis() {
        String closingParenthesis = ")";

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            if (variable.getType() == Type.TEXT) {
                expressionBetweenParenthesis.appendEndingParenthesis();
                if (expressionBetweenParenthesis.isComplete()) {
                    appendContent(expressionBetweenParenthesis.getExpression());
                    hasExpressionBetweenParenthesis = false;
                    expressionBetweenParenthesis = null;
                }
                return;
            }
        }

        if (condition != null) {
            initializeComparisonConditionIfNecessary();
            appendContent(closingParenthesis);
            return;
        }

        if (parameter != null) {
            appendContent(closingParenthesis);
            return;
        }

        appendContent(closingParenthesis);
    }

    private void defineVariableType(Token token) {
        if (token == null || token.getLexeme().equals("\n")) {
            return;
        }

        RobotoyVariable variable = generator.newRobotoyVariable();

        String lexeme = token.getLexeme();
        if (lexeme.equalsIgnoreCase(Type.NUMBER.getRobotoyType())) {
            variable.setType(Type.NUMBER);

        } else if (lexeme.equalsIgnoreCase(Type.TEXT.getRobotoyType())) {
            variable.setType(Type.TEXT);

        } else if (lexeme.equalsIgnoreCase(Type.COLOR.getRobotoyType())) {
            generator.addImport("br.inf.furb.tcc.robotoy.robot.generic.ColorDefinition");
            variable.setType(Type.COLOR);
        }
    }

    private void appendVariableName(Token token) throws SemanticError {
        String variableName = token.getLexeme();

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable == null) { // Não é declaração de atributo, e sim atualização do valor do atributo dentro do main.
            changeVariableValue(variableName);
            return;
        }

        assertNameNotRepeated(variableName);

        generator.indentLine();
        variable.setName(variableName);
        appendContent(variableName.concat(" = "));
        appendDoubleCastInVariable(variable);
    }

    private void assertNameNotRepeated(String name) throws SemanticError {
        if (generator.containsRobotoyVariableWithName(name)) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.VARIABLE_ALREADY_EXISTS, name));
        }
    }

    private void changeVariableValue(String variableName) throws SemanticError {
        assertVariableDeclaration(variableName);

        RobotoyVariable variable = generator.getRobotoytVariable(variableName);
        assert variable != null;
        generator.setRobotoyVariableInEditing(variable);

        generator.indentLine();
        appendContent(variableName);
        appendContent(" = ");
        appendDoubleCastInVariable(variable);
    }

    private void endCommand() {
        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            generator.removeRobotoyVariableInEditing();
        }

        appendContent(";");
        generator.breakLine();
    }

    private void appendVariable(Token token) throws SemanticError {
        String variableNameToAppend = token.getLexeme();
        assertVariableDeclaration(variableNameToAppend);

        RobotoyVariable variableToAppend = generator.getRobotoytVariable(variableNameToAppend);
        Type variableTypeToAppend = variableToAppend.getType();
        if (hasSignalToAppend) {
            assertCanAddSignalInType(variableTypeToAppend);
        }

        RobotoyVariable variableInEditing = generator.getRobotoyVariableInEditing();
        if (variableInEditing != null) {
            appendVariableInVariable(variableNameToAppend);
            return;
        }

        if (condition != null) {
            initializeComparisonConditionIfNecessary();
            if (variableTypeToAppend == Type.COLOR) {
                variableNameToAppend = variableNameToAppend.concat(".getColor()");
            }
            comparisonConditionPart.setType(variableTypeToAppend, line);
            appendContent(variableNameToAppend);
            return;
        }

        if (parameter != null) {
            parameter.assertContentTypeToAppend(variableTypeToAppend, line);
            appendContent(variableNameToAppend);
            return;
        }
    }

    private void appendVariableInVariable(String variableNameToAppend) throws SemanticError {
        RobotoyVariable variableInEditing = generator.getRobotoyVariableInEditing();
        RobotoyVariable variableToAppend = generator.getRobotoytVariable(variableNameToAppend);
        assertCompatibleTypes(variableInEditing, variableToAppend);

        Type variableTypeInEditing = variableInEditing.getType();
        Type variableTypeToAppend = variableToAppend.getType();
        if (variableTypeInEditing == Type.TEXT && variableTypeToAppend == Type.NUMBER) {
            appendFormattedDouble(variableNameToAppend);
        } else if (variableTypeInEditing == Type.TEXT && variableTypeToAppend == Type.COLOR) {
            appendContent(variableNameToAppend.concat(".getName()"));
        } else {
            appendContent(variableNameToAppend);
        }
    }

    private void assertCanAddSignalInType(Type type) throws SemanticError {
        if (type != Type.NUMBER) {
            throw new SemanticError(line, SemanticValidationMessage.CANNOT_ADD_SIGNAL);
        }
    }

    private void assertCompatibleTypes(RobotoyVariable variable, RobotoyVariable otherVariable) throws SemanticError {
        if (variable.getType() == otherVariable.getType()) { // TODO Tem que tratar o toString de cor e o double (Double.toString(double)) na atribuição de um atributo com tipo String.
            return;
        }

        if (variable.getType() == Type.TEXT) { // Cor e double são aceitos como String.
            return;
        }

        throw new SemanticError(line, String.format(SemanticValidationMessage.INCOMPATIBLE_TYPES, variable.getName(), variable.getType().getRobotoyType(), otherVariable.getName(), otherVariable.getType().getRobotoyType()));
    }

    private void appendNumber(Token token) throws SemanticError {
        String number = token.getLexeme().replace(',', '.');

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            appendNumberInVariable(variable, number);
            return;
        }

        if (condition != null) {
            initializeComparisonConditionIfNecessary();
            comparisonConditionPart.setType(Type.NUMBER, line);
            appendContent(number);
            return;
        }

        if (parameter != null) {
            parameter.assertContentTypeToAppend(Type.NUMBER, line);
            appendContent(number);
            return;
        }
    }

    private void appendNumberInVariable(RobotoyVariable variable, String number) throws SemanticError {
        assertCompatibleTypeForNumber(variable);

        Type type = variable.getType();
        if (type == Type.NUMBER) {
            appendContent(number);
        } else if (type == Type.TEXT) {
            appendFormattedDouble(number);
        }
    }

    private void appendColor(Token token) throws SemanticError {
        if (hasSignalToAppend) {
            throw new SemanticError(line, SemanticValidationMessage.CANNOT_ADD_SIGNAL);
        }

        String colorName = token.getLexeme();

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            appendColorInVariable(variable, colorName);
            return;
        }

        if (condition != null) {
            initializeComparisonConditionIfNecessary();
            comparisonConditionPart.setType(Type.COLOR, line);
            appendContent(getCorrespondingColor(colorName));
            return;
        }

        if (parameter != null) {
            parameter.assertContentTypeToAppend(Type.COLOR, line);
            return;
        }
    }

    private void appendColorInVariable(RobotoyVariable variable, String colorName) throws SemanticError {
        assertCompatibleTypeForColor(variable);
        Type type = variable.getType();
        if (type == Type.COLOR) {
            String colorDeclaration = "new ColorDefinition(\"%s\", %s)";
            appendContent(String.format(colorDeclaration, colorName, getCorrespondingColor(colorName)));
        } else if (type == Type.TEXT) {
            String colorDeclaration = "\"%s\"";
            appendContent(String.format(colorDeclaration, colorName));
        }
    }

    private void appendText(Token token) throws SemanticError {
        if (hasSignalToAppend) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.CANNOT_ADD_SIGNAL));
        }

        String text = token.getLexeme();

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            appendVariableInVariable(text, variable);
        }

        if (condition != null) {
            initializeComparisonConditionIfNecessary();
            comparisonConditionPart.setType(Type.TEXT, line);
            appendContent(text);
            return;
        }

        if (parameter != null) {
            parameter.assertContentTypeToAppend(Type.TEXT, line);
            appendContent(text);
            return;
        }
    }

    private void appendVariableInVariable(String text, RobotoyVariable variable) throws SemanticError {
        assertCompatibleTypeForText(variable);
        appendContent(text);
    }

    private String getCorrespondingColor(String colorName) {
        generator.addImport("br.inf.furb.tcc.robotoy.robot.generic.Color");

        if (colorName.toLowerCase().contains("pret")) {
            return "Color.BLACK";
        } else if (colorName.toLowerCase().contains("branc")) {
            return "Color.WHITE";
        } else if (colorName.toLowerCase().contains("verde")) {
            return "Color.GREEN";
        } else if (colorName.toLowerCase().contains("azul")) {
            return "Color.BLUE";
        } else if (colorName.toLowerCase().contains("amarel")) {
            return "Color.YELLOW";
        } else if (colorName.toLowerCase().contains("vermelh")) {
            return "Color.RED";
        }
        return null;
    }

    private void appendIdentifyColor(Token token) throws SemanticError {
        if (hasSignalToAppend) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.CANNOT_ADD_SIGNAL));
        }

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            assertCompatibleTypeForColor(variable);
            Type type = variable.getType();
            if (type == Type.COLOR) {
                appendContent("_ROBOT.detectColor()");
            } else if (type == Type.TEXT) {
                appendContent("_ROBOT.detectColor().getName()");
            }
            return;
        }

        if (condition != null) {
            initializeComparisonConditionIfNecessary();
            comparisonConditionPart.setType(Type.COLOR, line);
            appendContent("_ROBOT.detectColor().getColor()");
            return;
        }

        if (parameter != null) {
            parameter.assertContentTypeToAppend(Type.COLOR, line);
            return;
        }
    }

    private void assertCompatibleTypeForNumber(RobotoyVariable variable) throws SemanticError {
        Type type = variable.getType();
        assert type != null;
        if (type == Type.COLOR) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.CANNOT_ATTRIBUTE_NUMBER, variable.getName(), type.getRobotoyType()));
        }
    }

    private void assertCompatibleTypeForColor(RobotoyVariable variable) throws SemanticError {
        Type type = variable.getType();
        assert type != null;
        if (type == Type.NUMBER) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.CANNOT_ATTRIBUTE_COLOR, variable.getName(), type.getRobotoyType()));
        }
    }

    private void assertCompatibleTypeForText(RobotoyVariable variable) throws SemanticError {
        Type type = variable.getType();
        assert type != null;
        if (type != Type.TEXT) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.CANNOT_ATTRIBUTE_TEXT, variable.getName(), type.getRobotoyType()));
        }
    }

    private void assertVariableDeclaration(String variableName) throws SemanticError {
        if (!generator.hasRobotoyVariable(variableName)) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.VARIABLE_NOT_DECLARED, variableName));
        }
    }

    private void appendDoubleCastInVariable(RobotoyVariable variable) {
        if (variable.getType() == Type.NUMBER) {
            appendContent("(double) ");
        }
    }

    private void appendFormattedDouble(String doubleValue) {
        generator.addDoubleImport();

        if (hasSignalToAppend) {
            hasSignalToAppend = false;
            doubleValue = signal.concat(doubleValue);
            signal = null;
        }

        if (hasExpressionBetweenParenthesis) {
            expressionBetweenParenthesis.appendDecimalFormat();
            expressionBetweenParenthesis.appendContent(doubleValue);
            return;
        }

        appendContent(String.format(DOUBLE_CONVERSION, doubleValue));
    }

    private void appendContent(String content) {
        if (hasSignalToAppend) {
            hasSignalToAppend = false;
            content = signal.concat(content);
            signal = null;
        }

        if (hasExpressionBetweenParenthesis && !expressionBetweenParenthesis.isComplete()) {
            expressionBetweenParenthesis.appendContent(content);
            return;
        }

        if (condition != null) {
            if (comparisonConditionPart != null) {
                comparisonConditionPart.appendContent(content);
                return;
            }
        }

        if (parameter != null) {
            parameter.appendContent(content);
            return;
        }

        generator.appendContent(content);
    }

    private void appendArithmeticOperator(Token token) throws SemanticError {
        String arithmeticOperator = token.getLexeme();
        assert "+".equals(arithmeticOperator) || "-".equals(arithmeticOperator) || "*".equals(arithmeticOperator) || "/".equals(arithmeticOperator) || "%".equals(arithmeticOperator);
        arithmeticOperator = " ".concat(arithmeticOperator).concat(" ");

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            assertCompatibleTypeForArithmeticOperator(variable.getType(), arithmeticOperator.trim());
            appendContent(arithmeticOperator);
            return;
        }

        if (condition != null) {
            initializeComparisonConditionIfNecessary();
            assertCompatibleTypeForArithmeticOperator(comparisonConditionPart.getType(), arithmeticOperator.trim());
            appendContent(arithmeticOperator);
            return;
        }

        if (parameter != null) {
            assertCompatibleTypeForArithmeticOperator(parameter.getType(), arithmeticOperator.trim());
            appendContent(arithmeticOperator);
            return;
        }
    }

    private void initializeComparisonConditionIfNecessary() {
        if (comparisonConditionPart == null) {
            comparisonConditionPart = new ComparisonConditionPart();
        }
    }

    private void assertCompatibleTypeForArithmeticOperator(Type type, String arithmeticOperator) throws SemanticError {
        if (type == Type.TEXT && hasExpressionBetweenParenthesis) {
            return;
        }

        if (type != Type.NUMBER) {
            throw new SemanticError(line, String.format(SemanticValidationMessage.CANNOT_ADD_ARITHMETIC_OPERATOR, arithmeticOperator));
        }
    }

    private void appendConcatenationOperator(Token token) throws SemanticError {
        String concatenationOperator = token.getLexeme();
        assert ".".equals(concatenationOperator);
        concatenationOperator = " + ";

        RobotoyVariable variable = generator.getRobotoyVariableInEditing();
        if (variable != null) {
            assertCompatibleTypeForConcatenationOperator(variable.getType());
            appendContent(concatenationOperator);
            return;
        }

        if (condition != null) {
            assertCompatibleTypeForConcatenationOperator(comparisonConditionPart.getType());
            appendContent(concatenationOperator);
            return;
        }

        if (parameter != null) {
            assertCompatibleTypeForConcatenationOperator(parameter.getType());
            appendContent(concatenationOperator);
            return;
        }
    }

    private void assertCompatibleTypeForConcatenationOperator(Type type) throws SemanticError {
        if (type != Type.TEXT) {
            throw new SemanticError(line, SemanticValidationMessage.CANNOT_ADD_CONCATENATION_OPERATOR);
        }
    }

}
