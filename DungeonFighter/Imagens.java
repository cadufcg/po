package DungeonFighter;

import javax.swing.*;
import java.awt.*;

public class Imagens {
    private ImageIcon[] icones;
    private int h,l;
    public Imagens(int h,int l){
        this.h=h;
        this.l=l;
        icones = new ImageIcon[16];
        getImagem();
    }
    private void getImagem(){
        icones[0] = tamanho("grass.png");   // Grama
        icones[1] = tamanho("hole.jpg");    // Armadilha 1
        icones[2] = tamanho("trap.png");    // Armadilha 2
        icones[3] = tamanho("boss.png");    // Chefe
        icones[4] = tamanho("enemy.png");   // Inimigo
        icones[5] = tamanho("potion.png");  // Poção
        icones[6] = tamanho("guerreiro.jpg");       // Jogador classe 0
        icones[7] = tamanho("arco.png");       // Jogador classe 1
        icones[8] = tamanho("esc.png");       // Jogador classe 2
        icones[9] = tamanho("ini1.png"); //Inimigo tipo 0
        icones[10] = tamanho("ini2.png"); //Inimigo tipo 1
        icones[11] = tamanho("ini3.jpg"); //Inimigo tipo 2
        icones[12] = tamanho("ini4.png"); //Inimigo tipo 3
        icones[13] = tamanho("ini5.png"); //Inimigo tipo 4
        icones[14] = tamanho("wumpus.jpg"); //Chefe 
        icones[15] = tamanho("desconhecido.png");
    }
    private ImageIcon tamanho(String string) {
        ImageIcon iconeOriginal = new ImageIcon(string);
        Image imagem = iconeOriginal.getImage();
        Image imagemRedimensionada = imagem.getScaledInstance(l, h, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }
    public ImageIcon getIcone(int i) {
        if (i >= 0 && i < icones.length) {
            return icones[i];
        } else {
            return null; 
        }
    }
    public ImageIcon getJogador(int classe) {
        switch (classe) {
            case 0:
                return icones[6];
            case 1:
                return icones[7];
            case 2:
                return icones[8];
            default:
                return icones[6]; 
        }
    }
}
