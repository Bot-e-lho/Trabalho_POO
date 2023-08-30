package Wumpus;

public class Jogador extends Elemento {
    private int energiaVital;
    private int flechas;
    private int bateria;
    private boolean temBateria;

    public Jogador(Posicao posicao) {
        super(posicao);
        this.energiaVital = 100;
        this.flechas = 1;
        this.bateria = 0;
        this.temBateria = true;
    }
    
    public void coletarOuro() {
        if (podeColetarOuro()) {
        } else {
        }
    }

    private boolean podeColetarOuro() {
        return true; 
    }

    public int getEnergiaVital() {
        return energiaVital;
    }

    public void setEnergiaVital(int energiaVital) {
        this.energiaVital = energiaVital;
    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        this.flechas = flechas;
    }

    public boolean temBateria() {
        return temBateria;
    }
    
    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
    public int getBateria() {
        return bateria;
    }
    
    
    public void setLanterna(boolean temBateria) {
        this.temBateria = temBateria;
    }

    public void moverPara(Posicao novaPosicao) {
        setPosicao(novaPosicao);
    }

    public void usarFlecha() {
        flechas--;
    }

    public void usarLanterna() {
        temBateria = false;
    }

    public void coletarLanterna() {
        temBateria = true;
    }

    public void coletarFlecha() {
        flechas++;
    }

    @Override
    public void interagir(Elemento outroElemento) {
    }

    private void diminuirEnergiaVital(int i) {
        
    }
    
    public class Wumpus extends Elemento {
    public Wumpus(Posicao posicao) {
        super(posicao);
    }

    @Override
    public void interagir(Elemento elem) {
    }
}


public class Monstro2 extends Elemento {
    public Monstro2(Posicao posicao) {
        super(posicao);
    }

    @Override
    public void interagir(Elemento elem) {
        if (elem instanceof Jogador jogador) {
            jogador.diminuirEnergiaVital(25); 
        }
    }
}


public class Ouro extends Elemento {
    public Ouro(Posicao posicao) {
        super(posicao);
    }

    @Override
    public void interagir(Elemento elem) {
        if( elem instanceof Jogador ){
            Jogador jogador = (Jogador) elem;
        }
    }
    }

public class Madeira extends Elemento {
    public Madeira(Posicao posicao) {
        super(posicao);
    }

    @Override
    public void interagir(Elemento elem) {
    }
}
}
