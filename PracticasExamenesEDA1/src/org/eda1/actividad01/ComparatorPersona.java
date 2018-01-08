package org.eda1.actividad01;

import java.util.Comparator;

public class ComparatorPersona implements Comparator<Persona> {

	private enum Atributo {NOMBRE, COD};
	private Atributo atName;
	private boolean ordenAsc; //Para indicar si el orden es ascendente o no
	
	
	/**
	 * 
	 * Queremos ordenar una Persona.
	 * Tanto por el orden natural de su nombre o por el orden natural de su código.
	 * Incluso si queremos en ascendente o descendente.
	 * El atributo es lo que queremos ordenar (nombre o codigo)
	 * y ordenAsc es si queremos que sea Ascendente
	 * o no.
	 * 
	 * @param atributo (nombre o codigo)
	 * @param ordenAsc (verdadero o falso)
	 */
	public ComparatorPersona(String atributo, boolean ordenAsc){
		this.ordenAsc = ordenAsc;
		switch(atributo.toLowerCase()){
		case "nombre":
			this.atName = Atributo.NOMBRE;
			break;
		case "cod":
			this.atName = Atributo.COD;
			break;
		default :
			this.atName = Atributo.NOMBRE;
			break;
		}
	}

	@Override
	public int compare(Persona o1, Persona o2) {
		int result = 0;
		switch(atName){
		case NOMBRE:
			result = compare(o1, o2, o1.getNombre(), o2.getNombre());
			break;
		case COD:
			result = compare(o1, o2, o1.getCod(), o2.getCod());
			break;
		}
		return result;
	}

	private int compare(Persona o1, Persona o2, String cad1, String cad2){
		
		
		/*
		 * Si cad1 es menor que cad2, se devolverá -1 (en orden natural o ascendente)
		 * En orden descendiente es al contrario
		 * 
		 */
		if (cad1.compareTo(cad2) < 0) {
			if (ordenAsc)
				return -1;
			return 1;
		}
		
		/*
		 * Si cad2 mayor que cad1, devolverá 1 (en orden natural o ascendente)
		 * En orden descendiente, devolverá lo contrario.
		 */
		if (cad1.compareTo(cad2) > 0) {
			if (ordenAsc)
				return 1;
			return -1;
		}
		return o1.compareTo(o2); //Si las cadenas (atributos a comparar) son iguales
								// entonces comparamos las personas por sus otros atributos
								//con el compareTo de la clase Persona.
	}
}
