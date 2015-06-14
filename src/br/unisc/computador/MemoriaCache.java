package br.unisc.computador;

import br.unisc.enums.PoliticaSubstituicao;
import br.unisc.main.Utility;
import java.util.Arrays;
import java.util.Optional;

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
    
    /**
     * Busca endereço na memória cache, caso não encontre busca na memória principal e salva na cache
     * @param endereco Endereço a ser buscado
     * @return Dado do endereço
     */
    public byte buscarEndereco(int endereco) {
        byte[] dadosBloco;
        int tag = getTag(endereco);
        int indiceConjunto = getIndex(endereco);
        BlocoCache[] conjunto = conjuntos[indiceConjunto];
        Optional<BlocoCache> bloco = lookup(conjunto, tag);
        
        quantidadeBuscas++;

        if (bloco.isPresent()) {
            quantidadeHits++;
            dadosBloco = bloco.get().getDados();
        } else {
            quantidadeMisses++;
            dadosBloco = memoriaPrincipal.getBlocoPorEndereco(endereco);            
            gravarCache(conjunto, tag, dadosBloco);
        }

        int offset = getOffset(endereco);
        return dadosBloco[offset];
    }
    
    /**
     * Grava bloco em conjunto da memória cache, utilizando política de substituição caso necessário
     * @param conjunto Conjunto em que o bloco deve ser guardado
     * @param tag Tag do bloco
     * @param dadosBloco Dados do bloco
     * @return Objeto do bloco salvo na memória cache
     */
    private BlocoCache gravarCache(BlocoCache[] conjunto, int tag, byte[] dadosBloco) {
        int posicao = 0;
        
        if (politicaSubstituicao == PoliticaSubstituicao.ALE) {
            posicao = Utility.randInt(0, tamanhoConjunto - 1);
        }

        // to-do: outras políticas de substituição

        BlocoCache bloco = conjunto[posicao];
        bloco.setValido(true);
        bloco.setTag(tag);
        bloco.setDados(dadosBloco);
        
        return bloco;
    }
    
    /**
     * Busca bloco em um conjunto (através da tag)
     * @param conjunto Conjunto para buscar o bloco
     * @param tag Tag do endereço
     * @return Bloco encontrado, ou nulo caso não encontrado
     */
    private Optional<BlocoCache> lookup(BlocoCache[] conjunto, int tag) {
        return Arrays.stream(conjunto).filter(c -> c.isValido() && c.getTag() == tag).findFirst();
    }
    
    /**
     * Busca a posição dentro do bloco em que o endereço se encontra (offset)
     * @param endereco Endereço de memória
     * @return Offset
     */
    private int getOffset(int endereco) {
        return endereco & ((1 << tamanhoOffset) - 1); // mantém apenas os N bits menos significativos, sendo N = tamanhoOffset
    }
    
    /**
     * Busca o índice do conjunto em que o endereço se encontra
     * @param endereco Endereço de memória
     * @return Índice do conjunto
     */
    private int getIndex(int endereco) {
        int index = endereco >> tamanhoOffset; // remove o offset do endereço
        index = index & ((1 << tamanhoIndex) - 1); // mantém apenas os N bits menos significativos, sendo N = tamanhoIndex
        return index;
    }
    
    /**
     * Busca os bits referentes à tag do endereço
     * @param endereco Endereço de memória
     * @return Tag
     */
    private int getTag(int endereco) {
        return endereco >> (tamanhoOffset + tamanhoIndex); // tag = endereço - index - offset
    }

    /**
     * Calcula hit-rate (relação entre hits e quantidade de buscas)
     * @return Hit-rate
     */
    public double getHitRate() {
        return (double)quantidadeHits / (double)quantidadeBuscas;
    }
    
    /**
     * Calcula miss-rate (relação entre misses e quantidade de buscas)
     * @return Miss-rate
     */
    public double getMissRate() {
        return (double)quantidadeMisses / (double)quantidadeBuscas;
    }

    public int getQuantidadeBuscas() {
        return quantidadeBuscas;
    }
    
    public int getQuantidadeHits() {
        return quantidadeHits;
    }
    
    public int getQuantidadeMisses() {
        return quantidadeMisses;
    }
    
    public int getTamanhoCache() {
        return tamanhoCache;
    }
    
    public int getQuantidadeConjuntos() {
        return quantidadeConjuntos;
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
    
    public PoliticaSubstituicao getPoliticaSubstituicao() {
        return politicaSubstituicao;
    }
    
}
