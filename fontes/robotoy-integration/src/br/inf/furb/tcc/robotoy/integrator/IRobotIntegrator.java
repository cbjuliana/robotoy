package br.inf.furb.tcc.robotoy.integrator;
import java.io.File;
import java.util.Properties;


public interface IRobotIntegrator {
	
	public void generateFile(String program, File propertiesFile, File javaFile) throws Exception;
	
}
