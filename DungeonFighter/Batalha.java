package DungeonFighter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class Batalha extends JFrame implements ActionListener{
    public Batalha(Jogador jogador,Inimigo inimigo){
        Container tela = getContentPane();
        tela.setLayout(null);
        tela.setSize(700, 550); //tamanho janela
        tela.setVisible(true); //janela visivel
        setLocationRelativeTo(null); //janela centralizada
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
