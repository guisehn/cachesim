/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.computador;

import br.unisc.enums.PoliticaSubstituicao;
import br.unisc.main.Utility;

/**
 *
 * @author guilhermesehn
 */
public class MemoriaCache {
    
    private final int quantidadeConjuntos;
    private final int tamanhoEndereco;
    private final int tamanhoCache;
    private final int tamanhoBloco;
    private final int tamanhoConjunto;
    private final int tamanhoOffset;
    private final int tamanhoIndex;
    private final PoliticaSubstituicao politicaSubstituicao;
    
    // Quantidade de buscas, hits e misses na memória cache
    private int quantidadeBuscas;
    private int quantidadeHits;
    private int quantidadeMisses;
    
    // Referência para a memória principal
    private final MemoriaPrincipal memoriaPrincipal;
    
    // Primeiro índice = conjunto. Segundo índice = tamanhoOffset do bloco
    private final BlocoCache[][] conjuntos;
    
    public MemoriaCache(MemoriaPrincipal mp, int tamanhoCache, int quantidadeConjuntos, PoliticaSubstituicao politica) {
        this.memoriaPrincipal = mp;
        this.tamanhoBloco = mp.getTamanhoBloco();
        this.tamanhoEndereco = mp.getTamanhoEndereco();
        this.tamanhoCache = tamanhoCache;
        this.quantidadeConjuntos = quantidadeConjuntos;
        this.tamanhoOffset = (int)Utility.log2(tamanhoBloco);
        this.tamanhoIndex = (int)Utility.log2(quantidadeConjuntos);
        this.politicaSubstituicao = politica;
        this.quantidadeBuscas = 0;
        this.quantidadeMisses = 0;
        this.quantidadeHits = 0;

        // Calcula o tamanho do conjunto com base nas demais informações
        this.tamanhoConjunto = (tamanhoCache) / (quantidadeConjuntos * tamanhoBloco);

        // Cria os objetos para armazenar os dados da cache
        conjuntos = new BlocoCache[quantidadeConjuntos][tamanhoConjunto];
        for (int i = 0; i < quantidadeConjuntos; i++) {
            conjuntos[i] = new BlocoCache[tamanhoConjunto];

            for (int j = 0; j < tamanhoConjunto; j++) {
                conjuntos[i][j] = new BlocoCache();
            }
        }
    }

    public byte buscarEndereco(int endereco) {
        quantidadeBuscas++;
        
        int tag = endereco >> (tamanhoOffset + tamanhoIndex);        
        int indiceConjunto = endereco >> tamanhoOffset; // remove o offset do endereço
        indiceConjunto = indiceConjunto & ((1 << tamanhoIndex) - 1); // busca apenas os bits do índice do conjunto
        
        BlocoCache[] conjunto = conjuntos[indiceConjunto];
        BlocoCache blocoEncontrado = null;
        
        for (BlocoCache bloco : conjunto) {
            if (bloco.isValido() && bloco.getTag() == tag) {
                blocoEncontrado = bloco;
                break;
            }
        }

        byte[] dadosBloco;
        
        if (blocoEncontrado != null) {
            quantidadeHits++;
            dadosBloco = blocoEncontrado.getDados();
        } else {
            quantidadeMisses++;
            dadosBloco = memoriaPrincipal.getBlocoPorEndereco(endereco);

            blocoEncontrado = new BlocoCache();
            blocoEncontrado.setValido(true);
            blocoEncontrado.setTag(tag);
            blocoEncontrado.setDados(dadosBloco);
            
            gravarCache(conjunto, blocoEncontrado);
        }

        int offset = endereco & ((1 << tamanhoOffset) - 1); // busca o offset dentro do endereço
        return dadosBloco[offset];
    }
    
    private void gravarCache(BlocoCache[] conjunto, BlocoCache bloco) {
        if (politicaSubstituicao == PoliticaSubstituicao.ALE) {
            int posicao = Utility.randInt(0, tamanhoConjunto - 1);
            conjunto[posicao] = bloco;
            return;
        }

        // to-do: outras políticas de substituições
    }
    
    public int getTamanhoCache() {
        return tamanhoCache;
    }
    
    public int getQuantidadeBuscas() {
        return quantidadeBuscas;
    }
    
    public int getQuantidadeMisses() {
        return quantidadeMisses;
    }
    
    public int getQuantidadeHits() {
        return quantidadeHits;
    }
    
    public int getTamanhoIndex() {
        return tamanhoIndex;
    }
    
    public int getTamanhoOffset() {
        return tamanhoOffset;
    }
    
    public int getTamanhoTag() {
        return tamanhoEndereco - tamanhoIndex - tamanhoOffset;
    }
    
    public double getHitRate() {
        return (double)quantidadeHits / (double)quantidadeBuscas;
    }
    
    public double getMissRate() {
        return (double)quantidadeMisses / (double)quantidadeBuscas;
    }
    
    public PoliticaSubstituicao getPoliticaSubstituicao() {
        return politicaSubstituicao;
    }
    
}
