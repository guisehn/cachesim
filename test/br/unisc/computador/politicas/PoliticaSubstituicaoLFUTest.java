
package br.unisc.computador.politicas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guilhermesehn
 */
public class PoliticaSubstituicaoLFUTest {
    
    public PoliticaSubstituicaoLFUTest() {
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
        
        PoliticaSubstituicaoLFU politica = new PoliticaSubstituicaoLFU();

        politica.marcarBlocoGravado(conjunto[1]);
        politica.marcarBlocoGravado(conjunto[0]);
        politica.marcarBlocoGravado(conjunto[3]);
        politica.marcarBlocoGravado(conjunto[2]);

        politica.marcarBlocoLido(conjunto[3]);
        
        assertEquals(1, politica.calcularPosicaoSubstituicao(conjunto));
        politica.marcarBlocoGravado(conjunto[1]);
        
        assertEquals(0, politica.calcularPosicaoSubstituicao(conjunto));
        politica.marcarBlocoGravado(conjunto[0]);
        
        assertEquals(2, politica.calcularPosicaoSubstituicao(conjunto));
        politica.marcarBlocoGravado(conjunto[2]);

        assertEquals(1, politica.calcularPosicaoSubstituicao(conjunto));
        politica.marcarBlocoGravado(conjunto[1]);
    }
    
}
