package DungeonFighter;

public class Jogador extends Inimigo{
    private int classe; //jogador guarda classe e as pocoes
    private int pocao;
    public int getPocao() {
        return pocao;
    }
    public void setPocao(int pocao) {
        this.pocao = pocao;
    }
    public int getClasse() {
        return classe;
    }
    public Jogador(float vida,float ataque,float defesa,int classe){
        super(vida, ataque, defesa);
        this.classe=classe;
        new Tabuleiro(this); //instancia tabuleiro com esse objeto
    }
    public void tomadano(float dano){
        this.setVida(this.getVida()-(dano));
    }
    public void tomapocao(){
        this.setVida(this.getVida()+10);
    }
}
