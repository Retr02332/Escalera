package Escalera;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.GridBagConstraints;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import java.util.ArrayList;

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
	private Timer delayPlayerDOWN;
	private JFrame jframe = this;
	private Controller controller;
	private MatrizMaker matrizMaker;
	private GridBagConstraints constraints;
	private JPanel panelInfo = new JPanel(new GridBagLayout());
	
	public Gui() {
		initGui();
		this.pack();
		this.setVisible(true);
        this.setSize(new Dimension(750, 750));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Escaleras y serpientes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initGui() {
		matrizMaker = new MatrizMaker(10, 0, 1, 0);
		panelInfo.setPreferredSize(new Dimension(750, 115));
		controller = new Controller();
		listener = new Listener();
		
		rollDie = new JButton("Tirar dado");
		rollDie.addActionListener(listener);
		constraints = makeConstraints(1, 1, 1, 1, 0, GridBagConstraints.NONE);
		panelInfo.add(rollDie, constraints);
		
		labelDie = new JLabel("Cara actual: ");
		constraints = makeConstraints(1, 2, 1, 1, 0, GridBagConstraints.NONE);
		panelInfo.add(labelDie, constraints);
		
		firstPlayer = new JLabel("Jugador 1: Azul");
		constraints = makeConstraints(1, 3, 1, 1, 0, GridBagConstraints.NONE);
		panelInfo.add(firstPlayer, constraints);
		
		secondPlayer = new JLabel("Jugador 2: Verde");
		constraints = makeConstraints(1, 4, 1, 1, 0, GridBagConstraints.NONE);
		panelInfo.add(secondPlayer, constraints);
		
		thirdPlayer = new JLabel("Jugador 3: Rojo");
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
		constraints.gridwidth  = gridwidth;
		constraints.gridheight = gridheight;
		constraints.ipady = ipady;
		constraints.fill = fill;
		
		return constraints;
	}
	
	private void movePlayer() {
		ArrayList<Integer> newPosition = controller.gameTurn();
		labelDie.setText("Cara actual: " + newPosition.get(0));
		
		// Logica de escaleras y serpientes
		if(controller.stair(newPosition.get(1)) != -1) {
			up(newPosition);
		} else if(controller.snake(newPosition.get(1)) != -1) {
			down(newPosition);
		} else {
			go(newPosition);
		}
		// Simulación de los jugadores 2 y 3
		controller.nextTurn();
		if(controller.currentTurn() != 0) {
			movePlayer();
		}
	}
	
	private void up(ArrayList<Integer> newPosition) {
		matrizMaker = new MatrizMaker(10, newPosition.get(0), newPosition.get(1), controller.currentTurn());
		paintBox();
		
		ActionListener upListener = new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
		    	playerUP(newPosition);
		    	delayPlayerUP.stop();
		    }
		};
		delayPlayerUP = new Timer(1000, upListener);
		delayPlayerUP.start();
	}
	
	private void down(ArrayList<Integer> newPosition) {
		matrizMaker = new MatrizMaker(10, newPosition.get(0), newPosition.get(1), controller.currentTurn());
		paintBox();
		
		ActionListener downListener = new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
		    	playerDOWN(newPosition);
		    	delayPlayerDOWN.stop();
		    }
		};
		delayPlayerDOWN = new Timer(1000, downListener);
		delayPlayerDOWN.start();
	}
	
	private void playerUP(ArrayList<Integer> newPosition) {
		controller.movePlayerBackend(controller.stair(newPosition.get(1)));
        matrizMaker = new MatrizMaker(10, newPosition.get(0), controller.stair(newPosition.get(1)), controller.currentTurn());
        paintBox();
	}
	
	private void playerDOWN(ArrayList<Integer> newPosition) {
		controller.movePlayerBackend(controller.snake(newPosition.get(1)));
		matrizMaker = new MatrizMaker(10, newPosition.get(0), controller.snake(newPosition.get(1)), controller.currentTurn());
        paintBox();
	}
	
	private void go(ArrayList<Integer> newPosition) {
		matrizMaker = new MatrizMaker(10, newPosition.get(0), newPosition.get(1), controller.currentTurn());
		paintBox();
	}
	
	private void clearFrame() {
		jframe.getContentPane().removeAll();
		jframe.revalidate();
        jframe.repaint();
	}
	
	private void paintBox() {
		clearFrame();
		updateGUI();
        validateUpdateFrame();
	}
	
	private void updateGUI() {
		add(matrizMaker, BorderLayout.NORTH);
        add(new JSeparator(), BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
	}
	
	private void validateUpdateFrame() {
		jframe.validate();
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			if(eventAction.getSource() == rollDie) {
				if(controller.currentTurn() == 0) {
					movePlayer();
				}
			}
		}
	}
}