
package br.unisc.computador;

import br.unisc.computador.politicas.PoliticaSubstituicao;
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
public class MemoriaCacheTest {
    
    public MemoriaCacheTest() {
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
    public void getTamanhoOffsetDeveCalcularCorretamente() {
        MemoriaPrincipal mp;
        MemoriaCache cache;
        
        mp = new MemoriaPrincipal(12, 4);
        cache = new MemoriaCache(mp, 40, 8, new StubPoliticaSubstituicao());
        assertEquals(2, cache.getTamanhoOffset());
        
        mp = new MemoriaPrincipal(12, 8);
        cache = new MemoriaCache(mp, 40, 8, new StubPoliticaSubstituicao());
        assertEquals(3, cache.getTamanhoOffset());
        
        mp = new MemoriaPrincipal(12, 16);
        cache = new MemoriaCache(mp, 40, 8, new StubPoliticaSubstituicao());
        assertEquals(4, cache.getTamanhoOffset());
    }
    
    @Test
    public void getTamanhoIndexDeveCalcularCorretamente() {
        MemoriaPrincipal mp = new MemoriaPrincipal(12, 4);
        MemoriaCache cache;
        
        cache = new MemoriaCache(mp, 40, 8, new StubPoliticaSubstituicao());
        assertEquals(3, cache.getTamanhoIndex());
        
        cache = new MemoriaCache(mp, 40, 16, new StubPoliticaSubstituicao());
        assertEquals(4, cache.getTamanhoIndex());
        
        cache = new MemoriaCache(mp, 40, 32, new StubPoliticaSubstituicao());
        assertEquals(5, cache.getTamanhoIndex());
    }

    @Test
    public void getTamanhoTagDeveCalcularCorretamente() {
        MemoriaPrincipal mp;
        MemoriaCache cache;
        
        mp = new MemoriaPrincipal(12, 4);
        cache = new MemoriaCache(mp, 40, 8, new StubPoliticaSubstituicao());
        assertEquals(12 - 2 - 3, cache.getTamanhoTag());

        cache = new MemoriaCache(mp, 40, 16, new StubPoliticaSubstituicao());
        assertEquals(12 - 2 - 4, cache.getTamanhoTag());
        
        cache = new MemoriaCache(mp, 40, 32, new StubPoliticaSubstituicao());
        assertEquals(12 - 2 - 5, cache.getTamanhoTag());
        
        mp = new MemoriaPrincipal(24, 8);
        cache = new MemoriaCache(mp, 40, 16, new StubPoliticaSubstituicao());
        assertEquals(24 - 3 - 4, cache.getTamanhoTag());

        cache = new MemoriaCache(mp, 40, 32, new StubPoliticaSubstituicao());
        assertEquals(24 - 3 - 5, cache.getTamanhoTag());
        
        cache = new MemoriaCache(mp, 40, 64, new StubPoliticaSubstituicao());
        assertEquals(24 - 3 - 6, cache.getTamanhoTag());
    }
    
    class StubPoliticaSubstituicao implements PoliticaSubstituicao {

        @Override
        public void marcarBlocoLido(Object[] conjunto, Object bloco) { }

        @Override
        public void marcarBlocoGravado(Object[] conjunto, Object bloco) { }

        @Override
        public int calcularPosicaoSubstituicao(Object[] conjunto) {
            return 0;
        }
        
    }
}
