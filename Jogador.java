package Wumpus;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Jogador extends Elemento {
    private int energiaVital;
    private int flechas;
    private int bateria;
    private List<String> objetosCarregados;
    private JLabel energiaLabel;
    private JLabel flechasLabel;
    private JLabel bateriaLabel;

    public Jogador(Posicao posicao, JPanel interfacePanel) {
        super(posicao);
        this.energiaVital = 100;
        this.flechas = 1;
        this.bateria = 3;
        this.objetosCarregados = new ArrayList<>();

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

    @Override
    public void interagir(Elemento elem) {
        if (elem instanceof Wumpus) {
            diminuirEnergiaVital(25);
        } else if (elem instanceof Mysterio) {
            diminuirEnergiaVital(10);
        }
    }
}

