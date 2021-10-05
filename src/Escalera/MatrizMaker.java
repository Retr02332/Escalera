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

    public MatrizMaker(final int dimension, final int faceDie, final int currentPos, int turn) {
        this.filas = dimension;
        this.columnas = dimension;
        this.gridLayout = new GridLayout(filas, columnas);
        super.setLayout(gridLayout);

        this.fillMatriz(faceDie, currentPos, turn);
    }

    private void fillMatriz(final int faceDie, final int currentPos, int turn) {
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
                lightBox(count, currentPos, turn, panel);
                count++;
                
                if(f == 1) {
                     jLabel.setText(String.valueOf(count2));
                     panel.setBackground(Color.lightGray.brighter());
                     lightBox(count2, currentPos, turn, panel);
                     count2--;
                 } else if(f == 3) {
                     jLabel.setText(String.valueOf(count3));
                     panel.setBackground(Color.lightGray.brighter());
                     lightBox(count3, currentPos, turn, panel);
                     count3--;
                 } else if(f == 5) {
                     jLabel.setText(String.valueOf(count4));
                     panel.setBackground(Color.lightGray.brighter());
                     lightBox(count4, currentPos, turn, panel);
                     count4--;
                 } else if(f == 7) {
                     jLabel.setText(String.valueOf(count5));
                     panel.setBackground(Color.lightGray.brighter());
                     lightBox(count5, currentPos, turn, panel);
                     count5--;
                 } else if(f == 9) {
                     jLabel.setText(String.valueOf(count6));
                     panel.setBackground(Color.lightGray.brighter());
                     lightBox(count6, currentPos, turn, panel);
                     count6--;
                 }
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
    
    private void lightBox(final int count, final int currentPos, int turn, JPanel panel) {
    	if(count == currentPos) {
    		switch(turn) {
    		    case 0:
    		    	panel.setBackground(Color.blue);
    		    	break;
    		    case 1:
    		    	panel.setBackground(Color.green);
    		    	break;
    		    case 2:
    		    	panel.setBackground(Color.red);
    		    	break;
    		}
    	}
    }
}