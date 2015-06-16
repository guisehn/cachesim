package br.unisc.computador.politicas;

public class PoliticaSubstituicaoLRU implements PoliticaSubstituicao {
    
    @Override
    public int calcularPosicaoSubstituicao(Object[] conjunto) {
        return 0;
    }

    @Override
    public void marcarBlocoGravado(Object bloco) { }
    
    @Override
    public void marcarBlocoLido(Object bloco) { }
    
}
