package Escalera;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Controller {
	public static final Map<Integer, Integer> STAIRS = new ConcurrentHashMap<>();
	public static final Map<Integer, Integer> SNAKES = new ConcurrentHashMap<>();
	private Player player = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();
	private Dado dado = new Dado();

	/**
	 * Constructor por defector con valores de los maps
	 */
	public Controller() {
		STAIRS.put(17,47);
		STAIRS.put(32,69);
		STAIRS.put(63,82);
		SNAKES.put(96,52);
		SNAKES.put(42,22);
		SNAKES.put(29,7);
	}

	public Player getPlayer() {
		return player;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Player getPlayer3() {
		return player3;
	}

	/*
	 * Función para actualizar la posición (casilla) actual del jugador
	 * 
	 * @Param newPosition: Casilla donde el jugador se debe desplazar
	 * */
	public void movePlayerBackend(int newPosition) {
		player.move(newPosition);
	}

	public void movePlayer2Backend(final int newPosition) {
		player2.movePlayer2(newPosition);
	}

	public void movePlayer3Backend(final int newPosition) {
		player3.movePlayer3(newPosition);
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
		player.setName(Player.PLAYER_1);
		int face = player.rollDie(dado);
//		Logger.log("Player position: " + player.getCurrentPosition());
		movePlayerBackend(player.getCurrentPosition() + face);
		return Arrays.asList(face, player.getCurrentPosition());
	}

	public List<Integer> getTurnPlayer2() {
		player2.setName(Player.PLAYER_2);
		int face = player2.rollDie(dado);
//		Logger.log("Player2 position: " + player2.getCurrentPositionPlayer2());
		movePlayer2Backend(player2.getCurrentPositionPlayer2() + face);
		return Arrays.asList(face, player2.getCurrentPositionPlayer2());
	}

	public List<Integer> getTurnPlayer3() {
		player3.setName(Player.PLAYER_3);
		int face = player3.rollDie(dado);
//		Logger.log("Player3 position: " + player3.getCurrentPositionPlayer3());
		movePlayer3Backend(player3.getCurrentPositionPlayer3() + face);
		return Arrays.asList(face, player3.getCurrentPositionPlayer3());
	}


}