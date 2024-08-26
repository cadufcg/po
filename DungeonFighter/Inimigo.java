package DungeonFighter;

public class Inimigo {
    private float vida;
    private float ataque;
    private float defesa;
    public Inimigo(float vida,float ataque,float defesa){ //inimigo é qualquer coisa que tem vida,ataque e defesa
        this.vida=vida;
        this.ataque=ataque;
        this.defesa=defesa;
    }
	public float getVida() {
		return vida;
	}
	public void setVida(float vida) {
		this.vida = vida;
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
    public void ataca(Inimigo inimigo){ //metodo de ataque, dano= atq*(100-defesa do inimigo)/100
        inimigo.setVida(inimigo.getVida()-(this.ataque)*(100-(inimigo.getDefesa()))/100);
    }
}
