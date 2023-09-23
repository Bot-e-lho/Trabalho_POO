package Wumpus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Jogador {
    private Posicao pos;
    private int energiaVital;
    private int flechas;
    private int bateria;
    private boolean ouroColetado;
    private int madeiraColetada;
    private List<String> objetosCarregados;
    private JLabel energiaLabel;
    private JLabel flechasLabel;
    private JLabel bateriaLabel;
    private JLabel ouroLabel;
    private Ambiente ambiente;
    private JogoWumpus jogo;
    private Sensor sensor;
    private boolean vivo;
    
    public Jogador(Posicao posicao, JPanel interfacePanel, Ambiente ambiente, JogoWumpus jogo) {
        this.ouroColetado = false;
        this.pos = posicao;
        this.energiaVital = 100;
        this.flechas = 1;
        this.bateria = 2;
        this.objetosCarregados = new ArrayList<>();
        this.ambiente = ambiente;
        this.jogo = jogo;
        this.sensor = new Sensor(ambiente);
        this.vivo = true;

        energiaLabel = new JLabel("Energia: " + energiaVital);
        flechasLabel = new JLabel("Flechas: " + flechas);
        bateriaLabel = new JLabel("Bateria: " + bateria);
        String pegouOuro = ouroColetado ? "RUN!" : "Vazio"; 
        ouroLabel = new JLabel("Ouro Coletado: " + pegouOuro);

        interfacePanel.add(energiaLabel);
        interfacePanel.add(flechasLabel);
        interfacePanel.add(bateriaLabel);
        interfacePanel.add(ouroLabel);
    }
    
    public Jogador() {
        this.energiaVital = 100;
        this.flechas = 1;
        this.bateria = 2;
        this.ouroColetado = false;
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


    public void enfrentarWumpus() {
        diminuirEnergiaVital(20);
    }

    public void cairNoPoco() {
        diminuirEnergiaVital(10);
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
            abrirJanela("Você perdeu!");
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
    
    public boolean getOuro() {
        return ouroColetado;
    }

    public int getBateria() {
        return bateria;
    }

    public void moverParaCima() {
        int x = pos.getX();
        int y = pos.getY();

        if (posicaoValida(x - 1, y)) {
            pos = new Posicao(x - 1, y);
            jogo.atualizarTabuleiro();
            atualizarSensor();
            ambiente.revelarPosicao(x - 1, y);
            verificarConteudoPosicao(x - 1, y);
        } else {
            abrirJanela("O Agente esbarra em algo, uma parede o impede de prosseguir.");
        }
    }

    public void moverParaBaixo() {
        int x = pos.getX();
        int y = pos.getY();

        if (posicaoValida(x + 1, y)) {
            pos = new Posicao(x + 1, y);
            jogo.atualizarTabuleiro();
            atualizarSensor();
            ambiente.revelarPosicao(x + 1, y);
            verificarConteudoPosicao(x + 1, y);
        } else {
            abrirJanela("O Agente esbarra em algo, uma parede o impede de prosseguir.");
        }
    }

    public void moverParaEsquerda() {
        int x = pos.getX();
        int y = pos.getY();

        if (posicaoValida(x, y - 1)) {
            pos = new Posicao(x, y - 1);
            jogo.atualizarTabuleiro();
            atualizarSensor();
            ambiente.revelarPosicao(x , y - 1);
            verificarConteudoPosicao(x, y - 1);
        }else {
            abrirJanela("O Agente esbarra em algo, uma parede o impede de prosseguir.");
        }
    }

    public void moverParaDireita() {
        int x = pos.getX();
        int y = pos.getY();

        if (posicaoValida(x, y + 1)) {
            pos = new Posicao(x, y + 1);
            jogo.atualizarTabuleiro();
            atualizarSensor();
            ambiente.revelarPosicao(x, y + 1);
            verificarConteudoPosicao(x, y - 1);
        }else {
            abrirJanela("O Agente esbarra em algo, uma parede o impede de prosseguir.");
        }
    }

    private boolean posicaoValida(int x, int y) {
        return x >= 0 && x < 15 && y >= 0 && y < 15;
    }
    
    public Sensor getSensor() {
        atualizarSensor();
        return sensor;
    }
    
    public boolean getOuroColetado() {
        return true;
    }

    public void coletarOuro() {
        if (ambiente.getElemento(pos) == Ambiente.ouro && !ouroColetado) {
            ouroColetado = true;
            ambiente.setConteudoPosicao(pos.getX(), pos.getY(), Ambiente.vazio);
            jogo.atualizarTabuleiro(); 
        }
    }
 
    public void atualizarSensor() {
        int elementoAtual = ambiente.getElemento(pos);

        if (elementoAtual == Ambiente.brisa) {
            sensor.setBrisa(true);
        } else {
            sensor.setBrisa(false);
        }


        if (elementoAtual == Ambiente.malCheiro) {
            sensor.setMalCheiro(true);
        } else {
            sensor.setMalCheiro(false);
        }
        
        if (elementoAtual == Ambiente.grito) {
            sensor.setGrito(true);
        } else {
            sensor.setGrito(false);
        }
        
        if (elementoAtual == Ambiente.brilho) {
            sensor.setBrilho(true);
        } else {
            sensor.setBrilho(false);
        }
        
        if (elementoAtual == Ambiente.parede) {
            sensor.setParede(true);
        } else {
            sensor.setParede(false);
        }
    }
    private void abrirJanela(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void verificarConteudoPosicao(int x, int y) {
        int conteudo = ambiente.getConteudoPosicao(x, y);

        switch (conteudo) {
            case Ambiente.malCheiro -> abrirJanela("O Agente sente um cheiro ruim.");
            case Ambiente.brisa -> abrirJanela("O Agente sente uma brisa.");
            case Ambiente.monstro2 -> abrirJanela("O Agente encontrou um monstro");
            case Ambiente.wumpus -> abrirJanela("O Agente encontrou o Wumpus");
            case Ambiente.ouro -> abrirJanela("O Agente encontrou ouro!");
            default -> {
            }
        }
    }
}
