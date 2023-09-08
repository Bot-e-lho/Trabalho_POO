package Wumpus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class Tabuleiro extends JFrame {
    private JButton[][] botoesTabuleiro;
    private int tamanhoTabuleiro = 15;
    private int posicaoX, posicaoY; 
    private final Ambiente ambiente;
    private final Jogador jogador;

    public Tabuleiro(Ambiente ambiente, Jogador jogador) {
        this.ambiente = ambiente;
        this.jogador = jogador;

        setTitle("Jogo do Wumpus");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        botoesTabuleiro = new JButton[tamanhoTabuleiro][tamanhoTabuleiro];

        JPanel tabuleiroPanel = new JPanel(new GridLayout(tamanhoTabuleiro, tamanhoTabuleiro));

        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                botoesTabuleiro[i][j] = new JButton();
                tabuleiroPanel.add(botoesTabuleiro[i][j]);
                final int x = i;
                final int y = j;

                botoesTabuleiro[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moverPersonagem(x, y);
                    }
                });
            }
        }

        add(tabuleiroPanel);

        posicaoX = tamanhoTabuleiro - 1; 
        posicaoY = 0; // Linha 1
        atualizarTabuleiro();
    }

    public void atualizarTabuleiro() {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                // Limpe todos os botões
                botoesTabuleiro[i][j].setText("");
            }
        }
        botoesTabuleiro[posicaoX][posicaoY].setText("P"); // Mostra a posição do jogador
    }

    public void moverPersonagem(int newX, int newY) {
        if (Math.abs(newX - posicaoX) + Math.abs(newY - posicaoY) == 1) {
            posicaoX = newX;
            posicaoY = newY;
            jogador.moverPara(new Posicao(posicaoX, posicaoY)); // Atualiza a posição do jogador
            atualizarTabuleiro();
        } else {
            JOptionPane.showMessageDialog(null, "Movimento inválido!");
        }
    }
}