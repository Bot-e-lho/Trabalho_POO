package Wumpus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jogador {
    private Posicao pos;
    private int energiaVital;
    private int flechas;
    private int bateria;
    private List<String> objetosCarregados;
    private JLabel energiaLabel;
    private JLabel flechasLabel;
    private JLabel bateriaLabel;
    private Ambiente ambiente;

    public Jogador(Posicao posicao, JPanel interfacePanel, Ambiente ambiente) {
        this.pos = posicao;
        this.energiaVital = 100;
        this.flechas = 1;
        this.bateria = 3;
        this.objetosCarregados = new ArrayList<>();
        this.ambiente = ambiente;

        energiaLabel = new JLabel("Energia: " + energiaVital);
        flechasLabel = new JLabel("Flechas: " + flechas);
        bateriaLabel = new JLabel("Bateria: " + bateria);

        interfacePanel.add(energiaLabel);
        interfacePanel.add(flechasLabel);
        interfacePanel.add(bateriaLabel);
    }

    public void coletarObjeto(String objeto) {
        if (objetosCarregados.size() < 3) {
            objetosCarregados.add(objeto);
        }
    }

    public void descartarObjeto(String objeto) {
        if (objetosCarregados.contains(objeto)) {
            objetosCarregados.remove(objeto);
        }
    }

    public void coletarOuro() {
        coletarObjeto("Ouro");
    }

    public void enfrentarWumpus() {
        diminuirEnergiaVital(20); // Subtrai 20 da energia ao enfrentar o Wumpus
    }

    public void cairNoPoco() {
        diminuirEnergiaVital(10); // Subtrai 10 da energia ao cair no poço
    }

    public void pegarMadeira() {
        coletarObjeto("Madeira");
    }

    public void enfrentarMonstro2() {
        diminuirEnergiaVital(15);
    }

    public void diminuirEnergiaVital(int quantidade) {
        energiaVital -= quantidade;
        energiaLabel.setText("Energia: " + energiaVital);

        if (energiaVital <= 0) {
        }
    }

    public void usarFlecha() {
        flechas--;
        flechasLabel.setText("Flechas: " + flechas);
    }

    public void usarLanterna() {
        bateria--;
        bateriaLabel.setText("Bateria: " + bateria);
    }

    public void coletarBateria() {
        bateria++;
        bateriaLabel.setText("Bateria: " + bateria);
    }

    public void coletarFlecha() {
        flechas++;
        flechasLabel.setText("Flechas: " + flechas);
    }

    public Posicao getPosicao() {
        return pos;
    }

    public int getFlechas() {
        return flechas;
    }

    public int getEnergiaVital() {
        return energiaVital;
    }

    public int getBateria() {
        return bateria;
    }

    public void moverPara(Posicao novaPosicao) {
        int X = Math.abs(novaPosicao.getX() - pos.getX());
        int Y = Math.abs(novaPosicao.getY() - pos.getY());

        if ((X <= 2 && Y == 0) || (Y <= 2 && X == 0)) {
            if (posicaoValida(novaPosicao)) {
                int elemento = ambiente.getElemento(novaPosicao);

                switch (elemento) {
                    case Ambiente.ouro -> coletarOuro(); 
                    case Ambiente.wumpus -> enfrentarWumpus();
                    case Ambiente.poco -> cairNoPoco();
                    case Ambiente.madeira -> pegarMadeira();
                    case Ambiente.monstro2 -> enfrentarMonstro2();
                    default -> {
                    }
                }
            }
        }
    }

    private boolean posicaoValida(Posicao posicao) {
        int x = posicao.getX();
        int y = posicao.getY();

        return x >= 0 && x < 15 && y >= 0 && y < 15;
    }
}