package br.unisc.computador.politicas;

public enum TipoPoliticaSubstituicao {
    
    ALE {
        @Override
        public String getNome() {
            return "Aleatória";
        }

        @Override
        public PoliticaSubstituicao getPoliticaSubstituicao() {
            return new PoliticaSubstituicaoALE();
        }
    },

    COM {
        @Override
        public String getNome() {
            return "COM";
        }

        @Override
        public PoliticaSubstituicao getPoliticaSubstituicao() {
            return new PoliticaSubstituicaoCOM();
        }
    },

    LFU {
        @Override
        public String getNome() {
            return "LFU";
        }

        @Override
        public PoliticaSubstituicao getPoliticaSubstituicao() {
            return new PoliticaSubstituicaoLFU();
        }
    },

    LRU {
        @Override
        public String getNome() {
            return "LRU";
        }

        @Override
        public PoliticaSubstituicao getPoliticaSubstituicao() {
            return new PoliticaSubstituicaoLRU();
        }
    };
    
    public abstract String getNome();
    public abstract PoliticaSubstituicao getPoliticaSubstituicao();
    
}
