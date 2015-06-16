package br.unisc.computador.politicas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PoliticaSubstituicaoLRUTest {
    
    public PoliticaSubstituicaoLRUTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testarPoliticaSubstituicao() {
        Object[] conjunto = new Object[] { new Object(), new Object(), new Object(), new Object() };
        PoliticaSubstituicaoLRU politica = new PoliticaSubstituicaoLRU();
        
        // Blocos gravados na ordem: 1, 0, 3, 2
        // As visitas devem ser gravadas no momento que o bloco é gravado, portanto
        // estão com a mesma ordem.
        politica.marcarBlocoGravado(conjunto[1]);
        politica.marcarBlocoGravado(conjunto[0]);
        politica.marcarBlocoGravado(conjunto[3]);
        politica.marcarBlocoGravado(conjunto[2]);
        
        // Visita o bloco 3, logo, o estado das visitas (do mais antigo para o mais
        // recente) agora deve estar: 1, 0, 2, 3
        politica.marcarBlocoLido(conjunto[3]);
        
        // O bloco visitado por último é o 1, logo este deve ser marcado para ser
        // substituído.
        assertEquals(1, politica.calcularPosicaoSubstituicao(conjunto));
        
        // Bloco 1 é marcado como gravado novamente na cache (assim, torna-se
        // o mais recente a ser gravado e visitado).
        // Estado das visitas: 0, 2, 3, 1
        politica.marcarBlocoGravado(conjunto[1]);
        
        // 0 deve ser substituído
        assertEquals(0, politica.calcularPosicaoSubstituicao(conjunto));
        politica.marcarBlocoGravado(conjunto[0]);
        
        // 2 deve ser substituído
        assertEquals(2, politica.calcularPosicaoSubstituicao(conjunto));
        politica.marcarBlocoGravado(conjunto[2]);
        
        // 3 deve ser substituído
        assertEquals(3, politica.calcularPosicaoSubstituicao(conjunto));
        politica.marcarBlocoGravado(conjunto[3]);
        
        // Visita bloco 1 e 2.
        politica.marcarBlocoLido(conjunto[1]);
        politica.marcarBlocoLido(conjunto[2]);

        assertEquals(0, politica.calcularPosicaoSubstituicao(conjunto));
        politica.marcarBlocoGravado(conjunto[0]);

        assertEquals(3, politica.calcularPosicaoSubstituicao(conjunto));
    }
}
