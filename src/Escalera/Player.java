package Escalera;

import java.util.Random;

public class Player {
	private String name = "";
	private int currentPosition = 1;
	private int currentPositionPlayer2 = 1;
	private int currentPositionPlayer3 = 1;
	public static final String PLAYER_1 = "Player 1";
	public static final String PLAYER_2 = "Player 2";
	public static final String PLAYER_3 = "Player 3";

	public String getName() {
		return name;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}

	public int getCurrentPositionPlayer2() {
		return currentPositionPlayer2;
	}

	public int getCurrentPositionPlayer3() {
		return currentPositionPlayer3;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*
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
	
	// Este metodo recibe la suma de (posActual + caraDado) para tener la nueva posActual (casilla)
	public void move(int newPosition) {
		currentPosition = newPosition;
	}

	public void movePlayer2(final int newPositionPlayer2) {
		currentPositionPlayer2 = newPositionPlayer2;
	}

	public void movePlayer3(final int newPositionPlayer3) {
		currentPositionPlayer3 = newPositionPlayer3;
	}
}