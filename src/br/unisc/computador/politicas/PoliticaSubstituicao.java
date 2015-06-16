package br.unisc.computador.politicas;

public interface PoliticaSubstituicao {

    void marcarBlocoLido(Object bloco);
    void marcarBlocoGravado(Object bloco);
    int calcularPosicaoSubstituicao(Object[] conjunto);
    
}
