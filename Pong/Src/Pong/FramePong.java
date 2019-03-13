/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong;

import Pong.Abstract.PongObject;
import Pong.Concrete.PlayField;
import Pong.Concrete.Paddle;
import Pong.Interfaces.GameDisplay;
import Pong.Concrete.Ball;
import Pong.Enums.ObjectShape;
import Pong.Enums.TeamScored;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author Isaac Denney Isaac.Denney@western.edu
 */
public class FramePong extends javax.swing.JFrame {

    private Random random = new Random();
    public Timer clock = new Timer(17, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    });
    public Timer ballClock = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((DisplayPanel) panelPong).addBall();
        }

    });

    private int mouseX;
    private int mouseY;
    private boolean _IsGameRunning = false;

    /**
     * Creates new form FramePong
     */
    public FramePong() {
        initComponents();
    }

    private void startGame() {
        if (!_IsGameRunning) {
            _IsGameRunning = true;
            initGame();
            runGame();
        }
    }

    private void resetGame() {

    }

    private void initGame() {

    }

    private void runGame() {
        clock.start();
        ballClock.start();
    }

    private void tick() {
        ((DisplayPanel) panelPong).tick();
    }

    protected class DisplayPanel extends JPanel implements GameDisplay {

        private ScorePanel panelScore;
        private GamePanel panelGame;

        int panelScoreH = 50;
        
        private boolean _IsInit = false;

        public Point getMouseLoc() {
            Point value = null;

            if (this._IsInit) {
                value = panelGame.getMouseLoc();
            }

            return value;
        }

        public void setMouseLoc(Point value) {
            if (this._IsInit) {
                panelGame.setMouseLoc(value);
            }
        }

        public DisplayPanel() {
            this.addComponentListener(new ComponentListener() {
                @Override
                public void componentResized(ComponentEvent e) {
                    init();
                }

                @Override
                public void componentMoved(ComponentEvent e) {
                    init();
                }

                @Override
                public void componentShown(ComponentEvent e) {
                    //init();
                }

                @Override
                public void componentHidden(ComponentEvent e) {
                    //init();
                }

            });
        }

        public void tick() {
            if (this._IsInit) {
                panelGame.tick();
            }
        }

        public void addBall() {
            panelGame.addBall();
        }

        public void init() {
            if (!_IsInit) {
                initScorePanel();
                initPlayPanel();
                _IsInit = true;
            }
        }

        private void initScorePanel() {
            Rectangle bounds = getBounds();
            
            Insets thisBorder;
            if (this.getBorder()!=null) {
                thisBorder=this.getBorder().getBorderInsets(this);
                if (thisBorder!=null) {
                    bounds.x+=thisBorder.left;
                    bounds.y+=thisBorder.top;
                    bounds.width+=-(bounds.x+thisBorder.right);
                    bounds.height=panelScoreH;
                    
                }
            }
            
            panelScore = (ScorePanel) this.add(new ScorePanel(this));
            panelScore.setBounds(bounds);
        }

        private void initPlayPanel() {
            panelGame = (GamePanel) this.add(new GamePanel(this));
            Rectangle bounds = getBounds();
            
            Insets thisBorder;
            if (this.getBorder()!=null) {
                thisBorder=this.getBorder().getBorderInsets(this);
                if (thisBorder!=null) {
                    bounds.x+=thisBorder.left;
                    bounds.y+=thisBorder.top+panelScoreH;
                    bounds.width+=-(bounds.x+thisBorder.right);
                    bounds.height+=-(bounds.y+thisBorder.bottom);
                    
                }
            }
            
            panelGame.setBounds(bounds);
        }

        @Override
        public void scoreChanged(TeamScored teamScored, int score) {
            switch (teamScored) {
                    case Left:
                        panelScore.setScoreLeft(score);
                        break;
                    case Right:
                        panelScore.setScoreRight(score);
                        break;
                }
        }

        protected class ScorePanel extends JPanel {

            DisplayPanel _parent;
            private Font font;
            int scoreLeft=0;
            int scoreRight=0;
            
            public int getScoreLeft() {
                return scoreLeft;
            }
            public void setScoreLeft(int value) {
                scoreLeft=value;
                this.repaint();
            }
            
            public int getScoreRight() {
                return scoreRight;
            }
            public void setScoreRight(int value) {
                scoreRight = value;
                this.repaint();
            }

            public ScorePanel(DisplayPanel parent) {
                this._parent = parent;
                font = new Font("TimesRoman", Font.PLAIN, 20);
            }
            
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                try {
                        paintScores(g, Color.BLACK);
                    } catch (NullPointerException ex) {
                        if (ex != null) {

                        }
                    }
            }
            private void paintScores(Graphics g, Color color) {
                if (g != null) {
                    Color oldColor = oldColor = g.getColor();
                    if (color != null) {
                        g.setColor(color);
                    }
                    
                    int marginLeft=5;
                    int marginTop=25;
                    int marginRight=15;

                    g.setFont(font);
                    g.setColor(color);
                    String leftScore = String.valueOf(getScoreLeft());
                    String rightScore = String.valueOf(getScoreRight());
                    
                    g.drawString(leftScore, marginLeft, marginTop);
                    g.drawString(rightScore, this.getWidth() - (marginRight+g.getFontMetrics().stringWidth(leftScore)), marginTop);

                    g.setColor(oldColor);
                }
            }
        }

        protected class GamePanel extends JPanel implements GameDisplay {

            GameDisplay _parent;
            private Point mouseLoc;
            private PlayField field;
            private boolean _IsInit = false;

            public Point getMouseLoc() {
                if (mouseLoc != null) {
                    return (Point) mouseLoc.clone();
                }

                return null;
            }

            public void setMouseLoc(Point value) {
                if (value != null) {
                    mouseLoc = value;
                    if (field != null) {
                        Paddle paddle = (Paddle) field.getPaddles().get(0);
                        Point paddlePos = paddle.getPosition();
                        paddle.setPosition(paddlePos.getLocation().x, mouseLoc.y);
                        this.repaint();
                    }
                }
            }

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (field != null) {
                    try {
                        ArrayList<? extends PongObject> paddles = field.getPaddles();
                        ArrayList<? extends PongObject> balls = field.getBalls();

                        paintPaddles(g, paddles);
                        paintBalls(g, balls);
                    } catch (NullPointerException ex) {
                        if (ex != null) {

                        }
                    }
                }
            }

            private void paintPongObject(Graphics g, PongObject object, ObjectShape shape) {
                if (g != null && object != null && shape != ObjectShape.None) {
                    g.setColor(object.getColor());
                    switch (shape) {
                        case Rect:
                            g.fill3DRect(object.getBounds().x, object.getBounds().y, object.getBounds().width, object.getBounds().height, true);
                            break;
                        case Oval:
                            g.fillOval(object.getBounds().x, object.getBounds().y, object.getBounds().width, object.getBounds().height);
                            break;
                    }
                }
            }

            private void paintPaddles(Graphics g, ArrayList<? extends PongObject> objects) {
                if (g != null) {
                    for (PongObject item : objects) {
                        paintPongObject(g, item, ObjectShape.Rect);
                    }
                }
            }

            private void paintBalls(Graphics g, ArrayList<? extends PongObject> objects) {
                if (g != null) {
                    for (PongObject item : objects) {
                        paintPongObject(g, item, ObjectShape.Oval);
                    }
                }
            }
        
            public void addBall() {
                field.createBall();
            }

            public void tick() {
                field.nextFrame();
                this.repaint();
            }

            public GamePanel(GameDisplay parent) {
                this._parent = parent;

                this.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        init();
                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {
                        init();
                    }

                    @Override
                    public void componentShown(ComponentEvent e) {
                        //init();
                    }

                    @Override
                    public void componentHidden(ComponentEvent e) {
                        //init();
                    }

                });
            }

            public void init() {
                if (!_IsInit) {
                    _IsInit = true;
                    field = new PlayField(this);
                    
                }
            }

            @Override
            public void scoreChanged(TeamScored teamScored, int score) {
                _parent.scoreChanged(teamScored,score);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonStart = new javax.swing.JButton();
        panelPong = new DisplayPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setText("Project 3 Pong");

        buttonStart.setLabel("Start");
        buttonStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonStartMouseClicked(evt);
            }
        });

        panelPong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelPong.setPreferredSize(new java.awt.Dimension(1024, 768));
        panelPong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panelPongMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout panelPongLayout = new javax.swing.GroupLayout(panelPong);
        panelPong.setLayout(panelPongLayout);
        panelPongLayout.setHorizontalGroup(
            panelPongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelPongLayout.setVerticalGroup(
            panelPongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPong, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonStart))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 530, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPong, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonStart)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStartMouseClicked
        startGame();
    }//GEN-LAST:event_buttonStartMouseClicked

    private void panelPongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPongMouseMoved
        ((DisplayPanel) panelPong).setMouseLoc(evt.getPoint());
    }//GEN-LAST:event_panelPongMouseMoved

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePong().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelPong;
    // End of variables declaration//GEN-END:variables
}
