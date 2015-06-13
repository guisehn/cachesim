/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.main;

import br.unisc.exceptions.ArquivoInvalidoException;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author guilhermesehn
 */
public class ArquivoEnderecosTest {
    
    @Rule
    public TemporaryFolder pasta = new TemporaryFolder();
    
    public ArquivoEnderecosTest() {
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
    
    @Test(expected = ArquivoInvalidoException.class)
    public void deveLancarExceptionParaArquivoNaoEncontrado() throws ArquivoInvalidoException {
        String caminhoPasta = pasta.getRoot().getPath();
        String caminhoArquivoNaoExistente = caminhoPasta + "/nao-existe.txt";

        ArquivoEnderecos arquivoEnderecos = new ArquivoEnderecos(caminhoArquivoNaoExistente);
        arquivoEnderecos.buscarEnderecos();
    }

    @Test(expected = ArquivoInvalidoException.class)
    public void deveLancarExceptionSeArquivoEstiverVazio() throws ArquivoInvalidoException, IOException {
        File arquivo = pasta.newFile("arquivo-vazio.txt");
        FileUtils.writeStringToFile(arquivo, "");  

        ArquivoEnderecos arquivoEnderecos = new ArquivoEnderecos(arquivo.getPath());
        arquivoEnderecos.buscarEnderecos();
    }

    @Test(expected = ArquivoInvalidoException.class)
    public void deveLancarExceptionSeAlgumaLinhaNaoEstiverEmBinario() throws ArquivoInvalidoException, IOException {
        File arquivo = pasta.newFile("formato-invalido.txt");
        FileUtils.writeStringToFile(arquivo, "oi");  

        ArquivoEnderecos arquivoEnderecos = new ArquivoEnderecos(arquivo.getPath());
        arquivoEnderecos.buscarEnderecos();
    }

    @Test(expected = ArquivoInvalidoException.class)
    public void deveLancarExceptionSeEnderecosDeMemoriaPossuemTamanhosDiferentes() throws ArquivoInvalidoException, IOException {
        File arquivo = pasta.newFile("formato-invalido.txt");
        FileUtils.writeStringToFile(arquivo, "11\n1");  

        ArquivoEnderecos arquivoEnderecos = new ArquivoEnderecos(arquivo.getPath());
        arquivoEnderecos.buscarEnderecos();
    }

    @Test
    public void deveCalcularEnderecosCorretamente() throws ArquivoInvalidoException, IOException {
        File arquivo = pasta.newFile("arquivo-ok.txt");
        FileUtils.writeStringToFile(arquivo, "111\n101\n000\n001\n110");  

        ArquivoEnderecos arquivoEnderecos = new ArquivoEnderecos(arquivo.getPath());
        Integer[] enderecos = arquivoEnderecos.buscarEnderecos();
        Integer[] enderecosCorretos = { 0b111, 0b101, 0b0, 0b001, 0b110 };
        
        assertArrayEquals(enderecos, enderecosCorretos);
    }

}
