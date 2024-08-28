package DungeonFighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener{
    private JButton[] botoes;private String[] textos;private Container menu;public int[][] backup;public int novamente;
    Menu(){
        super("Menu");
        menu= getContentPane();
        setLayout(null);
        backup = new int[10][5];
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(135,50,250));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botoes[0]){//jogar normal
            new Selecao(false);
            this.dispose();
        }else if(e.getSource() == botoes[1]){//jogar debug, no final botar instanciar a selecao
            new Selecao(true);
            this.dispose();
        }else if(e.getSource() == botoes[2]){//Sair
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
