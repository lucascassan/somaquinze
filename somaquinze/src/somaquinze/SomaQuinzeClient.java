package somaquinze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class SomaQuinzeClient extends javax.swing.JFrame implements KeyListener {

    public String player = "";
    public String c_values = "";
    public String[] board = new String[9];
    public String turno = "1";
    public boolean pronto = false;
      
    public String modo = "casa"; //casa,valor,espera
    public int casa = 0;
    public int valor = 0; 
    
    private Color color_default  = new Color(153,217,89);
    private Color color_selected = Color.orange;
    private Color color_used     = new Color(74,105,34);
    private Color color_win      = new Color(255,153,13);
    
    
    
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_1)
            sendCommand(1);
        if (e.getKeyCode() == KeyEvent.VK_2)
            sendCommand(2);
        if (e.getKeyCode() == KeyEvent.VK_3)
            sendCommand(3);
        if (e.getKeyCode() == KeyEvent.VK_4)
            sendCommand(4);
        if (e.getKeyCode() == KeyEvent.VK_5)
            sendCommand(5);
        if (e.getKeyCode() == KeyEvent.VK_6)
            sendCommand(6);
        if (e.getKeyCode() == KeyEvent.VK_7)
            sendCommand(7);
        if (e.getKeyCode() == KeyEvent.VK_8)
            sendCommand(8);
        if (e.getKeyCode() == KeyEvent.VK_9)
            sendCommand(9);
    }
    
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }
    
    public void Final(String APlayer, String ASequencia)
    {
        String message = (APlayer.equals(player))?
                          "Você ganhou!":
                          "Você perdeu!";
      
        //Conjunto Vencedor
        for (int i = 0; i < 3; i++) {
            int aux = Integer.parseInt(ASequencia.substring(i,i+1));
            JButton botao = retornaBotaoCasa(aux);
            botao.setBackground(color_win);                   
        }
        
        jLabel7.setText(message);
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja jogar Novamente?", message, JOptionPane.YES_NO_OPTION) != 0)
            System.exit(0);
        else
            novoJogo();
      
    }
        
    public SomaQuinzeClient() {
        for (int a = 0; a < 9; a++) {
            board[a] = "";            
        }     
        initComponents();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
    }
    
    
    public void sendCommand(int v){         
        if (!player.equals(turno))
            return;
   
          if (modo.equals("casa")){
            v = v-1;
            if (!board[v].equals(""))
                return;
            casa = v;
            JButton botao = retornaBotaoCasa(v);
            botao.setBackground(color_selected);
            modo = "valor";  
            jLabel7.setText("Escolha um valor");
            return;
        }
        
        if (modo.equals("valor")){
            valor = v;
            for (int i = 0; i < 9; i++) 
                if (board[i].equals(String.valueOf(valor)))
                    return;
            tcpClient.writeMessage("jogada|"+this.player+"|"+String.valueOf(casa)+"|"+String.valueOf(valor)+"|");
            modo = "casa";
            valor = 0;
            casa = 0;
            jLabel7.setText("Aguarde o outro jogador");
        }
    }
    
    public JButton retornaBotaoCasa(int index) {
        switch (index) {
            case 0:
                return jButton4;
            case 1:
                return jButton3;
            case 2:
                return jButton5;
            case 3:
                return jButton6;
            case 4:
                return jButton7;
            case 5:
                return jButton8;
            case 6:
                return jButton9;
            case 7:
                return jButton10;
            case 8:
                return jButton11; 
            default:
                break;         
        }
        return null;
    }
    
    public JButton retornaBotaoNumero(int index) {
        switch (index) {
            case 1:
                return jButton15;
            case 2:
                return jButton16;
            case 3:
                return jButton17;
            case 4:
                return jButton18;
            case 5:
                return jButton19;
            case 6:
                return jButton20;
            case 7:
                return jButton21;
            case 8:
                return jButton22;
            case 9:
                return jButton14;
            default:
                break;         
        }
        return null;
    }

    public void atualizarPainel(Graphics g) {  
        if (!player.equals("")) {  
            for (int i = 0; i < 9; i++) {
                JButton botao = retornaBotaoCasa(i);

                if (!board[i].equals("")){
                    botao.setBackground(color_used);
                    botao.setText(board[i]);
                    
                    JButton botaoEscolha = retornaBotaoNumero(Integer.parseInt(board[i]));
                    botaoEscolha.setVisible(false);       
                }
            }
      
            if (this.pronto){
                if (this.player.equals(turno))
                    jLabel7.setText("Escolha uma casa");
                 else
                    jLabel7.setText("Aguarde o outro jogador");
            }
        } 
    }

    public void iniciar() {     
    }
    
    public void novoJogo(){
        for (int i = 0; i < 9; i++) {
            board[i]= "";
            JButton botao = retornaBotaoCasa(i);
            botao.setText("");
            botao.setBackground(color_default);
           
            JButton botaoEscolha = retornaBotaoNumero(i+1);
            botaoEscolha.setVisible(true);       
         }
        
        //Tratar Novo jogo // Pontuação?
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel() {
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                atualizarPainel(g);
            }
        };
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("0 X 0");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(400, 660));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(650, 35));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setText("Tutorial");
        jButton2.setToolTipText("");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 80, 50));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Conectar");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, -1));

        jTextField2.setText("1515");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 40, -1));

        jTextField1.setText("localhost");
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, -1));

        jLabel1.setText("Porta");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel2.setText("Servidor");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButton12.setBackground(new java.awt.Color(204, 204, 204));
        jButton12.setText("Sair");
        jButton12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton12.setEnabled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 40, -1));

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setText("Pronto");
        jButton13.setToolTipText("");
        jButton13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton13.setEnabled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 80, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/somaquinze/img/Logo.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(436, 440));
        jPanel1.setPreferredSize(new java.awt.Dimension(673, 400));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(153, 217, 89));
        jButton3.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 100, 90));

        jButton4.setBackground(new java.awt.Color(153, 217, 89));
        jButton4.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setToolTipText("");
        jButton4.setBorder(null);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 100, 90));

        jButton5.setBackground(new java.awt.Color(153, 217, 89));
        jButton5.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 100, 90));

        jButton6.setBackground(new java.awt.Color(153, 217, 89));
        jButton6.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 90));

        jButton7.setBackground(new java.awt.Color(153, 217, 89));
        jButton7.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 100, 90));

        jButton8.setBackground(new java.awt.Color(153, 217, 89));
        jButton8.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setBorder(null);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 100, 90));

        jButton9.setBackground(new java.awt.Color(153, 217, 89));
        jButton9.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setBorder(null);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 100, 90));

        jButton10.setBackground(new java.awt.Color(153, 217, 89));
        jButton10.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 100, 90));

        jButton11.setBackground(new java.awt.Color(153, 217, 89));
        jButton11.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setBorder(null);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 100, 90));

        jPanel3.setBackground(new java.awt.Color(225, 225, 225));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton14.setBackground(new java.awt.Color(241, 241, 241));
        jButton14.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton14.setText("9");
        jButton14.setBorder(null);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 30, 30));

        jButton15.setBackground(new java.awt.Color(241, 241, 241));
        jButton15.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton15.setText("1");
        jButton15.setBorder(null);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 30));

        jButton16.setBackground(new java.awt.Color(241, 241, 241));
        jButton16.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton16.setText("2");
        jButton16.setBorder(null);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 30, 30));

        jButton17.setBackground(new java.awt.Color(241, 241, 241));
        jButton17.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton17.setText("3");
        jButton17.setBorder(null);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 30, 30));

        jButton18.setBackground(new java.awt.Color(241, 241, 241));
        jButton18.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton18.setText("4");
        jButton18.setBorder(null);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 30, 30));

        jButton19.setBackground(new java.awt.Color(241, 241, 241));
        jButton19.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton19.setText("5");
        jButton19.setBorder(null);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 30, 30));

        jButton20.setBackground(new java.awt.Color(241, 241, 241));
        jButton20.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton20.setText("6");
        jButton20.setBorder(null);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 30, 30));

        jButton21.setBackground(new java.awt.Color(241, 241, 241));
        jButton21.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton21.setText("7");
        jButton21.setBorder(null);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 30, 30));

        jButton22.setBackground(new java.awt.Color(241, 241, 241));
        jButton22.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton22.setText("8");
        jButton22.setBorder(null);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VClick(evt);
            }
        });
        jPanel3.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 30, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 400, 50));

        jLabel7.setBackground(new java.awt.Color(128, 181, 74));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Conecte-se");
        jLabel7.setToolTipText("");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setName("lbAviso"); // NOI18N
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 390, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            
            String server = jTextField1.getText();
            int porta = Integer.parseInt(jTextField2.getText());
            tcpClient = new SomaQuinzeClientMain(server, porta, this);
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
            tcpClient.writeMessage("0");
            jPanel2.setEnabled(false);
            jLabel7.setText("Aguardando jogador");
            jButton12.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "  Falha ao encontrar servidor", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
       
    }//GEN-LAST:event_jPanel1KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (modo.equals("casa"))
            sendCommand(1);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (modo.equals("casa"))
            sendCommand(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (modo.equals("casa"))
            sendCommand(3);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       if (modo.equals("casa"))
            sendCommand(4);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       if (modo.equals("casa"))
            sendCommand(5);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       if (modo.equals("casa"))
            sendCommand(6);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       if (modo.equals("casa"))
            sendCommand(7);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (modo.equals("casa"))
            sendCommand(8);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (modo.equals("casa"))
            sendCommand(9);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseMoved

    private void VClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VClick
      if (modo.equals("valor")){
            JButton button = (JButton)evt.getSource();
            sendCommand(Integer.parseInt(button.getText()));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_VClick

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            HelpPlay help = new HelpPlay();
            help.setVisible(true);
            
            
    }//GEN-LAST:event_jButton2ActionPerformed

    public void closeConnection() {
        try {
            tcpClient.closeConnection();
            jButton1.setEnabled(true);
            jButton2.setEnabled(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new RunnableImpl());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    private SomaQuinzeClientMain tcpClient;

    private static class RunnableImpl implements Runnable {

        public RunnableImpl() {
        }

        public void run() {
            new SomaQuinzeClient().setVisible(true);
        }
    }
    
    
}
