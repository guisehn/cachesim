/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.computador;

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
    public void deveCalcularTamanhoMemoriaCorretamente() {
        MemoriaPrincipal mp;
        
        mp = new MemoriaPrincipal(10, 4);
        assertEquals(mp.getTamanhoMemoria(), 1024);
        
        mp = new MemoriaPrincipal(20, 4);
        assertEquals(mp.getTamanhoMemoria(), 1_048_576);
        
        mp = new MemoriaPrincipal(22, 4);
        assertEquals(mp.getTamanhoMemoria(), 4_194_304);
    }
    
    @Test
    public void deveSetarEObterValoresDosEnderecosCorretamente() {
        MemoriaPrincipal mp = new MemoriaPrincipal(10, 4);
        mp.setValorEndereco(0, (byte)5);
        mp.setValorEndereco(1, (byte)9);
        mp.setValorEndereco(2, (byte)2);
        mp.setValorEndereco(3, (byte)4);
        
        assertEquals(mp.getValorEndereco(0), (byte)5);
        assertEquals(mp.getValorEndereco(1), (byte)9);
        assertEquals(mp.getValorEndereco(2), (byte)2);
        assertEquals(mp.getValorEndereco(3), (byte)4);
    }
    
    @Test
    public void deveBuscarBlocosCorretamente() {
        MemoriaPrincipal mp = new MemoriaPrincipal(10, 4);

        mp.setValorEndereco(0, (byte)5);
        mp.setValorEndereco(1, (byte)9);
        mp.setValorEndereco(2, (byte)2);
        mp.setValorEndereco(3, (byte)4);
        mp.setValorEndereco(4, (byte)15);
        mp.setValorEndereco(5, (byte)22);
        mp.setValorEndereco(6, (byte)30);
        mp.setValorEndereco(7, (byte)90);
        mp.setValorEndereco(8, (byte)3);
        mp.setValorEndereco(9, (byte)62);
        mp.setValorEndereco(10, (byte)4);
        mp.setValorEndereco(11, (byte)0);
        
        assertArrayEquals(mp.getBloco(0), new byte[] { 5, 9, 2, 4 });
        assertArrayEquals(mp.getBloco(1), new byte[] { 15, 22, 30, 90 });
        assertArrayEquals(mp.getBloco(2), new byte[] { 3, 62, 4, 0 });
    }
    
    @Test
    public void deveBuscarBlocosPorEnderecoCorretamente() {
        MemoriaPrincipal mp;
        
        mp = new MemoriaPrincipal(10, 4);

        mp.setValorEndereco(0, (byte)5);
        mp.setValorEndereco(1, (byte)9);
        mp.setValorEndereco(2, (byte)2);
        mp.setValorEndereco(3, (byte)4);
        mp.setValorEndereco(4, (byte)15);
        mp.setValorEndereco(5, (byte)22);
        mp.setValorEndereco(6, (byte)30);
        mp.setValorEndereco(7, (byte)90);
        mp.setValorEndereco(8, (byte)3);
        mp.setValorEndereco(9, (byte)62);
        mp.setValorEndereco(10, (byte)4);
        mp.setValorEndereco(11, (byte)0);
        
        assertArrayEquals(mp.getBlocoPorEndereco(0), new byte[] { 5, 9, 2, 4 });
        assertArrayEquals(mp.getBlocoPorEndereco(1), new byte[] { 5, 9, 2, 4 });
        assertArrayEquals(mp.getBlocoPorEndereco(2), new byte[] { 5, 9, 2, 4 });
        assertArrayEquals(mp.getBlocoPorEndereco(3), new byte[] { 5, 9, 2, 4 });

        assertArrayEquals(mp.getBlocoPorEndereco(4), new byte[] { 15, 22, 30, 90 });
        assertArrayEquals(mp.getBlocoPorEndereco(5), new byte[] { 15, 22, 30, 90 });
        assertArrayEquals(mp.getBlocoPorEndereco(6), new byte[] { 15, 22, 30, 90 });
        assertArrayEquals(mp.getBlocoPorEndereco(7), new byte[] { 15, 22, 30, 90 });
        
        assertArrayEquals(mp.getBlocoPorEndereco(8), new byte[] { 3, 62, 4, 0 });
    }
}
