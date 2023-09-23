package Wumpus;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MonstroWumpus {
    private Posicao pos;
    //private boolean agenteMorto;
    private Ambiente ambiente;
    private JogoWumpus jogo;
    private Sensor sensor;
    
    public MonstroWumpus(Posicao p, Ambiente a, JogoWumpus j) {
        this.pos = p;
        this.ambiente = a;
        this.jogo = j;
        this.sensor = new Sensor(a);
    }
    
    private boolean posicaoValida(int x, int y) {
        return x >= 0 && x < 15 && y >= 0 && y < 15;
    }
    
    public Posicao getPosicao() {
        return pos;
    }
}
