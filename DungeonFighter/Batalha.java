package DungeonFighter;
import javax.swing.*;
import java.awt.event.*;
public class Batalha extends JFrame implements ActionListener{
    private int classe,tipo,duracao;private boolean sucesso;
    private Jogador jogador;
    private Inimigo inimigo;private Tabuleiro tabuleiro;
    private JLabel jogadorVida, inimigoVida, ini,jog,cargas,pocoes;
    private JButton atacar, hab, pocao,sair; private JTextArea log;private JScrollPane scrollpane;
    private Imagens imagens; private float dano;
    public Batalha(Jogador jogador,Inimigo inimigo,Tabuleiro tabuleiro){
        super("Batalha");
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.tabuleiro = tabuleiro;
        tipo = inimigo.getTipo();
        classe = jogador.getClasse();
        setLayout(null);
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jogadorVida = new JLabel("Vida do Jogador: " + jogador.getVida());
        inimigoVida = new JLabel("Vida do Inimigo: " + inimigo.getVida());
        atacar = new JButton("Atacar");
        hab = new JButton("Hab.Especial");
        pocao = new JButton("Usar poção");
        imagens = new Imagens(200,250);
        jog = new JLabel(imagens.getIcone(6+classe));
        ini = new JLabel(imagens.getIcone(9+tipo));
        pocoes = new JLabel("Poções: "+jogador.getPocao());
        pocoes.setBounds(400,450,100,50);
        cargas = new JLabel("Cargas: "+jogador.getCargas());
        cargas.setBounds(250,450,100,50);
        sair = new JButton("Sair");
        sair.setBounds(300,100,100,50);
        jog.setBounds(10, 90, 250, 200);
        ini.setBounds(430, 90, 250, 200);
        jogadorVida.setBounds(50, 50, 200, 30);
        inimigoVida.setBounds(450, 50, 200, 30);
        atacar.setBounds(100, 400, 100, 50);
        hab.setBounds(250, 400, 100, 50);
        pocao.setBounds(400, 400, 100, 50);
        atacar.addActionListener(this);
        hab.addActionListener(this);
        pocao.addActionListener(this);
        sair.addActionListener(this);
        log = new JTextArea();
        log.setEditable(false); 
        scrollpane = new JScrollPane(log);
        scrollpane.setBounds(265, 200, 150, 200);
        add(pocoes);
        add(cargas);
        add(jog);
        add(ini);
        add(jogadorVida);
        add(inimigoVida);
        add(atacar);
        add(hab);
        add(pocao);
        add(scrollpane);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == atacar){
            dano = jogador.ataca(inimigo);
            addLog("Jogador atacou o inimigo");
            addLog("Dano: "+dano);
            inimigoataca();
        }else if(e.getSource() == hab){
        if(jogador.getCargas()>0){
            addLog("Jogador usou habilidade especial");
            if(classe==0){
                dano = jogador.ataca(inimigo) * 2;
                addLog("Jogador atacou o inimigo");
                addLog("Dano: "+dano);
            }else if(classe==1){
                addLog("Jogador identificou o\n ponto fraco do inimigo");
                inimigo.setDefesa(inimigo.getDefesa()/2);
                duracao=3;
            }else if(classe==2){
                jogador.setDefesa(jogador.getDefesa()*2);
                addLog("Jogador se defendeu");
                duracao=3;
            }
            jogador.setCargas(jogador.getCargas()-1);
        }else{
            addLog("Não há cargas restantes");
        }
            inimigoataca();
        }else if(e.getSource() == pocao){
            sucesso = jogador.tomapocao();
            if(sucesso){
                addLog("Jogador tomou uma poção");
            }else{
                addLog("Sem poções");
            }
            inimigoataca();
        }else if(e.getSource() == sair){
            dispose();
        }
        if(jogador.getVida()==0){
            addLog("Jogador foi derrotado");
            atacar.setEnabled(false);
            hab.setEnabled(false);
            pocao.setEnabled(false);
            add(sair);
            dispose();
            tabuleiro.fechar();
        }
        if(duracao>0){
            duracao--;
        }else if(duracao==0){
            inimigo.reset();
            jogador.resetjogador();
        }
        update();
    }
    public void update(){
        jogadorVida.setText("Vida do Jogador: " + jogador.getVida());
        inimigoVida.setText("Vida do Inimigo: " + inimigo.getVida());
        cargas.setText("Cargas: " + jogador.getCargas());
        pocoes.setText("Poções: "+ jogador.getPocao());
    }
    private void addLog(String mensagem) {
        log.append(mensagem + "\n"); 
    }
    private void inimigoataca(){
        if(inimigo.getVida()>0){
            dano=inimigo.ataca(jogador);
            addLog("Inimigo atacou o jogador");
            addLog("Dano: "+dano);
        }else if(inimigo.getTipo()!=5){
            addLog("Inimigo derrotado!");
            atacar.setEnabled(false);
            hab.setEnabled(false);
            pocao.setEnabled(false);
            add(sair);
            jogador.levelup();
            tabuleiro.update();
        }else if(inimigo.getTipo()==5){
            addLog("Chefe derrotado!");
            atacar.setEnabled(false);
            hab.setEnabled(false);
            pocao.setEnabled(false);
            add(sair);
            dispose();
            tabuleiro.setVitoria(true);
            tabuleiro.fechar();
        }
    }
}
