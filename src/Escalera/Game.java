package Escalera;

import java.awt.EventQueue;

public class Game {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			Gui GUI = new Gui();
		});
	}
}