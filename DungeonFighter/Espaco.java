package DungeonFighter;

import java.util.Random;

public class Espaco {           //metodo usado no inicio para distribuir o tabuleiro, melhorar a distribuicao,
    private int[][] tabuleiro; //o jeito que eu fiz nao garante o maximo de distribuicao e o chefe nao anda na ultima linha ainda
    private int armadilhas;
    private int armadilhas2;
    private int inimigos;
    private Random random;
    private int x,y,chefe=1,pocoes=3;
    public Espaco(int linhas, int colunas, int armadilhas, int armadilhas2, int inimigos) {
        this.tabuleiro = new int[linhas][colunas];
        this.armadilhas = armadilhas;
        this.armadilhas2 = armadilhas2;
        this.inimigos = inimigos;
        this.random = new Random();
    }
    public int[][] aleatorizar(){
        tabuleiro[0][0]=9;
        randomizarMonstro(chefe,3,4);
        randomizarMonstro(inimigos,4,0);
        randomizar(armadilhas,1);
        randomizar(armadilhas2,2);
        randomizar(pocoes,5);
        tabuleiro[0][0]=0;
        return tabuleiro;
  }
  private void randomizar(int quant,int valor){
    while(quant>0){
        x=random.nextInt(10);
        y=random.nextInt(5);
        if(tabuleiro[x][y]==0){
           tabuleiro[x][y]=valor;
           quant--;
        }
    }
  }
    private void randomizarMonstro(int quant, int valor, int m) {
        boolean[] linhasocupadas = new boolean[tabuleiro.length];
        while (quant > 0) {
            int sucesso = 0;
            x = random.nextInt(10);
            y = m % 5; 
            if (tabuleiro[x][y] == 0 && !linhasocupadas[x]) {
                tabuleiro[x][y] = valor;
                quant--;
                linhasocupadas[x] = true; 
                sucesso = 1; 
            }
            if (sucesso == 1) {
                m++;
                if (m >= 5) {
                    m = 0;
                }
            }
        }
    }
}

