package Wumpus;

import javax.swing.*;
import java.awt.Font;
    
public class TelaJogabilidade extends JFrame {
    public TelaJogabilidade() {
        setTitle("Como Jogar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea comoJogar = new JTextArea();
        comoJogar.setEditable(false);
        comoJogar.setLineWrap(true);
        comoJogar.setWrapStyleWord(true);
        
        Font fontePersonalizada = new Font("Times New Roman", Font.ITALIC, 32);
        comoJogar.setFont(fontePersonalizada);

        String textos = "Bem-vindo ao Mundo de Wumpus (2.0)!\n\n";
        textos += "Como no mundo de Wumpus, o agente ainda deve buscar o ouro e fugir de Wumpus";
        textos += " exceto que agora existem mais perigos que antes, outro monstro ronda a caverna.";
        textos += "Tente sobreviver.\n";
        textos += "Para jogar use os botões cima, baixo, esquerda, direita para se mover";
        textos += " e coletar para pegar o ouro, baterias e madeira!\n\n";
        textos += "A madeira serve para criar flechas ou atravessar buracos.\n";
        textos += "A bateria serve para usar a lanterna, que mostra todas as posições em uma direção \n\n";
        textos += "Boa sorte, jovem agente!\n";

        comoJogar.setText(textos);

        JScrollPane scrollPane = new JScrollPane(comoJogar);

        getContentPane().add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaJogabilidade();
        });
    }
}
