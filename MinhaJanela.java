package Wumpus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class MinhaJanela extends JFrame {

    public MinhaJanela() {
        criarInt();
        setVisible(true);
    }

    public void criarInt() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton botaoJogar = new JButton("Jogar");
        JButton botaoDebug = new JButton("Debug");
        JButton botaoSair = new JButton("Sair");

        botaoJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarNovoJogo(); 
            }
        });
        
        botaoDebug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        painelBotoes.add(botaoJogar);
        painelBotoes.add(botaoDebug);
        painelBotoes.add(botaoSair);

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        getContentPane().add(painelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void iniciarNovoJogo() {
    Jogador jogador = new Jogador(a);
    Tabuleiro tabuleiro = new Tabuleiro(15);
    
    jogador.setEnergiaVital(100);
    jogador.setBateria(2);

    Posicao posicaoJogador = new Posicao(tabuleiro.getTamanho() - 1, 0);
    jogador.setPosicao(posicaoJogador);

    tabuleiro.posicionarElemento(posicaoJogador, jogador);

    Ouro ouro = new Ouro();
    tabuleiro.posicionarElementoAleatoriamente(ouro);

    Monstro wumpus = new Monstro("Wumpus");
    tabuleiro.posicionarElementoAleatoriamente(wumpus);

    Monstro monstro2 = new Monstro("Materia acumulada");
    tabuleiro.posicionarElementoAleatoriamente(monstro2);

    Madeira madeira1 = new Madeira();
    tabuleiro.posicionarElementoAleatoriamente(madeira1);

    Madeira madeira2 = new Madeira();
    tabuleiro.posicionarElementoAleatoriamente(madeira2);

    Poco poco1 = new Poco();
    tabuleiro.posicionarElementoAleatoriamente(poco1);

    Poco poco2 = new Poco();
    tabuleiro.posicionarElementoAleatoriamente(poco2);

    Poco poco3 = new Poco();
    tabuleiro.posicionarElementoAleatoriamente(poco3);

    atualizarInterface();

    JOptionPane.showMessageDialog(null, "Novo jogo");
}
    
}
