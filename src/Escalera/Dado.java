package Escalera;

import java.util.ArrayList;
import java.util.Arrays;

public class Dado {
	private ArrayList<Integer> faces = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
	
	public ArrayList<Integer> getFaces() {
		return faces;
	}
}