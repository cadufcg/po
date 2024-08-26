package DungeonFighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Tabuleiro implements ActionListener{
    int[][] tabuleiro,player;int i,j,x,y,linha,armadilhas,armadilhas2,inimigos;JButton[][] botao;
    int posicao;JLabel pos,vida,ataque,defesa,pot;Random random;
    Jogador jogador;ImageIcon[] icones;Inimigo inimigo;
    Tabuleiro(Jogador jogador){
        JFrame menu=new JFrame();
        menu.setLayout(null);
        this.jogador=jogador;
        armadilhas=5;armadilhas2=5;inimigos=5;
        random=new Random();tabuleiro=new int[10][5];botao=new JButton[10][5];posicao=00;icones();
        for(i=0;i<10;i++){
            for(j=0;j<5;j++){
                x=20+90*i;
                y=100+90*j;
                botao[i][j]=new JButton();
                botao[i][j].setBounds(x,y,90,90);
                botao[i][j].addActionListener(this);
                menu.add(botao[i][j]);
                if(i==0&j==0||i==1&j==0||i==0&j==1){
                    botao[i][j].setEnabled(true);
                }else{
                    botao[i][j].setEnabled(false);
                }
            }
        }
        //String posi=String.valueOf(posicao);
        x=0;y=0;
        pos=new JLabel("Posição:"+String.valueOf(x)+String.valueOf(y));pos.setBounds(100,600,120,20);
        vida=new JLabel("Vida:"+jogador.getVida());vida.setBounds(220,600,120,20);
        ataque=new JLabel("Ataque:"+jogador.getAtaque());ataque.setBounds(220,625,120,20);
        defesa=new JLabel("Defesa:"+jogador.getDefesa());defesa.setBounds(220,650,120,20);
        pot=new JLabel("Poções:"+jogador.getPocao());pot.setBounds(290,600,120,20);
        inimigo=new Inimigo(20,20,20);
        aleatorizar();update();
        menu.add(pos);menu.add(vida);menu.add(ataque);menu.add(defesa);menu.add(pot);
        menu.setSize(960,768);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(i=0;i<10;i++){
            for(j=0;j<5;j++){
                if(e.getSource() == botao[i][j]){
                    if(tabuleiro[i][j]==0&(i==x+1&j==y)||(i==x&j==y+1)||(i==x-1&j==y)||(i==x&j==y-1)||(i==x&j==y)){
                        x=i;y=j;
                        movimento(i,j);
                        update();
                    }else{
                    botao[i][j].setEnabled(false);
                    }
                }
            }
        }
    }
    private void movimento(int x,int y){
        for(i=0;i<10;i++){
            for(j=0;j<5;j++){
                if(i==x+1&j==y||i==x&j==y+1||i==x-1&j==y||i==x&j==y-1){
                    botao[i][j].setEnabled(true);
                }else if(i==x&j==y){
                    botao[i][j].setEnabled(true);
                }else{
                    botao[i][j].setEnabled(false);
                }if(tabuleiro[x][y]!=0){
                    situacao();
                }
            }
        }
    }
    public void situacao(){
        if(tabuleiro[i][j]==5){
            jogador.setPocao(jogador.getPocao()+1);
            tabuleiro[i][j]=0;
        }else if(tabuleiro[i][j]==1){
            jogador.setVida(jogador.getVida()-10);
        }else if(tabuleiro[i][j]==2){
            float dano=random.nextFloat(20);
            jogador.setVida(jogador.getVida()-dano);
        }else if(tabuleiro[i][j]==3||tabuleiro[i][j]==4){
            new Batalha(jogador,inimigo);
        }
    }
    private void update(){
        pos.setText("Posição:"+String.valueOf(x)+String.valueOf(y));
        vida.setText("Vida:"+jogador.getVida());
        ataque.setText("Ataque:"+jogador.getAtaque());
        defesa.setText("Defesa:"+jogador.getDefesa());
        pot.setText("Poções:"+jogador.getPocao());
        for(i=0;i<10;i++){
            for(j=0;j<5;j++){
                botao[i][j].setIcon(returnimage(i,j));
            }
        }   
    }
    private void aleatorizar(){
        for(i=0;i<10;i++){
            for(j=0;j<5;j++){
                if(i==0&j==0){
                    tabuleiro[i][j]=0;
                }else if(Math.random()<0.15&armadilhas>0){
                    armadilhas-=1;
                    tabuleiro[i][j]=1;
                }else if(Math.random()<0.05&armadilhas2>0){
                    armadilhas2-=1;
                    tabuleiro[i][j]=2;
                }else if(i==9&j==4){
                    tabuleiro[i][j]=3;
                }else if(Math.random()<0.2&inimigos>0){
                    inimigos-=1;
                    tabuleiro[i][j]=4;
                }else if(Math.random()<0.3){
                    tabuleiro[i][j]=5;
                }else{
                    tabuleiro[i][j]=0;
                }
            }
        }
    }
    private void icones(){
        icones = new ImageIcon[7];
        icones[0] = tamanho("grass.png");//grama
        icones[1] = tamanho("hole.jpg");//armadilha
        icones[2] = tamanho("trap.png");//armadilha2
        icones[3] = tamanho("boss.png");//boss
        icones[4] = tamanho("enemy.png");//inimigo
        icones[5] = tamanho("potion.png");//pocao
        if(jogador.getClasse()==0){
            icones[6] = tamanho("1.png");//jogador
        }else if(jogador.getClasse()==1){
            icones[6] = tamanho("2.png");//jogador
        }else if(jogador.getClasse()==2){
            icones[6] = tamanho("3.png");//jogador
        }              
    }
    private ImageIcon tamanho(String imagem){
        ImageIcon icone = new ImageIcon(imagem);
        Image img = icone.getImage();
        Image resizedImage = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    private ImageIcon returnimage(int i,int j){
        if(i==x&j==y){
            return icones[6];
        }
        return icones[tabuleiro[i][j]];
    }
}
