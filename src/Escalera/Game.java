package Escalera;

import java.awt.EventQueue;

/**
 * Clase para inciciar el juego
 * @author retr02332
 *
 */
public class Game {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			Gui GUI = new Gui();
		});
	}
}