package org.eda1.prueba01;

import java.util.Comparator;

public class Greater implements Comparator<Pescador> {
	String campo;

	public Greater(String campo) {
		this.campo = campo;
	}

	@Override
	public int compare(Pescador o1, Pescador o2) {
		int comp = 0;
		try {
			Less comparator1 = new Less(campo);
			comp = comparator1.compare(o1, o2);


		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return - comp;
	}

}
