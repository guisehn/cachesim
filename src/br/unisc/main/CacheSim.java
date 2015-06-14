/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.main;

import br.unisc.computador.MemoriaCache;
import br.unisc.computador.MemoriaPrincipal;
import br.unisc.enums.PoliticaSubstituicao;
import br.unisc.exceptions.ArquivoInvalidoException;

/**
 *
 * @author guilhermesehn
 */
public class CacheSim {

    private static final int ENDERECOS_POR_BLOCO = 4;
    
    public static void main(String[] args) {
        int tamanhoCache;
        int numeroConjuntos;
        PoliticaSubstituicao politica;
        String caminhoArquivoEnderecos;
        
        try {
            tamanhoCache = Integer.valueOf(args[0]);
            numeroConjuntos = Integer.valueOf(args[1]);
            politica = PoliticaSubstituicao.valueOf(args[2].toUpperCase());
            caminhoArquivoEnderecos = args[3];
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException ex) {
            erroArgumentos(ex);
            return;
        }

        lerArquivo(tamanhoCache, numeroConjuntos, politica, caminhoArquivoEnderecos);
    }
    
    private static void lerArquivo(int tamanhoCache, int numeroConjuntos, PoliticaSubstituicao politicaSubstituicao,
            String caminhoArquivoEnderecos) {
        ArquivoEnderecos arquivoEnderecos = new ArquivoEnderecos(caminhoArquivoEnderecos);
        Integer[] enderecos;
        
        try {
            enderecos = arquivoEnderecos.buscarEnderecos();
        } catch (ArquivoInvalidoException ex) {
            System.out.println("Erro:");
            System.out.println(ex.getMessage());
            return;
        }

        int tamanhoEndereco = arquivoEnderecos.getTamanhoEndereco();
        MemoriaPrincipal mp = new MemoriaPrincipal(tamanhoEndereco, ENDERECOS_POR_BLOCO);
        MemoriaCache cache = new MemoriaCache(mp, tamanhoCache * 1024, numeroConjuntos, politicaSubstituicao);
        
        buscarEnderecos(cache, enderecos);
        imprimirResultados(mp, cache);
    }
    
    private static void buscarEnderecos(MemoriaCache cache, Integer[] enderecos) {
        int i = 0;
        for (Integer endereco : enderecos) {
            cache.buscarEndereco(endereco);
            i++;
        }
    }
    
    private static void imprimirResultados(MemoriaPrincipal mp, MemoriaCache cache) {        
        String tamanhoMemoriaPrincipal = Utility.humanReadableByteCount(mp.getTamanhoMemoria());

        System.out.format("   Tam MP: %s", tamanhoMemoriaPrincipal);
        System.out.println();

        System.out.format("Tam Cache: %s KB", (cache.getTamanhoCache() / 1024));
        System.out.println();

        System.out.format(" Endereço: %s bits, %s, %s, %s bits (rotulo, conjunto, palavra)", mp.getTamanhoEndereco(),
                cache.getTamanhoTag(), cache.getTamanhoIndex(), cache.getTamanhoOffset());
        System.out.println();

        System.out.format(" Hit-rate: %s%%", cache.getHitRate());
        System.out.println();
    }
    
    private static void erroArgumentos(Exception ex) {
        System.out.println("Argumentos inválidos");
        System.out.println("Execute passando os seguintes argumentos:");
        System.out.println();
        System.out.println(" CSIZE_KB : Tamanho da memória cache (16, 32, 64, 128, 256 e 512) em KBytes");
        System.out.println("   N_WAYS : Número de conjuntos/vias/ways (1 ~ tamanho da cache)");
        System.out.println("   POLICY : Política de substituição (ale|com|lfu|lru)");
        System.out.println("ADDR_FILE : Arquivo qualquer com uma lista de endereços de N bits.");
        System.out.println();
        System.out.println("Exemplo: ./cachesim 16 8 ale addresses.txt");
    }
  
}