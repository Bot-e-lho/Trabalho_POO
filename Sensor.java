package Wumpus;

public class Sensor {
    private boolean brisa;
    private boolean malCheiro;
    private boolean brilho;
    private boolean grito;
    private boolean parede;
    private Ambiente ambiente;


    public Sensor(Ambiente ambiente) {
        this.brisa = false;
        this.malCheiro = false;
        this.brilho = false;
        this.grito = false;
        this.parede = false;
        this.ambiente = ambiente;
    }

    public boolean isBrisa(Posicao posicao) {
        int elemento = ambiente.getElemento(posicao);
        return elemento == Ambiente.brisa;
    }

    public void setBrisa(boolean brisa) {
        this.brisa = brisa;
    }

    public boolean isMalCheiro(Posicao posicao) {
        int elemento = ambiente.getElemento(posicao);
        return elemento == Ambiente.malCheiro;
    }

    public void setMalCheiro(boolean malCheiro) {
        this.malCheiro = malCheiro;
    }

    public boolean isBrilho(Posicao posicao) {
        int elemento = ambiente.getElemento(posicao);
        return elemento == Ambiente.brilho;
    }


    public void setBrilho(boolean brilho) {
        this.brilho = brilho;
    }

    public boolean isGrito(Posicao posicao) {
        int elemento = ambiente.getElemento(posicao);
        return elemento == Ambiente.grito;
    }

    public void setGrito(boolean grito) {
        this.grito = grito;
    }

    public boolean isParede(Posicao posicao) {
        int elemento = ambiente.getElemento(posicao);
        return elemento == Ambiente.parede;
    }

    public void setParede(boolean parede) {
        this.parede = parede;
    }
    
    public boolean isElementoOculto(Posicao posicao) {
        int elemento = ambiente.getElemento(posicao);
        return elemento == Ambiente.vazio;
    }
}