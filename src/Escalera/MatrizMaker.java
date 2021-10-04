package Escalera;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class MatrizMaker extends GridLayout {
	private final JPanel panelMatriz;
	private final JFrame jFrame;
    private final int columnas;
    private final int filas;

    public MatrizMaker(final JFrame jFrame, final JPanel panelMatriz, final int filas, final int columnas, final int currentPos, boolean init) {
        super(filas, columnas);
        this.filas = filas;
        this.columnas = columnas;
        this.panelMatriz = panelMatriz;
        this.jFrame = jFrame;

        this.matriz(currentPos, init);
    }

    private void matriz(final int currentPos, boolean init) {
        fillMatriz(currentPos, init);
    }

    private void fillMatriz(final int currentPos, boolean init) {
        int count = 100;
        removeBoxes();
        for(int f=0; f<filas; f++) {
            for(int c=0; c<columnas; c++) {
                JPanel panel = centerJLabel(new JLabel(String.valueOf(count)));
                if(count == currentPos) {
                	panel.setBackground(Color.blue);
                }
                this.panelMatriz.add(panel);
                count--;
            }
        }
        if(init) {this.jFrame.add(this.panelMatriz, BorderLayout.PAGE_START);}
    }

    private JPanel centerJLabel(final JLabel jLabel) {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(jLabel, BorderLayout.CENTER);
        return panel;
    }
    
    private void removeBoxes() {
    	this.panelMatriz.removeAll();
        this.panelMatriz.revalidate();
        this.panelMatriz.repaint();
    }
}
