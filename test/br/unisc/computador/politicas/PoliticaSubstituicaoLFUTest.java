package br.unisc.computador.politicas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

        // Blocos gravados na ordem: 1, 0, 3, 2
        // No momento em que são gravados, os blocos ficam com frequência = 1
        politica.marcarBlocoGravado(conjunto, conjunto[1]);
        politica.marcarBlocoGravado(conjunto, conjunto[0]);
        politica.marcarBlocoGravado(conjunto, conjunto[3]);
        politica.marcarBlocoGravado(conjunto, conjunto[2]);

        // Visita o bloco 3, logo aumenta a sua frequência. Agora:
        // Frequencia bloco 0 = 1
        // Frequencia bloco 1 = 1
        // Frequencia bloco 2 = 1
        // Frequencia bloco 3 = 2
        politica.marcarBlocoLido(conjunto, conjunto[3]);
        
        // Os blocos 0, 1 e 2 possuem as frequências mínimas (F = 1), portanto
        // um deles deverá ser substituído.
        // Para o "desempate", o registro mais antigo, ou seja, gravado há
        // mais tempo na cache será o escolhido para ser substituído.
        // Como o bloco 1 foi o primeiro a ser colocado na cache, e possui a
        // frequência mínima, este deve ser escolhido para ser substituído.
        assertEquals(1, politica.calcularPosicaoSubstituicao(conjunto));
        
        // Bloco 1 é marcado como gravado novamente na cache (assim, torna-se
        // o mais recente a ser gravado na cache). Sua frequência foi novamente
        // setada para 1.
        politica.marcarBlocoGravado(conjunto, conjunto[1]);
        
        // Os blocos 0, 1 e 2 possuem as frequências mínimas (F = 1), portanto
        // um deles deverá ser substituído.
        // Agora, o mais antigo é o bloco 0, seguido pelo bloco 2, e depois pelo
        // bloco 1, que foi regravado na cache.
        // Sendo o mais antigo, o bloco 0 deve ser agora marcado para ser substituído.
        assertEquals(0, politica.calcularPosicaoSubstituicao(conjunto));
        
        // Bloco 0 regravado.
        politica.marcarBlocoGravado(conjunto, conjunto[0]);
        
        // Os blocos 0, 1 e 2 possuem as frequências mínimas (F = 1), portanto
        // um deles deverá ser substituído.
        // O bloco 2 é o mais antigo da cache agora, então ele é o escolhido.
        assertEquals(2, politica.calcularPosicaoSubstituicao(conjunto));
        
        // Bloco 2 regravado.
        politica.marcarBlocoGravado(conjunto, conjunto[2]);

        // Agora o bloco 1 é o mais antigo da cache novamente com frequência mínima,
        // portanto deve ser apontado para substituição.
        assertEquals(1, politica.calcularPosicaoSubstituicao(conjunto));
        
        // Bloco 1 regravado.
        politica.marcarBlocoGravado(conjunto, conjunto[1]);
        
        // Visita bloco 0, aumentando a sua frequência para 2. Agora:
        // Frequencia bloco 0 = 2
        // Frequencia bloco 1 = 1
        // Frequencia bloco 2 = 1
        // Frequencia bloco 3 = 2
        politica.marcarBlocoLido(conjunto, conjunto[0]);
        
        // Os blocos com frequência mínima agora são o 1 e o 2. Sendo o 2 o mais antigo,
        // este deverá ser marcado para substituição.
        assertEquals(2, politica.calcularPosicaoSubstituicao(conjunto));
    }
    
}
