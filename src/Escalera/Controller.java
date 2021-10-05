package Escalera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Controller {
	private HashMap<Integer, Integer> stairs = new HashMap<Integer, Integer>() {{ 
		put(17,47);
		put(32,69);
		put(63,82); 
	}};
	private HashMap<Integer, Integer> snakes = new HashMap<Integer, Integer>() {{
		put(96,52);
		put(42,22);
		put(29,7);
	}};
	private Player player = new Player();
	private Dado dado = new Dado();
	
	/*
	 * Función para actualizar la posición (casilla) actual del jugador
	 * 
	 * @Param newPosition: Casilla donde el jugador se debe desplazar
	 * */
	public void movePlayerBackend(int newPosition) {
		player.move(newPosition);
	}
	
	/*
	 * Función para obtener la posición actual del jugador
	 * 
	 * Return: Posición actual del jugador (int)
	 * */
	public int getCurrentPositionPlayer() {
		return player.getCurrentPosition();
	}
	
	/*
	 *  Función para comprobar si en la posición actual (despues de mover al jugador) hay una escalera o no
	 *  
	 *  @Param currentPositionPlayer: Posición (casilla) del jugador actual
	 *  
	 *  Return: -1 en caso de no haber escalera, o el numero de la casilla en donde nos deja la escalera 
	 *  una vez subamos.
	 * */
	public int stair(int currentPositionPlayer) {
		return (stairs.get(currentPositionPlayer) != null)? stairs.get(currentPositionPlayer) : -1;
	}
	
	/*
	 *  Función para comprobar si en la posición actual (despues de mover al jugador) hay una serpiente o no
	 *  
	 *  @Param currentPositionPlayer: Posición (casilla) del jugador actual
	 *  
	 *  Return: -1 en caso de no haber una serpiente, o el numero de la casilla en donde nos deja la serpiente
	 *  una vez bajemos.
	 * */
	public int snake(int currentPositionPlayer) {
		return (snakes.get(currentPositionPlayer) != null)? snakes.get(currentPositionPlayer) : -1;
	}
	
	/*
	 * Función que representa el turno de un jugador
	 * 
	 * Return: (cara del dado, distancia que debe recorrer el player para avanzar)
	 * */
	public ArrayList<Integer> gameTurn() {
		int face = player.rollDie(dado);
		movePlayerBackend((player.getCurrentPosition() + face));
		return new ArrayList<Integer>(Arrays.asList(face, player.getCurrentPosition()));
	}
}