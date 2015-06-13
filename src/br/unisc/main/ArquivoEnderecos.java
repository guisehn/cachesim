/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.main;

import br.unisc.exceptions.ArquivoInvalidoException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 *
 * @author guilhermesehn
 */
public class ArquivoEnderecos {
    
    private final File arquivo;
    private int tamanhoEndereco;
    
    public ArquivoEnderecos(String caminho) {
        arquivo = new File(caminho);
    }
    
    public Integer[] buscarEnderecos() throws ArquivoInvalidoException {
        if (!arquivo.exists()) {
            throw new ArquivoInvalidoException(arquivo, "Arquivo não encontrado");
        }
        
        Integer[] enderecos;
        
        try (Stream<String> linhas = Files.lines(arquivo.toPath(), Charset.defaultCharset())) {
            String[] arrayLinhas = linhas.toArray(size -> new String[size]);
            
            if (arrayLinhas.length == 0) {
                throw new ArquivoInvalidoException(arquivo, "O arquivo está vazio");
            }
            
            tamanhoEndereco = arrayLinhas[0].length();
            
            if (tamanhoEndereco == 0) {
                throw new ArquivoInvalidoException(arquivo, "Endereço de memória deve ter ao"
                    + " menos um bit");
            }
            
            for (String linha : arrayLinhas) {
                validarLinha(linha);
            }
            
            enderecos = binariosParaDecimais(arrayLinhas);
        } catch (IOException ex) {
            throw new ArquivoInvalidoException(arquivo, "Erro ao ler arquivo");
        }
        
        return enderecos;
    }
    
    public int getTamanhoEndereco() {
        return tamanhoEndereco;
    }
    
    private void validarLinha(String linha) throws ArquivoInvalidoException {
        if (linha.length() != tamanhoEndereco) {
            throw new ArquivoInvalidoException(arquivo, "Todos os endereços de memória devem ter"
                + " o mesmo tamanho");
        }

        if (!linha.trim().matches("[01]+")) {
            throw new ArquivoInvalidoException(arquivo, "Os endereços de memória devem ser" +
                " representados em formato binário");
        }
    }
    
    private Integer[] binariosParaDecimais(String[] binarios) {
        Integer[] enderecos = new Integer[binarios.length];
        int i = 0;
        
        for (String bin : binarios) {
            enderecos[i++] = Integer.parseInt(bin.trim(), 2);
        }
        
        return enderecos;
    }
    
}
