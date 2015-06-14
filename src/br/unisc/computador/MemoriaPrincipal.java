/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.computador;

import java.util.Arrays;


/**
 *
 * @author guilhermesehn
 */
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
    
    public int getTamanhoMemoria() {
        return tamanhoMemoria;
    }

    public int getTamanhoEndereco() {
        return tamanhoEndereco;
    }
    
    public int getTamanhoBloco() {
        return tamanhoBloco;
    }
    
    public byte[] getBloco(int numBloco) {
        int primeiroEnderecoBloco = numBloco * tamanhoBloco;
        return Arrays.copyOfRange(enderecos, primeiroEnderecoBloco, primeiroEnderecoBloco + tamanhoBloco);
    }
    
    public byte[] getBlocoPorEndereco(int endereco) {
        int numBloco = (int)Math.floor(endereco / tamanhoBloco);
        return getBloco(numBloco);
    }

    public byte getValorEndereco(int endereco) {
        return enderecos[endereco];
    }
    
    public void setValorEndereco(int endereco, byte valor) {
        enderecos[endereco] = valor;
    }
    
}
