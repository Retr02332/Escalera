package Escalera;

import java.util.List;
import java.util.Arrays;

/**
 * 
 * @author retr02332
 * Clase que representa un dado
 *
 */
public class Dado {
	private List<Integer> faces = Arrays.asList(1,2,3,4,5,6);
	
	/**
	 * Función para obtener las caras del dado
	 * @return, las caras del dado
	 */
	public List<Integer> getFaces() {
		return faces;
	}
}