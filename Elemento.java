package Wumpus;

public abstract class Elemento {
    private Posicao posicao;

    public Elemento(Posicao posicao) {
        this.posicao = posicao;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public abstract void interagir(Elemento outroElemento);
}
