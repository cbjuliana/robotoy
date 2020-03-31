package br.inf.furb.tcc.robotoy.compiler.generator;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import br.inf.furb.tcc.robotoy.compiler.generator.java.CodeGeneratorJava;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotMock;

public abstract class CodeGeneratorApiTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    protected static final File TEMP_DIR = new File("temp");
    protected static final File JAVA_FILE_GENERATED = new File(TEMP_DIR, "Program.java");

    @BeforeClass
    public static void beforeClass() {
        TEMP_DIR.mkdir();
    }

    @AfterClass
    public static void afterClass() {
        TEMP_DIR.delete();
    }

    protected String generateCode(String program) throws Exception {
        CodeGeneratorJava codeGenerator = new CodeGeneratorJava(program, RobotMock.class);
        codeGenerator.generateCode(JAVA_FILE_GENERATED);
        return IOUtils.toString(new FileInputStream(JAVA_FILE_GENERATED));
    }

    protected String generateCode(String program, String javaFileName) throws Exception {
        File generatedJavaFile = new File(TEMP_DIR, javaFileName);
        new CodeGeneratorJava(program, RobotMock.class).generateCode(generatedJavaFile);
        return IOUtils.toString(new FileInputStream(generatedJavaFile));
    }

}
