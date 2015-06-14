package br.unisc.computador;

public class BlocoCache {
    
    private boolean valido;
    private int tag;
    private byte[] dados;
    
    public BlocoCache() {
        valido = false;
        tag = 0;
        dados = null;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }
 
}
