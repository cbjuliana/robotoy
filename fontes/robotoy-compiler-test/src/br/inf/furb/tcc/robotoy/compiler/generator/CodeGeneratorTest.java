package br.inf.furb.tcc.robotoy.compiler.generator;

import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.ValidationMessage.CONSTRUCTOR_WITH_PARAMETER;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.ValidationMessage.INVALID_CONSTRUCTORS_AMOUNT;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import br.inf.furb.tcc.robotoy.compiler.generator.java.CodeGeneratorJava;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotConstructorWithParameterMock;
import br.inf.furb.tcc.robotoy.compiler.generator.robot.mock.RobotInvalidConstructorsAmountMock;

public class CodeGeneratorTest extends CodeGeneratorApiTest {

	@Test
	public void testRobotClass001() {
		exception.expect(IllegalStateException.class);
		exception.expectMessage(String.format(INVALID_CONSTRUCTORS_AMOUNT, RobotInvalidConstructorsAmountMock.class.getName()));
		new CodeGeneratorJava(StringUtils.EMPTY, RobotInvalidConstructorsAmountMock.class);
	}

	@Test
	public void testRobotClass002() {
		exception.expect(IllegalStateException.class);
		exception.expectMessage(String.format(CONSTRUCTOR_WITH_PARAMETER, RobotConstructorWithParameterMock.class.getName()));
		new CodeGeneratorJava(StringUtils.EMPTY, RobotConstructorWithParameterMock.class);
	}

}
