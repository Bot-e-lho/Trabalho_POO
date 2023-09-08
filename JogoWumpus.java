package Wumpus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoWumpus extends JFrame implements ActionListener {
    private Ambiente ambiente;
    private Jogador jogador;
    private JPanel tabuleiroPanel;
    private JButton jogarButton;
    private JButton debugButton;
    private JButton sairButton;
    private JButton jogabilidadeButton;

    public JogoWumpus() {
        super("Bem-vindo ao Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        ambiente = new Ambiente(15);

        tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(15, 15));
        getContentPane().add(tabuleiroPanel, BorderLayout.CENTER);

        jogador = new Jogador(new Posicao(0, 1), tabuleiroPanel, ambiente);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1));

        jogarButton = new JButton("Jogar");
        debugButton = new JButton("Debug");
        jogabilidadeButton = new JButton("Jogabilidade");
        sairButton = new JButton("Sair");

        jogarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        debugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        jogabilidadeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        getContentPane().add(painel, BorderLayout.WEST);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void iniciarJogo() {
    boolean jogoEmAndamento = true;

    while (jogoEmAndamento) {
        Posicao posicaoAtual = jogador.getPosicao();

        int elementoAtual = ambiente.getElemento(posicaoAtual);
        if (elementoAtual == Ambiente.ouro && posicaoAtual.equals(new Posicao(0, 1))) {
            JOptionPane.showMessageDialog(null, "Você venceu!");
            jogoEmAndamento = false; 
        } else if (elementoAtual == Ambiente.wumpus || elementoAtual == Ambiente.monstro2
                || jogador.getEnergiaVital() <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over");
            jogoEmAndamento = false;
        } else if (elementoAtual == Ambiente.poco) {
            jogador.cairNoPoco();
            if (jogador.getEnergiaVital() <= 0) {
                JOptionPane.showMessageDialog(null, "Você caiu em um poço");
                jogoEmAndamento = false;
            }
    }
}
    }}