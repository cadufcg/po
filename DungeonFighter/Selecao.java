package DungeonFighter;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selecao extends JFrame implements ActionListener{
    int minv,mind,mina,stat,classe;
    ImageIcon icone,cla2,cla3;JButton botao,botao2,botao3,labam,labvm,labdm,labame,labvme,labdme,start;
    JLabel label2,labvida,labatq,labdef,label3,label4;float vida,ataque,defesa;
    ImageIcon[] icones;JButton[] botoes;
    String[] nomes = {"1", "2", "3"};
    public Selecao(){
        //adicionar slider atributos e classes
        super("Classe");
        stat=10;
        vida=ataque=defesa=0;
        botoes();
        setSize(700, 550); //tamanho janela
        setVisible(true); //janela visivel
        setLocationRelativeTo(null); //janela centralizada
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botoes[0]){
            stats(10, 20, 30, 0);
	    }else if(e.getSource() == botoes[1]){
            stats(20, 15, 15, 1);
        }else if(e.getSource() == botoes[2]){
            stats(30, 20, 10, 2);
        }else if(e.getSource() == labvm){
            if(stat>0){
                vida+=1;
                stat-=1;
                setstats();
            }
        }else if(e.getSource() == labam){
            if(stat>0){
             ataque+=1;
              stat-=1;
              setstats();
            }
        }else if(e.getSource() == labdm){
            if(stat>0){
             defesa+=1;
             stat-=1;
             setstats();
            }
        }else if(e.getSource() == labvme){
            if(stat<10&vida>0&vida>minv){
                vida-=1;
                stat+=1;
                setstats();
            }
        }else if(e.getSource() == labame){
            if(stat<10&ataque>0&ataque>mina){
              ataque-=1;
              stat+=1;
              setstats();
            }
        }else if(e.getSource() == labdme){
            if(stat<10&defesa>0&defesa>mind){
             defesa-=1;
             stat+=1;
             setstats();
            }
        }else if(e.getSource() == start){
            dispose();
            new Jogador(vida,ataque,defesa,classe);
        }
    }
    public void setstats(){
        labvida.setText("Vida: "+vida);
        labatq.setText("Ataque: "+ataque);
        labdef.setText("Defesa: "+defesa);
        label3.setText("Pontos de Atributos: "+stat);
    }
    private void botoes(){
        Container tela = getContentPane();
        setLayout(null);
        icones = new ImageIcon[] {
            tamanho("1.png"),
            tamanho("2.png"),
            tamanho("3.png")
        };
        botoes = new JButton[icones.length];
        for (int i = 0; i < icones.length; i++) {
            botoes[i] = botoes(icones[i], 200 * i, 30, 200, 250, this);
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
    private ImageIcon tamanho(String imagem){
        ImageIcon icone = new ImageIcon(imagem);
        Image img = icone.getImage();
        Image resizedImage = img.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    private JButton botoes(String s, int x, int y, int l, int h, ActionListener listener) {
        JButton botao = new JButton(s);
        botao.setBounds(x, y, l, h);
        botao.addActionListener(listener);
        return botao;
    }
    private JButton botoes(ImageIcon s, int x, int y, int l, int h, ActionListener listener) {
        JButton botao = new JButton(s);
        botao.setBounds(x, y, l, h);
        botao.addActionListener(listener);
        return botao;
    }
    private void stats(int vida, int ataque, int defesa, int classe) {
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
    private JLabel labels(String s, int x, int y, int l, int h){
        JLabel label = new JLabel(s);
        label.setBounds(x, y, l, h);
        return label;
    }
    public int getClasse(){
        return classe;
    }
}
