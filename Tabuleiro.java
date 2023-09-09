package Wumpus;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class Tabuleiro extends JFrame {
    private JButton[][] botoesTabuleiro;
    private int tamanhoTabuleiro = 15;
    private final Ambiente ambiente;
    private final Jogador jogador;
    private JogoWumpus jogo;

    public Tabuleiro(Ambiente ambiente, Jogador jogador, JogoWumpus jogo) {
    this.ambiente = ambiente;
    this.jogador = jogador;
    this.jogo = jogo;

    setTitle("Jogo do Wumpus");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    botoesTabuleiro = new JButton[tamanhoTabuleiro][tamanhoTabuleiro];

    JPanel tabuleiroPanel = new JPanel(new GridLayout(tamanhoTabuleiro, tamanhoTabuleiro));

        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                botoesTabuleiro[i][j] = new JButton();
                tabuleiroPanel.add(botoesTabuleiro[i][j]);
            }
        }

        JFrame botoesFrame = new JFrame("Controle");
botoesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
botoesFrame.setSize(300, 150);

JButton botaoVazio1 = new JButton("");
JButton botaoVazio2 = new JButton("");
JButton coletar = new JButton("Coletar");
JButton botaoVazio4 = new JButton("");
JButton botaoVazio5 = new JButton("");

JButton moverCimaButton = new JButton("Cima");
JButton moverBaixoButton = new JButton("Baixo");
JButton moverEsquerdaButton = new JButton("Esquerda");
JButton moverDireitaButton = new JButton("Direita");

JPanel botoesPanel = new JPanel();
botoesPanel.setLayout(new GridLayout(3, 3));

botoesPanel.add(botaoVazio1);
botoesPanel.add(moverCimaButton);
botoesPanel.add(botaoVazio2);
botoesPanel.add(moverEsquerdaButton);
botoesPanel.add(coletar);
botoesPanel.add(moverDireitaButton);
botoesPanel.add(botaoVazio4);
botoesPanel.add(moverBaixoButton);
botoesPanel.add(botaoVazio5);

botoesFrame.add(botoesPanel);
botoesFrame.setVisible(true);
        moverCimaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogador.moverParaCima();
                jogador.atualizarSensor();
                atualizarTabuleiro();
            }
        });

        moverBaixoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogador.moverParaBaixo();
                jogador.atualizarSensor();
                atualizarTabuleiro();
            }
        });

        moverEsquerdaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogador.moverParaEsquerda();
                jogador.atualizarSensor();
                atualizarTabuleiro();
            }
        });

        moverDireitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogador.moverParaDireita();
                jogador.atualizarSensor();
                atualizarTabuleiro();
            }
        });
        
        coletar.addActionListener(e -> {
    int posXJogador = jogador.getPosicao().getX();
    int posYJogador = jogador.getPosicao().getY();
    
    if (posXJogador >= 0 && posXJogador < tamanhoTabuleiro && posYJogador >= 0 && posYJogador < tamanhoTabuleiro) {
        int elemento = ambiente.getElemento(jogador.getPosicao());
        switch (elemento) {
            case Ambiente.ouro -> {
                ambiente.setConteudoPosicao(posXJogador, posYJogador, Ambiente.vazio);
            }
            case Ambiente.madeira -> {
                ambiente.setConteudoPosicao(posXJogador, posYJogador, Ambiente.vazio);
            }
            case Ambiente.poco -> {
                jogador.diminuirEnergiaVital(100);
            }
            default -> {
            }
        }
        atualizarTabuleiro();
    }
});


        botoesFrame.add(botoesPanel, BorderLayout.CENTER);

        botoesFrame.setVisible(true);
        
        add(tabuleiroPanel);

    tabuleiroPanel.setFocusable(true);
    tabuleiroPanel.requestFocusInWindow();

    tabuleiroPanel.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP -> jogador.moverParaCima();
                case KeyEvent.VK_DOWN -> jogador.moverParaBaixo();
                case KeyEvent.VK_LEFT -> jogador.moverParaEsquerda();
                case KeyEvent.VK_RIGHT -> jogador.moverParaDireita();
                default -> {
                }
            }
        }
    });
    }
    public void atualizarTabuleiro() {
    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 15; j++) {
            if (botoesTabuleiro[i][j] != null) {
                if (ambiente.foiRevelada(i, j)) {
                    int elemento = ambiente.getElemento(new Posicao(i, j));
                    switch (elemento) {
                        case Ambiente.jogador -> botoesTabuleiro[i][j].setText("Agente");
                        case Ambiente.brisa -> botoesTabuleiro[i][j].setText("Brisa");
                        case Ambiente.malCheiro -> botoesTabuleiro[i][j].setText("Mal Cheiro");
                        default -> botoesTabuleiro[i][j].setText("");
                    }
                } else {
                    botoesTabuleiro[i][j].setText("");
                }

    Posicao posicaoJogador = jogador.getPosicao();
    int xJogador = posicaoJogador.getX();
    int yJogador = posicaoJogador.getY();

    if (botoesTabuleiro[xJogador][yJogador] != null) {
        botoesTabuleiro[xJogador][yJogador].setText("Agente");
    }
    
    Sensor sensor = jogador.getSensor();
    
    if (sensor.isBrilho(posicaoJogador)) {
    abrirJanela("O Agente está vendo algo brilhante");
}

    
    if (sensor.isBrilho(posicaoJogador)) {
        abrirJanela("O Agente está vendo algo brilhante");
    }
    
    if (sensor.isParede(posicaoJogador)) {
        abrirJanela("O Agente esbarra em algo, uma parede o impede de prosseguir.");
    }
    
    if (sensor.isMalCheiro(posicaoJogador)) {
        abrirJanela("O Agente sente uma fedor podre");
    }
    
    if (sensor.isGrito(posicaoJogador)) {
        abrirJanela("O Agente ouve um grito");
    }

            }
        }
    }
    }
    private void abrirJanela(String mensagem) {
    JOptionPane.showMessageDialog(this, mensagem, "Aviso", JOptionPane.INFORMATION_MESSAGE);
}
    
}
