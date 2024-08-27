package DungeonFighter;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Tabuleiro implements ActionListener{
    private int[][] tabuleiro;private int i,j,x,y;private JButton[][] botao;
    ;private JLabel pos,vida,ataque,defesa,pot;private Random random;private Imagens imagens;
    private Jogador jogador;private Inimigo inimigo;private JFrame menu;
    Tabuleiro(Jogador jogador){
        menu=new JFrame();
        menu.setLayout(null);
        this.jogador=jogador;
        random=new Random();tabuleiro=new int[10][5];botao=new JButton[10][5];//matriz tabuleiro para guardar a informacao e botao para mostrar na tela
        botoes(botao);
        x=0;y=0; //posicao inicial
        pos=new JLabel("Posição:"+String.valueOf(x)+String.valueOf(y));pos.setBounds(100,600,120,20);
        vida=new JLabel("Vida:"+jogador.getVida());vida.setBounds(220,600,120,20);
        ataque=new JLabel("Ataque:"+jogador.getAtaque());ataque.setBounds(220,625,120,20);
        defesa=new JLabel("Defesa:"+jogador.getDefesa());defesa.setBounds(220,650,120,20);
        pot=new JLabel("Poções:"+jogador.getPocao());pot.setBounds(100,630,120,20);
        inimigo=new Inimigo(20,20,20);
        Espaco espaco = new Espaco(10,5,7,5,5);
        tabuleiro = espaco.aleatorizar();
        imagens = new Imagens(90,90);
        menu.add(pos);menu.add(vida);menu.add(ataque);menu.add(defesa);menu.add(pot);
        update();
        menu.setSize(960,768);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {//metodo apertar botoes 
        for(i=0;i<10;i++){
            for(j=0;j<5;j++){
                if(e.getSource() == botao[i][j]){
                    if((i==x+1&&j==y)||(i==x&&j==y+1)||(i==x-1&&j==y)||(i==x&&j==y-1)||(i==x&&j==y)){
                        x=i;y=j;
                        movimento(i,j);
                    }else{
                    botao[i][j].setEnabled(false);
                    }
                }
            }
        }
    }
    private void botoes(JButton[][] botao){//metodo inicializa botoes na tela
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
    }
    private void movimento(int x,int y){//metodo para habilitar e desabilitar os botoes e comecar as situacoes
        for(i=0;i<10;i++){
            for(j=0;j<5;j++){
                if((i==x+1&&j==y)||(i==x&&j==y+1)||(i==x-1&&j==y)||(i==x&&j==y-1)||(i==x&&j==y)){
                    botao[i][j].setEnabled(true);
                }else{
                    botao[i][j].setEnabled(false);
                }
            }
        }
        update();
        if(tabuleiro[x][y]!=0){
            situacao();
            update();
        }
    }
    
public void situacao(){ //se o jogador pisar em um botao que nao é grama acontece uma situacao
    int caso = tabuleiro[x][y];
    switch(caso){
    case 5: //se foi uma pocao ele vai ganhar uma pocao
        jogador.setPocao(jogador.getPocao()+1);
        tabuleiro[x][y]=0; //a pocao no chao vira grama
        break;
    case 1: //se for buraco ele perde vida fixa
        jogador.setVida(jogador.getVida()-10);
        break;
    case 2:  //se for a outra armadilha ele perde vida de 1 até 20
        int dano=random.nextInt(20)+1;
        jogador.setVida(jogador.getVida()-dano);
        break;
    case 3:
    case 4://se for um inimigo ele inicia uma batalha
        new Batalha(jogador,inimigo);
        break;
    }
    
}
private void update(){ //metodo para atualizar as informacoes na tela
    pos.setText("Posição:"+String.valueOf(x)+String.valueOf(y));
    vida.setText("Vida:"+jogador.getVida());
    ataque.setText("Ataque:"+jogador.getAtaque());
    defesa.setText("Defesa:"+jogador.getDefesa());
    pot.setText("Poções:"+jogador.getPocao());
    for(i=0;i<10;i++){
        for(j=0;j<5;j++){
            botao[i][j].setIcon(espaco(i,j));
        }
    }   
}
private ImageIcon espaco(int i,int j){//metodo para saber o que esta no botao no momento, retornando a imagem especifica
    if (i == x && j == y) {
        return imagens.getJogador(jogador.getClasse());
    }
    return imagens.getIcone(tabuleiro[i][j]);
}
   
}
