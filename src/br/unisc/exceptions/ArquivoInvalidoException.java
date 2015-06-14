package br.unisc.exceptions;

import java.io.File;

public class ArquivoInvalidoException extends Exception {
    
    public ArquivoInvalidoException(File arquivo, String message) {
        super(arquivo.getName() + ": " + message);
    }
    
}
