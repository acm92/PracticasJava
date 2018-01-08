package org.eda1.prueba01;

public class Pescador implements Comparable<Pescador> {
	private String nombre;
	private String apellido;
	private int id;
	private int kilos;

	public Pescador(int id) {
		this.id = id;
	}

	public Pescador(int id, String nombre, String apellidos) {
		this.id = id;
		this.nombre = nombre.toLowerCase();
		this.apellido = apellidos.toLowerCase();
	}

	public void actualizaKilos(int... ventas) {
		int totalKilos = 0;

		for (int i = 0; i < ventas.length; i++)
			totalKilos += ventas[i];
		kilos += totalKilos;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public int getKilos() {
		return kilos;
	}

	@Override
	public String toString() {
		// id.- nombre apellido (kilos k) .... Si k<=0 no se muestra el valor
		if (kilos <= 0)
			return id + ".- " + nombre + " " + apellido;
		else
			return id + ".- " + nombre + " " + apellido + " " + "(" + kilos + "k" + ")";

	}

	@Override
	public int compareTo(Pescador o) {
		// Orden natural: this.id
		if (id < o.getId())
			return -1;
		if (id > o.getId())
			return 1;
		return 0;
	}
}