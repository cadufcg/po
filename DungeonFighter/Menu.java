package DungeonFighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener{
    JButton botao1,botao2,botao3;Selecao sel;JButton[] botoes;String[] textos;Container menu;
    Menu(){
        menu= getContentPane();
        setLayout(null);
        ImageIcon image=new ImageIcon("menu.jpg");
        Image img = image.getImage(); 
        Image resizedImage = img.getScaledInstance(960, 768, Image.SCALE_SMOOTH); //funcao pra enquadrar a imagem na resolucao
        JLabel background = new JLabel(new ImageIcon(resizedImage));
        background.setBounds(0,0,960,768);
        menu.add(background);
        textos = new String[]{"Jogar","Debug","Sair"};
        botoes = new JButton[3];
        fazerbotoes(botoes, textos);
        setSize(960,768);
        menu.setComponentZOrder(background, menu.getComponentCount() - 1); //metodo para os botoes ficarem acima do background
        setVisible(true);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(new Color(135,50,250));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botoes[0]){
            new Selecao();
        }else if(e.getSource() == botoes[1]){
            new Jogador(0,0,0,0);
        }else if(e.getSource() == botoes[2]){
            JFrame frame = new JFrame();
            String[] options = new String[2];
            options[0] = "Sim";
            options[1] = "Não";
            int resp=JOptionPane.showOptionDialog(frame.getContentPane(), "Sair?", "Sair", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(resp==0){
                System.exit(0);
            }
        
        }
    }
    public void fazerbotoes(JButton[] botoes,String[] textos){ //funcao para criar os botoes
        for(int i=0;i<3;i++){
        botoes[i] = new JButton(textos[i]);
        botoes[i].setBounds(700, 300+i*100, 200, 100);
        botoes[i].setForeground(Color.BLACK);
        botoes[i].addActionListener(this);
        menu.add(botoes[i]);
        }
    }
}
