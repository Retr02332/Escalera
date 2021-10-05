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
    	int count = 1;
        int count2 = 20;
        int count3 = 40;
        int count4 = 60;
        int count5 = 80;
        int count6 = 100;
        
        for(int f=0; f<filas; f++) {
            for(int c=0; c<columnas; c++) {
            	final JLabel jLabel = new JLabel(String.valueOf(count));
                final JPanel panel = centerJLabel(jLabel);
                
                if(f == 1) {
                    jLabel.setText(String.valueOf(count2--));
                     panel.setBackground(Color.lightGray.brighter());
                 } else if(f == 3) {
                     jLabel.setText(String.valueOf(count3--));
                     panel.setBackground(Color.lightGray.brighter());
                 } else if(f == 5) {
                     jLabel.setText(String.valueOf(count4--));
                     panel.setBackground(Color.lightGray.brighter());
                 } else if(f == 7) {
                     jLabel.setText(String.valueOf(count5--));
                     panel.setBackground(Color.lightGray.brighter());
                 } else if(f == 9) {
                     jLabel.setText(String.valueOf(count6--));
                     panel.setBackground(Color.lightGray.brighter());
                 }
                if(count == currentPos) {
                	panel.setBackground(Color.blue);
                }
                count++;
                super.add(panel, c);
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