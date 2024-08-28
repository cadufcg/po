package DungeonFighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tela extends JFrame implements ActionListener{
    private JButton[] botoes;private String[] textos;JLabel vitorioso,perdedor;
    private Jogador jogador;@SuppressWarnings("unused")
    private boolean vitoria;
    Tela(Jogador jogador,boolean vitoria){
        super("Fim de jogo");
        getContentPane();
        setLayout(null);
        this.jogador = jogador;
        this.vitoria = vitoria;
        ImageIcon image=new ImageIcon("fim.jpg");
        Image img = image.getImage(); 
        Image resizedImage = img.getScaledInstance(960, 768, Image.SCALE_SMOOTH); //funcao pra enquadrar a imagem na resolucao
        JLabel background = new JLabel(new ImageIcon(resizedImage));
        background.setBounds(0,0,960,768);
        add(background);
        Font fonte = new Font("Comic Sans",Font.BOLD,35);
        vitorioso = new JLabel("Vitória!");
        vitorioso.setBounds(230,290,400,200);
        perdedor = new JLabel("Derrota");
        perdedor.setBounds(230,290,400,200);
        vitorioso.setFont(fonte);
        perdedor.setFont(fonte);
        textos = new String[]{"Reiniciar jogo","Novo Jogo"};
        botoes = new JButton[2];
        fazerbotoes(botoes, textos);
        resultado(vitoria);
        setSize(960,768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(new Color(135,50,250));
        setComponentZOrder(background, getComponentCount() - 1); //metodo para os botoes ficarem acima do background
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botoes[0]){//jogar novamente
            jogador.resetvida();
            new Tabuleiro(jogador,jogador.getDebug(),true);
            dispose();
        }else if(e.getSource() == botoes[1]){//jogo novo
            new Selecao(jogador.getDebug());
            dispose();
        }
    }
    public void resultado(boolean vitoria){
        if(vitoria==true){
            add(vitorioso);
        }else{
            add(perdedor);
        }
    }
    public void fazerbotoes(JButton[] botoes,String[] textos){ //funcao para criar os botoes
        for(int i=0;i<2;i++){
        botoes[i] = new JButton(textos[i]);
        botoes[i].setBounds(700, 300+i*100, 200, 100);
        botoes[i].setForeground(Color.BLACK);
        botoes[i].addActionListener(this);
        add(botoes[i]);
        }
    }
}
