package Wumpus;

import javax.swing.*;

public class Wumpus extends Elemento {
    private JLabel wumpusLabel;

    public Wumpus(Posicao posicao, JPanel interfacePanel) {
        super(posicao);
        wumpusLabel = new JLabel("W");
        interfacePanel.add(wumpusLabel);
    }

    @Override
    public void interagir(Elemento elem) {
        if (elem instanceof Jogador) {
            JOptionPane.showMessageDialog(null, "Você foi pego pelo Wumpus!", "FAUSTÃO ESTÁ AQUI", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarPosicao(Posicao novaPosicao) {
        setPosicao(novaPosicao);
        wumpusLabel.setText("W");
    }
}