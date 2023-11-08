package TestesAutomatizados;

import ArvoreBinariaDeBusca.ArvoreBinariaBusca;
import ArvoreBinariaDeBusca.ArvoreVazia;
import ArvoreBinariaDeBusca.No;
import ArvoreBinariaDeBusca.NoInexistenteException;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class TesteArvoreDeBusca<T> {
    ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
    ExpectedException exceptionRule = ExpectedException.none();

    // Nesse método é inicializado uma arvore que tem 3 valores expecificos
    // que sao feitos alguns testes, são eles: quais são seu filhoDir e filhoEsq
    // quem são seus pais, e é feito um teste de insercção com um numero repetido
    @Test
    public void testInserir() throws Exception {

        // Criando nós espécificos

        No noUm = new No(50);
        No noDois = new No(63);
        No noTres = new No(22);

        // Inserindo os 7 valores.
        arvore.inserir(noUm);
        arvore.inserir(new No(38));
        arvore.inserir(new No(40));
        arvore.inserir(noDois);
        arvore.inserir(noTres);
        arvore.inserir(new No(62));
        arvore.inserir(new No(70));
        arvore.inserir(new No(22));

        //Definindo nós a serem procurados.

        No noProcuradoUm = noUm;
        No noProcuradoDois = noDois;
        No noProcuradoTres = noTres;

        // Obtendo filhos esquerdo e direito do noUm noDois noTres.

        No filhoEsqNoUm = noUm.getFilhoEsq();
        No filhoDirNoUm = noUm.getFilhoDir();
        No filhoEsqNoDois = noDois.getFilhoEsq();
        No filhoDirNoDois = noDois.getFilhoDir();
        No filhoEsqNoTres = noTres.getFilhoEsq();
        No filhoDirNoTres = noTres.getFilhoDir();

        // Obtendo pai do noTres e noDois

        No paiNoTres = noTres.getPai();
        No paiNoDois = noDois.getPai();


        // Testando inserção do noUm

        assertEquals(filhoEsqNoUm.getDado(), 38);
        assertEquals(filhoDirNoUm.getDado(), 63);

        // Testando inserção do noDois.

        assertEquals(filhoEsqNoDois.getDado(), 62);
        assertEquals(filhoDirNoDois.getDado(), 70);

        // Testando inserção do noTres.
        // Pode se Observar que caso o usuario entre com um dado repetido ele sera alocado
        // a esquerda do seu semelhante, ou seja, o 22 foi adicionado a esquerda do noTres
        // cujo o mesmo possui o mesmo getDado que é 22.

        assertEquals(filhoEsqNoTres.getDado(), 22);
        assertEquals(filhoDirNoTres, null);

        // Testando pais do noUm noDois noTres

        assertEquals(noUm.getPai(), null);
        assertEquals(paiNoTres.getDado(), 38);
        assertEquals(paiNoDois.getDado(), 50);

    }

    // Teste da remoção de um No sem filhos
    @Test
    public void testRemoverCase1() throws Exception {
        // Criando nó espécifico e adicionando outros nós para teste
        No noUm = new No(40);
        No noDois = new No(70);

        // Inserindo valores na arvore
        arvore.inserir(new No(50));
        arvore.inserir(new No(38));
        arvore.inserir(noUm);
        arvore.inserir(new No(63));
        arvore.inserir(new No(22));
        arvore.inserir(new No(62));
        arvore.inserir(noDois);

        // Pegando o pai dos No de teste
        No paiUm = noUm.getPai(); // 38
        No paiDois = noDois.getPai(); // 63

        // Verificação inicial antes da remoção
        assertEquals(paiUm.getFilhoDir(), noUm);
        assertEquals(paiDois.getFilhoDir(), noDois);

        //Removendo os valores
        arvore.remover(noUm);
        arvore.remover(noDois);

        // Verificação pois da remoção do noUm e noDois
        assertEquals(paiUm.getFilhoDir(), null);
        assertEquals(paiDois.getFilhoDir(), null);

    }

    //  Teste da remoção de um No com pelo menos 1 filho
    @Test
    public void testRemoverCase2() throws Exception{
        // Criando nó espécifico e adicionando outros nós
        No noUm = new No(50);
        No noDois = new No(63);

        // Inserindo valores na arvore
        arvore.inserir(new No(50));
        arvore.inserir(noUm);
        arvore.inserir(noDois);
        arvore.inserir(new No(22));
        arvore.inserir(new No(70));

        // Pegando o pai de ambos os No de teste
        No paiUm = noUm.getPai(); // 50
        No paiDois = noDois.getPai(); // 50

        // Pegando o filho dos No teste
        No filhoUm = noUm.getFilhoEsq();
        No filhoDois = noDois.getFilhoDir();

        // Verificação Inicial
        assertEquals(paiUm.getFilhoEsq(), noUm);
        assertEquals(paiDois.getFilhoDir(), noDois);

        //remoção dos No
        arvore.remover(noUm);
        arvore.remover(noDois);

        // Verificação pós remoção do noUm e noDois
        assertEquals(paiUm.getFilhoEsq(), filhoUm);
        assertEquals(paiDois.getFilhoDir(), filhoDois);
    }

    // Teste da remoção de um No com dois Filhos
    @Test
    public void testRemoverCase3() throws Exception {
        // Criando nó espécifico e adicionando outros nós
        No noUm = new No(50);
        No noDois = new No(40);
        No noTres = new No(38);

        // Inserindo valores na arvore
        arvore.inserir(noUm);
        arvore.inserir(noTres);
        arvore.inserir(noDois);
        arvore.inserir(new No(63));
        arvore.inserir(new No(22));
        arvore.inserir(new No(62));
        arvore.inserir(new No(70));

        // Verificação Inicial
        assertEquals(noUm.getDado(), 50);

        // removendo o noUm que é a raiz e verificando se o seu dado foi alterado
        arvore.remover(noUm);
        assertEquals(noUm.getDado(), 40);

        // verificando se o noDois foi removido, ja que ele foi utilizado para remover o noUm
        assertEquals(noTres.getFilhoDir(), null);
        assertEquals(noTres.getFilhoEsq().getDado(), 22);

    }

    // Teste dda Busca de um valor na arvore
    @Test
    public void testBuscar() throws Exception {

        // Buscando em uma arvore vazia

        No valor1 = new No(69);

        try {
            No resultado = arvore.buscar(valor1.getDado());
            // Se chegou até aqui, a busca falhou
            fail("A busca não lançou a exceção esperada.");
        } catch (ArvoreVazia e) {
            // A exceção foi lançada, o teste passa
            assertEquals("Árvore Vazia!", e.getMessage());
        }

        // Adicionando valores na árvore

        int [] valores = {50, 38, 22, 40, 63, 62, 70};
        for(int valor : valores) {
            arvore.inserir(new No(valor));
        }

        // Buscando Valores

        for(int valor : valores) {
            No valorEncontrado = arvore.buscar(valor);
            assertEquals(valorEncontrado.getDado(), valor);
        }

        // Buscando um valor Inexistente na arvore

        No valorInexistente = new No(34);

        try {
            No resultado = arvore.buscar(valorInexistente.getDado());
            // Se chegou até aqui, a busca falhou
            fail("A busca não lançou a exceção esperada.");
        } catch (NoInexistenteException e) {
            // A exceção foi lançada, o teste passa
            assertEquals("No inexistente!", e.getMessage());
        }
    }

    // Teste da visita de um No
    @Test
    public void testVisitar() throws Exception{
        // Criando nó espécifico e adicionando outros nós
        No noUm = new No(50);
        No noDois = new No(40);

        // Inserindo valores na arvore
        arvore.inserir(noUm);
        arvore.inserir(new No(38));
        arvore.inserir(noDois);
        arvore.inserir(new No(63));
        arvore.inserir(new No(22));
        arvore.inserir(new No(62));
        arvore.inserir(new No(70));

        // Testando o resultado da visita

        assertEquals(arvore.visitar(noUm), noUm);
        assertEquals(arvore.visitar(noUm).getFilhoDir(), noUm.getFilhoDir());
        assertEquals(arvore.visitar(noDois), noDois);
        assertEquals(arvore.visitar(noDois).getFilhoEsq(), noDois.getFilhoEsq());
    }

    // Teste para saber se a arvore está completa ou não
    @Test
    public void testEhCompleta() throws Exception {
        //Adicionando valores na árvore
        int [] valores = {50, 38, 22, 40, 63, 62, 70};
        for(int valor : valores) {
            arvore.inserir(new No(valor));
        }

        //Testando o método

        boolean resultadoEsperado = true;

        assertTrue(arvore.ehCompleta() == resultadoEsperado);
    }

    // Teste da Altura da arvore
    @Test
    public void testAltura() throws Exception {

        //testando caso a arvore ainda não tenha valor
        assertEquals(-1, arvore.altura() );

        //Adicionando valores na árvore
        int [] valores = {50, 38, 22, 40, 63, 62, 70, 71, 61, 60,59};
        for(int valor : valores) {
            arvore.inserir(new No(valor));
        }

        // Testando método
        int valorEsperado = 5;

        assertEquals(valorEsperado, arvore.altura());

    }
    @Test
    public void testRemoverPelaRaiz() throws Exception{

        // Criando nós espécificos

        No noUm = new No(50);
        No noDois = new No(63);
        No noTres = new No(22);
        No noQuatro = new No(38);

//         Inserindo os 7 valores.
        arvore.inserir(noUm);
        arvore.inserir(noQuatro);
        arvore.inserir(new No(40));
        arvore.inserir(noDois);
        arvore.inserir(noTres);
        arvore.inserir(new No(62));
        arvore.inserir(new No(70));

        arvore.remover(noUm);
        assertEquals(40, noUm.getDado());
        arvore.remover(noUm);
        assertEquals(38, noUm.getDado());
        arvore.remover(noUm);
        assertEquals(noUm.getDado(), 22);

    }

    // Teste do Método de percorrer a lista em Pre Ordem
    @Test
    public void testPreOrdem() throws Exception {
        // Inserindo os valores na Arvore
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        No raiz = new No(10);
        arvore.inserir(raiz);
        arvore.inserir(new No(8));
        arvore.inserir(new No(9));
        arvore.inserir(new No(6));
        arvore.inserir(new No(7));
        arvore.inserir(new No(20));
        arvore.inserir(new No(15));
        arvore.inserir(new No(16));
        arvore.inserir(new No(12));
        arvore.inserir(new No(13));

        // Redirecionar a saída para uma variável para verificar a saída
        ByteArrayOutputStream saidaAtual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaAtual));

        // Chamar o método percorrerEmLargura
        arvore.preOrdem(raiz);

        // Verificar a saída esperada
        String saidaEsperada = "10 8 6 7 9 20 15 12 13 16 ";
        assertEquals(saidaEsperada, saidaAtual.toString());
    }

    // Teste do Método  de percorrer a lista em Ordem Simétrica
    @Test
    public void testInOrdem() throws Exception {
        // Inserindo os valores na Arvore
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        No raiz = new No(10);
        arvore.inserir(raiz);
        arvore.inserir(new No(8));
        arvore.inserir(new No(9));
        arvore.inserir(new No(6));
        arvore.inserir(new No(7));
        arvore.inserir(new No(20));
        arvore.inserir(new No(15));
        arvore.inserir(new No(16));
        arvore.inserir(new No(12));
        arvore.inserir(new No(13));

        // Redirecionar a saída para uma variável para verificar a saída
        ByteArrayOutputStream saidaAtual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaAtual));

        // Chamar o método percorrerEmLargura
        arvore.inOrdem(raiz);

        // Verificar a saída esperada
        String saidaEsperada = "6 7 8 9 10 12 13 15 16 20 ";
        assertEquals(saidaEsperada, saidaAtual.toString());
    }

    // Teste do Método de percorrer a lista em Pos Ordem
    @Test
    public void testPosOrdem() throws Exception {
        // Inserindo os valores na Arvore
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        No raiz = new No(10);
        arvore.inserir(raiz);
        arvore.inserir(new No(8));
        arvore.inserir(new No(9));
        arvore.inserir(new No(6));
        arvore.inserir(new No(7));
        arvore.inserir(new No(20));
        arvore.inserir(new No(15));
        arvore.inserir(new No(16));
        arvore.inserir(new No(12));
        arvore.inserir(new No(13));

        // Redirecionar a saída para uma variável para verificar a saída
        ByteArrayOutputStream saidaAtual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaAtual));

        // Chamar o método percorrerEmLargura
        arvore.posOrdem(raiz);

        // Verificar a saída esperada
        String saidaEsperada = "7 6 9 8 13 12 16 15 20 10 ";
        assertEquals(saidaEsperada, saidaAtual.toString());
    }

    //Teste do método Percorrer em largura
    @Test
    public void testPercorrerEmLargura() throws Exception{

        // Inserindo os valores na Arvore
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        No raiz = new No(10);
        arvore.inserir(raiz);
        arvore.inserir(new No(8));
        arvore.inserir(new No(9));
        arvore.inserir(new No(6));
        arvore.inserir(new No(7));
        arvore.inserir(new No(20));
        arvore.inserir(new No(15));
        arvore.inserir(new No(16));
        arvore.inserir(new No(12));
        arvore.inserir(new No(13));

        // Redirecionar a saída para uma variável para verificar a saída
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));

        // Chamar o método percorrerEmLargura
        arvore.percorrerEmLargura(raiz);

        // Verificar a saída esperada
        String saidaEsperada = "10 8 20 6 9 15 7 12 16 13 ";
        assertEquals(saidaEsperada, saida.toString());
    }
}
