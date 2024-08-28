package DungeonFighter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JOptionPane;

public class Jogador extends Inimigo{
    private int classe,pocao,nivel,cargas; //jogador guarda classe e as pocoes
    private boolean debug;
    public int getPocao() {
        return pocao;
    }
    public boolean getDebug(){
        return debug;
    }
    public void setPocao(int pocao) {
        this.pocao = pocao;
    }
    public int getClasse() {
        return classe;
    }
    public int getCargas() {
        return cargas;
    }
    public void setCargas(int cargas) {
        this.cargas = cargas;
    }
    public Jogador(float vida,float ataque,float defesa,int classe,boolean debug){
        super(vida, ataque, defesa,-1);
        this.setMaxvida(vida);
        this.setMaxdef(defesa);
        this.setMaxatq(ataque);
        this.debug=debug;
        this.classe = classe;
        cargas = 3;
        nivel = 1;
    }
    public void resetjogador(){
        this.setDefesa(this.getMaxdef());
        this.setAtaque(this.getMaxatq());
    }
    public void resetvida(){
        this.cargas = 3;
        this.nivel = 1;
        this.setVida(this.getMaxvida());
    }
    public void novojogo(){
        new Tabuleiro(this,debug,false); 
    }
    @Override
    public void setVida(float vida) {
		if(vida<=0){
			vida=0;
            gameover();
		}
		BigDecimal decimal = new BigDecimal(vida).setScale(2, RoundingMode.HALF_UP);
		vida = decimal.floatValue();
        super.setVida(vida);
	}
    public boolean tomapocao(){
        if(pocao>0){
            this.setVida(this.getVida()+10);
            pocao--;
            return true;
        }else{
            return false;
        }
    }
    public void gameover(){
        JOptionPane.showMessageDialog(null, "Game Over!","Derrota", JOptionPane.INFORMATION_MESSAGE);
    }
    public void levelup(){
        super.setVida(this.getVida()+2*nivel);
        super.setAtaque(this.getVida()+2*nivel);
        super.setDefesa(this.getVida()+2*nivel);
        nivel++;
    }
}
