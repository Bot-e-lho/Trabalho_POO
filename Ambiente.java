package Wumpus;

import java.util.Random;

public final class Ambiente {
    public static final int vazio = 0;
    public static final int ouro = 1;
    public static final int wumpus = 2;
    public static final int monstro2 = 3;
    public static final int poco = 4;
    public static final int madeira = 5;

    private int[][] matrizAmbiente;
    private int tamanho;

    public Ambiente(int tamanho) {
        this.tamanho = tamanho;
        this.matrizAmbiente = new int[tamanho][tamanho];
        inicializarAmbiente();
    }

    public void inicializarAmbiente() {
        Random random = new Random();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matrizAmbiente[i][j] = random.nextInt(5);
            }
        }

        matrizAmbiente[0][1] = vazio;
        
        int ouroX = random.nextInt(tamanho);
        int ouroY = random.nextInt(tamanho);
        matrizAmbiente[ouroX][ouroY] = ouro;

    int wumpusX = random.nextInt(tamanho);
    int wumpusY = random.nextInt(tamanho);
    matrizAmbiente[wumpusX][wumpusY] = wumpus;
    
    int monstro2X = random.nextInt(tamanho);
    int monstro2Y = random.nextInt(tamanho);
    matrizAmbiente[monstro2X][monstro2Y] = wumpus;

    for (int i = 0; i < 5; i++) {
        int poçoX = random.nextInt(tamanho);
        int poçoY = random.nextInt(tamanho);

        while ((poçoX == 0 && poçoY == 1) || matrizAmbiente[poçoX][poçoY] != vazio) {
            poçoX = random.nextInt(tamanho);
            poçoY = random.nextInt(tamanho);
        }

        matrizAmbiente[poçoX][poçoY] = poco;
    }

    for (int i = 0; i < 2; i++) {
        int madeiraX = random.nextInt(tamanho);
        int madeiraY = random.nextInt(tamanho);

        while ((madeiraX == 0 && madeiraY == 1) || matrizAmbiente[madeiraX][madeiraY] != vazio) {
            madeiraX = random.nextInt(tamanho);
            madeiraY = random.nextInt(tamanho);
        }

        matrizAmbiente[madeiraX][madeiraY] = madeira;
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
}