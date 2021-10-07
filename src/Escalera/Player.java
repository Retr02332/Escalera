package Escalera;

import java.util.Random;

/**
 * Clase que representa las caracteristicas y funcionalidades de los jugadores
 * @author retr02332
 *
 */
public class Player {
	private String name = "";
	private int currentPosition = 1;
	
	/**
	 * Función para establecer un nombre la jugador
	 * @param name, nombre del jugador
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Función para obtener el nombre del jugador actual
	 * @return, el nombre del jugador
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Función para actualizar la posición actual del jugador
	 * @param newPosition
	 */
	public void move(int newPosition) {
		currentPosition = newPosition;
	}
	
	/**
	 * Función para obtener la posición actual del jugador
	 * @return
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	/**
	 * Función para tirar el dado
	 * 
	 * @Param dado: Objeto dado que contiene sus respectivas caras
	 * 
	 * Return: La cara del dado resultante despues del tiro
	 */
	public int rollDie(Dado dado) {
		Random random = new Random();
		int face = random.nextInt(5)+0;  // Entre 1 y 6
		return dado.getFaces().get(face);
	}
}