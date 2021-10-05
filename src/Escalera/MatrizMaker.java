package Escalera;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Make a GridLayout
 */

public class MatrizMaker extends JPanel {
    private final int filas;
    private final int columnas;
    private GridLayout gridLayout;

    public MatrizMaker(final int dimension, final int currentPos) {
        this.filas = dimension;
        this.columnas = dimension;
        this.gridLayout = new GridLayout(filas, columnas);
        super.setLayout(gridLayout);

        this.fillMatriz(currentPos);
    }

    private void fillMatriz(final int currentPos) {
        int count = 100;
        for(int f=0; f<filas; f++) {
            for(int c=0; c<columnas; c++) {
                final JPanel panel = centerJLabel(new JLabel(String.valueOf(count)));
                if(count == currentPos) {
                	panel.setBackground(Color.blue);
                }
                count--;
                super.add(panel);
            }
        }
    }

    public static JPanel centerJLabel(final JLabel jLabel) {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        //Para centrar el JLabel dentro del JPanel
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(jLabel, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(45, 60));
        return panel;
    }
}