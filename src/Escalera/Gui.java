package Escalera;

import java.util.ArrayList;

public class Gui {
	private Controller controller = new Controller();
	
	/*
	 * Función para mover el jugador en la GUI
	 * */
	public void movePlayer() {
		ArrayList<Integer> data = controller.gameTurn();
		int numberOfCells = data.get(0);
		float distance = data.get(1);
		
		for(int i=0; i < numberOfCells; i++) {
			if((controller.getCurrentPositionPlayer() % 10) == 0) {
				controller.updateLimitRow((controller.getLimitRow() + 10));
				// playerUP (one cell)
			}
			// move(distance, controller.getDirection()); => para saber hacia que dirección mover el player
			if(controller.stair(controller.getCurrentPositionPlayer()) != -1) {
				// logicUP(controller.stair(controller.getCurrentPositionPlayer()));
			}
			else if(controller.snake(controller.getCurrentPositionPlayer()) != -1) {
				// logicDOWN(controller.snake(controller.getCurrentPositionPlayer()));
			}
		}
	}
}