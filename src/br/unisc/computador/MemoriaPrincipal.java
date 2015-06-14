package br.unisc.computador;

import java.util.Arrays;

public class MemoriaPrincipal {

    private final byte[] enderecos;
    private final int tamanhoMemoria;
    private final int tamanhoEndereco;
    private final int tamanhoBloco;
    
    public MemoriaPrincipal(int tamanhoEndereco, int tamanhoBloco) {
        this.tamanhoBloco = tamanhoBloco;
        this.tamanhoEndereco = tamanhoEndereco;
        this.tamanhoMemoria = (int)Math.pow(2, tamanhoEndereco);
        this.enderecos = new byte[tamanhoMemoria];
    }
    
    /**
     * Busca bloco na memória através de seu índice
     * @param indiceBloco Índice do bloco
     * @return Conteúdo do bloco
     */
    public byte[] getBloco(int indiceBloco) {
        int primeiroEnderecoBloco = indiceBloco * tamanhoBloco;
        return Arrays.copyOfRange(enderecos, primeiroEnderecoBloco, primeiroEnderecoBloco + tamanhoBloco);
    }
    
    /**
     * Busca bloco na memória através de um endereço que está dentro do bloco
     * @param endereco Endereço de memória
     * @return Conteúdo do bloco
     */
    public byte[] getBlocoPorEndereco(int endereco) {
        int indiceBloco = (int)Math.floor(endereco / tamanhoBloco);
        return getBloco(indiceBloco);
    }

    /**
     * Busca o dado de um endereço de memória diretamente
     * @param endereco Endereço
     * @return Dados
     */
    public byte getValorEndereco(int endereco) {
        return enderecos[endereco];
    }

    /**
     * Define o valor de um endereço de memória diretamente
     * @param endereco Endereço
     * @param dado Dado a ser gravado
     */
    public void setValorEndereco(int endereco, byte dado) {
        enderecos[endereco] = dado;
    }

    public int getTamanhoMemoria() {
        return tamanhoMemoria;
    }

    public int getTamanhoEndereco() {
        return tamanhoEndereco;
    }
    
    public int getTamanhoBloco() {
        return tamanhoBloco;
    }
    
}
