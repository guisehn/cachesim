package br.unisc.computador.politicas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PoliticaSubstituicaoCOMTest {
    
    public PoliticaSubstituicaoCOMTest() {
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
        Object[] conjunto0 = new Object[] { new Object(), new Object(), new Object(), new Object() };
        Object[] conjunto1 = new Object[] { new Object(), new Object(), new Object(), new Object() };
        
        PoliticaSubstituicaoCOM politica = new PoliticaSubstituicaoCOM();

        assertEquals(0, politica.calcularPosicaoSubstituicao(conjunto0));
        politica.marcarBlocoGravado(conjunto0, conjunto0[0]);
        
        assertEquals(1, politica.calcularPosicaoSubstituicao(conjunto0));
        politica.marcarBlocoGravado(conjunto0, conjunto0[1]);
        
        assertEquals(2, politica.calcularPosicaoSubstituicao(conjunto0));
        politica.marcarBlocoGravado(conjunto0, conjunto0[2]);
        
        assertEquals(0, politica.calcularPosicaoSubstituicao(conjunto1));
        politica.marcarBlocoGravado(conjunto1, conjunto1[0]);
        
        assertEquals(3, politica.calcularPosicaoSubstituicao(conjunto0));
        politica.marcarBlocoGravado(conjunto0, conjunto0[3]);
        
        assertEquals(1, politica.calcularPosicaoSubstituicao(conjunto1));
        politica.marcarBlocoGravado(conjunto1, conjunto1[1]);
        
        assertEquals(0, politica.calcularPosicaoSubstituicao(conjunto0));
        politica.marcarBlocoGravado(conjunto0, conjunto0[0]);
        
        assertEquals(2, politica.calcularPosicaoSubstituicao(conjunto1));
        politica.marcarBlocoGravado(conjunto1, conjunto1[2]);
        
        assertEquals(1, politica.calcularPosicaoSubstituicao(conjunto0));
        politica.marcarBlocoGravado(conjunto0, conjunto0[1]);
        
        assertEquals(3, politica.calcularPosicaoSubstituicao(conjunto1));
        politica.marcarBlocoGravado(conjunto1, conjunto1[3]);
        
        assertEquals(0, politica.calcularPosicaoSubstituicao(conjunto1));
        politica.marcarBlocoGravado(conjunto1, conjunto1[0]);
    }
}
