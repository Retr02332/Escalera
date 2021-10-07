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
 * 
 * @author retr02332
 * Clase utilizada para realizar todas las operaciónes de despliegue y personalización del tablero de juego
 *
 */
public class MatrizMaker extends JPanel {
    private final int filas;
    private final int columnas;
    private GridLayout gridLayout;
    private static final String SNAKE = "src/img/snake.png";
    private static final String LADDER = "src/img/ladder.png";

    /**
     * Constructor especialmente diseñado para crear el tablero de juego bajo las condiciones especificadas
     * 
     * @param dimension, tamaño de la matriz
     * @param faceDie, cara del dado resultante despues de girarlo
     * @param currentPos, posición actual del jugador
     * @param player, objeto que representa al jugador actual
     */
    public MatrizMaker(final int dimension, final int faceDie, final int currentPos, final Player player) {
        this.filas = dimension;
        this.columnas = dimension;
        this.gridLayout = new GridLayout(filas, columnas);
        super.setLayout(gridLayout);

        fillMatriz(faceDie, currentPos, player);
    }
    
    /**
     * Función para rellenar la matriz
     * 
     * @param faceDie, La cara del dado resultante despues de girarlo
     * @param currentPos, Posición actual del jugador
     * @param player, Objeto player del jugador actual
     */
    private void fillMatriz(final int faceDie, final int currentPos, final Player player) {
        List<Integer> counters = Arrays.asList(1, 20, 40, 60, 80, 100);

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                final JLabel jLabel = new JLabel(String.valueOf(counters.get(0)));
                final JPanel panel = alignJLabel(jLabel);
                lightBox(counters.get(0), currentPos, panel, player);
                counters.set(0, counters.get(0)+1);
                
                switch(f) {
                    case 1:
                    	injectImg(jLabel, counters.get(1));
                    	jLabel.setText(String.valueOf(counters.get(1)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(1), currentPos, panel, player);
                        counters.set(1, counters.get(1)-1);
                        break;
                        
                    case 3:
                    	injectImg(jLabel, counters.get(2));
                    	jLabel.setText(String.valueOf(counters.get(2)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(2), currentPos, panel, player);
                        counters.set(2, counters.get(2)-1);
                        break;
                    
                    case 5:
                    	injectImg(jLabel, counters.get(3));
                    	jLabel.setText(String.valueOf(counters.get(3)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(3), currentPos, panel, player);
                        counters.set(3, counters.get(3)-1);
                        break;
                        
                    case 7:
                    	injectImg(jLabel, counters.get(4));
                    	jLabel.setText(String.valueOf(counters.get(4)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(4), currentPos, panel, player);
                        counters.set(4, counters.get(4)-1);
                        break;
                    
                    case 9:
                    	injectImg(jLabel, counters.get(5));
                    	jLabel.setText(String.valueOf(counters.get(5)));
                        panel.setBackground(Color.lightGray.brighter());
                        lightBox(counters.get(5), currentPos, panel, player);
                        counters.set(5, counters.get(5)-1);
                        break;
                }
                super.add(panel, c);
            }
        }
    }
    
    /**
     * Función para alinear la numeración en la casilla del tablero
     * 
     * @param jLabel, contiene la numeración de la casilla
     * 
     * @return un panel que contiene la casilla con su debida numeración orientada
     */
    private static JPanel alignJLabel(final JLabel jLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(70, 60));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(jLabel, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Función para simular el movimiento del jugador durante la partida
     * 
     * @param count, numero de casilla actual
     * @param currentPos, posición actual del jugador
     * @param panel, panel que representa las casillas del tablero
     * @param player, representa al jugador actual
     */
    private void lightBox(final int count, int currentPos, JPanel panel, final Player player) {
        Logger.log("Current Pos: " + count);
        if(currentPos > 100) {currentPos = 100; player.move(100);}
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
    
    /**
     * Función para inyectar las imagenes de escaleras y serpientes en las casillas correspondientes
     * 
     * @param jLabel, representa la imagen a inyectar
     * @param count, representa la numeración de la casilla en cuestión
     */
    private void injectImg(JLabel jLabel, int count) {
    	if(Controller.STAIRS.containsKey(count) || Controller.STAIRS.containsValue(count)) {
            jLabel.setIcon(getIcon(LADDER));
        }
    	else if(Controller.SNAKES.containsKey(count) || Controller.SNAKES.containsValue(count)) {
    		jLabel.setIcon(getIcon(SNAKE));
    	}
    }
    
    /**
     * Función para obtener la imagen a utilizar
     * 
     * @param iconName, ruta en donde se encuentra la imagen
     * 
     * @return, la imagen previamente solicitada
     */
    private static ImageIcon getIcon(final String iconName) {
        return Objects.requireNonNull(new ImageIcon(iconName), "El icono no se encuentra");
    }
}