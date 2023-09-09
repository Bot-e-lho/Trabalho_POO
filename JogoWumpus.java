package Wumpus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public final class JogoWumpus extends JFrame implements ActionListener {
    private Ambiente ambiente;
    private Jogador jogador;
    private JPanel tabuleiroPanel;
    private JButton jogarButton;
    private JButton debugButton;
    private JButton sairButton;
    private JButton jogabilidadeButton;
    private Timer gameTimer;
    boolean jogoEmAndamento = false;
    private JButton[][] botoesTabuleiro;

    public JogoWumpus(JButton[][] botoesTabuleiro) {
        super("Bem-vindo ao Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(15, 15));

        this.botoesTabuleiro = botoesTabuleiro;
        ambiente = new Ambiente(15);
        jogador = new Jogador(new Posicao(14, 0), tabuleiroPanel, ambiente, this);

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
        
        tabuleiroPanel.setFocusable(true);
        tabuleiroPanel.requestFocusInWindow();
        
        painel.add(jogarButton);
        painel.add(debugButton);
        painel.add(jogabilidadeButton);
        painel.add(sairButton);

        getContentPane().add(painel, BorderLayout.WEST);
        setLocationRelativeTo(null);
        setVisible(true);
                atualizarTabuleiro();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

public void iniciarJogo() {
    if (!jogoEmAndamento) {
        JFrame tabuleiroFrame = new JFrame("O Mundo de Wumpus");
        tabuleiroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabuleiroFrame.setSize(1024, 720);

        tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(15, 15));
        tabuleiroFrame.add(tabuleiroPanel);

        this.botoesTabuleiro = new JButton[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                botoesTabuleiro[i][j] = new JButton();
                tabuleiroPanel.add(botoesTabuleiro[i][j]);
            }
        }
        setVisible(true);
        atualizarTabuleiro();
        jogoEmAndamento = true;
        gameTimer = new Timer(1000, (ActionEvent e) -> {
        });
        gameTimer.start();

        Tabuleiro tabuleiro = new Tabuleiro(ambiente, jogador, this);
        tabuleiroFrame.setVisible(true);
    }
}

    public void atualizarTabuleiro() {
    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 15; j++) {
            if (botoesTabuleiro[i][j] != null) {
                int conteudo = ambiente.getConteudoPosicao(i, j);
                botoesTabuleiro[i][j].setText("");
                
                if (ambiente.foiRevelada(i, j)) {
                    Color cor = Color.WHITE; 

                    switch (conteudo) {
                        case Ambiente.malCheiro -> cor = Color.RED;
                        case Ambiente.brisa -> cor = Color.BLUE;
                        case Ambiente.monstro2 -> cor = Color.BLACK;
                        case Ambiente.ouro -> cor = Color.YELLOW;
                        default -> {
                        }
                    }

                    botoesTabuleiro[i][j].setBackground(cor);
                }
            }
        }
    }


        Posicao posicaoJogador = jogador.getPosicao();
        int xJogador = posicaoJogador.getX();
        int yJogador = posicaoJogador.getY();

        if (botoesTabuleiro[xJogador][yJogador] != null) {
            botoesTabuleiro[xJogador][yJogador].setText("007");
        }
    }
    
    private void encerrarJogo() {
        jogoEmAndamento = false;
        if (gameTimer != null) {
            gameTimer.stop();
        }
    }

}
