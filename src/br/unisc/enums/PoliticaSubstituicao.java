/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.enums;

/**
 *
 * @author guilhermesehn
 */
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
