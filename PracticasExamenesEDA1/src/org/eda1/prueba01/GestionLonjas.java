package org.eda1.prueba01;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import org.eda1.edaAuxiliar.AVLTree;

public class GestionLonjas implements Iterable<Lonja> {

	 /**
	  * Ángel Ciudad Montalbán
	  */

	private AVLTree<Lonja> lonjas;

	private enum SECCIONES {
		LONJA, VENTAS;

		void loadLonja(AVLTree<Lonja> lonjas, Scanner scan, String nombre) {
			String[] items;
			while (scan.hasNextLine()) {
				String linea = scan.nextLine();
				if (linea.isEmpty())
					continue;
				if (linea.startsWith("%"))
					break;
				if (linea.startsWith("Lonja")) {
					nombre = linea;
					Lonja lonja = new Lonja(nombre);
					String nombrePescador = "";
					String apellidoPescador = "";
					int contadorPescadores = 0;
					linea = scan.nextLine();
					items = linea.split("[ ;]+");
					for (int i = 0; i < items.length; i++) {
						if (i % 2 == 0) {
							nombrePescador = items[i];
							contadorPescadores++;
						}
						if (i % 2 != 0) {
							apellidoPescador = items[i];
							contadorPescadores++;
						}
						if (contadorPescadores % 2 == 0) {
							lonja.add(nombrePescador, apellidoPescador);
							lonjas.add(lonja);
							contadorPescadores = 0;
						}
					}
				}
			}
		}

		void loadVentas(AVLTree<Lonja> lonjas, Scanner scan, String linea) {
			String[] items;
			int[] datos;
			// items = linea.split("[ ;=>-]+");
			while (scan.hasNextLine()) {
				linea = scan.nextLine();
				if (linea.isEmpty())
					continue;
				items = linea.split("[ =>;-]+");
				int idLonja = Integer.parseInt(items[0]);
				int idPescador = Integer.parseInt(items[1]);
				datos = new int[items.length - 2];
				for (int i = 2; i < items.length; i++) {
					datos[i - 2] = Integer.parseInt(items[i]);
				}
				Lonja nueva = lonjas.find(new Lonja(idLonja));
				if (nueva != null)
					nueva.actualizaKilos(idPescador, datos); // idLonja pero
																// actualizaKilos
																// es idPescador

			}
		}

		void loadSeccion(AVLTree<Lonja> lonjas, Scanner scan, String linea) {
			switch (this) {
			case LONJA:
				loadLonja(lonjas, scan, linea);
				break;
			case VENTAS:
				loadVentas(lonjas, scan, linea);
				break;
			}
		}
	}

	public GestionLonjas() {
		this.lonjas = new AVLTree<Lonja>();
	}

	public void load(String file) {
		Scanner scan = null;
		String linea = "";
		SECCIONES seccion = null;
		clear();

		try {
			scan = new Scanner(new File(file));
		} catch (Exception e) {
			System.out.println("Error al cargar el archivo --> " + e.getMessage());
			System.exit(-1);
		}

		while (scan.hasNextLine()) {
			linea = scan.nextLine();
			if (linea.isEmpty() || linea.contains("%"))
				continue;
			if (linea.equals("@Lonjas")) {
				seccion = SECCIONES.LONJA;
			}
			if (linea.equals("@Ventas")) {
				seccion = SECCIONES.VENTAS;
			}
			seccion.loadSeccion(lonjas, scan, linea);
		}

	}

	public Lonja find(int id) {
		Lonja nueva = new Lonja(id);
		Lonja existente = lonjas.find(nueva);

		return existente;

	}

	public double getKilos(int idLonja, int idPescador) {
		double kilos = 0;
		int idLonjaLocal = 1;
		for (Lonja loj : lonjas) {
			int idPescadorLocal = 1;
			for (Pescador per : loj) {
				if (idLonja == idLonjaLocal && idPescador == idPescadorLocal) {
					kilos = per.getKilos();
					return (int) kilos;
				}
				idPescadorLocal++;
			}
			idLonjaLocal++;
		}
		return -1;

	}

	public int size() {
		int tamanio = this.lonjas.size();
		return tamanio;
	}

	public void clear() {
		Lonja.idStatic = 0;
		this.lonjas.clear();
	}

	public String toStringWithOrder(int idLonja, Comparator<Pescador> comp) {
		ArrayList<Pescador> arr = new ArrayList<Pescador>();

		for (Lonja lo : lonjas) {
			int idLonjas = 1;
			for (Pescador per : lo) {
				if (idLonjas == idLonja)
					arr.add(per);
				idLonjas++;
			}
		}

		if (comp == null)
			return arr.toString();

		arr.sort(comp);
		return arr.toString();
	}

	@Override
	public String toString() {
		return lonjas.toString();
	}

	@Override
	public Iterator<Lonja> iterator() {
		return lonjas.iterator();
	}
}