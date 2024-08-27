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
        randomizar(armadilhas,1);
        randomizar(armadilhas2,2);
        randomizar(inimigos,4);
        randomizar(chefe,3);
        randomizar(pocoes,5);
        return tabuleiro;
  }
  private void randomizar(int quant,int valor){
    while(quant>0){
        x=random.nextInt(10);
        y=random.nextInt(5);
        if(tabuleiro[x][y]==0&&(x!=0&&y!=0)){
           tabuleiro[x][y]=valor;
           quant=quant-1;
        }
    }
  }
}

