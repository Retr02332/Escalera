package Escalera;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gui extends JFrame {
	private Player player;
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

    public void initGui() {
        this.controller = new Controller();
        this.player = controller.getPlayer();
        this.player.setName(Player.PLAYER_1);
        this.matrizMaker = new MatrizMaker(10, 0, 1, player);
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

    public GridBagConstraints makeConstraints(int gridx, int gridy, int gridwidth, int gridheight, int ipady, int fill) {

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
            up(newPosition, this.player);
        } else if (controller.snake(newPosition.get(1)) != -1) {
            down(newPosition, this.player);
        } else {
            go(newPosition, this.player);
        }
        //Habilitando el turno del siguiente jugador
        cpuTurn2 = true;
    }

    private void movePlayer2() {
        List<Integer> newPosition = controller.getTurnPlayer2();
        labelDie.setText("Cara actual: " + newPosition.get(0));
        final Player player2 = controller.getPlayer2();
        // Logica de escaleras y serpientes
        if (controller.stair(newPosition.get(1)) != -1) {
            up(newPosition, player2);
        } else if (controller.snake(newPosition.get(1)) != -1) {
            down(newPosition, player2);
        } else {
            go(newPosition, player2);
        }
        //Habilitando el turno del siguiente jugador
        cpuTurn3 = true;
    }

    private void movePlayer3() {
        List<Integer> newPosition = controller.getTurnPlayer3();
        labelDie.setText("Cara actual: " + newPosition.get(0));
        final Player player3 = controller.getPlayer3();
        // Logica de escaleras y serpientes
        if (controller.stair(newPosition.get(1)) != -1) {
            up(newPosition, player3);
        } else if (controller.snake(newPosition.get(1)) != -1) {
            down(newPosition, player3);
        } else {
            go(newPosition, player3);
        }
        //Habilitamos el boton del Jugador
        rollDie.setEnabled(true);
    }

    private void up(List<Integer> newPosition, Player player) {
        matrizMaker = new MatrizMaker(10, newPosition.get(0), newPosition.get(1), player);
        paintBox();

        ActionListener upListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                synchronized (this) {
                    playerUP(newPosition, player);
                    delayPlayerUP.stop();
                }
            }
        };
        delayPlayerUP = new Timer(1000, upListener);
        delayPlayerUP.start();
    }

    private void down(List<Integer> newPosition, Player player) {
        matrizMaker = new MatrizMaker(10, newPosition.get(0), newPosition.get(1), player);
        paintBox();

        ActionListener downListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                synchronized (this) {
                    playerDOWN(newPosition, player);
                    delayPlayerDOWN.stop();
                }
            }
        };
        delayPlayerDOWN = new Timer(1000, downListener);
        delayPlayerDOWN.start();
    }

    private void playerUP(List<Integer> newPosition, Player player) {
        controller.movePlayerBackend(controller.stair(newPosition.get(1)));
        matrizMaker = new MatrizMaker(10, newPosition.get(0), controller.stair(newPosition.get(1)), player);
        paintBox();
    }

    private void playerDOWN(List<Integer> newPosition, Player player) {
        controller.movePlayerBackend(controller.snake(newPosition.get(1)));
        matrizMaker = new MatrizMaker(10, newPosition.get(0), controller.snake(newPosition.get(1)), player);
        paintBox();
    }

    private void go(List<Integer> newPosition, Player player) {
        matrizMaker = new MatrizMaker(10, newPosition.get(0), newPosition.get(1), player);
        paintBox();
    }

    private void clearFrame() {
        synchronized (this) {
            jframe.getContentPane().removeAll();
            this.updateGUI();
            jframe.revalidate();
            jframe.repaint();
        }

    }

    private void paintBox() {
        synchronized (this) {
            clearFrame();
            updateGUI();
            validateUpdateFrame();
        }
    }

    private void updateGUI() {
        add(matrizMaker, BorderLayout.NORTH);
        add(new JSeparator(), BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
    }

    private void validateUpdateFrame() {
        jframe.validate();
    }

    //Turno del CPU
    private boolean cpuTurn2 = false;
    private boolean cpuTurn3 = false;

    public boolean isCpuTurn2() {
        return cpuTurn2;
    }

    public boolean isCpuTurn3() {
        return cpuTurn3;
    }

    //Para administrar mejor RAM y CPU sin crear demasiados Hilos.
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent eventAction) {
            if (eventAction.getSource() == rollDie) {
                movePlayer();
                rollDie.setEnabled(false);
                //sleep(1000);
                CompletableFuture.runAsync(() -> {
                    sleep(1000);
                    if (isCpuTurn2()) {
                        movePlayer2();
                    }
                    sleep(1000);
                    if (isCpuTurn3()) {
                        movePlayer3();
                    }
                    sleep(1000);
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