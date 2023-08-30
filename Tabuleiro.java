package Wumpus;

import java.util.Random;

public class Tabuleiro {
    private Elemento[][] matriz;
    private int tamanho;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.matriz = new Elemento[tamanho][tamanho];
        inicializarTabuleiro();
    }

    public int getTamanho() {
        return tamanho;
    }
    

    public Elemento getElemento(Posicao posicao) {
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        if (linha >= 0 && linha < tamanho && coluna >= 0 && coluna < tamanho) {
            return matriz[linha][coluna];
        }
        return null;
    }

    
    public void posicionarElemento(Posicao posicao, Elemento elemento) {
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        
        if (linha >= 0 && linha < tamanho && coluna >= 0 && coluna < tamanho) {
            matriz[linha][coluna] = elemento;
            elemento.setPosicao(posicao); 
        }
    }

    private void inicializarTabuleiro() {
    Random random = new Random();

    Posicao posicaoJogador = new Posicao(tamanho - 1, 0);
    posicionarElemento(posicaoJogador, new Jogador(posicaoJogador));

    Posicao posicaoWumpus = new Posicao(random.nextInt(tamanho), random.nextInt(tamanho));
    posicionarElemento(posicaoWumpus, new Wumpus(posicaoWumpus));

    Posicao posicaoMonstro2 = new Posicao(random.nextInt(tamanho), random.nextInt(tamanho));
    posicionarElemento(posicaoMonstro2, new Monstro2(posicaoMonstro2));

    Posicao posicaoOuro = new Posicao(random.nextInt(tamanho), random.nextInt(tamanho));
    posicionarElemento(posicaoOuro, new Ouro(posicaoOuro));

    int numeroDePocos = 5;
    for (int i = 0; i < numeroDePocos; i++) {
        Posicao posicaoPoco = new Posicao(random.nextInt(tamanho), random.nextInt(tamanho));
        posicionarElemento(posicaoPoco, new Poco(posicaoPoco));
    }

    int numeroDeMadeiras = 2;
    for (int i = 0; i < numeroDeMadeiras; i++) {
        Posicao posicaoMadeira = new Posicao(random.nextInt(tamanho), random.nextInt(tamanho));
        posicionarElemento(posicaoMadeira, new Madeira(posicaoMadeira));
    }
}

}