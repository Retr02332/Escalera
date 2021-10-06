package Escalera;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Objects;
import java.util.Arrays;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;

/**
 * Make a GridLayout
 */

public class MatrizMaker extends JPanel {
    private final int filas;
    private final int columnas;
    private GridLayout gridLayout;
    private static final String SNAKE = "src/img/snake.png";
    private static final String LADDER = "src/img/ladder.png";

    public MatrizMaker(final int dimension, final int faceDie, final int currentPos, final Player player) {
        this.filas = dimension;
        this.columnas = dimension;
        this.gridLayout = new GridLayout(filas, columnas);
        super.setLayout(gridLayout);

        fillMatriz(faceDie, currentPos, player);
    }

    private void fillMatriz(final int faceDie, final int currentPos, final Player player) {
        List<Integer> counters = Arrays.asList(1, 20, 40, 60, 80, 100); 

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                final JLabel jLabel = new JLabel(String.valueOf(counters.get(0)));
                final JPanel panel = centerJLabel(jLabel);
                lightBox(counters.get(0), currentPos, panel, player);
                counters.set(counters.indexOf(counters.get(0)), counters.get(0)+1);
                
                switch(f) {
                    case 1:
                    	jLabel.setText(String.valueOf(counters.get(1)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(1), currentPos, panel, player);
                        counters.set(counters.indexOf(counters.get(1)), counters.get(1)-1);
                        break;
                        
                    case 3:
                    	jLabel.setText(String.valueOf(counters.get(2)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(2), currentPos, panel, player);
                        counters.set(counters.indexOf(counters.get(2)), counters.get(2)-1);
                        break;
                    
                    case 5:
                    	jLabel.setText(String.valueOf(counters.get(3)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(3), currentPos, panel, player);
                        counters.set(counters.indexOf(counters.get(3)), counters.get(3)-1);
                        break;
                        
                    case 7:
                    	jLabel.setText(String.valueOf(counters.get(4)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(4), currentPos, panel, player);
                        counters.set(counters.indexOf(counters.get(4)), counters.get(4)-1);
                        break;
                    
                    case 9:
                    	jLabel.setText(String.valueOf(counters.get(5)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(5), currentPos, panel, player);
                        counters.set(counters.indexOf(counters.get(5)), counters.get(5)-1);
                        break;
                }
                super.add(panel, c);
            }
        }
    }

    public static JPanel centerJLabel(final JLabel jLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(70, 60));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(jLabel, BorderLayout.CENTER);
        return panel;
    }

    public static ImageIcon getIcon(final String iconName) {
        return Objects.requireNonNull(new ImageIcon(iconName), "El icono no se encuentra");
    }

    private void lightBox(final int count, final int currentPos, JPanel panel, final Player player) {
        Logger.log("Current Post: " + count);
        if (count == currentPos) {
            if(player.getName().equals("player 1")) {
                panel.setBackground(Color.blue);
            } else if(player.getName().equals("player 2")) {
                panel.setBackground(Color.GREEN);
            } else {
                panel.setBackground(Color.RED);
            }
        }
    }
}