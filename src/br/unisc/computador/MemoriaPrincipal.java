/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.computador;


/**
 *
 * @author guilhermesehn
 */
public class MemoriaPrincipal {
    
    private final byte[] dados;
    private int tamanhoMemoria = 0;
    
    public MemoriaPrincipal(int tamanhoEndereco) {
        tamanhoMemoria = (int)Math.pow(2, tamanhoEndereco);
        dados = new byte[tamanhoMemoria];
    }
    
    public int getTamanhoMemoria() {
        return tamanhoMemoria;
    }
    
}
