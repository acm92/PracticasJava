package org.eda1.prueba01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.eda1.edaAuxiliar.AVLTree;

public class Lonja implements Comparable<Lonja>, Iterable<Pescador> {
	private String nombre;
	private int id;
	private AVLTree<Pescador> pescadores;
	public static int idStatic; // Imprescindible inicializar correctamente esta
								// variable...en el lugar que corresponda

	public Lonja(int id) {
		this.id = id;
		pescadores = new AVLTree<Pescador>();
	}

	public Lonja(String nombre) {
		this.nombre = nombre;
		id = ++idStatic;
		pescadores = new AVLTree<Pescador>();
	}

	public boolean add(String nombre, String apellidos) {

		Pescador nuevo = new Pescador(pescadores.size() + 1, nombre, apellidos);
		Pescador existente = pescadores.find(nuevo);

		if (existente != null) {
			pescadores.add(existente);
			return false;
		}

		pescadores.add(nuevo);

		return true;
	}

	public boolean remove(int id) {
		Pescador nuevo = new Pescador(id);
		Pescador existente = pescadores.find(nuevo);

		if (existente != null) {
			pescadores.remove(existente);
			return true;
		}

		return false;
	}

	public int size() {
		return this.pescadores.size();
	}

	public void clear() {
		this.pescadores.clear();
	}

	public double getKilos(int idPescador) {
		double kilos = 0;
		Pescador nuevo = new Pescador(idPescador);
		Pescador existente = pescadores.find(nuevo);

		if (existente != null)
			kilos = existente.getKilos();

		if (kilos == 0)
			return -1;

		return (int) kilos;
	}

	public boolean actualizaKilos(int id, int... kilos) {
		Pescador nuevo = new Pescador(id);
		Pescador existente = pescadores.find(nuevo);

		if (existente != null) {
			int totalKilos = 0;
			for (int kil : kilos)
				totalKilos += kil;
			if (existente.getKilos() == totalKilos)
				return false;
			existente.actualizaKilos(kilos);
			return true;
		}
		return false;

	}

	public String toStringWithOrder(Comparator<Pescador> comp) {
		ArrayList<Pescador> arr = new ArrayList<Pescador>();

		for (Pescador per : pescadores)
			arr.add(per);
		arr.sort(comp);

		return id + ".- " + this.nombre + " => " + arr.toString();
	}

	@Override
	public String toString() {
		// "1.- Lonja1 => [2.- emiliano perez, 3.- emiliano sanchez, 4.- juana
		// amate, 5.- zacarias amate]"
		ArrayList<Pescador> lista = new ArrayList<Pescador>();

		for (Pescador per : pescadores)
			lista.add(per);

		String salida = id + ".- " + nombre + " => ";
		salida += lista.toString();
		return salida;
	}

	@Override
	public int compareTo(Lonja o) {
		// Orden natural: this.id
		if (id < o.id)
			return -1;
		if (id > o.id)
			return 1;
		return 0;
	}

	@Override
	public Iterator<Pescador> iterator() {
		return pescadores.iterator();
	}
}