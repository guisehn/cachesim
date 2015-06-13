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
    private final int enderecosPorBloco;
    
    public MemoriaPrincipal(int tamanhoEndereco, int enderecosPorBloco) {
        this.enderecosPorBloco = enderecosPorBloco;
        this.tamanhoMemoria = (int)Math.pow(2, tamanhoEndereco);
        this.enderecos = new byte[tamanhoMemoria];
    }
    
    public int getTamanhoMemoria() {
        return tamanhoMemoria;
    }
    
    public byte[] getBloco(int numBloco) {
        int primeiroEnderecoBloco = numBloco * enderecosPorBloco;
        return Arrays.copyOfRange(enderecos, primeiroEnderecoBloco, primeiroEnderecoBloco + enderecosPorBloco);
    }
    
    public byte[] getBlocoPorEndereco(int endereco) {
        int numBloco = (int)Math.floor(endereco / enderecosPorBloco);
        return getBloco(numBloco);
    }

    public byte getValorEndereco(int endereco) {
        return enderecos[endereco];
    }
    
    public void setValorEndereco(int endereco, byte valor) {
        enderecos[endereco] = valor;
    }
    
}
