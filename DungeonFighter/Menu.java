package DungeonFighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener{
    JButton botao1,botao2,botao3;Selecao sel;
    Menu(){
        Container menu= getContentPane();
        setLayout(null);
        ImageIcon image=new ImageIcon("menu.jpg");
        Image img = image.getImage(); 
        Image resizedImage = img.getScaledInstance(960, 768, Image.SCALE_SMOOTH); 
        JLabel background = new JLabel(new ImageIcon(resizedImage));
        background.setBounds(0,0,960,768);
        menu.add(background);
        botao1 = new JButton("Jogar");
        botao2 = new JButton("Debug");
        botao3 = new JButton("Sair");
        botao1.setBounds(700, 300, 200, 100);
        botao1.setForeground(Color.blue);
        botao2.setBounds(700, 400, 200, 100);
        botao2.setForeground(Color.RED);
        botao3.setBounds(700, 500, 200, 100);
        botao3.setForeground(Color.DARK_GRAY);
        botao1.addActionListener(this);
        botao2.addActionListener(this);
        botao3.addActionListener(this);
        menu.add(botao1);
        menu.add(botao2);
        menu.add(botao3);
        setSize(960,768);
        menu.setComponentZOrder(background, menu.getComponentCount() - 1);
        setVisible(true);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(new Color(135,50,250));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botao1){
            new Selecao();
        }else if(e.getSource() == botao2){
            new Jogador(0,0,0,0);
        }else if(e.getSource() == botao3){
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
}
