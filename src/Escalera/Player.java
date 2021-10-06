package Escalera;

import java.util.Random;

public class Player {
	private String name = "";
	private int currentPosition = 1;

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void move(int newPosition) {
		currentPosition = newPosition;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
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
}