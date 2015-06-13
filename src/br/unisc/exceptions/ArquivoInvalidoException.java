/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.exceptions;

import java.io.File;

/**
 *
 * @author guilhermesehn
 */
public class ArquivoInvalidoException extends Exception {
    
    public ArquivoInvalidoException(File arquivo, String message) {
        super(arquivo.getName() + ": " + message);
    }
    
}
