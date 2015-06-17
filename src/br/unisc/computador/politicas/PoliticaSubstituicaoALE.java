package br.unisc.computador.politicas;

import br.unisc.main.Utility;

public class PoliticaSubstituicaoALE implements PoliticaSubstituicao {

    @Override
    public int calcularPosicaoSubstituicao(Object[] conjunto) {
        return Utility.randInt(0, conjunto.length - 1);
    }

    @Override
    public void marcarBlocoGravado(Object[] conjunto, Object bloco) { }
    
    @Override
    public void marcarBlocoLido(Object[] conjunto, Object bloco) { }
    
}
