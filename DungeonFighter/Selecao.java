package DungeonFighter;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selecao extends JFrame implements ActionListener{
    private int minv,mind,mina,stat,classe;private JButton labam,labvm,labdm,labame,labvme,labdme,start,hab;
    private JLabel labvida,labatq,labdef,label3;private float vida,ataque,defesa; 
    private JButton[] botoes;private Imagens imagens;private String[] habilidades;private boolean debug;
    public Selecao(boolean debug){
        //adicionar slider atributos e classes
        super("Classe");
        stat=10; //pontos de atribuicao
        this.debug=debug;
        habilidades = new String[]{"Espadão-\n Golpeia forte","Reconhecimento-\n reconhece armadilhas","Defesa absoluta-\n Defende absolutamente"};
        imagens = new Imagens(250, 200);
        botoes();  //metodos para criar a tela
        stats(40, 35, 45, 0);
        setSize(700, 550); //tamanho janela
        setVisible(true); //janela visivel
        setLocationRelativeTo(null); //janela centralizada
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botoes[0]){ //classe 1
            stats(40, 35, 45, 0);
	    }else if(e.getSource() == botoes[1]){ //classe 2
            stats(60, 55, 5, 1);
        }else if(e.getSource() == botoes[2]){ //classe 3
            stats(100, 15, 35, 2);
        }else if(e.getSource() == labvm){ //label vida mais
            if(stat>0){
                vida+=1;
                stat-=1;
                setstats();
            }
        }else if(e.getSource() == labam){//label atq mais
            if(stat>0){
             ataque+=1;
              stat-=1;
              setstats();
            }
        }else if(e.getSource() == labdm){//label def mais
            if(stat>0){
             defesa+=1;
             stat-=1;
             setstats();
            }
        }else if(e.getSource() == labvme){//label vida menos
            if(stat<10&vida>0&vida>minv){
                vida-=1;
                stat+=1;
                setstats();
            }
        }else if(e.getSource() == labame){//label atq menos
            if(stat<10&ataque>0&ataque>mina){
              ataque-=1;
              stat+=1;
              setstats();
            }
        }else if(e.getSource() == labdme){//label def menos
            if(stat<10&defesa>0&defesa>mind){
             defesa-=1;
             stat+=1;
             setstats();
            }
        }else if(e.getSource() == hab){
            JOptionPane.showMessageDialog(this,habilidades[classe],"Habilidade", JOptionPane.INFORMATION_MESSAGE);
        }else if(e.getSource() == start){//comecar
            Jogador jogador = new Jogador(vida,ataque,defesa,classe,debug); //instanciar jogador
            jogador.novojogo();
            dispose(); //fechar janela
        }
    }
    public void setstats(){ //metodo para atualizar os status para toda interacao que mexe com eles
        labvida.setText("Vida: "+vida);
        labatq.setText("Ataque: "+ataque);
        labdef.setText("Defesa: "+defesa);
        label3.setText("Pontos de Atributos: "+stat);
    }
    private void botoes(){//metodo para criar imagens,botoes e labels, basicamente todos componentes da tela para evitar repeticao
        Container tela = getContentPane();
        setLayout(null);
        String[] nomes = new String[]{"Guerreiro","Arqueiro","Escudo"};
        botoes = new JButton[3];
        for (int i = 0; i < 3; i++) {
            botoes[i] = botoes(imagens.getIcone(i+6), 200 * i, 30, 200, 250, this);
            tela.add(botoes[i]);
            JLabel label = new JLabel(nomes[i]);
            label.setBounds(65 + 200 * i, 10, 110, 20);
            tela.add(label);
        }
        labvida = labels("Vida: " + vida, 10, 300, 110, 20);
        labatq = labels("Ataque: " + ataque, 10, 350, 110, 20);
        labdef = labels("Defesa: " + defesa, 10, 400, 110, 20);
        label3 = labels("Pontos de Atributos: " + stat, 80, 300, 170, 20);
        labvm = botoes("+", 110, 325, 110, 20, this);
        labam = botoes("+", 110, 375, 110, 20, this);
        labdm = botoes("+", 110, 425, 110, 20, this);
        labvme = botoes("-", 0, 325, 110, 20, this);
        labame = botoes("-", 0, 375, 110, 20, this);
        labdme = botoes("-", 0, 425, 110, 20, this);
        start = botoes("Start", 400, 325, 200, 100, this);
        hab = botoes("Habilidade",255,295,130,50,this);
        tela.add(hab);
        tela.add(labvida);
        tela.add(labatq);
        tela.add(labdef);
        tela.add(label3);
        tela.add(labvm);
        tela.add(labam);
        tela.add(labdm);
        tela.add(labvme);
        tela.add(labame);
        tela.add(labdme);
        tela.add(start);
    }
    private JButton botoes(String s, int x, int y, int l, int h, ActionListener listener) { //metodo para criar os botoes
        JButton botao = new JButton(s);
        botao.setBounds(x, y, l, h);
        botao.addActionListener(listener);
        return botao;
    }
    private JButton botoes(ImageIcon s, int x, int y, int l, int h, ActionListener listener) { //metodo para criar os botoes com imagem
        JButton botao = new JButton(s);
        botao.setBounds(x, y, l, h);
        botao.addActionListener(listener);
        return botao;
    }
    private void stats(int vida, int ataque, int defesa, int classe) { //atualizar os status
        this.minv = vida;
        this.vida = vida;
        this.mina = ataque;
        this.ataque = ataque;
        this.mind = defesa;
        this.defesa = defesa;
        this.stat = 10;
        this.classe = classe;
        setstats();
    }
    private JLabel labels(String s, int x, int y, int l, int h){ //metodo para criar as labels
        JLabel label = new JLabel(s);
        label.setBounds(x, y, l, h);
        return label;
    }
    public int getClasse(){ //o jogador vai precisar da classe da selecao, a imagem vai mudar de acordo com a classe
        return classe;
    }
}
