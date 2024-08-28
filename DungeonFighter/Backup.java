package DungeonFighter;

public class Backup {
    private static int[][] tabuleiroinicial;

    public static void settabuleiroinicial(int[][] tabuleiro) {
        tabuleiroinicial=new int[tabuleiro.length][];
        for (int i=0;i<tabuleiro.length; i++) {
            tabuleiroinicial[i]=tabuleiro[i].clone();
        }
    }
    public static int[][] gettabuleiroinicial() {
        return tabuleiroinicial;
    }
}