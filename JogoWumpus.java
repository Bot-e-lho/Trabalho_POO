package Wumpus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Random;

public final class JogoWumpus extends JFrame implements ActionListener {
    private final Ambiente ambiente;
    private final Jogador jogador;
    private final JPanel tabuleiroPanel;
    private final JButton jogarButton;
    private final JButton debugButton;
    private final JButton sairButton;
    private final JButton jogabilidadeButton;
    private final JPanel player;
    private Timer gameTimer;
    boolean jogoEmAndamento = false;
    private JButton[][] botoesTabuleiro;
    private MonstroWumpus wumpus;
    private Random num = new Random();

    public JogoWumpus(JButton[][] botoesTabuleiro) {
        super("Bem-vindo ao Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(15, 15));
        
        player = new JPanel();
        player.setLayout(new GridLayout(1, 3));
        
        int x = num.nextInt(16);
        int y = num.nextInt(16);

        this.botoesTabuleiro = botoesTabuleiro;
        ambiente = new Ambiente(15);
        jogador = new Jogador(new Posicao(14, 0), player, ambiente, this);
        wumpus = new MonstroWumpus(new Posicao(x, y), ambiente, this);
        

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 0));

        jogarButton = new JButton("Jogar");
        debugButton = new JButton("Debug");
        jogabilidadeButton = new JButton("Jogabilidade");
        sairButton = new JButton("Sair");
        
        painel.add(jogarButton);
        painel.add(debugButton);
        painel.add(jogabilidadeButton);
        painel.add(sairButton);
        
        getContentPane().add(painel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
        

        jogarButton.addActionListener((ActionEvent e) -> {
            iniciarJogo();
        });

        debugButton.addActionListener((ActionEvent e) -> {
            debugMode();
        });

        jogabilidadeButton.addActionListener((var e) -> {
            new TelaJogabilidade();
        });

        sairButton.addActionListener((ActionEvent e) -> {
            sairJogo();
        });
        
        tabuleiroPanel.setFocusable(true);
        tabuleiroPanel.requestFocusInWindow();
        
        atualizarTabuleiro();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public void sairJogo() {
        int resposta = JOptionPane.showConfirmDialog(null, """
                                                           Voc\u00ea est\u00e1 saindo do jogo! 
                                                           \u00c9 o que desejas?""", "Confirmação",
            JOptionPane.YES_NO_OPTION
        );
        
        if (resposta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Até a próxima!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void iniciarJogo() {
        if (!jogoEmAndamento) {
            JFrame tabuleiroFrame = new JFrame("O Mundo de Wumpus");
            tabuleiroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tabuleiroFrame.setSize(1024, 720);

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
            tabuleiroFrame.setLocationRelativeTo(null);
            jogoEmAndamento = true;
            gameTimer = new Timer(1000, (ActionEvent e) -> {
            });
            gameTimer.start();

            Tabuleiro tabuleiro = new Tabuleiro(ambiente, jogador, this);
            tabuleiroFrame.setVisible(true);
        }
    }
    
    public void debugMode() {
        JFrame tabuleiroFrame = new JFrame("O Mundo de Wumpus");
        tabuleiroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabuleiroFrame.setSize(1024, 720);

        tabuleiroFrame.add(tabuleiroPanel);

        this.botoesTabuleiro = new JButton[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                botoesTabuleiro[i][j] = new JButton();
                Color cor = Color.WHITE;
                botoesTabuleiro[i][j].setText("");
                botoesTabuleiro[i][j].setBackground(cor);
                tabuleiroPanel.add(botoesTabuleiro[i][j]);
            }
        }
        setVisible(true);
        atualizarTabuleiro();
        tabuleiroFrame.setLocationRelativeTo(null);
        jogoEmAndamento = true;

        Tabuleiro tabuleiro = new Tabuleiro(ambiente, jogador, this);
        tabuleiroFrame.setVisible(true);
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
