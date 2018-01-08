package org.eda1.prueba01;

import java.util.Comparator;

public class Less implements Comparator<Pescador> {

	private enum ATRIBUTO {
		NOMBRE, APELLIDO
	};

	private ATRIBUTO campo;

	public Less(String campo) throws Exception {
		switch (campo.toLowerCase()) {
		case "nombre":
			this.campo = ATRIBUTO.NOMBRE;
			break;
		case "apellido":
			this.campo = ATRIBUTO.APELLIDO;
			break;
		default:
			throw new RuntimeException("Este atributo no existe");
		}
	}

	@Override
	public int compare(Pescador o1, Pescador o2) {
		int comp = 0;
		switch (campo) {
		case NOMBRE:
			// ...
			// Ordenamos primero por nombre y luego por apellido
			comp = o1.getNombre().compareTo(o2.getNombre());

			if (comp != 0)
				return comp;
			else
				return o1.getApellido().compareTo(o2.getApellido());

		case APELLIDO:

			comp = o1.getApellido().compareTo(o2.getApellido());

			if (comp != 0)
				return comp;
			else
				return o1.getNombre().compareTo(o2.getNombre());
		}
		// En caso de empate, orden natural
		return o1.compareTo(o2);

	}
}
