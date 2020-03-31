package br.inf.furb.tcc.robotoy.compiler.generator.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.inf.furb.tcc.robotoy.common.PropertyFile;
import br.inf.furb.tcc.robotoy.robot.generic.IRobot;

public final class ArduinoClassGenerator implements GenericCodeGenerator {

    private static final String TAB = "\t";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private String arduinoFileName;

    private final List<String> imports;
    private final List<String> globalVariables;
    private final List<String> setups;
    private final StringBuilder initialConfigurationContent;
    private final StringBuilder content;
    private final StringBuilder setupContent;
    private final StringBuilder mainContent;
    private final List<Attribute> attributes;
    private final List<RobotoyVariable> robotoyVariables;
    private final List<Method> methods;
    private StringBuilder contentInEditing;

    private RobotoyVariable robotoyVariable;
    private Method robotoyMethod;

    private int tabsAmount;

    public ArduinoClassGenerator() {
        tabsAmount = 2;
        imports = new ArrayList<String>();
        globalVariables = new ArrayList<String>();
        setups = new ArrayList<String>();
        initialConfigurationContent = new StringBuilder();
        content = new StringBuilder();
        setupContent = new StringBuilder();
        mainContent = new StringBuilder();
        contentInEditing = initialConfigurationContent;
        attributes = new ArrayList<Attribute>();
        robotoyVariables = new ArrayList<RobotoyVariable>();
        methods = new ArrayList<Method>();
    }

    public void createFile(File arduinoFile) throws IOException {
        arduinoFileName = arduinoFile.getName();

        content.setLength(0);
        tabsAmount = 0;

        generateContent();

        try (PrintStream stream = new PrintStream(arduinoFile)) {       
            stream.print(content.toString());
        }        
 
    }

    private void generateContent() {
        addClassDeclaration();
    }

    public Method newRobotoyMethod() {
        if (contentInEditing == mainContent) {
         
        }

        robotoyMethod = new Method();
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
        addGlobalVariables();
        addAttributes();
        addSetup();
        addMain();
        addMethods();
        
        //addMethodsRobo();
        
        /*
        addMethodEscrever();
        addMethodAndarFrente();
        addMethodAndarTras();
        addMethodVirarDireita();
        addMethodVirarEsquerda();
        addMethodSetarVelocidadeDeslocamento();
        addMethodSetarVelocidadeRotacao();
        addMethodPararAndar();
        addMethodPararGirar();
        addMethodPararVirar();
        addMethodCalcularDistanciaCentro();
        addMethodTemObstaculo();
        addMethodLerSonar();
        addMethodEmitirSom();
        addMethodVirarMotorMultiusoDireita();
        addMethodVirarMotorMultiusoEsquerda();
        addMethodIdentificarCor();
        addMethodGirarRodaDireitaFrente();
        addMethodGirarRodaEsquerdaFrente();
        addMethodGirarRodaDireitaTras();
        addMethodGirarRodaEsquerdaTras();*/
    }
    
    private void addMethodsRobo() {
        String file = Paths.get(PropertyFile.GEN_FOLDER, "arduino-robot-methods.ino").toString();
        try {
            String methods = new String(Files.readAllBytes(new File(file).toPath()));
            content.append(methods);
        } catch (IOException e) {
            System.out.println("Erro ao carregar métodos do robô Arduino.");
            throw new RuntimeException(e);
        } 
    }

    public void addImport(String clazz) {
        if (!imports.contains(clazz)) {
            imports.add(clazz);
        }
    }

    private void addImports() {
        for (Object toImport : imports) {
            content.append("#include <").append((String)toImport.toString()).append(">");
            breakLine(content);
        }
        breakLine(content);
    }
    
    public void addGlobalVariable(String globalVariable) {
        if (!globalVariables.contains(globalVariable)) {
            globalVariables.add(globalVariable);
        }
    }
    
    private void addGlobalVariables() {
        for (Object toGlobalVariable : globalVariables) {
            breakLine(content);
            content.append((String)toGlobalVariable.toString());
            breakLine(content);
        }
        breakLine(content);
    }
    
    public void addSetup(String setup) {
        setups.add(setup);
    }
    
    private void addSetups() {
        for (Object toSetup : setups) {
            breakLine(content);
            indentLine(content);
            content.append((String)toSetup.toString());
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
            content.append(variable.getType().getCorrespondingClassInJava()).append(" ").append(variable.getName()).append(";");
            breakLine(content);
        }

        if (!attributes.isEmpty()) {
            breakLine(content);
        }
        
        breakLine(content);
    }

    private void addMethods() {
        for (Method method : methods) {
            String name = method.getName();

            indentLine(content);
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
    
    private void addSetup() {
        indentLine(content);
        content.append("void setup() ");
        openBlock(content, true);
        indentLine(content);
        content.append("Serial.begin(9600);");
        breakLine(content);
        addSetups();
        indentLine(content);
        content.append("delay(900);");
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodEscrever() {
        indentLine();
        content.append("void _escrever(String texto)");
        openBlock(content, true);
        indentLine(content);
        content.append("lcd.clear();");
        breakLine(content);
        indentLine(content);
        content.append("lcd.print(texto);");
        breakLine(content);
        indentLine(content);
        content.append("delay(2000);");
        breakLine(content);
        indentLine(content);
        content.append("lcd.clear();");    
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodAndarFrente() {
        indentLine();
        content.append("void _andarFrente(int qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("motorEsquerdo.run(FORWARD);");
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(FORWARD);");
        breakLine(content);
        indentLine(content);
        content.append("delay(700);");
        breakLine(content);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("delay(900);"); 
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;"); 
        breakLine(content);
        closeBlock(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodAndarTras() {
        indentLine();
        content.append("void _andarTras(int qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("motorEsquerdo.run(BACKWARD);");
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(BACKWARD);");
        breakLine(content);
        indentLine(content);
        content.append("delay(700);");
        breakLine(content);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("delay(900);");  
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");  
        breakLine(content);
        closeBlock(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodVirarDireita() {
        indentLine();
        content.append("void _virarDireita(int qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("_setarVelocidadeRotacao();");
        breakLine(content);
        indentLine(content);
        content.append("motorEsquerdo.run(FORWARD);");
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(BACKWARD);");
        breakLine(content);
        indentLine(content);
        content.append("delay(730);");
        breakLine(content);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("_setarVelocidadeDeslocamento();");  
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");  
        breakLine(content);
        closeBlock(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodVirarEsquerda() {
        indentLine();
        content.append("void _virarEsquerda(int qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("_setarVelocidadeRotacao();");
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(FORWARD);");
        breakLine(content);
        indentLine(content);
        content.append("motorEsquerdo.run(BACKWARD);");
        breakLine(content);
        indentLine(content);
        content.append("delay(730);");
        breakLine(content);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("_setarVelocidadeDeslocamento();"); 
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");  
        breakLine(content);
        closeBlock(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodGirarRodaEsquerdaFrente() {
        indentLine();
        content.append("void _girarRodaEsquerdaFrente()");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < 1)");
        openBlock(content, true);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("_setarVelocidadeDeslocamento();");
        breakLine(content);
        indentLine(content);
        content.append("motorEsquerdo.run(FORWARD);");
        breakLine(content);
        indentLine(content);
        content.append("delay(730);");
        breakLine(content);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");  
        breakLine(content);
        closeBlock(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodGirarRodaDireitaFrente() {
        indentLine();
        content.append("void _girarRodaDireitaFrente()");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < 1)");
        openBlock(content, true);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("_setarVelocidadeDeslocamento();");
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(FORWARD);");
        breakLine(content);
        indentLine(content);
        content.append("delay(730);");
        breakLine(content);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");  
        breakLine(content);
        closeBlock(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodGirarRodaEsquerdaTras() {
        indentLine();
        content.append("void _girarRodaEsquerdaTras()");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < 1)");
        openBlock(content, true);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("_setarVelocidadeDeslocamento();");
        breakLine(content);
        indentLine(content);
        content.append("motorEsquerdo.run(BACKWARD);");
        breakLine(content);
        indentLine(content);
        content.append("delay(730);");
        breakLine(content);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");  
        breakLine(content);
        closeBlock(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodGirarRodaDireitaTras() {
        indentLine();
        content.append("void _girarRodaDireitaTras()");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < 1)");
        openBlock(content, true);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("_setarVelocidadeDeslocamento();");
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(BACKWARD);");
        breakLine(content);
        indentLine(content);
        content.append("delay(730);");
        breakLine(content);
        indentLine(content);
        content.append("_pararAndar();");  
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");  
        breakLine(content);
        closeBlock(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodSetarVelocidadeDeslocamento() {
        indentLine();
        content.append("void _setarVelocidadeDeslocamento()");
        openBlock(content, true);
        indentLine(content);
        content.append("motorEsquerdo.setSpeed(150);");  
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.setSpeed(150);");
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodSetarVelocidadeRotacao() {
        indentLine();
        content.append("void _setarVelocidadeRotacao()");
        openBlock(content, true);
        indentLine(content);
        content.append("motorEsquerdo.setSpeed(130);");  
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.setSpeed(130);");
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodPararAndar() {
        indentLine();
        content.append("void _pararAndar()");
        openBlock(content, true);
        indentLine(content);
        content.append("motorEsquerdo.run(RELEASE);");  
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(RELEASE);");
        breakLine(content);
        indentLine(content);
        content.append("delay(500);");  
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodPararGirar() {
        indentLine();
        content.append("void _pararGirar()");
        openBlock(content, true);
        indentLine(content);
        content.append("motorEsquerdo.run(RELEASE);");  
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(RELEASE);");
        breakLine(content);
        indentLine(content);
        content.append("delay(500);");  
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodPararVirar() {
        indentLine();
        content.append("void _pararVirar()");
        openBlock(content, true);
        indentLine(content);
        content.append("motorEsquerdo.run(RELEASE);");  
        breakLine(content);
        indentLine(content);
        content.append("motorDireito.run(RELEASE);");
        breakLine(content);
        indentLine(content);
        content.append("delay(500);");  
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodCalcularDistanciaCentro() {
        indentLine();
        content.append("int _calcularDistanciaCentro()");
        openBlock(content, true);
        indentLine(content);
        content.append("motorMultiuso.write(90);");  
        breakLine(content);
        indentLine(content);
        content.append("delay(200);");
        breakLine(content);
        indentLine(content);
        content.append("int leituraDoSonar = _lerSonar();");  
        breakLine(content);
        indentLine(content);
        content.append("delay(600);"); 
        breakLine(content);
        indentLine(content);
        content.append("leituraDoSonar = _lerSonar();"); 
        breakLine(content);
        indentLine(content);
        content.append("delay(600);"); 
        breakLine(content);
        indentLine(content);
        content.append("return leituraDoSonar;"); 
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodTemObstaculo() {
        indentLine();
        content.append("boolean _temObstaculo()");
        openBlock(content, true);
        indentLine(content);
        content.append("int distanciaObstaculo = _calcularDistanciaCentro();");  
        breakLine(content);
        indentLine(content);
        content.append("if (distanciaObstaculo <= 7)");
        openBlock(content, true);
        indentLine(content);
        content.append("return true;");
        breakLine(content);
        closeBlock(content, false);
        content.append(" else ");
        openBlock(content, true);
        indentLine(content);
        content.append("return false;");
        breakLine(content);
        closeBlock(content); 
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodLerSonar() {
        indentLine();
        content.append("int _lerSonar()");
        openBlock(content, true);
        indentLine(content);
        content.append("int distanciaObstaculo = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("digitalWrite(HC_SR04_TRIGGER, LOW);");
        breakLine(content);
        indentLine(content);
        content.append("delayMicroseconds(4);");
        breakLine(content);
        indentLine(content);
        content.append("digitalWrite(HC_SR04_TRIGGER, HIGH);");
        breakLine(content);
        indentLine(content);
        content.append("delayMicroseconds(20);");
        breakLine(content);
        indentLine(content);
        content.append("digitalWrite(HC_SR04_TRIGGER, LOW);");
        breakLine(content);
        indentLine(content);
        content.append("delayMicroseconds(10);");
        breakLine(content);
        indentLine(content);
        content.append("long pulseUs = pulseIn(HC_SR04_ECHO, HIGH);");
        breakLine(content);
        indentLine(content);
        content.append("distanciaObstaculo = pulseUs / 59;");
        breakLine(content);
        indentLine(content);
        content.append("delay(300);");
        breakLine(content);
        indentLine(content);
        content.append("return distanciaObstaculo;");
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
   
    private void addMethodEmitirSom() {
        indentLine();
        content.append("void _emitirSom()");
        openBlock(content, true);
        indentLine(content);
        content.append("tone(A0, 300, 300);");  
        breakLine(content);
        indentLine(content);
        content.append("digitalWrite(BUZZER, HIGH);");
        breakLine(content);
        indentLine(content);
        content.append("delay(500);");
        breakLine(content);
        indentLine(content);
        content.append("digitalWrite(BUZZER, LOW);");
        breakLine(content);
        indentLine(content);
        content.append("delay(500);");
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodVirarMotorMultiusoDireita() {
        indentLine();
        content.append("void _virarMotorMultiusoDireita(int qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("motorMultiuso.write(30);");
        breakLine(content);
        indentLine(content);
        content.append("delay(2000);");
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");
        breakLine(content);
        closeBlock(content, false);
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodVirarMotorMultiusoEsquerda() {
        indentLine();
        content.append("void _virarMotorMultiusoEsquerda(int qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("int i = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("while (i < qtde)");
        openBlock(content, true);
        indentLine(content);
        content.append("motorMultiuso.write(150);");
        breakLine(content);
        indentLine(content);
        content.append("delay(2000);");
        breakLine(content);
        indentLine(content);
        content.append("i = i + 1;");
        breakLine(content);
        closeBlock(content, false);
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMethodIdentificarCor() {
        indentLine();
        content.append("String _identificarCor()");
        openBlock(content, true);
        indentLine(content);
        content.append("int vlVermelho = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("int vlAzul = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("int vlVerde = 0;");  
        breakLine(content);
        indentLine(content);
        content.append("digitalWrite(s2, LOW);");  
        breakLine(content);
        indentLine(content);  
        content.append("digitalWrite(s3, LOW);");  
        breakLine(content);
        indentLine(content); 
        content.append("vlVermelho = pulseIn(out, digitalRead(out) == HIGH ? LOW : HIGH);");  
        breakLine(content);
        indentLine(content); 
        content.append("digitalWrite(s3, HIGH);");  
        breakLine(content);
        indentLine(content); 
        content.append("vlAzul = pulseIn(out, digitalRead(out) == HIGH ? LOW : HIGH);");   
        breakLine(content);
        indentLine(content);
        content.append("digitalWrite(s2, HIGH);");  
        breakLine(content);
        indentLine(content); 
        content.append("vlVerde = pulseIn(out, digitalRead(out) == HIGH ? LOW : HIGH);");   
        breakLine(content);
        indentLine(content);
        content.append("if (vlVermelho < vlAzul && vlVermelho < vlVerde && vlVermelho > 8)");   
        openBlock(content, true);
        indentLine(content);
        content.append("return vermelho;"); 
        breakLine(content);
        closeBlock(content, false);
        content.append(" else if (vlAzul < vlVermelho && vlAzul < vlVerde && vlAzul > 8)");   
        openBlock(content, true);
        indentLine(content);
        content.append("return azul;"); 
        breakLine(content);
        closeBlock(content, false);
        content.append(" else if (vlVerde < vlVermelho && vlVerde < vlAzul && vlVerde > 8)");   
        openBlock(content, true);
        indentLine(content);
        content.append("return verde;"); 
        breakLine(content);
        closeBlock(content, false);
        breakLine(content);
        indentLine(content);
        content.append("delay(2000);"); 
        breakLine(content);
        closeBlock(content);
        breakLine(content);
    }
    
    private void addMain() {
        content.append("void loop() ");
        openBlock(content, true);
        indentLine(content);
        content.append("while (_ligado)");
        openBlock(content, true);
        addExceptionTreatmentBlock();
        indentLine(content);
        content.append("_ligado = false;");
        breakLine(content);
        closeBlock(content, false);
        breakLine(content);
        closeBlock(content, true);
        breakLine(content);
    }

    private void addInitialConfiguration() {
        content.append(initialConfigurationContent.toString());
    }

    public void appendContent(String content) {
        contentInEditing.append(content);
    }
    
    private void addExceptionTreatmentBlock() {
        addMainContent();
    }

    private void addMainContent() {
        content.append(mainContent.toString());
    }
    
    public void addSetupContent() {
        content.append(setupContent.toString());
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
