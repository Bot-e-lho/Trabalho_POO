package Wumpus;

import javax.swing.*;

public class Mysterio extends Elemento {
    private JLabel mysterioLabel;

    public Mysterio(Posicao posicao, JPanel interfacePanel) {
        super(posicao);

        mysterioLabel = new JLabel("?");
        interfacePanel.add(mysterioLabel);
    }

    @Override
    public void interagir(Elemento elem) {
        if (elem instanceof Jogador) {
            JOptionPane.showMessageDialog(null, "Você não sabe o que encontrou!", "Mysterio Ataca", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void atualizarPosicao(Posicao novaPosicao) {
        setPosicao(novaPosicao);
        mysterioLabel.setText("?");
    }
}