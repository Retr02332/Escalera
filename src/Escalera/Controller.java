package Escalera;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Controller {
	private List<Player> players = Arrays.asList(new Player(), new Player(), new Player()); 
	public static final Map<Integer, Integer> STAIRS = new ConcurrentHashMap<>();
	public static final Map<Integer, Integer> SNAKES = new ConcurrentHashMap<>();
	private Dado dado = new Dado();
	private int turn = 0;

	public Controller() {
		STAIRS.put(17,47);
		STAIRS.put(32,69);
		STAIRS.put(63,82);
		SNAKES.put(96,52);
		SNAKES.put(42,22);
		SNAKES.put(29,7);
	}
	
	public void setNamePlayers() {
		for(int i=0; i<players.size(); i++) {
			players.get(i).setName("player "+(i+1));
		}
	}

	public Player getPlayer() {
		return players.get(turn);
	}

	public void nextTurn() {
		if(turn == 2) {turn = 0;}
		else {turn++;}
	}
	
	public int getTurn() {
		return turn;
	}
	
	/*
	 * Función para actualizar la posición (casilla) actual del jugador
	 * 
	 * @Param newPosition: Casilla donde el jugador se debe desplazar
	 * */
	public void movePlayerBackend(int newPosition) {
		players.get(turn).move(newPosition);
	}

	/*
	 * Función para obtener la posición actual del jugador
	 * 
	 * Return: Posición actual del jugador (int)
	 * */
	public int getCurrentPositionPlayer() {
		return players.get(turn).getCurrentPosition();
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
		return (STAIRS.get(currentPositionPlayer) != null)? STAIRS.get(currentPositionPlayer) : -1;
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
		return (SNAKES.get(currentPositionPlayer) != null)? SNAKES.get(currentPositionPlayer) : -1;
	}
	
	/*
	 * Función que representa el turno de un jugador
	 * 
	 * Return: (cara del dado, distancia que debe recorrer el player para avanzar)
	 * */
	public List<Integer> gameTurn() {
		int face = players.get(turn).rollDie(dado);
		movePlayerBackend(players.get(turn).getCurrentPosition() + face);
		return Arrays.asList(face, players.get(turn).getCurrentPosition());
	}
}