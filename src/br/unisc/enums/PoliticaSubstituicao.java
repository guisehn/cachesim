package br.unisc.enums;

public enum PoliticaSubstituicao {
    
    ALE {
        @Override
        public String getNome() {
            return "Aleatória";
        }
    },

    COM {
        @Override
        public String getNome() {
            return "COM";
        }
    },

    LFU {
        @Override
        public String getNome() {
            return "LFU";
        }
    },

    LRU {
        @Override
        public String getNome() {
            return "LRU";
        }
    };
    
    public abstract String getNome();
    
}
