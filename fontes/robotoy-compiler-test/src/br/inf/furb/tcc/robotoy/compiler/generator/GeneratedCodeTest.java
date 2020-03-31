package br.inf.furb.tcc.robotoy.compiler.generator;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class GeneratedCodeTest extends CodeGeneratorApiTest {

    private static final File FILES_FOR_COMPARISON_DIR = new File("res");

    @Test
    public void testGeneratedCode001() throws Exception {
        String program = "cor color <- verde";

        String fileName = "Program001.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode002() throws Exception {
        String program = "cor color <- azul";

        String fileName = "Program002.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode003() throws Exception {
        String program = "cor color <- vermelho";

        String fileName = "Program003.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode004() throws Exception {
        String program = "cor color <- vermelha";

        String fileName = "Program004.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode005() throws Exception {
        String program = "cor color <- branco";

        String fileName = "Program005.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode006() throws Exception {
        String program = "cor color <- branca";

        String fileName = "Program006.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode007() throws Exception {
        String program = "cor color <- preto";

        String fileName = "Program007.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode008() throws Exception {
        String program = "cor color <- preta";

        String fileName = "Program008.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode009() throws Exception {
        String program = "cor color <- amarelo";

        String fileName = "Program009.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode010() throws Exception {
        String program = "cor color <- amarela";

        String fileName = "Program010.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode011() throws Exception {
        String program = "cor color <- cor identificada";

        String fileName = "Program011.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);

        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode012() throws Exception {
        String program = "CoR CoLoR <- VeRmElHa";
        
        String fileName = "Program012.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    
    }
        
    @Test
    public void testGeneratedCode013() throws Exception {
        String program = "COR COLOR <- PRETO";
        
        String fileName = "Program013.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode014() throws Exception {
        String program = "cor color1 <- CoR IdEnTiFiCaDa";
        
        String fileName = "Program014.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode015() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("cor color1 <- verde").append("\n");
        program.append("cor Color1 <- color1").append("\n");
        program.append("color1 <- cor identificada").append("\n");
        program.append("Color1 <- color1");
        
        String fileName = "Program015.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program.toString(), fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode016() throws Exception {
        String program = "texto text <- \"Um texto qualquer.\"";
        
        String fileName = "Program016.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode017() throws Exception {
        String program = "texto text <- \"Qtd. de passos: \" . (10)";
        
        String fileName = "Program017.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode018() throws Exception {
        String program = "texto text <- \"Cor: \" . (verde)";
        
        String fileName = "Program018.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode019() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número qtdPassos <- 10").append("\n");
        program.append("cor color <- azul").append("\n");
        program.append("texto text <- \"Qtd. de passos: \" . qtdPassos . \" na cor \" . color . \".\"").append("\n");
        program.append("text <- qtdPassos . \"/\" . color");
        
        String fileName = "Program019.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program.toString(), fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode020() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número valor <- -5").append("\n");
        program.append("texto adição <- \"Resultado: \" . (5 + valor)").append("\n");
        program.append("texto subtração <- \"Resultado: \" . (5 - valor)").append("\n");
        program.append("texto divisão <- \"Resultado: \" . (5 / valor)").append("\n");
        program.append("texto multiplicação <- \"Resultado: \" . (5 * valor)").append("\n");
        program.append("texto restoDaDivisão <- \"Resultado: \" . (5 % valor)");
        
        String fileName = "Program020.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program.toString(), fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode021() throws Exception {
        String program = "texto text <- \"A cor atual é: \" . (cor identificada)";
        
        String fileName = "Program021.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode022() throws Exception {
        String program = "TeXtO TeXt <- \"TeXtO QuAlQuEr\"";
        
        String fileName = "Program022.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testGeneratedCode023() throws Exception {
        String program = "número number <- 10,5";
        
        String fileName = "Program023.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode024() throws Exception {
        String program = "número number <- 1 + 2 - (3 * (4 / (5 % 6)))";
        
        String fileName = "Program024.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode025() throws Exception {
        String program = "número number <- -20";
        
        String fileName = "Program025.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode026() throws Exception {
        String program = "número number <- +20";
        
        String fileName = "Program026.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program, fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode027() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número number1 <- 25").append("\n");
        program.append("número number2 <- -(number1)").append("\n");
        program.append("number2 <- +(-number2 * 3) - -(+number2 * 3)").append("\n");
        program.append("number1 <- number1 + number2");
        
        String fileName = "Program027.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program.toString(), fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode028() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("NúMeRo NuMbEr <- 10").append("\n");
        program.append("NuMbEr <- 125,00");
        
        String fileName = "Program028.java";
        String expected = getExpectedFileContent(fileName);
        String actual = generateCode(program.toString(), fileName);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode029() throws Exception {
        String program = "andar para frente";
    	
    	String fileName = "Program029.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode030() throws Exception {
    	String program = "andar para frente 10,5";
    	
    	String fileName = "Program030.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode031() throws Exception {
    	String program = "andar para frente 5+(10/5)*100-(-10%(5))";
    	
    	String fileName = "Program031.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode032() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número valor <- 5").append("\n");
        program.append("andar para frente valor").append("\n");
        program.append("andar para frente -valor").append("\n");
        program.append("andar para frente +valor").append("\n");
        program.append("andar para frente (valor) * 2").append("\n");
        program.append("andar para frente valor + valor");
    	
    	String fileName = "Program032.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode033() throws Exception {
        String program = "andar para trás";
    	
    	String fileName = "Program033.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode034() throws Exception {
    	String program = "andar para trás 10,5";
    	
    	String fileName = "Program034.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode035() throws Exception {
    	String program = "andar para trás 5+(10/5)*100-(-10%(5))";
    	
    	String fileName = "Program035.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode036() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número valor <- 5").append("\n");
        program.append("andar para trás valor").append("\n");
        program.append("andar para trás -valor").append("\n");
        program.append("andar para trás +valor").append("\n");
        program.append("andar para trás (valor) * 2").append("\n");
        program.append("andar para trás valor + valor");
    	
    	String fileName = "Program036.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode037() throws Exception {
    	String program = "parar de andar";
    	
    	String fileName = "Program037.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode038() throws Exception {
    	String program = "virar para a esquerda";
    	
    	String fileName = "Program038.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode039() throws Exception {
    	String program = "virar para a esquerda 52,7256";
    	
    	String fileName = "Program039.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode040() throws Exception {
    	String program = "virar para a esquerda (5+1*(-5--4))";
    	
    	String fileName = "Program040.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode041() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número valor <- 0,50").append("\n");
        program.append("virar para a esquerda valor").append("\n");
        program.append("virar para a esquerda -valor").append("\n");
        program.append("virar para a esquerda +valor").append("\n");
        program.append("virar para a esquerda (valor) * 2").append("\n");
        program.append("virar para a esquerda valor + valor");
    	
    	String fileName = "Program041.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode042() throws Exception {
    	String program = "virar para a direita";
    	
    	String fileName = "Program042.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode043() throws Exception {
    	String program = "virar para a direita 52,7256";
    	
    	String fileName = "Program043.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode044() throws Exception {
    	String program = "virar para a direita (5+1*(-5--4))";
    	
    	String fileName = "Program044.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode045() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número valor <- 0,50").append("\n");
        program.append("virar para a direita valor").append("\n");
        program.append("virar para a direita -valor").append("\n");
        program.append("virar para a direita +valor").append("\n");
        program.append("virar para a direita (valor) * 2").append("\n");
        program.append("virar para a direita valor + valor");
    	
    	String fileName = "Program045.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode046() throws Exception {
    	String program = "parar de virar";
    	
    	String fileName = "Program046.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode047() throws Exception {
    	String program = "virar motor multiuso para a esquerda 2";
    	
    	String fileName = "Program047.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode048() throws Exception {
    	String program = "virar motor multiuso para a esquerda 1*2";
    	
    	String fileName = "Program048.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode049() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número valor <- 1").append("\n");
        program.append("virar motor multiuso para a esquerda valor").append("\n");
        program.append("virar motor multiuso para a esquerda -valor").append("\n");
        program.append("virar motor multiuso para a esquerda +valor").append("\n");
        program.append("virar motor multiuso para a esquerda (valor) * 2").append("\n");
        program.append("virar motor multiuso para a esquerda valor + valor");
    	
    	String fileName = "Program049.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode050() throws Exception {
    	String program = "virar motor multiuso para a direita 2";
    	
    	String fileName = "Program050.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode051() throws Exception {
    	String program = "virar motor multiuso para a direita 1*2";
    	
    	String fileName = "Program051.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode052() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("número valor <- 1").append("\n");
        program.append("virar motor multiuso para a direita valor").append("\n");
        program.append("virar motor multiuso para a direita -valor").append("\n");
        program.append("virar motor multiuso para a direita +valor").append("\n");
        program.append("virar motor multiuso para a direita (valor) * 2").append("\n");
        program.append("virar motor multiuso para a direita valor + valor");
    	
    	String fileName = "Program052.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode053() throws Exception {
    	String program = "girar roda esquerda para frente";
    	
    	String fileName = "Program053.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode054() throws Exception {
    	String program = "girar roda esquerda para trás";
    	
    	String fileName = "Program054.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode055() throws Exception {
    	String program = "girar roda direita para frente";
    	
    	String fileName = "Program055.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode056() throws Exception {
    	String program = "girar roda direita para trás";
    	
    	String fileName = "Program056.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode057() throws Exception {
    	String program = "parar de girar";
    	
    	String fileName = "Program057.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode058() throws Exception {
    	String program = "escrever \"Olá mundo!\"";
    	
    	String fileName = "Program058.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode059() throws Exception {
    	String program = "escrever \"Olá\" . \" mundo!\"";
    	
    	String fileName = "Program059.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode060() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("texto text <- \"Olá\"").append("\n");
        program.append("escrever text").append("\n");
        program.append("escrever text . \" mundo!\"").append("\n");
        program.append("text <- \"mundo!\"").append("\n");
        program.append("escrever \"Olá \" . text");
    	
    	String fileName = "Program060.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode061() throws Exception {
    	String program = "emitir som";
    	
    	String fileName = "Program061.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program, fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode062() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("se tem obstáculo").append("\n");
        program.append("	virar para a esquerda").append("\n");
        program.append("fim do se");
    	
    	String fileName = "Program062.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode063() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("se não tem obstáculo").append("\n");
    	program.append("	andar para frente").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program063.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode064() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("texto fruta <- \"maçã\"").append("\n");
    	program.append("se \"maçã\" = fruta ou fruta =/ \"maçã\"").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program064.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode065() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("texto fruta <- \"pêra\"").append("\n");
    	program.append("se fruta =/ \"maçã\" e \"maçã\" = fruta").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program065.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode066() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- verde").append("\n");
    	program.append("se color = verde ou color =/ azul").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program066.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode067() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- cor identificada").append("\n");
    	program.append("se amarela =/ color e color = branco").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program067.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode068() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("número valor <- 10 / 2").append("\n");
    	program.append("se 10 / 2 = valor ou valor =/ 5").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program068.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode069() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("número valor <- 10 / 2").append("\n");
    	program.append("se valor =/ 5 e 10 / 2 = valor").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program069.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode070() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("número valor <- 10").append("\n");
    	program.append("se valor < 20 e valor <= 20 ou valor > -5 e valor >= -15,5 ou não tem obstáculo").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program070.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode071() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("texto fruta <- \"maçã verde\"").append("\n");
    	program.append("número qtd <- 10").append("\n");
    	program.append("se cor identificada = verde e fruta =/ \"pêra\" e qtd <= 10").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program071.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode072() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("texto fruta <- \"maçã verde\"").append("\n");
    	program.append("número qtd <- 10").append("\n");
    	program.append("se cor identificada = verde e fruta =/ \"pêra\" e qtd <= 10").append("\n");
    	program.append("fim do se");
    	
    	String fileName = "Program071.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    
    @Test
    public void testGeneratedCode073() throws Exception {
        StringBuilder program = new StringBuilder();
        program.append("enquanto tem obstáculo").append("\n");
        program.append("	virar para a esquerda").append("\n");
        program.append("fim do enquanto");
    	
    	String fileName = "Program073.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode074() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("enquanto não tem obstáculo").append("\n");
    	program.append("	andar para frente").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program074.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode075() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("texto fruta <- \"maçã\"").append("\n");
    	program.append("enquanto \"maçã\" = fruta ou fruta =/ \"maçã\"").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program075.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode076() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("texto fruta <- \"pêra\"").append("\n");
    	program.append("enquanto fruta =/ \"maçã\" e \"maçã\" = fruta").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program076.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode077() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- verde").append("\n");
    	program.append("enquanto color = verde ou color =/ azul").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program077.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode078() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("cor color <- cor identificada").append("\n");
    	program.append("enquanto amarela =/ color e color = branco").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program078.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode079() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("número valor <- 10 / 2").append("\n");
    	program.append("enquanto 10 / 2 = valor ou valor =/ 5").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program079.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode080() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("número valor <- 10 / 2").append("\n");
    	program.append("enquanto valor =/ 5 e 10 / 2 = valor").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program080.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode081() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("número valor <- 10").append("\n");
    	program.append("enquanto valor < 20 e valor <= 20 ou valor > -5 e valor >= -15,5 e tem obstáculo").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program081.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode082() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("texto fruta <- \"maçã verde\"").append("\n");
    	program.append("número qtd <- 10").append("\n");
    	program.append("enquanto cor identificada = verde e fruta =/ \"pêra\" e qtd <= 10").append("\n");
    	program.append("fim do enquanto");
    	
    	String fileName = "Program082.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode091() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("andarParaFrente").append("\n");
    	program.append("rotina andarParaFrente").append("\n");
    	program.append("	andar para frente 2").append("\n");
    	program.append("fim da rotina");
    	
    	String fileName = "Program091.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testGeneratedCode092() throws Exception {
    	StringBuilder program = new StringBuilder();
    	program.append("Rotina1").append("\n");
    	program.append("Rotina2").append("\n");
    	program.append("\n").append("\n");
    	program.append("rotina Rotina1").append("\n");
    	program.append("	escrever \"Rotina 1\"").append("\n");
    	program.append("fim da rotina").append("\n");
    	program.append("\n").append("\n");
    	program.append("rotina Rotina2").append("\n");
    	program.append("	escrever \"Rotina 2\"").append("\n");
    	program.append("fim da rotina");
    	
    	String fileName = "Program092.java";
    	String expected = getExpectedFileContent(fileName);
    	String actual = generateCode(program.toString(), fileName);
    	assertEquals(expected, actual);
    }
    
    private static String getExpectedFileContent(String fileName) throws Exception {
        return IOUtils.toString(new FileInputStream(new File(FILES_FOR_COMPARISON_DIR, fileName))).replaceAll("    ", "\t");
    }

}
