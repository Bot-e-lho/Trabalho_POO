package Wumpus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BoasVindas extends JFrame {

    private static boolean jogoIniciado = false;
    private static boolean modoDebug = false;

    public BoasVindas() {
        super("Bem-vindo ao Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1));

        JButton jogarButton = new JButton("Jogar");
        JButton debugButton = new JButton("Debug");
        JButton jogabilidadeButton = new JButton("Jogabilidade");
        JButton sairButton = new JButton("Sair");

        jogarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        debugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modoDebug = !modoDebug;
                System.out.println("Modo Debug: " + modoDebug);
            }
        });

        jogabilidadeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Configurar opções de jogabilidade?");
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sair do jogo");
                System.exit(0);
            }
        });

        painel.add(jogarButton);
        painel.add(debugButton);
        painel.add(jogabilidadeButton);
        painel.add(sairButton);

        getContentPane().add(painel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void iniciarJogo() {
        new JogoWumpus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BoasVindas();
            }
        });
    }
}

class JogoWumpus extends JFrame {
    private int tamanhoTabuleiro = 15;
    private PosicaoTabuleiro[][] tabuleiro; 

    public JogoWumpus() {
        super("Jogo do Wumpus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        tabuleiro = new PosicaoTabuleiro[tamanhoTabuleiro][tamanhoTabuleiro];

        JPanel tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(tamanhoTabuleiro, tamanhoTabuleiro));

        Random random = new Random();

        for (int y = 0; y < tamanhoTabuleiro; y++) {
            for (int x = 0; x < tamanhoTabuleiro; x++) {
                tabuleiro[y][x] = new PosicaoTabuleiro(); 
            }
        }

        gerarElementoAleatorio('O', random);
        gerarElementoAleatorio('W', random);
        gerarElementoAleatorio('M', random);
        gerarElementoAleatorio('P', random);

        for (int y = 0; y < tamanhoTabuleiro; y++) {
            for (int x = 0; x < tamanhoTabuleiro; x++) {
                JLabel label = new JLabel(String.valueOf(tabuleiro[y][x].getConteudo()));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tabuleiroPanel.add(label);
            }
        }

        getContentPane().add(tabuleiroPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void gerarElementoAleatorio(char elemento, Random random) {
        int quantidade = elemento == 'P' ? 5 : 1; 
        for (int i = 0; i < quantidade; i++) {
            int x, y;
            do {
                x = random.nextInt(tamanhoTabuleiro);
                y = random.nextInt(tamanhoTabuleiro);
            } while (tabuleiro[y][x].getConteudo() != '?');
            tabuleiro[y][x].setConteudo(elemento);
        }
    }
}

class PosicaoTabuleiro {
    private char conteudo;

    public PosicaoTabuleiro() {
        conteudo = '?';
    }

    public char getConteudo() {
        return conteudo;
    }

    public void setConteudo(char conteudo) {
        this.conteudo = conteudo;
    }
}
