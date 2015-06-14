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
public class MemoriaCache {
    
    private int quantidadeConjuntos;
    private int tamanho;
    
    public MemoriaCache(int tamanhoKb, int quantidadeConjuntos) {
        this.tamanho = tamanhoKb * 1024;
        this.quantidadeConjuntos = quantidadeConjuntos;
    }
    
}
