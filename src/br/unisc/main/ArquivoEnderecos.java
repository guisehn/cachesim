package br.unisc.main;

import br.unisc.exceptions.ArquivoInvalidoException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Stream;

public class ArquivoEnderecos {
    
    private final File arquivo;
    private int tamanhoEndereco;
    
    public ArquivoEnderecos(String caminho) {
        arquivo = new File(caminho);
    }

    /**
     * Retorna os endereços do arquivo em formato inteiro
     * @return Lista de endereços em formato inteiro
     * @throws ArquivoInvalidoException Erro de formato do arquivo
     */
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
            
            this.tamanhoEndereco = arrayLinhas[0].length();
            
            if (tamanhoEndereco == 0) {
                throw new ArquivoInvalidoException(arquivo, "Endereço de memória deve ter ao menos um bit");
            }
            
            for (String linha : arrayLinhas) {
                validarLinha(linha);
            }
            
            enderecos = stringsBinariasParaInteiros(arrayLinhas);
        } catch (IOException ex) {
            throw new ArquivoInvalidoException(arquivo, "Erro ao ler arquivo");
        }
        
        return enderecos;
    }
    
    /**
     * Valida uma linha do arquivo
     * @param linha Conteúdo da linha a ser validada
     * @throws ArquivoInvalidoException Erro de formato
     */
    private void validarLinha(String linha) throws ArquivoInvalidoException {
        if (linha.length() != tamanhoEndereco) {
            throw new ArquivoInvalidoException(arquivo, "Todos os endereços de memória devem ter o mesmo tamanho");
        }

        if (!linha.trim().matches("[01]+")) {
            throw new ArquivoInvalidoException(arquivo, "Os endereços de memória devem ser representados em formato binário");
        }
    }
    
    /**
     * Converte array de strings contendo linhas em binário para array de inteiros
     * @param binarios Array de strings em binário
     * @return Array de inteiros
     */
    private Integer[] stringsBinariasParaInteiros(String[] binarios) {
        return Arrays.stream(binarios).map(str -> Integer.parseInt(str, 2)).toArray(s -> new Integer[s]);
    }
    
    public int getTamanhoEndereco() {
        return tamanhoEndereco;
    }
    
}
