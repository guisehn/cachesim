package br.unisc.computador.politicas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PoliticaSubstituicaoLFU implements PoliticaSubstituicao {
    
    private final Map<Object, InfoBloco> infos;
    private final AtomicInteger timestamp;
    
    public PoliticaSubstituicaoLFU() {
        infos = new HashMap<>();
        timestamp = new AtomicInteger(0);
    }
    
    @Override
    public int calcularPosicaoSubstituicao(Object[] conjunto) {
        InfoBloco[] infosConjunto = Arrays.stream(conjunto).map(bloco -> getInfoBloco(bloco)).toArray(s -> new InfoBloco[s]);
        InfoBloco[] frequenciaMinima = buscarBlocosComFrequenciaMinima(infosConjunto);
        Object blocoMaisAntigo = buscarBlocoMaisAntigo(frequenciaMinima).getBloco();
        int indiceBlocoMaisAntigo = 0;
        
        for (int i = 0; i < conjunto.length; i++) {
            if (conjunto[i] == blocoMaisAntigo) {
                indiceBlocoMaisAntigo = i;
                break;
            }
        }

        return indiceBlocoMaisAntigo;
    }

    @Override
    public void marcarBlocoLido(Object[] conjunto, Object bloco) {
        InfoBloco info = getInfoBloco(bloco);
        info.setFrequencia(info.getFrequencia() + 1);
    }
    
    @Override
    public void marcarBlocoGravado(Object[] conjunto, Object bloco) {
        InfoBloco info = getInfoBloco(bloco);
        info.setFrequencia(1);
        info.setTimestampCriacao(timestamp.incrementAndGet());
    }
    
    private InfoBloco buscarBlocoMaisAntigo(InfoBloco[] infos) {
        InfoBloco maisAntigo = null;
        
        for (InfoBloco info : infos) {
            if (maisAntigo == null || info.getTimestampCriacao() < maisAntigo.getTimestampCriacao()) {
                maisAntigo = info;
            }
        }
        
        return maisAntigo;
    }
    
    private InfoBloco[] buscarBlocosComFrequenciaMinima(InfoBloco[] infos) {
        List<InfoBloco> minimos = new ArrayList<>();
        int minimo = 0;
        boolean primeiro = true;

        for (InfoBloco info : infos) {
            if (primeiro || info.getFrequencia() < minimo) {
                primeiro = false;
                minimo = info.getFrequencia();

                minimos.clear();
                minimos.add(info);
            } else if (info.getFrequencia() == minimo) {
                minimos.add(info);
            }
        }
        
        return minimos.toArray(new InfoBloco[minimos.size()]);
    }
    
    private InfoBloco getInfoBloco(Object bloco) {
        InfoBloco info;
        
        if (infos.containsKey(bloco)) {
            info = infos.get(bloco);
        } else {
            info = new InfoBloco(bloco);
            infos.put(bloco, info);
        }

        return info;
    }
    
    private class InfoBloco {
        
        private final Object bloco;
        private int timestampCriacao;
        private int frequencia;
        
        public InfoBloco(Object bloco) {
            this.bloco = bloco;
            this.frequencia = 0;
            this.timestampCriacao = 0;
        }
        
        public Object getBloco() {
            return bloco;
        }

        public long getTimestampCriacao() {
            return timestampCriacao;
        }

        public void setTimestampCriacao(int timestampCriacao) {
            this.timestampCriacao = timestampCriacao;
        }

        public int getFrequencia() {
            return frequencia;
        }

        public void setFrequencia(int frequencia) {
            this.frequencia = frequencia;
        }
        
    }
    
}
