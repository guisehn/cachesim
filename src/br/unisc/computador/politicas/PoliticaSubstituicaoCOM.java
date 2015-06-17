package br.unisc.computador.politicas;

import java.util.HashMap;
import java.util.Map;

public class PoliticaSubstituicaoCOM implements PoliticaSubstituicao {

    private final Map<Object[], Integer> contadores;
    
    public PoliticaSubstituicaoCOM() {
        contadores = new HashMap<>();
    }
    
    @Override
    public int calcularPosicaoSubstituicao(Object[] conjunto) {
        return getContagem(conjunto);
    }

    @Override
    public void marcarBlocoGravado(Object[] conjunto, Object bloco) {
        int contagem = getContagem(conjunto);
        
        if (contagem + 1 >= conjunto.length) {
            contagem = 0;
        } else {
            contagem++;
        }
        
        contadores.put(conjunto, contagem);
    }
    
    @Override
    public void marcarBlocoLido(Object[] conjunto, Object bloco) { }
    
    private int getContagem(Object[] conjunto) {
        return (contadores.containsKey(conjunto)) ? contadores.get(conjunto) : 0;
    }
    
}
