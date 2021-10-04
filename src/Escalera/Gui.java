package Escalera;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.GridBagConstraints;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
//import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;

public class Gui extends JFrame {
	private JLabel labelDie;
	private JLabel firstPlayer;
	private JLabel secondPlayer;
	private JLabel thirdPlayer;
	private JButton rollDie;
	private Listener listener;
	private JFrame frame = this;
	private Controller controller;
	private MatrizMaker matrizMaker;
	private GridBagConstraints constraints;
	private JPanel panelMatriz = new JPanel(new FlowLayout());
	private JPanel panelInfo = new JPanel(new GridBagLayout());
	
	public Gui() {
		initGui();
		this.pack();
		this.setVisible(true);
        this.setSize(new Dimension(650, 420));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Escaleras y serpientes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initGui() {
		this.getContentPane().setLayout(new BorderLayout());
		panelInfo.setPreferredSize(new Dimension(650, 240));
		panelMatriz.setPreferredSize(new Dimension(650, 180));
		matrizMaker = new MatrizMaker(frame, panelMatriz, 10, 10, 1, true);
		constraints = new GridBagConstraints();
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
		
		add(panelInfo, BorderLayout.PAGE_END);
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
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			if(eventAction.getSource() == rollDie) {
				ArrayList<Integer> newPosition = controller.gameTurn();
				labelDie.setText("Cara actual: "+newPosition.get(0));
				matrizMaker = new MatrizMaker(frame, panelMatriz, 10, 10, newPosition.get(1), false);
			}
		}
	}
}