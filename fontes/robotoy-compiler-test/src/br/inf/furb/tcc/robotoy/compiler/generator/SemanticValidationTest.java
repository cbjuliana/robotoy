package br.inf.furb.tcc.robotoy.compiler.generator;

import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.CANNOT_ADD_ARITHMETIC_OPERATOR;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.CANNOT_ADD_CONCATENATION_OPERATOR;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.CANNOT_ADD_SIGNAL;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.CANNOT_ATTRIBUTE_COLOR;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.CANNOT_ATTRIBUTE_NUMBER;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.CANNOT_ATTRIBUTE_TEXT;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.INCOMPATIBLE_OPERATOR_FOR_COMPARISON;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.INCOMPATIBLE_TYPES;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.INVALID_COMPARISION;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.INVALID_ELEMENTS_RELATION;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.INVALID_PARAMETER_TYPE;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.METHOD_ALREADY_EXISTS;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.METHOD_NOT_DECLARED;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.SIGNAL_ALREADY_EXISTS;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.VARIABLE_ALREADY_EXISTS;
import static br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage.VARIABLE_NOT_DECLARED;

import org.junit.Test;

import br.inf.furb.tcc.robotoy.compiler.analyzer.SemanticError;
import br.inf.furb.tcc.robotoy.compiler.generator.java.RobotoyVariable;
import br.inf.furb.tcc.robotoy.compiler.generator.java.Type;

public class SemanticValidationTest extends CodeGeneratorApiTest {

    @Test
    public void testValidationError001() throws Exception {
        String variableName = "name";

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(VARIABLE_NOT_DECLARED, variableName));

        String program = "name <- 10";
        generateCode(program);
    }

    @Test
    public void testValidationError002() throws Exception {
        String methodNameInvoked = "name";

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(METHOD_NOT_DECLARED, methodNameInvoked));

        String program = "name";
        generateCode(program);
    }

    @Test
    public void testValidationError003() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("color");
        variable.setType(Type.COLOR);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("number");
        otherVariable.setType(Type.NUMBER);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_TYPES, otherVariable.getName(), otherVariable.getType().getRobotoyType(), variable.getName(), variable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("cor color <- verde").append("\n");
        program.append("número number <- color");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError004() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("color");
        variable.setType(Type.COLOR);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("number");
        otherVariable.setType(Type.NUMBER);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_TYPES, variable.getName(), variable.getType().getRobotoyType(), otherVariable.getName(), otherVariable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 10,0").append("\n");
        program.append("cor color <- number");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError005() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("text");
        variable.setType(Type.TEXT);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("number");
        otherVariable.setType(Type.NUMBER);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_TYPES, otherVariable.getName(), otherVariable.getType().getRobotoyType(), variable.getName(), variable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("número number <- text");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError006() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("text");
        variable.setType(Type.TEXT);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("color");
        otherVariable.setType(Type.COLOR);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_TYPES, otherVariable.getName(), otherVariable.getType().getRobotoyType(), variable.getName(), variable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("cor color <- text");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError007() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("color");
        variable.setType(Type.COLOR);

        exception.expectMessage(String.format(CANNOT_ATTRIBUTE_NUMBER, variable.getName(), variable.getType().getRobotoyType()));

        String program = "cor color <- 10,0";
        generateCode(program);
    }

    @Test
    public void testValidationError008() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("number");
        variable.setType(Type.NUMBER);

        exception.expectMessage(String.format(CANNOT_ATTRIBUTE_COLOR, variable.getName(), variable.getType().getRobotoyType()));

        String program = "número number <- amarelo";
        generateCode(program);
    }

    @Test
    public void testValidationError009() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("number");
        variable.setType(Type.NUMBER);

        exception.expectMessage(String.format(CANNOT_ATTRIBUTE_COLOR, variable.getName(), variable.getType().getRobotoyType()));

        String program = "número number <- cor identificada";
        generateCode(program);
    }

    @Test
    public void testValidationError010() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("number");
        variable.setType(Type.NUMBER);

        exception.expectMessage(String.format(CANNOT_ATTRIBUTE_TEXT, variable.getName(), variable.getType().getRobotoyType()));

        String program = "número number <- \"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError011() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("color");
        variable.setType(Type.COLOR);

        exception.expectMessage(String.format(CANNOT_ATTRIBUTE_TEXT, variable.getName(), variable.getType().getRobotoyType()));

        String program = "cor color <- \"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError012() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        StringBuilder program = new StringBuilder();
        program.append("cor color <- verde").append("\n");
        program.append("número number <- -color");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError013() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"teste\"").append("\n");
        program.append("número number <- -text");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError014() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        String program = "número number <- -cor identificada";
        generateCode(program);
    }

    @Test
    public void testValidationError015() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        String program = "número number <- -branco";
        generateCode(program);
    }

    @Test
    public void testValidationError016() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        String program = "número number <- -\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError017() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        StringBuilder program = new StringBuilder();
        program.append("cor color <- verde").append("\n");
        program.append("número number <- +color");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError018() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"teste\"").append("\n");
        program.append("número number <- +text");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError019() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        String program = "número number <- +cor identificada";
        generateCode(program);
    }

    @Test
    public void testValidationError020() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        String program = "número number <- +branco";
        generateCode(program);
    }

    @Test
    public void testValidationError021() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_SIGNAL);

        String program = "número number <- +\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError022() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        StringBuilder program = new StringBuilder();
        program.append("cor color <- verde").append("\n");
        program.append("número number <- --color");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError023() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"teste\"").append("\n");
        program.append("número number <- --text");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError024() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- --cor identificada";
        generateCode(program);
    }

    @Test
    public void testValidationError025() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- --branco";
        generateCode(program);
    }

    @Test
    public void testValidationError026() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- --\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError027() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- --\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError028() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- --(10)";
        generateCode(program);
    }

    @Test
    public void testValidationError029() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        StringBuilder program = new StringBuilder();
        program.append("cor color <- verde").append("\n");
        program.append("número number <- ++color");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError030() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"teste\"").append("\n");
        program.append("número number <- ++text");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError031() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- ++cor identificada";
        generateCode(program);
    }

    @Test
    public void testValidationError032() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- ++branco";
        generateCode(program);
    }

    @Test
    public void testValidationError033() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- ++\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError034() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- ++\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError035() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- ++(10)";
        generateCode(program);
    }

    @Test
    public void testValidationError036() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        StringBuilder program = new StringBuilder();
        program.append("cor color <- verde").append("\n");
        program.append("número number <- -+color");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError037() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"teste\"").append("\n");
        program.append("número number <- -+text");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError038() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- -+cor identificada";
        generateCode(program);
    }

    @Test
    public void testValidationError039() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- -+branco";
        generateCode(program);
    }

    @Test
    public void testValidationError040() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- -+\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError041() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- -+\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError042() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- -+(10)";
        generateCode(program);
    }

    @Test
    public void testValidationError043() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        StringBuilder program = new StringBuilder();
        program.append("cor color <- verde").append("\n");
        program.append("número number <- +-color");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError044() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"teste\"").append("\n");
        program.append("número number <- +-text");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError045() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- +-cor identificada";
        generateCode(program);
    }

    @Test
    public void testValidationError046() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- +-branco";
        generateCode(program);
    }

    @Test
    public void testValidationError047() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- +-\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError048() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- +-\"teste\"";
        generateCode(program);
    }

    @Test
    public void testValidationError049() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(SIGNAL_ALREADY_EXISTS);

        String program = "número number <- +-(10)";
        generateCode(program);
    }

    @Test
    public void testValidationError050() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(VARIABLE_ALREADY_EXISTS, "variableName"));

        StringBuilder program = new StringBuilder();
        program.append("número variableName <- 50,0").append("\n");
        program.append("texto variableName <- \"teste\"");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError051() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(METHOD_ALREADY_EXISTS, "methodName"));

        StringBuilder program = new StringBuilder();
        program.append("rotina methodName").append("\n");
        program.append("fim da rotina").append("\n");
        program.append("rotina methodName").append("\n");
        program.append("fim da rotina");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError052() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_CONCATENATION_OPERATOR);

        StringBuilder program = new StringBuilder();
        program.append("cor color <- vermelho").append("\n");
        program.append("se color . branco = verde").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError053() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_CONCATENATION_OPERATOR);

        StringBuilder program = new StringBuilder();
        program.append("cor color <- vermelho").append("\n");
        program.append("se \"teste\" =/ color . 15,0").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError054() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_CONCATENATION_OPERATOR);

        StringBuilder program = new StringBuilder();
        program.append("número number <- 25,50").append("\n");
        program.append("se number . 55,0 = \"teste\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError055() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_CONCATENATION_OPERATOR);

        StringBuilder program = new StringBuilder();
        program.append("número number <- 29,65").append("\n");
        program.append("se \"teste\" =/ number . azul").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError056() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_CONCATENATION_OPERATOR);

        StringBuilder program = new StringBuilder();
        program.append("número quantidade <- 5").append("\n");
        program.append("andar para trás quantidade . verde");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError057() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_CONCATENATION_OPERATOR);

        StringBuilder program = new StringBuilder();
        program.append("número quantidade <- 152,25").append("\n");
        program.append("virar para a esquerda quantidade . \"teste\"");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError058() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(CANNOT_ADD_CONCATENATION_OPERATOR);

        StringBuilder program = new StringBuilder();
        program.append("número quantidade <- 152,25").append("\n");
        program.append("virar motor multiuso para a direita quantidade . -(-quantidade)");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError059() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "+"));

        String program = "cor color <- verde + azul";
        generateCode(program);
    }

    @Test
    public void testValidationError060() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "-"));

        String program = "cor color <- branco - azul";
        generateCode(program);
    }

    @Test
    public void testValidationError061() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "*"));

        String program = "cor color <- preto * branco";
        generateCode(program);
    }

    @Test
    public void testValidationError062() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "/"));

        String program = "cor color <- vermelho / amarelo";
        generateCode(program);
    }

    @Test
    public void testValidationError063() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "%"));

        String program = "cor color <- amarelo % verde";
        generateCode(program);
    }

    @Test
    public void testValidationError064() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "+"));

        String program = "texto text <- \"texto\" + 20";
        generateCode(program);
    }

    @Test
    public void testValidationError065() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "-"));

        String program = "texto text <- 80 - verde";
        generateCode(program);
    }

    @Test
    public void testValidationError066() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "*"));

        String program = "texto text <- azul * \"texto\"";
        generateCode(program);
    }

    @Test
    public void testValidationError067() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "/"));

        String program = "texto text <- 40,25 / branco";
        generateCode(program);
    }

    @Test
    public void testValidationError068() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "%"));

        String program = "texto text <- 60 % vermelho";
        generateCode(program);
    }

    @Test
    public void testValidationError069() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "+"));

        StringBuilder program = new StringBuilder();
        program.append("se verde + \"maçã\" = \"verdemaçã\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError070() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "-"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"teste\"").append("\n");
        program.append("enquanto 100 < text - amarelo").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError071() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "*"));

        StringBuilder program = new StringBuilder();
        program.append("se azul * \"azul\" =\\ azul").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError072() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "/"));

        StringBuilder program = new StringBuilder();
        program.append("enquanto \"teste\" = \"teste\" / branca").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError073() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(CANNOT_ADD_ARITHMETIC_OPERATOR, "%"));

        StringBuilder program = new StringBuilder();
        program.append("se 179,432 <= \"179,432\" % 5").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError076() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("text");
        variable.setType(Type.TEXT);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("color");
        otherVariable.setType(Type.COLOR);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, variable.getType().getRobotoyType(), otherVariable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("cor color <- amarela").append("\n");
        program.append("se text = color").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError077() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("color");
        variable.setType(Type.COLOR);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("text");
        otherVariable.setType(Type.TEXT);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, variable.getType().getRobotoyType(), otherVariable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("cor color <- azul").append("\n");
        program.append("texto text <- \"texto\"").append("\n");
        program.append("enquanto color =/ text").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError078() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("text");
        variable.setType(Type.TEXT);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("number");
        otherVariable.setType(Type.NUMBER);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, variable.getType().getRobotoyType(), otherVariable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("número number <- 10,15").append("\n");
        program.append("se text = number").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError079() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("number");
        variable.setType(Type.NUMBER);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("text");
        otherVariable.setType(Type.TEXT);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, variable.getType().getRobotoyType(), otherVariable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 20 * 3").append("\n");
        program.append("texto text <- \"texto\"").append("\n");
        program.append("enquanto number =/ text").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError080() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("number");
        variable.setType(Type.NUMBER);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("color");
        otherVariable.setType(Type.COLOR);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, variable.getType().getRobotoyType(), otherVariable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 20 * 3").append("\n");
        program.append("cor color <- azul").append("\n");
        program.append("enquanto number = color").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError081() throws Exception {
        RobotoyVariable variable = new RobotoyVariable();
        variable.setName("color");
        variable.setType(Type.COLOR);

        RobotoyVariable otherVariable = new RobotoyVariable();
        otherVariable.setName("number");
        otherVariable.setType(Type.NUMBER);

        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, variable.getType().getRobotoyType(), otherVariable.getType().getRobotoyType()));

        StringBuilder program = new StringBuilder();
        program.append("cor color <- vermelha").append("\n");
        program.append("número number <- 10,15").append("\n");
        program.append("se color =/ number").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError082() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, "cor", "número"));

        StringBuilder program = new StringBuilder();
        program.append("se verde =/ 10,0").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError083() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, "número", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("enquanto 50,55 / 5 = azul").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError084() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, "número", "texto"));

        StringBuilder program = new StringBuilder();
        program.append("enquanto 15 * 2 = \"teste\" . \"outroTeste\"").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError085() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, "texto", "número"));

        StringBuilder program = new StringBuilder();
        program.append("enquanto \"0,5\" =/ 10 % 2").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError086() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, "texto", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("se \"verde\" = verde").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError087() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_COMPARISION, "cor", "texto"));

        StringBuilder program = new StringBuilder();
        program.append("se amarelo = \"amarelo\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError088() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_OPERATOR_FOR_COMPARISON, "cor"));

        StringBuilder program = new StringBuilder();
        program.append("cor color <- azul").append("\n");
        program.append("se color < cor identificada").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError089() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_OPERATOR_FOR_COMPARISON, "cor"));

        StringBuilder program = new StringBuilder();
        program.append("se cor identificada > verde").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError090() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_OPERATOR_FOR_COMPARISON, "cor"));

        StringBuilder program = new StringBuilder();
        program.append("cor color <- azul").append("\n");
        program.append("se color <= azul").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError091() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_OPERATOR_FOR_COMPARISON, "cor"));

        StringBuilder program = new StringBuilder();
        program.append("enquanto azul >= amarelo").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError092() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_OPERATOR_FOR_COMPARISON, "texto"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("se text < \"texto\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError093() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_OPERATOR_FOR_COMPARISON, "texto"));

        StringBuilder program = new StringBuilder();
        program.append("se \"maçã\" > \"pêra\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError094() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_OPERATOR_FOR_COMPARISON, "texto"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"Texto qualquer.\"").append("\n");
        program.append("se text <= \"Outro texto qualquer.\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError095() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INCOMPATIBLE_OPERATOR_FOR_COMPARISON, "texto"));

        StringBuilder program = new StringBuilder();
        program.append("enquanto \"texto\" >= \"outroTexto\"").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError096() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "número", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 10,5").append("\n");
        program.append("cor color <- verde").append("\n");
        program.append("se number + color = \"whatever\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError097() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "texto", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("cor color <- verde").append("\n");
        program.append("enquanto text . color =/ \"whatever\"").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError098() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "número", "texto"));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 100").append("\n");
        program.append("texto text <- \"texto\"").append("\n");
        program.append("se number / text > \"whatever\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError099() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "texto", "número"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("número number <- 25 % 2").append("\n");
        program.append("enquanto text . number > \"whatever\"").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }
    
    @Test
    public void testValidationError100() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "número", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("cor color <- preto").append("\n");
        program.append("se 10,50 % color =/ \"whatever\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }
    
    @Test
    public void testValidationError101() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "texto", "cor"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- preto").append("\n");
    	program.append("enquanto \"preto\" . color = \"whatever\"").append("\n");
    	program.append("fim do enquanto");
    	
    	generateCode(program.toString());
    }
    

    @Test
    public void testValidationError102() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "número", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 75").append("\n");
        program.append("se number % preta > \"whatever\"").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }
    
    @Test
    public void testValidationError103() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "número", "texto"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("número number <- 75").append("\n");
    	program.append("se number % \"75\" > \"whatever\"").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError104() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "texto", "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("número number <- 125,25").append("\n");
    	program.append("se \"75\" . number > \"whatever\"").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError105() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "texto", "número"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("enquanto text . (-17) > \"whatever\"").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }
    
    @Test
    public void testValidationError106() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "texto", "cor"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("se text . (verde) > \"whatever\"").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    
    @Test
    public void testValidationError107() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "número", "texto"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("se -1080 * text > \"whatever\"").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }

    @Test
    public void testValidationError108() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "número", "cor"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("enquanto -((128) / 50) / (verde) > \"whatever\"").append("\n");
    	program.append("fim do enquanto");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError109() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "texto", "cor"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("se \"azul\" . (verde) <= \"whatever\"").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError110() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "número", "texto"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("se (50 % 2) / -2 + \"60\" <= \"whatever\"").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError111() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "primeiro", "texto", "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("enquanto \"60\" . (50 % 2) / -2 <= \"whatever\"").append("\n");
    	program.append("fim do enquanto");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError112() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "número", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 10,5").append("\n");
        program.append("cor color <- verde").append("\n");
        program.append("se \"whatever\" = number + color").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError113() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "texto", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("cor color <- verde").append("\n");
        program.append("enquanto \"whatever\" =/ text . color").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError114() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "número", "texto"));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 100").append("\n");
        program.append("texto text <- \"texto\"").append("\n");
        program.append("se \"whatever\" > number / text").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }

    @Test
    public void testValidationError115() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "texto", "número"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("número number <- 25 % 2").append("\n");
        program.append("enquanto \"whatever\" > text . number").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }
    
    @Test
    public void testValidationError116() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "número", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("cor color <- preto").append("\n");
        program.append("se \"whatever\" =/ 10,50 % color").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }
    
    @Test
    public void testValidationError117() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "texto", "cor"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- preto").append("\n");
    	program.append("enquanto \"whatever\" = \"preto\" . color").append("\n");
    	program.append("fim do enquanto");
    	
    	generateCode(program.toString());
    }
    

    @Test
    public void testValidationError118() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "número", "cor"));

        StringBuilder program = new StringBuilder();
        program.append("número number <- 75").append("\n");
        program.append("se \"whatever\" > number % preta").append("\n");
        program.append("fim do se");

        generateCode(program.toString());
    }
    
    @Test
    public void testValidationError119() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "número", "texto"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("número number <- 75").append("\n");
    	program.append("se \"whatever\" > number % \"75\"").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError120() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "texto", "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("número number <- 125,25").append("\n");
    	program.append("se \"whatever\" > \"75\" . number").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError121() throws Exception {
        exception.expect(SemanticError.class);
        exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "texto", "número"));

        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"texto\"").append("\n");
        program.append("enquanto \"whatever\" > text . (-17)").append("\n");
        program.append("fim do enquanto");

        generateCode(program.toString());
    }
    
    @Test
    public void testValidationError122() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "texto", "cor"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("se \"whatever\" > text . (verde)").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    
    @Test
    public void testValidationError123() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "número", "texto"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("se \"whatever\" > -1080 * text").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }

    @Test
    public void testValidationError124() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "número", "cor"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("enquanto \"whatever\" > -((128) / 50) / (verde)").append("\n");
    	program.append("fim do enquanto");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError125() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "texto", "cor"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("se \"whatever\" <= \"azul\" . (verde)").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError126() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "número", "texto"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("se \"whatever\" <= (50 % 2) / -2 + \"60\"").append("\n");
    	program.append("fim do se");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError127() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_ELEMENTS_RELATION, "segundo", "texto", "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("enquanto \"whatever\" <= \"60\" . (50 % 2) / -2").append("\n");
    	program.append("fim do enquanto");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError128() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- vermelha").append("\n");
    	program.append("andar para frente color");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError129() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("andar para frente text . \"maçã\"");
    	
    	generateCode(program.toString());
    }
    
    
    @Test
    public void testValidationError130() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "andar para frente verde";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError131() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "andar para frente \"n vezes\"";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError132() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- vermelha").append("\n");
    	program.append("andar para trás color");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError133() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("andar para trás text . \"maçã\"");
    	
    	generateCode(program.toString());
    }
    
    
    @Test
    public void testValidationError134() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "andar para trás verde";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError135() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "andar para trás \"n vezes\"";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError136() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- vermelha").append("\n");
    	program.append("virar para a esquerda color");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError137() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("virar para a esquerda text . \"maçã\"");
    	
    	generateCode(program.toString());
    }
    
    
    @Test
    public void testValidationError138() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "virar para a esquerda verde";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError139() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "virar para a esquerda \"n vezes\"";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError140() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- vermelha").append("\n");
    	program.append("virar para a direita color");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError141() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("virar para a direita text . \"maçã\"");
    	
    	generateCode(program.toString());
    }
    
    
    @Test
    public void testValidationError142() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "virar para a direita verde";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError143() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "virar para a direita \"n vezes\"";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError144() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- vermelha").append("\n");
    	program.append("virar motor multiuso para a esquerda color");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError145() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("virar motor multiuso para a esquerda text . \"maçã\"");
    	
    	generateCode(program.toString());
    }
    
    
    @Test
    public void testValidationError146() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "virar motor multiuso para a esquerda verde";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError147() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "virar motor multiuso para a esquerda \"n vezes\"";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError148() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- vermelha").append("\n");
    	program.append("virar motor multiuso para a direita color");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError149() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("texto text <- \"texto\"").append("\n");
    	program.append("virar motor multiuso para a direita text . \"maçã\"");
    	
    	generateCode(program.toString());
    }
    
    
    @Test
    public void testValidationError150() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "virar motor multiuso para a direita verde";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError151() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "número"));
    	
    	String program = "virar motor multiuso para a direita \"n vezes\"";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError152() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "texto"));
    	
    	String program = "escrever verde";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError153() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "texto"));
    	
    	String program = "escrever -10,75 + 10";
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError154() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "texto"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- verde").append("\n");
    	program.append("escrever color");
    	
    	generateCode(program.toString());
    }
    
    @Test
    public void testValidationError155() throws Exception {
    	exception.expect(SemanticError.class);
    	exception.expectMessage(String.format(INVALID_PARAMETER_TYPE, "texto"));
    	
    	StringBuilder program = new StringBuilder();
    	program.append("número number <- -10,75 + 10").append("\n");
    	program.append("escrever number");
    	
    	generateCode(program.toString());
    }

}
