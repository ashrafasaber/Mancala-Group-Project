
import java.awt.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The class that satisfies the view part of MVC for our Mancala program
 *
 * @author Team SRS
 */
public class MancalaView extends JComponent implements ChangeListener  {
    
    private JFrame mainFrame;
    private JPanel buttonPanel;
    private JPanel quitPanel;
    private JPanel startScreen;
    private JPanel pitPanel;
    private JPanel gamePanel;
    private JButton endTurn;
    private JButton undo;
    private JButton quit;
    private JButton threeBtn;
    private JButton fourBtn;
    private JButton currentTurn;
    private JComboBox<String> styles;
    private JButton startBtn;
    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton leftMancala;
    private JButton rightMancala;
    private JLabel label0;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5 ;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    
    private MancalaModel model;
    
    BoardLayout layout;
    
    
    private int[] stonesInPit;
    
    /**
     * Constructs the view for our Mancala game and interacts with controller to
     * communicate with the model
     */
    public MancalaView(MancalaModel model ) {
        
        this.model=model;
        layout = new BlueLayout();
        
        stonesInPit=new int[14];
        model.attach(this);
        
        endTurn = new JButton("End Turn");
        
        undo = new JButton("Undo");
        
        quit = new JButton("Quit");
        
        quitPanel = new JPanel();
        quitPanel.add(quit);
        
        // Panel with the main buttons on it
        buttonPanel = new JPanel();
        buttonPanel.add(undo);
        buttonPanel.add(endTurn);
        
        // TODO: Mancala Board Panel
        JPanel boardMain = new JPanel();
        boardMain.setLayout(new BoxLayout(boardMain, BoxLayout.X_AXIS));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        
        JPanel panel6 = new JPanel();
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
        
        JPanel rMan = new JPanel();
        rMan.setLayout(new BoxLayout(rMan, BoxLayout.Y_AXIS));
        
        JPanel lMan = new JPanel();
        lMan.setLayout(new BoxLayout(lMan, BoxLayout.Y_AXIS));
        
        label0 = new JLabel("");
        label1 = new JLabel("");
        label2 = new JLabel("");
        label3 = new JLabel("");
        label4 = new JLabel("");
        label5 = new JLabel("");
        label6 = new JLabel("A Score");
        label7 = new JLabel("");
        label8 = new JLabel("");
        label9 = new JLabel("");
        label10 = new JLabel("");
        label11 = new JLabel("");
        label12 = new JLabel("");
        label13 = new JLabel("B Score");
        
        
        button0 = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        leftMancala = new JButton();
        rightMancala = new JButton();
        currentTurn = new JButton();
        
        panel1.add(label12);
        panel1.add(button12);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(button0);
        panel1.add(label0);
        
        panel2.add(label11);
        panel2.add(button11);
        panel2.add(Box.createVerticalStrut(10));
        panel2.add(button1);
        panel2.add(label1);
        
        panel3.add(label10);
        panel3.add(currentTurn);
        panel3.add(button10);
        panel3.add(Box.createVerticalStrut(10));
        panel3.add(button2);
        panel3.add(label2);
        
        panel4.add(label9);
        panel4.add(button9);
        panel4.add(Box.createVerticalStrut(10));
        panel4.add(button3);
        panel4.add(label3);
        
        panel5.add(label8);
        panel5.add(button8);
        panel5.add(Box.createVerticalStrut(10));
        panel5.add(button4);
        panel5.add(label4);
        
        panel6.add(label7);
        panel6.add(button7);
        panel6.add(Box.createVerticalStrut(10));
        panel6.add(button5);
        panel6.add(label5);
        
        lMan.add(label13);
        lMan.add(leftMancala);
        rMan.add(label6);
        rMan.add(rightMancala);
        
        boardMain.add(Box.createHorizontalStrut(25));
        boardMain.add(lMan);
        boardMain.add(Box.createHorizontalStrut(25));
        boardMain.add(panel1);
        boardMain.add(panel2);
        boardMain.add(panel3);
        boardMain.add(panel4);
        boardMain.add(panel5);
        boardMain.add(panel6);
        boardMain.add(rMan);
        boardMain.add(Box.createHorizontalStrut(25));
        
        
        // Puts all the panels on one game panel
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(quitPanel, BorderLayout.NORTH);
        gamePanel.add(boardMain, BorderLayout.CENTER);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // createStartScreen();
        
        startScreen = new JPanel();
        // startScreen.setBackground(Color.BLUE);
        startScreen.setLayout(new GridBagLayout());
        GridBagConstraints gbcS = new GridBagConstraints();
        gbcS.gridx = 0;
        gbcS.gridy = 0;
        gbcS.insets = new Insets(25, 25, 25, 25);
        
        // For Stone number selection
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcT = new GridBagConstraints();
        gbcT.gridx = 0;
        gbcT.gridy = 0;
        gbcT.insets = new Insets(2, 2, 2, 2);
        JPanel topMessagePane = new JPanel();
        JLabel stonesMessage = new JLabel("Select number of stones per pit");
        stonesMessage.setFocusable(false);
        topMessagePane.add(stonesMessage);
        JPanel stoneBtnPanel = new JPanel();
        threeBtn = new JButton("3");
        threeBtn.setPreferredSize(new Dimension(65, 45));
        fourBtn = new JButton("4");
        fourBtn.setPreferredSize(new Dimension(65, 45));
        stoneBtnPanel.add(threeBtn);
        stoneBtnPanel.add(fourBtn);
        topPanel.add(topMessagePane, gbcT);
        gbcT.gridy++;
        topPanel.add(stoneBtnPanel, gbcT);
        
        // For Style selection
        
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcM = new GridBagConstraints();
        gbcM.gridx = 0;
        gbcM.gridy = 0;
        gbcM.insets = new Insets(2, 2, 2, 2);
        JPanel middleMessagePane = new JPanel();
        JLabel styleMessage = new JLabel("Select board style");
        styleMessage.setFocusable(false);
        middleMessagePane.add(styleMessage);
        JPanel comboPane = new JPanel();
        styles = new JComboBox<String>();
        styles.addItem("Red");
        styles.addItem("Blue");
        styles.addItem("Green");
        styles.addItem("Pink");
        comboPane.add(styles);
        middlePanel.add(middleMessagePane, gbcM);
        gbcM.gridy++;
        middlePanel.add(comboPane, gbcM);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        startBtn = new JButton("Play");
        bottomPanel.add(startBtn);
        
        
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.undoGamePlay();
                model.updateListeners();
            }
        });
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
                model.setGameStatus("Game Started");
                if(styles.getSelectedIndex()==0){
                    setBoardLayout(new RedLayout());
                    boardMain.setBackground(layout.panelColors());
                    rMan.setBackground(layout.panelColors());
                    lMan.setBackground(layout.panelColors());
                    panel1.setBackground(layout.panelColors());
                    panel2.setBackground(layout.panelColors());
                    panel3.setBackground(layout.panelColors());
                    panel4.setBackground(layout.panelColors());
                    panel5.setBackground(layout.panelColors());
                    panel6.setBackground(layout.panelColors());
                    
                }
                else if(styles.getSelectedIndex()==1){
                    setBoardLayout(new BlueLayout());
                    boardMain.setBackground(layout.panelColors());
                    rMan.setBackground(layout.panelColors());
                    lMan.setBackground(layout.panelColors());
                    panel1.setBackground(layout.panelColors());
                    panel2.setBackground(layout.panelColors());
                    panel3.setBackground(layout.panelColors());
                    panel4.setBackground(layout.panelColors());
                    panel5.setBackground(layout.panelColors());
                    panel6.setBackground(layout.panelColors());
                    
                }else if(styles.getSelectedIndex()==2){
                    setBoardLayout(new GreenLayout());
                    boardMain.setBackground(layout.panelColors());
                    rMan.setBackground(layout.panelColors());
                    lMan.setBackground(layout.panelColors());
                    panel1.setBackground(layout.panelColors());
                    panel2.setBackground(layout.panelColors());
                    panel3.setBackground(layout.panelColors());
                    panel4.setBackground(layout.panelColors());
                    panel5.setBackground(layout.panelColors());
                    panel6.setBackground(layout.panelColors());
                    
                }else if(styles.getSelectedIndex()==3){
                    setBoardLayout(new PinkLayout());
                    
                    boardMain.setBackground(layout.panelColors());
                    rMan.setBackground(layout.panelColors());
                    lMan.setBackground(layout.panelColors());
                    panel1.setBackground(layout.panelColors());
                    panel2.setBackground(layout.panelColors());
                    panel3.setBackground(layout.panelColors());
                    panel4.setBackground(layout.panelColors());
                    panel5.setBackground(layout.panelColors());
                    panel6.setBackground(layout.panelColors());
                }
                
            }
        });
        
        threeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setStones(3);
                
                
            }
        });
        
        fourBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setStones(4);
            }
        });
        
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.undoGamePlay();
                model.updateListeners();
            }
        });
        
        endTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.changeTurn();
                model.updateListeners();
            }
        });
        
        button0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.pitAccessible(0)) {
                    model.distributeStones(0);
                    //	model.updateListeners();
                }
            }
        });
        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(1)) {
                    model.distributeStones(1);
                    //	model.updateListeners();
                }
            }
        });
        
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(2)) {
                    model.distributeStones(2);
                    //	model.updateListeners();
                }
            }
        });
        
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(3)) {
                    model.distributeStones(3);
                    //	model.updateListeners();
                }
            }
        });
        
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(4)) {
                    model.distributeStones(4);
                    //	model.updateListeners();
                }
            }
        });
        
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(5)) {
                    model.distributeStones(5);
                    //	model.updateListeners();
                }
            }
        });
        
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(7)) {
                    model.distributeStones(7);
                    //	model.updateListeners();
                }
            }
        });
        
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(8)) {
                    model.distributeStones(8);
                    //model.updateListeners();
                }
            }
        });
        
        button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(9)) {
                    model.distributeStones(9);
                    //	model.updateListeners();
                }
            }
        });
        
        button10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(10)) {
                    model.distributeStones(10);
                    //model.updateListeners();
                }
            }
        });
        
        button11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(11)) {
                    model.distributeStones(11);
                    //model.updateListeners();
                }
            }
        });
        
        button12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (model.pitAccessible(12)) {
                    model.distributeStones(12);
                    //model.updateListeners();
                }
            }
        });
        
        
        startScreen.add(topPanel, gbcS);
        gbcS.gridy += 3;
        startScreen.add(middlePanel, gbcS);
        gbcS.gridy += 3;
        startScreen.add(bottomPanel, gbcS);
        
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Super Duper Wuper Mancala");
        mainFrame.add(gamePanel);
        mainFrame.pack();
        mainFrame.setSize(800, 500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
        gamePanel.setVisible(false);
        
        mainFrame.add(startScreen);
        
        
    }
    /**
     * This fn starts the game and shifts from startScreen to gameScreen
     */
    public void startGame() {
        startScreen.setVisible(false);
        gamePanel.setVisible(true);
    }
    
    /**
     * This fn gets the desired button
     * @param btn
     * @return
     */
    public JButton getButton(String btn) {
        if (btn.equals("quit")) {
            return quit;
        } else if (btn.equals("play")) {
            return startBtn;
        }else if (btn.equals("threeBtn")) {
            return threeBtn;
        }else if (btn.equals("fourBtn")) {
            return fourBtn;
        }else if (btn.equals("undo")) {
            return undo;
        }else if (btn.equals("endTurn")) {
            return endTurn;
        }else if (btn.equals("button0")) {
            return button0;
        }else if (btn.equals("button1")) {
            return button1;
        }else if (btn.equals("button2")) {
            return button2;
        }else if (btn.equals("button3")) {
            return button3;
        }else if (btn.equals("button4")) {
            return button4;
        }else if (btn.equals("button5")) {
            return button5;
        }else if (btn.equals("button7")) {
            return button7;
        }else if (btn.equals("button8")) {
            return button8;
        }else if (btn.equals("button9")) {
            return button9;
        }else if (btn.equals("button10")) {
            return button10;
        }else if (btn.equals("button11")) {
            return button11;
        }else if (btn.equals("button12")) {
            return button12;
        }
        
        return null;
    }
    /**
     * Gets label0
     * @return
     */
    public JLabel getLabel0() {
        return label0;
    }
    /**
     * Gets label1
     * @return
     */
    public JLabel getLabel1() {
        return label1;
    }
    /**
     * Gets label2
     * @return
     */
    public JLabel getLabel2() {
        return label2;
    }
    /**
     * Gets label3
     * @return
     */
    public JLabel getLabel3() {
        return label3;
    }
    /**
     * Gets label4
     * @return
     */
    public JLabel getLabel4() {
        return label4;
    }
    /**
     * Gets label5
     * @return
     */
    public JLabel getLabel5() {
        return label5;
    }
    /**
     * Gets label6
     * @return
     */
    public JLabel getLabel6() {
        return label6;
    }
    /**
     * Gets label7
     * @return
     */
    public JLabel getLabel7() {
        return label7;
    }
    /**
     * Gets label8
     * @return
     */
    public JLabel getLabel8() {
        return label8;
    }
    /**
     * Gets label9
     * @return
     */
    public JLabel getLabel9() {
        return label9;
    }
    /**
     * Gets label10
     * @return
     */
    public JLabel getLabel10() {
        return label10;
    }
    /**
     * Gets label11
     * @return
     */
    public JLabel getLabel11() {
        return label11;
    }
    /**
     * Gets label12
     * @return
     */
    public JLabel getLabel12() {
        return label12;
    }
    /**
     * Gets label13
     * @return
     */
    public JLabel getLabel13() {
        return label13;
    }
    
    
    
    /**
     * paintComponent fn
     * @return
     */
    public void paintComponent(Graphics g){ 	 
    }
    /**
     * sets the boardlayout for the strategy pattern
     * @return
     */
    public void setBoardLayout(BoardLayout layout){
        this.layout=layout;
    }
    /**
     * stateChanged function, updates the button labels
     * @return
     */
    public void stateChanged(ChangeEvent e) {
        
        stonesInPit=model.getPits();
        
        button0.setText(String.valueOf(stonesInPit[0]));
        button1.setText(String.valueOf(stonesInPit[1]));
        button2.setText(String.valueOf(stonesInPit[2]));
        button3.setText(String.valueOf(stonesInPit[3]));
        button4.setText(String.valueOf(stonesInPit[4]));
        button5.setText(String.valueOf(stonesInPit[5]));
        rightMancala.setText(String.valueOf(stonesInPit[6]));
        button7.setText(String.valueOf(stonesInPit[7]));
        button8.setText(String.valueOf(stonesInPit[8]));
        button9.setText(String.valueOf(stonesInPit[9]));
        button10.setText(String.valueOf(stonesInPit[10]));
        button11.setText(String.valueOf(stonesInPit[11]));
        button12.setText(String.valueOf(stonesInPit[12]));
        leftMancala.setText(String.valueOf(stonesInPit[13]));
        
        currentTurn.setText(String.valueOf(model.getCurrentTurn()));
        
    }
    
    
    
}