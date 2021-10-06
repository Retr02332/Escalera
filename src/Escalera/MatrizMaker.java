package Escalera;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Objects;
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

        this.fillMatriz(faceDie, currentPos, player);
    }

    private void fillMatriz(final int faceDie, final int currentPos, final Player player) {
        int count = 1;
        int count2 = 20;
        int count3 = 40;
        int count4 = 60;
        int count5 = 80;
        int count6 = 100;

        final int[][] matriz = new int[filas][columnas];

        for (int f = 0; f < matriz.length; f++) {
            for (int c = 0; c < matriz[f].length; c++) {
                final JLabel jLabel = new JLabel(String.valueOf(count));
                final JPanel panel = centerJLabel(jLabel);
                lightBox(count, currentPos, panel, player);

                /*if(Controller.STAIRS.containsValue(count)) {
                    jLabel.setIcon(getIcon(LADDER));
                }

                if(Controller.STAIRS.containsKey(count)) {
                    jLabel.setIcon(getIcon(LADDER));
                }

                if(Controller.SNAKES.containsValue(count)) {
                    jLabel.setIcon(getIcon(SNAKE));
                }
                if(Controller.SNAKES.containsKey(count)) {
                    jLabel.setIcon(getIcon(SNAKE));
                }*/
                count++;

                if (f == 1) {
                    jLabel.setText(String.valueOf(count2));
                    panel.setBackground(Color.lightGray.brighter());
                    lightBox(count2, currentPos, panel, player);
                    count2--;
                } else if (f == 3) {
                    jLabel.setText(String.valueOf(count3));
                    panel.setBackground(Color.lightGray.brighter());
                    lightBox(count3, currentPos, panel, player);
                    count3--;
                } else if (f == 5) {
                    jLabel.setText(String.valueOf(count4));
                    panel.setBackground(Color.lightGray.brighter());
                    lightBox(count4, currentPos, panel, player);
                    count4--;
                } else if (f == 7) {
                    jLabel.setText(String.valueOf(count5));
                    panel.setBackground(Color.lightGray.brighter());
                    lightBox(count5, currentPos, panel, player);
                    count5--;
                } else if (f == 9) {
                    jLabel.setText(String.valueOf(count6));
                    panel.setBackground(Color.lightGray.brighter());
                    lightBox(count6, currentPos, panel, player);
                    count6--;
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