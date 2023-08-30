package Wumpus;
public class Campos {
    private boolean Ouro;
    private boolean Wumpus;
    private boolean Monstro2;
    private boolean Poço;
    private boolean Madeira;
    private boolean visitada;
    
    
    public Campos() {
        Ouro = false;
        Wumpus = false;
        Monstro2 = false;
        Poço = false;
        Madeira = false;
        visitada = false;
    }
    
    public boolean temOuro() {
        return Ouro;
    }

    public boolean temWumpus() {
        return Wumpus;
    }

    public boolean Monstro2() {
        return Monstro2;
    }

    public boolean Poço() {
        return Poço;
    }

    public boolean Madeira() {
        return Madeira;
    }

    public boolean foiVisitada() {
        return visitada;
    }

    public void setTemOuro(boolean temOuro) {
        this.Ouro = temOuro;
    }

    public void setTemWumpus(boolean temWumpus) {
        this.Wumpus = temWumpus;
    }

    public void setTemMonstro2(boolean Monstro2) {
        this.Monstro2 = Monstro2;
    }

    public void setTemPoço(boolean Poço) {
        this.Poço = Poço;
    }

    public void setTemMadeira(boolean temMadeira) {
        this.Madeira = temMadeira;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
