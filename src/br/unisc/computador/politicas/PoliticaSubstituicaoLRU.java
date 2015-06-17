package br.unisc.computador.politicas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PoliticaSubstituicaoLRU implements PoliticaSubstituicao {
    
    private final Map<Object, InfoBloco> infos;
    private final AtomicInteger timestamp;
    
    public PoliticaSubstituicaoLRU() {
        infos = new HashMap<>();
        timestamp = new AtomicInteger(0);
    }
    
    @Override
    public int calcularPosicaoSubstituicao(Object[] conjunto) {
        InfoBloco[] infosConjunto = Arrays.stream(conjunto).map(bloco -> getInfoBloco(bloco)).toArray(s -> new InfoBloco[s]);
        Object blocoMaisAntigo = buscarBlocoMaisAntigo(infosConjunto).getBloco();
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
        info.setTimestampUltimaVisita(timestamp.incrementAndGet());
    }
    
    @Override
    public void marcarBlocoGravado(Object[] conjunto, Object bloco) {
        InfoBloco info = getInfoBloco(bloco);
        info.setTimestampUltimaVisita(timestamp.incrementAndGet());
    }
    
    private InfoBloco buscarBlocoMaisAntigo(InfoBloco[] infos) {
        InfoBloco maisAntigo = null;
        
        for (InfoBloco info : infos) {
            if (maisAntigo == null || info.getTimestampUltimaVisita() < maisAntigo.getTimestampUltimaVisita()) {
                maisAntigo = info;
            }
        }
        
        return maisAntigo;
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
        private int timestampUltimaVisita;
        
        public InfoBloco(Object bloco) {
            this.bloco = bloco;
            this.timestampUltimaVisita = 0;
        }
        
        public Object getBloco() {
            return bloco;
        }

        public long getTimestampUltimaVisita() {
            return timestampUltimaVisita;
        }

        public void setTimestampUltimaVisita(int timestampCriacao) {
            this.timestampUltimaVisita = timestampCriacao;
        }
        
    }
    
}
