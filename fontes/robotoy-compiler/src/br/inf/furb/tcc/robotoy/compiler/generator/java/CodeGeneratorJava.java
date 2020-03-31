package br.inf.furb.tcc.robotoy.compiler.generator.java;

import java.io.File;
import java.io.StringReader;
import java.lang.reflect.Constructor;

import org.apache.commons.lang.StringUtils;

import br.inf.furb.tcc.robotoy.compiler.analyzer.GenericSemanticAnalyzer;
import br.inf.furb.tcc.robotoy.compiler.analyzer.GenericSyntaticAnalyzer;
import br.inf.furb.tcc.robotoy.compiler.analyzer.LexicalAnalyzer;
import br.inf.furb.tcc.robotoy.compiler.analyzer.SemanticAnalyzerJava;
import br.inf.furb.tcc.robotoy.compiler.analyzer.SemanticAnalyzerArduino;
import br.inf.furb.tcc.robotoy.compiler.analyzer.SyntaticAnalyzerArduino;
import br.inf.furb.tcc.robotoy.compiler.analyzer.SyntaticAnalyzerJava;
import br.inf.furb.tcc.robotoy.compiler.analyzer.message.ValidationMessage;
import br.inf.furb.tcc.robotoy.compiler.generator.java.ArduinoClassGenerator;
import br.inf.furb.tcc.robotoy.compiler.generator.java.GenericCodeGenerator;
import br.inf.furb.tcc.robotoy.compiler.generator.java.JavaClassGenerator;
import br.inf.furb.tcc.robotoy.robot.generic.IRobot;

public final class CodeGeneratorJava {

    private final String program;
    private Class<? extends IRobot> clazz;
    private JavaClassGenerator javaClassGenerator;

    public CodeGeneratorJava(String program, Class<? extends IRobot> clazz) {
        if (program == null) {
            throw new IllegalArgumentException("O programa não pode ser nulo.");
        }
        
        this.program = program.concat("\n");
        
        this.clazz = clazz;
        
        Constructor<?>[] constructors = this.clazz.getConstructors();
        if (constructors.length != 1) {
            throw new IllegalStateException(String.format(ValidationMessage.INVALID_CONSTRUCTORS_AMOUNT, clazz.getName()));
        }

        if (constructors[0].getParameterTypes().length > 0) {
            throw new IllegalStateException(String.format(ValidationMessage.CONSTRUCTOR_WITH_PARAMETER, clazz.getName()));
        }
        
        javaClassGenerator = new JavaClassGenerator();
    }

    public void generateCode(File file) throws Exception {
        // TODO Validar extensão e se é um arquivo.        
        javaClassGenerator.finalizeInitialConfiguration();
        
        if (!StringUtils.isBlank(program)) {
           
            LexicalAnalyzer lexical = new LexicalAnalyzer(new StringReader(program));
           
            GenericSyntaticAnalyzer syntatic;
            GenericSemanticAnalyzer semantic; 
           
            syntatic = new SyntaticAnalyzerJava();
            semantic = new SemanticAnalyzerJava(javaClassGenerator);
     
            syntatic.parse(lexical, semantic);
        }


        javaClassGenerator.createFile(file, clazz); // TODO Alterar caminho.
    }
    
    public JavaClassGenerator getJavaClassGenerator() {
        return javaClassGenerator; 
    }
    


}
