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
    
    public MemoriaPrincipal(int tamanhoEndereco) {
        int tamanhoMemoria = (int)Math.pow(2, tamanhoEndereco);
        dados = new byte[tamanhoMemoria];
    }
    
}
