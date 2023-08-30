package Wumpus;

import javax.swing.JFrame;

public class MundoDeWumpus extends JFrame {

    private static final int BOARD_SIZE = 15;
    private Campos[][] tabuleiro;   
    
    public MundoDeWumpus() {
        setTitle("O Mundo de Wumpus");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        criarInt();
    }
}
