package Escalera;

import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gui extends JFrame {
    private JLabel labelDie;
    private JButton rollDie;
    private Listener listener;
    private JLabel firstPlayer;
    private JLabel thirdPlayer;
    private JLabel secondPlayer;
    private Timer delayPlayerUP;
    private JFrame jframe = this;
    private Timer delayPlayerDOWN;
    private Controller controller;
    private MatrizMaker matrizMaker;
    private GridBagConstraints constraints;
    private JPanel panelInfo = new JPanel(new GridBagLayout());
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public Gui() {
        initGui();
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(900, 750));
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Escaleras y serpientes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGui() {
        controller = new Controller();
        controller.setNamePlayers();
        matrizMaker = new MatrizMaker(10, 0, 1, controller.getPlayer());
        panelInfo.setPreferredSize(new Dimension(750, 115));
        listener = new Listener();

        rollDie = new JButton("Tirar dado");
        rollDie.addActionListener(listener);
        constraints = makeConstraints(1, 1, 1, 1, 0, GridBagConstraints.NONE);
        panelInfo.add(rollDie, constraints);

        labelDie = new JLabel("Cara actual: ");
        labelDie.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        constraints = makeConstraints(1, 2, 1, 1, 0, GridBagConstraints.NONE);
        panelInfo.add(labelDie, constraints);

        firstPlayer = new JLabel("Jugador 1: Azul");
        firstPlayer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        firstPlayer.setForeground(Color.BLUE);
        constraints = makeConstraints(1, 3, 1, 1, 0, GridBagConstraints.NONE);
        panelInfo.add(firstPlayer, constraints);

        secondPlayer = new JLabel("Jugador 2: Verde");
        secondPlayer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        secondPlayer.setForeground(Color.GREEN);
        constraints = makeConstraints(1, 4, 1, 1, 0, GridBagConstraints.NONE);
        panelInfo.add(secondPlayer, constraints);

        thirdPlayer = new JLabel("Jugador 3: Rojo");
        thirdPlayer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        thirdPlayer.setForeground(Color.RED);
        constraints = makeConstraints(1, 5, 1, 1, 0, GridBagConstraints.NONE);
        panelInfo.add(thirdPlayer, constraints);

        add(this.matrizMaker, BorderLayout.NORTH);
        add(new JSeparator(), BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
    }

    private GridBagConstraints makeConstraints(int gridx, int gridy, int gridwidth, int gridheight, int ipady, int fill) {

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.ipady = ipady;
        constraints.fill = fill;

        return constraints;
    }

    private void movePlayer() {
        List<Integer> newPosition = controller.gameTurn();
        labelDie.setText("Cara actual: " + newPosition.get(0));

        // Logica de escaleras y serpientes
        if (controller.stair(newPosition.get(1)) != -1) {
            up(newPosition);
        } else if (controller.snake(newPosition.get(1)) != -1) {
            down(newPosition);
        } else {
            go(newPosition);
        }
        displayWinner();
    }

    private void up(List<Integer> newPosition) {
        go(newPosition);
        
        ActionListener upListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                synchronized (this) {
                    playerUP(newPosition);
                    delayPlayerUP.stop();
                }
            }
        };
        delayPlayerUP = new Timer(500, upListener);
        delayPlayerUP.start();
    }

    private void down(List<Integer> newPosition) {
    	go(newPosition);

        ActionListener downListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                synchronized (this) {
                    playerDOWN(newPosition);
                    delayPlayerDOWN.stop();
                }
            }
        };
        delayPlayerDOWN = new Timer(500, downListener);
        delayPlayerDOWN.start();
    }
    
    private void go(List<Integer> newPosition) {
        matrizMaker = new MatrizMaker(10, newPosition.get(0), newPosition.get(1), controller.getPlayer());
        paintBox();
    }

    private void playerUP(List<Integer> newPosition) {
        controller.movePlayerBackend(controller.stair(newPosition.get(1)));
        matrizMaker = new MatrizMaker(10, newPosition.get(0), controller.stair(newPosition.get(1)), controller.getPlayer());
        paintBox();
    }

    private void playerDOWN(List<Integer> newPosition) {
        controller.movePlayerBackend(controller.snake(newPosition.get(1)));
        matrizMaker = new MatrizMaker(10, newPosition.get(0), controller.snake(newPosition.get(1)), controller.getPlayer());
        paintBox();
    }

    private void paintBox() {
        synchronized (this) {
        	updateFrame();
            validateUpdateFrame();
        }
    }
    
    private void updateFrame() {
        synchronized (this) {
            jframe.getContentPane().removeAll();
            updateGUI();
            jframe.revalidate();
            jframe.repaint();
        }
    }

    private void validateUpdateFrame() {
        jframe.validate();
    }
    
    private void updateGUI() {
        add(matrizMaker, BorderLayout.NORTH);
        add(new JSeparator(), BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
    }
    
    private void displayWinner() {
    	if(controller.getPlayer().getCurrentPosition() == 100) {
    		JOptionPane.showMessageDialog(
					null, 
					controller.getMessageWinner(), 
					"Ganador del juego", 
					JOptionPane.INFORMATION_MESSAGE
			);
    	}
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent eventAction) {
            if (eventAction.getSource() == rollDie) {
                movePlayer();
                rollDie.setEnabled(false);
                CompletableFuture.runAsync(() -> {
                    sleep(2000);
                    controller.nextTurn();
                    if (controller.getTurn() == 1) {
                        movePlayer();
                        sleep(2000);
                        controller.nextTurn();
                    }
                    if (controller.getTurn() == 2) {
                        movePlayer();
                        sleep(1000);
                        rollDie.setEnabled(true);
                        controller.nextTurn();
                    }
                },executorService);
            }
        }
    }

    public void sleep(final long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}