package DungeonFighter;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro implements ActionListener{
    private int[][] tabuleiro;private int i,j,x,y,escolha,dicas;private boolean debug,dicatrue,vitoria;private JButton[][] botao;
    private JLabel pos,vida,ataque,defesa,pot;private Random random;private Imagens imagens;private JButton obst,dica,sair;
    private Jogador jogador;private ArrayList<Inimigo> inimigos;private Inimigo chefe;private JFrame menu;@SuppressWarnings("unused")
    private boolean fazerBackup;
    Tabuleiro(Jogador jogador,boolean debug,boolean fazerBackup){
        menu=new JFrame();
        menu.setLayout(null);
        this.jogador=jogador;
        this.debug=debug;
        this.fazerBackup = fazerBackup;
        random=new Random();tabuleiro=new int[10][5];botao=new JButton[10][5];//matriz tabuleiro para guardar a informacao e botao para mostrar na tela
        botoes(botao);
        dicas=3;
        vitoria=false;
        x=0;y=0; //posicao inicial
        imagens = new Imagens(90,90);
        pos=new JLabel("Posição:"+String.valueOf(x)+String.valueOf(y));pos.setBounds(100,600,120,20);
        vida=new JLabel("Vida:"+jogador.getVida());vida.setBounds(220,600,120,20);
        ataque=new JLabel("Ataque:"+jogador.getAtaque());ataque.setBounds(220,625,120,20);
        defesa=new JLabel("Defesa:"+jogador.getDefesa());defesa.setBounds(220,650,120,20);
        pot=new JLabel("Poções:"+jogador.getPocao());pot.setBounds(100,630,120,20);
        obst=new JButton(imagens.getIcone(tabuleiro[x][y]));obst.setBounds(425,5,90,90);
        dica=new JButton("Dicas: "+dicas);dica.setBounds(300,620,120,20);
        sair=new JButton("Sair");sair.setBounds(800,620,120,70);
        dica.addActionListener(this);
        sair.addActionListener(this);
        inimigos=new ArrayList<>();
        inimigos.add(new Inimigo(20,20,5,0));
        inimigos.add(new Inimigo(10,10,10,1));
        inimigos.add(new Inimigo(30,20,10,2));
        inimigos.add(new Inimigo(40,25,5,3));
        inimigos.add(new Inimigo(200,1,0,4));
        chefe = new Inimigo(150,15,20,5);
        if (fazerBackup) {
            tabuleiro = Backup.gettabuleiroinicial();
        }else{
            Espaco espaco = new Espaco(10, 5, 7, 5, 5);
            tabuleiro = espaco.aleatorizar();
            uploadbackup();
        }
        menu.add(pos);menu.add(vida);menu.add(ataque);menu.add(defesa);menu.add(pot);menu.add(obst);menu.add(dica);menu.add(sair);
        update();
        menu.setSize(960,768);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {//metodo apertar botoes 
        if(e.getSource() == sair){
            JFrame frame = new JFrame();
            String[] options = new String[2];
            options[0] = "Sim";
            options[1] = "Não";
            int resp=JOptionPane.showOptionDialog(frame.getContentPane(), "Sair?", "Sair", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(resp==0){
            fechar();
            }
        }else if(e.getSource() == dica){
            dicatrue=true;
            for(i=0;i<10;i++){
                for(j=0;j<5;j++){
                    botao[i][j].setEnabled(true);
                }
            }
        }else{
            for(i=0;i<10;i++){
                for(j=0;j<5;j++){
                    if(e.getSource() == botao[i][j]){
                        if(dicatrue){
                            contaDica(i);
                            dicas--;
                            dicatrue=false;
                            for (int k = 0; k < 10; k++) {
                                for (int l = 0; l < 5; l++) {
                                    if (!((k==i&&l==j)||(k==x+1&&l==y)||(k==x&&l==y+1)||(k==x-1&&l==y)||(k==x&&l==y-1)||(k==x&&l==y))){
                                        botao[k][l].setEnabled(false);
                                    }
                                }
                            }
                            update();
                            break;
                        }
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
    }
    private void uploadbackup() {
        Backup.settabuleiroinicial(tabuleiro);
    }
    private void contaDica(int linha){
        int cont=0;
        for(i=0;i<5;i++){
            if(tabuleiro[linha][i]==1||tabuleiro[linha][i]==2){
                cont++;
            }
        }
        JOptionPane.showMessageDialog(null, "Há "+cont+" armadilha(s) nesta coluna","Dica", JOptionPane.INFORMATION_MESSAGE);
    }
    public void setVitoria(boolean result){
        this.vitoria=result;
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
        jogador.setVida(jogador.getVida()-5);
        break;
    case 2:  //se for a outra armadilha ele perde vida de 1 até 10
        int dano=random.nextInt(10)+1;
        jogador.setVida(jogador.getVida()-dano);
        break;
    case 3:
        new Batalha(jogador,chefe,this);
        break;
    case 4://se for um inimigo ele inicia uma batalha
        escolha=random.nextInt(inimigos.size());
        Inimigo inimigo=inimigos.get(escolha);
        new Batalha(jogador,inimigo,this);
        inimigos.remove(escolha);
        tabuleiro[x][y]=0;
        break;
    }
    
}
public void fechar(){
    new Tela(jogador,vitoria);
    menu.dispose();
}
public void update(){ //metodo para atualizar as informacoes na tela
    pos.setText("Posição:" + String.valueOf(x) + String.valueOf(y));
    vida.setText("Vida:" + jogador.getVida());
    ataque.setText("Ataque:" + jogador.getAtaque());
    defesa.setText("Defesa:" + jogador.getDefesa());
    pot.setText("Poções:" + jogador.getPocao());
    obst.setIcon(imagens.getIcone(tabuleiro[x][y]));
    dica.setText("Dicas: " + dicas);
    for(i=0;i<10;i++){
        for(j=0;j<5;j++){
            botao[i][j].setIcon(espaco(i,j));
        }
    }   
    if(jogador.getVida()==0){
        fechar();
    }
}
private ImageIcon espaco(int i,int j){//metodo para saber o que esta no botao no momento, retornando a imagem especifica
    if (i == x && j == y) {
        return imagens.getJogador(jogador.getClasse());
    }
    if (debug) {
        return imagens.getIcone(tabuleiro[i][j]);
    } else {
        if (tabuleiro[i][j] == 3) {
            return imagens.getIcone(3);
        }
        return imagens.getIcone(15); 
    }
}
}
