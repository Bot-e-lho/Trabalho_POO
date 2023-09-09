package Wumpus;

import java.util.Random;

public final class Ambiente {
    public static final int vazio = 0;
    public static final int ouro = 1;
    public static final int wumpus = 2;
    public static final int monstro2 = 3;
    public static final int poco = 4;
    public static final int madeira = 5;
    public static final int jogador = 6;
    public static final int brisa = 7;
    public static final int malCheiro = 8; 
    public static final int brilho = 9; 
    public static final int grito = 10; 
    public static final int parede = 11;
    public static final int oculto = 12;
    private Posicao posicaoInicialJogador;
    private boolean[][] posicoesReveladas;
    private int[][] matrizAmbiente;
    private int[][] conteudoPosicoes;
    private int tamanho;
    private boolean ouroPresente;
    private Jogador player;

    public Ambiente(int tamanho) {
        this.tamanho = tamanho;
        this.matrizAmbiente = new int[tamanho][tamanho];
        this.posicoesReveladas = new boolean[tamanho][tamanho];
        this.conteudoPosicoes = new int[tamanho][tamanho];
        this.ouroPresente = false;
        this.player = player;

    }
    
    public void setPosicaoInicialJogador(Posicao posicao) {
        this.posicaoInicialJogador = posicao;
    }

    public void inicializarAmbiente(Posicao posicaoInicialJogador) {
    Random random = new Random();

    for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matrizAmbiente[i][j] = oculto;
                posicoesReveladas[i][j] = false;
                conteudoPosicoes[i][j] = vazio;
            }
        }
    int ouroX, ouroY;
        do {
            ouroX = random.nextInt(tamanho);
            ouroY = random.nextInt(tamanho);
        } while (posicaoInicialJogador.getX() == ouroX && posicaoInicialJogador.getY() == ouroY);

        matrizAmbiente[ouroX][ouroY] = ouro;
        ouroPresente = true;
    }

    public void moverJogador(int x, int y) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            if (!posicoesReveladas[x][y]) {
                posicoesReveladas[x][y] = true;

                switch (matrizAmbiente[x][y]) {
                    case Ambiente.ouro -> {
                        player.coletarOuro(); 
        }
                    case wumpus -> {
                    }
                    case madeira -> {
                    }
                    case poco -> {
                    }
                    case monstro2 -> {
                    }
                    case brisa -> {
                    }
                    case malCheiro -> {
                    }
                    case brilho -> {
                    }
                    case grito -> {
                    }
                    default -> {
                    }
                }
                }
            } else {
            }
        }
    





    public int getElemento(Posicao posicao) {
        int x = posicao.getX();
        int y = posicao.getY();
        
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            return matrizAmbiente[x][y];
        } else {
            return -1;
        }
    }
    
    private void posicionarElementoMalCheiro(int wumpusX, int wumpusY) {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    for (int i = 0; i < 4; i++) {
        int newX = wumpusX + dx[i];
        int newY = wumpusY + dy[i];

        if (newX >= 0 && newX < tamanho && newY >= 0 && newY < tamanho) {
            if (!posicoesReveladas[newX][newY]) {
                matrizAmbiente[newX][newY] = malCheiro;
            }
        }
    }
}
    
    public boolean foiRevelada(int x, int y) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            return posicoesReveladas[x][y];
        }
        return false;
    }

    public int getConteudoPosicao(int x, int y) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            return conteudoPosicoes[x][y];
        }
        return -1; 
    }

    public void revelarPosicao(int x, int y) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            posicoesReveladas[x][y] = true;
        }
    }

    public void setConteudoPosicao(int x, int y, int conteudo) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            conteudoPosicoes[x][y] = conteudo;
        }
    }
}

