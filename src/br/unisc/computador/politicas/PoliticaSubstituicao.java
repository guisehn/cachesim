package br.unisc.computador.politicas;

public interface PoliticaSubstituicao {

    void marcarBlocoLido(Object[] conjunto, Object bloco);
    void marcarBlocoGravado(Object[] conjunto, Object bloco);
    int calcularPosicaoSubstituicao(Object[] conjunto);
    
}
