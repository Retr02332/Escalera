package Escalera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Controller {
	private int DISTANCE_BETWEEN_CELLS = 100; // ¿Unidad de medida? => px
	private HashMap<Integer, Integer> stairs = new HashMap<Integer, Integer>() {{ 
		put(6,30);
		put(45,90); 
	}};
	private HashMap<Integer, Integer> snakes = new HashMap<Integer, Integer>() {{
		put(10,2);
		put(90,7);
	}};
	private Player player = new Player();
	private Dado dado = new Dado();
	private int limitRow = 10;
	
	/*
	 * Función para actualizar la posición (casilla) actual del jugador
	 * 
	 * @Param newPosition: Casilla donde el jugador se debe desplazar
	 * */
	private void movePlayerBackend(int newPosition) {
		player.move(newPosition);
	}
	
	/*
	 * Función para actualizar el limite de fila actual
	 * 
	 * @Param newLimitRow: nuevo limite de fila 
	 * */
	public void updateLimitRow(int newLimitRow) {
		limitRow = newLimitRow;
	}
	
	/*
	 * Función para obtener el limite de fila actual
	 * 
	 * Return: Limite de fila actual (int)
	 * */
	public int getLimitRow() {
		return limitRow;
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
	 * Función para descomponer el limitRow y obtener la decena (o centena si el numero es de 3 cifras)
	 * del limitRow.
	 * 
	 * @Param limitRow: Limita de la fila actual
	 * 
	 * Return: centena del limitRow
	 * 
	 * */
	public int decompose(int limitRow) {
		return (limitRow == 100)? 10 : (limitRow / 10);
	}
	
	/* 
	 * Obtener la dirección en donde se debe desplazar el jugador
	 * 
	 * Return left|right
	 */
	public String getDirection() {
		if(decompose(limitRow) % 2 == 0) {
			return "left";
		}
		else {
			return "right";
		}
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
		return (snakes.get(currentPositionPlayer) != null)? stairs.get(currentPositionPlayer) : -1;
	}
	
	/*
	 * Función que representa el turno de un jugador
	 * 
	 * Return: (cara del dado, distancia que debe recorrer el player para avanzar)
	 * */
	public ArrayList<Integer> gameTurn() {
		int face = player.rollDie(dado);
		movePlayerBackend((player.getCurrentPosition() + face));
		return new ArrayList<Integer>(Arrays.asList(face, DISTANCE_BETWEEN_CELLS));
	}
}