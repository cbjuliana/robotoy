package br.inf.furb.tcc.robotoy.compiler.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.inf.furb.tcc.robotoy.compiler.generator.CodeGeneratorTest;
import br.inf.furb.tcc.robotoy.compiler.generator.GeneratedCodeTest;
import br.inf.furb.tcc.robotoy.compiler.generator.SemanticValidationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ //
SemanticValidationTest.class, //
CodeGeneratorTest.class, //
GeneratedCodeTest.class //
})
public class SuiteAllRobotoyCompilerTest {
}
