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

public final class CodeGeneratorArduino {

    private final String program;
    private ArduinoClassGenerator arduinoClassGenerator;
    
    public CodeGeneratorArduino(String program) {
        if (program == null) {   
            throw new IllegalArgumentException("O programa não pode ser nulo.");
        }
        
        arduinoClassGenerator = new ArduinoClassGenerator();
        
        this.program = program.concat("\n"); 
    }

    public void generateCode(File file) throws Exception {        
        arduinoClassGenerator.finalizeInitialConfiguration();
        
        if (!StringUtils.isBlank(program)) {
            
            LexicalAnalyzer lexical = new LexicalAnalyzer(new StringReader(program));
            
            GenericSyntaticAnalyzer syntatic;
            GenericSemanticAnalyzer semantic; 
           
            syntatic = new SyntaticAnalyzerArduino();
            semantic = new SemanticAnalyzerArduino(arduinoClassGenerator);
           
            syntatic.parse(lexical, semantic);
        }


        arduinoClassGenerator.createFile(file); 
    }
    
    
    public ArduinoClassGenerator getArduinoClassGenerator() {
        return arduinoClassGenerator; 
    }

}
