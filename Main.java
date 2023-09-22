package Wumpus;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JButton[][] botoesTabuleiro = new JButton[15][15];
                Ambiente ambiente = new Ambiente(15);
                Sensor sensor = new Sensor(ambiente);
                ambiente.setPosicaoInicialJogador(new Posicao(14, 0));

                JPanel tabuleiroPanel = new JPanel(new GridLayout(15, 15));

                JogoWumpus jogo = new JogoWumpus(botoesTabuleiro); 
                Jogador jogador = new Jogador(new Posicao(14, 0), tabuleiroPanel, ambiente, jogo);
                Sensor sensorDoJogador = jogador.getSensor();

                ambiente.inicializarAmbiente(jogador.getPosicao());

                Tabuleiro tabuleiro = new Tabuleiro(ambiente, jogador, jogo);
            }
        });
    }
}
