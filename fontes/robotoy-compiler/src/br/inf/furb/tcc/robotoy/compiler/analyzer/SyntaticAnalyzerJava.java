package br.inf.furb.tcc.robotoy.compiler.analyzer;

public class SyntaticAnalyzerJava implements Constants, GenericSyntaticAnalyzer
{
    private Token currentToken;
    private Token previousToken;
    private LexicalAnalyzer scanner;
    private GenericSemanticAnalyzer semanticAnalyser;

    public void parse(LexicalAnalyzer scanner, GenericSemanticAnalyzer semanticAnalyser) throws AnalysisError
    {
        this.scanner = scanner;
        this.semanticAnalyser = semanticAnalyser;

        currentToken = scanner.nextToken();
        if (currentToken == null)
            currentToken = new Token(DOLLAR, "$", 0);

        programa();

        if (currentToken.getId() != DOLLAR)
            throw new SyntaticError(PARSER_ERROR[DOLLAR], currentToken.getPosition());
    }

    private void match(int token) throws AnalysisError
    {
        if (currentToken.getId() == token)
        {
            previousToken = currentToken;
            currentToken = scanner.nextToken();
            if (currentToken == null)
            {
                int pos = 0;
                if (previousToken != null)
                    pos = previousToken.getPosition()+previousToken.getLexeme().length();

                currentToken = new Token(DOLLAR, "$", pos);
            }
        }
        else
            throw new SyntaticError(PARSER_ERROR[token], currentToken.getPosition());
    }

    private void programa() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 1: // $
            case 17: // quebra_de_linha
            case 18: // identificador
            case 21: // numero
            case 22: // texto
            case 26: // andar
            case 30: // emitir
            case 32: // escrever
            case 33: // parar
            case 37: // girar
            case 38: // virar
            case 42: // cor
            case 53: // se
            case 55: // enquanto
            case 56: // rotina
                lista_de_comandos();
                lista_de_rotinas();
                semanticAnalyser.executeAction(46, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[64], currentToken.getPosition());
        }
    }

    private void lista_de_comandos() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 1: // $
            case 54: // senao
            case 56: // rotina
            case 57: // fim
                // EPSILON
                break;
            case 17: // quebra_de_linha
                match(17); // quebra_de_linha
                semanticAnalyser.executeAction(52, previousToken);
                lista_de_comandos();
                break;
            case 18: // identificador
            case 21: // numero
            case 22: // texto
            case 26: // andar
            case 30: // emitir
            case 32: // escrever
            case 33: // parar
            case 37: // girar
            case 38: // virar
            case 42: // cor
            case 53: // se
            case 55: // enquanto
                comando();
                match(17); // quebra_de_linha
                semanticAnalyser.executeAction(52, previousToken);
                lista_de_comandos();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[65], currentToken.getPosition());
        }
    }

    private void comando() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 18: // identificador
            case 21: // numero
            case 22: // texto
            case 42: // cor
                definicao_de_variavel_ou_invocacao_de_metodo();
                semanticAnalyser.executeAction(3, previousToken);
                break;
            case 26: // andar
            case 30: // emitir
            case 32: // escrever
            case 33: // parar
            case 37: // girar
            case 38: // virar
                semanticAnalyser.executeAction(29, previousToken);
                acao();
                semanticAnalyser.executeAction(10, previousToken);
                semanticAnalyser.executeAction(3, previousToken);
                break;
            case 53: // se
            case 55: // enquanto
                controle_de_fluxo();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[66], currentToken.getPosition());
        }
    }

    private void controle_de_fluxo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 53: // se
                se();
                break;
            case 55: // enquanto
                enquanto();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[67], currentToken.getPosition());
        }
    }

    private void se() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 53: // se
                match(53); // se
                semanticAnalyser.executeAction(19, previousToken);
                condicao();
                match(17); // quebra_de_linha
                semanticAnalyser.executeAction(52, previousToken);
                semanticAnalyser.executeAction(20, previousToken);
                lista_de_comandos();
                senao();
                match(57); // fim
                match(61); // do
                match(53); // se
                semanticAnalyser.executeAction(18, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[68], currentToken.getPosition());
        }
    }

    private void senao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 54: // senao
                match(54); // senao
                semanticAnalyser.executeAction(21, previousToken);
                match(17); // quebra_de_linha
                semanticAnalyser.executeAction(52, previousToken);
                lista_de_comandos();
                break;
            case 57: // fim
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[69], currentToken.getPosition());
        }
    }

    private void enquanto() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 55: // enquanto
                match(55); // enquanto
                semanticAnalyser.executeAction(27, previousToken);
                condicao();
                match(17); // quebra_de_linha
                semanticAnalyser.executeAction(52, previousToken);
                semanticAnalyser.executeAction(20, previousToken);
                lista_de_comandos();
                match(57); // fim
                match(61); // do
                match(55); // enquanto
                semanticAnalyser.executeAction(18, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[70], currentToken.getPosition());
        }
    }

    private void definicao_de_variavel_ou_invocacao_de_metodo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 18: // identificador
                match(18); // identificador
                semanticAnalyser.executeAction(47, previousToken);
                declaracao_nao_obrigatoria();
                break;
            case 21: // numero
            case 22: // texto
            case 42: // cor
                tipo();
                semanticAnalyser.executeAction(1, previousToken);
                match(18); // identificador
                semanticAnalyser.executeAction(2, previousToken);
                declaracao_obrigatoria();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[71], currentToken.getPosition());
        }
    }

    private void declaracao_obrigatoria() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 15: // "<-"
                match(15); // "<-"
                expressao();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[72], currentToken.getPosition());
        }
    }

    private void declaracao_nao_obrigatoria() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 15: // "<-"
                semanticAnalyser.executeAction(49, previousToken);
                match(15); // "<-"
                expressao();
                break;
            case 17: // quebra_de_linha
                semanticAnalyser.executeAction(48, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[73], currentToken.getPosition());
        }
    }

    private void tipo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 21: // numero
                match(21); // numero
                break;
            case 22: // texto
                match(22); // texto
                break;
            case 42: // cor
                match(42); // cor
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[74], currentToken.getPosition());
        }
    }

    private void expressao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 7: // "("
            case 18: // identificador
            case 19: // numerico
            case 20: // literal
            case 42: // cor
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
                aritmetica();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[75], currentToken.getPosition());
        }
    }

    private void aritmetica() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 7: // "("
            case 18: // identificador
            case 19: // numerico
            case 20: // literal
            case 42: // cor
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
                operacao();
                soma_subtracao();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[76], currentToken.getPosition());
        }
    }

    private void soma_subtracao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
                match(2); // "+"
                semanticAnalyser.executeAction(13, previousToken);
                operacao();
                soma_subtracao();
                break;
            case 3: // "-"
                match(3); // "-"
                semanticAnalyser.executeAction(13, previousToken);
                operacao();
                soma_subtracao();
                break;
            case 8: // ")"
            case 9: // ">"
            case 10: // "<"
            case 11: // ">="
            case 12: // "<="
            case 13: // "="
            case 14: // "=/"
            case 17: // quebra_de_linha
            case 24: // e
            case 25: // ou
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[77], currentToken.getPosition());
        }
    }

    private void operacao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 7: // "("
            case 18: // identificador
            case 19: // numerico
            case 20: // literal
            case 42: // cor
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
                termo();
                multiplicacao_divisao_resto();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[78], currentToken.getPosition());
        }
    }

    private void multiplicacao_divisao_resto() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 8: // ")"
            case 9: // ">"
            case 10: // "<"
            case 11: // ">="
            case 12: // "<="
            case 13: // "="
            case 14: // "=/"
            case 17: // quebra_de_linha
            case 24: // e
            case 25: // ou
                // EPSILON
                break;
            case 4: // "*"
                match(4); // "*"
                semanticAnalyser.executeAction(13, previousToken);
                termo();
                multiplicacao_divisao_resto();
                break;
            case 5: // "/"
                match(5); // "/"
                semanticAnalyser.executeAction(13, previousToken);
                termo();
                multiplicacao_divisao_resto();
                break;
            case 6: // "%"
                match(6); // "%"
                semanticAnalyser.executeAction(13, previousToken);
                termo();
                multiplicacao_divisao_resto();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[79], currentToken.getPosition());
        }
    }

    private void termo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
                match(2); // "+"
                semanticAnalyser.executeAction(11, previousToken);
                termo();
                break;
            case 3: // "-"
                match(3); // "-"
                semanticAnalyser.executeAction(11, previousToken);
                termo();
                break;
            case 7: // "("
                match(7); // "("
                semanticAnalyser.executeAction(9, previousToken);
                aritmetica();
                match(8); // ")"
                semanticAnalyser.executeAction(10, previousToken);
                break;
            case 18: // identificador
                match(18); // identificador
                semanticAnalyser.executeAction(4, previousToken);
                concatenar_literal();
                break;
            case 19: // numerico
                match(19); // numerico
                semanticAnalyser.executeAction(5, previousToken);
                break;
            case 20: // literal
                match(20); // literal
                semanticAnalyser.executeAction(8, previousToken);
                concatenar_literal();
                break;
            case 42: // cor
                match(42); // cor
                match(41); // identificada
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
                cor();
                semanticAnalyser.executeAction(6, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[80], currentToken.getPosition());
        }
    }

    private void concatenar_literal() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 4: // "*"
            case 5: // "/"
            case 6: // "%"
            case 8: // ")"
            case 9: // ">"
            case 10: // "<"
            case 11: // ">="
            case 12: // "<="
            case 13: // "="
            case 14: // "=/"
            case 17: // quebra_de_linha
            case 24: // e
            case 25: // ou
                // EPSILON
                break;
            case 16: // "."
                match(16); // "."
                semanticAnalyser.executeAction(12, previousToken);
                opcoes_de_concatenacao();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[81], currentToken.getPosition());
        }
    }

    private void opcoes_de_concatenacao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 7: // "("
                match(7); // "("
                semanticAnalyser.executeAction(9, previousToken);
                aritmetica();
                match(8); // ")"
                semanticAnalyser.executeAction(10, previousToken);
                concatenar_literal();
                break;
            case 18: // identificador
                match(18); // identificador
                semanticAnalyser.executeAction(4, previousToken);
                concatenar_literal();
                break;
            case 20: // literal
                match(20); // literal
                semanticAnalyser.executeAction(8, previousToken);
                concatenar_literal();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[82], currentToken.getPosition());
        }
    }

    private void acao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 26: // andar
                andar();
                break;
            case 30: // emitir
                emitir_som();
                break;
            case 32: // escrever
                escrever();
                break;
            case 33: // parar
                parar();
                break;
            case 37: // girar
                girar_roda();
                break;
            case 38: // virar
                match(38); // virar
                semanticAnalyser.executeAction(34, previousToken);
                virar();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[83], currentToken.getPosition());
        }
    }

    private void andar() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 26: // andar
                match(26); // andar
                semanticAnalyser.executeAction(33, previousToken);
                match(59); // para
                frente_ou_tras();
                semanticAnalyser.executeAction(30, previousToken);
                semanticAnalyser.executeAction(9, previousToken);
                quantidade_de_vezes();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[84], currentToken.getPosition());
        }
    }

    private void virar() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 35: // motor
                match(35); // motor
                semanticAnalyser.executeAction(32, previousToken);
                match(36); // multiuso
                match(59); // para
                match(63); // a
                esquerda_ou_direita();
                semanticAnalyser.executeAction(31, previousToken);
                semanticAnalyser.executeAction(9, previousToken);
                quantidade_de_vezes_obrigatoria();
                break;
            case 59: // para
                match(59); // para
                match(63); // a
                esquerda_ou_direita();
                semanticAnalyser.executeAction(31, previousToken);
                semanticAnalyser.executeAction(9, previousToken);
                quantidade_de_vezes();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[85], currentToken.getPosition());
        }
    }

    private void girar_roda() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 37: // girar
                match(37); // girar
                semanticAnalyser.executeAction(36, previousToken);
                match(34); // roda
                esquerda_ou_direita();
                semanticAnalyser.executeAction(31, previousToken);
                semanticAnalyser.executeAction(35, previousToken);
                match(59); // para
                frente_ou_tras();
                semanticAnalyser.executeAction(30, previousToken);
                semanticAnalyser.executeAction(9, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[86], currentToken.getPosition());
        }
    }

    private void parar() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 33: // parar
                match(33); // parar
                semanticAnalyser.executeAction(37, previousToken);
                match(60); // de
                acoes_que_param();
                semanticAnalyser.executeAction(9, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[87], currentToken.getPosition());
        }
    }

    private void escrever() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 32: // escrever
                match(32); // escrever
                semanticAnalyser.executeAction(41, previousToken);
                semanticAnalyser.executeAction(9, previousToken);
                semanticAnalyser.executeAction(51, previousToken);
                expressao();
                semanticAnalyser.executeAction(43, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[88], currentToken.getPosition());
        }
    }

    private void emitir_som() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 30: // emitir
                match(30); // emitir
                match(31); // som
                semanticAnalyser.executeAction(42, previousToken);
                semanticAnalyser.executeAction(9, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[89], currentToken.getPosition());
        }
    }

    private void frente_ou_tras() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 27: // frente
                match(27); // frente
                break;
            case 28: // tras
                match(28); // tras
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[90], currentToken.getPosition());
        }
    }

    private void esquerda_ou_direita() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 39: // esquerda
                match(39); // esquerda
                break;
            case 40: // direita
                match(40); // direita
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[91], currentToken.getPosition());
        }
    }

    private void quantidade_de_vezes() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 7: // "("
            case 18: // identificador
            case 19: // numerico
            case 20: // literal
            case 42: // cor
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
                semanticAnalyser.executeAction(50, previousToken);
                expressao();
                semanticAnalyser.executeAction(43, previousToken);
                break;
            case 17: // quebra_de_linha
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[92], currentToken.getPosition());
        }
    }

    private void quantidade_de_vezes_obrigatoria() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 7: // "("
            case 18: // identificador
            case 19: // numerico
            case 20: // literal
            case 42: // cor
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
                semanticAnalyser.executeAction(50, previousToken);
                expressao();
                semanticAnalyser.executeAction(43, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[93], currentToken.getPosition());
        }
    }

    private void acoes_que_param() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 26: // andar
                match(26); // andar
                semanticAnalyser.executeAction(38, previousToken);
                break;
            case 37: // girar
                match(37); // girar
                semanticAnalyser.executeAction(40, previousToken);
                break;
            case 38: // virar
                match(38); // virar
                semanticAnalyser.executeAction(39, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[94], currentToken.getPosition());
        }
    }

    private void condicao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 7: // "("
            case 18: // identificador
            case 19: // numerico
            case 20: // literal
            case 23: // nao
            case 42: // cor
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
            case 58: // tem
                semanticAnalyser.executeAction(22, previousToken);
                tipo_condicao();
                condicao_extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[95], currentToken.getPosition());
        }
    }

    private void tipo_condicao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 7: // "("
            case 18: // identificador
            case 19: // numerico
            case 20: // literal
            case 42: // cor
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
                comparar_numerico_literal();
                break;
            case 23: // nao
            case 58: // tem
                verificar_obstaculo();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[96], currentToken.getPosition());
        }
    }

    private void condicao_extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 17: // quebra_de_linha
                // EPSILON
                break;
            case 24: // e
                match(24); // e
                semanticAnalyser.executeAction(25, previousToken);
                condicao();
                break;
            case 25: // ou
                match(25); // ou
                semanticAnalyser.executeAction(26, previousToken);
                condicao();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[97], currentToken.getPosition());
        }
    }

    private void verificar_obstaculo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 23: // nao
            case 58: // tem
                nao();
                match(58); // tem
                match(29); // obstaculo
                semanticAnalyser.executeAction(24, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[98], currentToken.getPosition());
        }
    }

    private void nao() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 23: // nao
                match(23); // nao
                semanticAnalyser.executeAction(23, previousToken);
                break;
            case 58: // tem
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[99], currentToken.getPosition());
        }
    }

    private void comparar_numerico_literal() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // "+"
            case 3: // "-"
            case 7: // "("
            case 18: // identificador
            case 19: // numerico
            case 20: // literal
            case 42: // cor
            case 43: // branca
            case 44: // branco
            case 45: // preta
            case 46: // preto
            case 47: // vermelha
            case 48: // vermelho
            case 49: // amarela
            case 50: // amarelo
            case 51: // verde
            case 52: // azul
                expressao();
                operador_relacional_numerico();
                semanticAnalyser.executeAction(28, previousToken);
                expressao();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[100], currentToken.getPosition());
        }
    }

    private void operador_relacional_numerico() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 9: // ">"
                match(9); // ">"
                break;
            case 10: // "<"
                match(10); // "<"
                break;
            case 11: // ">="
                match(11); // ">="
                break;
            case 12: // "<="
                match(12); // "<="
                break;
            case 13: // "="
                match(13); // "="
                break;
            case 14: // "=/"
                match(14); // "=/"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[101], currentToken.getPosition());
        }
    }

    private void cor() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 43: // branca
                match(43); // branca
                break;
            case 44: // branco
                match(44); // branco
                break;
            case 45: // preta
                match(45); // preta
                break;
            case 46: // preto
                match(46); // preto
                break;
            case 47: // vermelha
                match(47); // vermelha
                break;
            case 48: // vermelho
                match(48); // vermelho
                break;
            case 49: // amarela
                match(49); // amarela
                break;
            case 50: // amarelo
                match(50); // amarelo
                break;
            case 51: // verde
                match(51); // verde
                break;
            case 52: // azul
                match(52); // azul
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[102], currentToken.getPosition());
        }
    }

    private void lista_de_rotinas() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 1: // $
                // EPSILON
                break;
            case 56: // rotina
                rotina();
                lista_de_rotinas();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[103], currentToken.getPosition());
        }
    }

    private void rotina() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 56: // rotina
                match(56); // rotina
                match(18); // identificador
                semanticAnalyser.executeAction(44, previousToken);
                match(17); // quebra_de_linha
                semanticAnalyser.executeAction(52, previousToken);
                lista_de_comandos();
                match(57); // fim
                match(62); // da
                match(56); // rotina
                semanticAnalyser.executeAction(45, previousToken);
                match(17); // quebra_de_linha
                semanticAnalyser.executeAction(52, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[104], currentToken.getPosition());
        }
    }

}
