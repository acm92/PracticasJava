package ejercicio1Mapas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Universidad {

	private TreeMap<String, TreeSet<String>> datos;

	public Universidad() {
		datos = new TreeMap<String, TreeSet<String>>();
	}

	public void cargarDatos(String nombreArchivo) {
		Scanner scan = null;
		String[] items;
		String linea = "";


		try {

			scan = new Scanner(new File(nombreArchivo));

		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			System.exit(-1);
		}

		while (scan.hasNextLine()) {
			linea = scan.nextLine();

			if (linea.isEmpty() || linea.startsWith("%"))
				continue;

			items = linea.split(" – ");

			addAlumno(items[0]);
			addAsignatura(items[0], items[1]);

		}

	}

	public void addAlumno(String nombreAlumno) {

		if (!(datos.containsKey(nombreAlumno))) {
			TreeSet<String> nuevasAsignaturas = new TreeSet<String>();
			datos.put(nombreAlumno, nuevasAsignaturas);
		}

	}

	public void addAsignatura(String nombreAlumno, String nombreAsignatura) {

		TreeSet<String> amigo = datos.get(nombreAlumno);

		if (amigo.isEmpty() || !(amigo.contains(nombreAsignatura)))
			amigo.add(nombreAsignatura);

	}

	public String toString() {
		return "";
	}

	public String alumnoMasAsignaturas() {
		String alumnoMas = "";
		int contadorAsignaturas = 0;

		for (Entry<String, TreeSet<String>> alumno : datos.entrySet()) {
			if (alumno.getValue().size() > contadorAsignaturas) {
				contadorAsignaturas = alumno.getValue().size();
				alumnoMas = alumno.getKey();
			}

		}

		return alumnoMas;

	}

	/*
	 * Está mal, hay que usar un TreeMap
	 *
	 */
	public String asignaturaMasAlumnos() {
		int aux = 0;
		String asignaturaMas = "";
		TreeSet<String> listaAsignaturas = new TreeSet<String>();

		for (Entry<String, TreeSet<String>> alumno : datos.entrySet()) {
			for (String asignatura : alumno.getValue()) {
				if (!(listaAsignaturas.contains(asignatura)))
					listaAsignaturas.add(asignatura);

			}
		}

		for (String asignaturaExistente : listaAsignaturas) {
			int contadorAlumnos = 0;
			for (Entry<String, TreeSet<String>> alumno : datos.entrySet()) {
				for (String asignatura : alumno.getValue()) {
					if (asignaturaExistente.equals(asignatura)) {
						contadorAlumnos++;

					}

				}
			}
			if (contadorAlumnos > aux) {
				aux = contadorAlumnos;
				asignaturaMas = asignaturaExistente;
			}
		}

		return asignaturaMas;
	}

	public String listadoAsignaturas() {
		return "";
	}

}
