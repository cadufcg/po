package DungeonFighter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Inimigo {
    private float vida,ataque,defesa,danobase,dano,maxvida,maxdef,maxatq;
	public void setMaxvida(float maxvida) {
		this.maxvida = maxvida;
	}
	public void setMaxdef(float maxdef) {
		this.maxdef = maxdef;
	}
	public void setMaxatq(float maxatq) {
		this.maxatq = maxatq;
	}
	public float getMaxvida() {
		return maxvida;
	}
	public float getMaxdef() {
		return maxdef;
	}
	public float getMaxatq() {
		return maxatq;
	}
	private int tipo;
	private Random random = new Random();
    public Inimigo(float vida,float ataque,float defesa,int tipo){ //inimigo é qualquer coisa que tem vida,ataque e defesa
        this.vida=vida;
        this.ataque=ataque;
        this.defesa=defesa;
		this.tipo=tipo;
		maxvida=vida;
		maxatq=ataque;
		maxdef=defesa;
    }
	public void reset(){
		this.defesa=maxdef;
		this.ataque=maxatq;
	}
	public float getVida() {
		return vida;
	}
	public void setVida(float vida) {
		if(vida<0){
			vida=0;
		}
		BigDecimal decimal = new BigDecimal(vida).setScale(2, RoundingMode.HALF_UP);
		this.vida = decimal.floatValue();
	}
	public int getTipo(){
		return tipo;
	}
	public float getAtaque() {
		return ataque;
	}
	public void setAtaque(float ataque) {
		this.ataque = ataque;
	}
	public float getDefesa() {
		return defesa;
	}
	public void setDefesa(float defesa) {
		this.defesa = defesa;
	}
    public float ataca(Inimigo inimigo){ //metodo de ataque, dano= atq*(100-defesa do inimigo)/100
		danobase=ataque*(100-inimigo.getDefesa())/100.0f;//dano base = atq do ser dividido pela defesa, defesa 0=100% de dano, defesa 100=0% de dano
		dano=danobase*random.nextFloat();//dano vai variar de dano base *(0 até 1)
		if (dano < 0) {
            dano = 0;
        }
		BigDecimal decimal = new BigDecimal(dano).setScale(2, RoundingMode.HALF_UP);
		dano = decimal.floatValue();
		inimigo.tomadano(dano);
		System.out.println(dano);
		return dano;
    }
	public void tomadano(float dano){
        this.setVida(this.getVida()-dano);
    }
}
