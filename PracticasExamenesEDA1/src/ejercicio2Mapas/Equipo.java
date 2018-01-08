package ejercicio2Mapas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eda1.edaAuxiliar.Greater;

public class Equipo {

	private TreeMap<String, TreeSet<String>> datos;

	public Equipo() {

		datos = new TreeMap<String, TreeSet<String>>();
	}

	public void leerArchivo(String nombreArchivo) {
		Scanner scan = null;
		String[] items = null;
		String[] componentes;
		String linea = "";

		datos.clear();

		try {

			scan = new Scanner(new File(nombreArchivo));

		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			System.exit(-1);

		}

		while (scan.hasNextLine()) {
			linea = scan.nextLine();

			if (linea.isEmpty() || linea.startsWith("@"))
				continue;

			items = linea.split("[:, ]+");

			addComponente(items[0]);

			componentes = new String[items.length - 1];

			for (int i = 1; i < items.length; i++)
				componentes[i - 1] = items[i];

			addModelos(items[0], componentes);

		}

	}

	public void addComponente(String nombreComponente) {

		if (!(datos.containsKey(nombreComponente))) {
			TreeSet<String> nuevo = new TreeSet<String>();
			datos.put(nombreComponente, nuevo);
		}

	}

	public void addModelos(String nombreComponente, String... nombresModelos) {

		TreeSet<String> modelos = datos.get(nombreComponente);

		for (int i = 0; i < nombresModelos.length; i++) {

			if (!(modelos.contains(nombresModelos[i])))
				modelos.add(nombresModelos[i]);
		}

	}

	public String toString() {

		return "";
	}

	public String modelosComponente(String nombreComponente) {

		if (!(datos.containsKey(nombreComponente)))
			return nombreComponente + " no existe";

		TreeSet<String> lista = datos.get(nombreComponente);
		String listado = "";
		int i = 0;

		for (String comp : lista) {
			listado += comp;
			if (i < lista.size() - 1)
				listado += ", ";
			i++;
		}

		return nombreComponente + " = " + "{" + listado + "}";
	}

	public String listadoComponentes() {
		TreeMap<Integer, TreeSet<String>> coleccion = new TreeMap<Integer, TreeSet<String>>(new Greater());
		String salida = "";

		for (Entry<String, TreeSet<String>> comp : datos.entrySet()) {
			for (String mod : comp.getValue()) {
				if (coleccion.containsKey(comp.getValue().size())) {

					TreeSet<String> modelosExistentes = coleccion.get(comp.getValue().size());
					modelosExistentes.add(comp.getKey());
					coleccion.put(comp.getValue().size(), modelosExistentes);

				} else {
					TreeSet<String> modelos = new TreeSet<String>();
					modelos.add(comp.getKey());
					coleccion.put(comp.getValue().size(), modelos);
				}

			}

		}

		for (Entry<Integer, TreeSet<String>> entrada1 : coleccion.entrySet()) {
			int i = 0;
			salida += entrada1.getKey() + " modelos => {";
			for (String com : entrada1.getValue()) {
				salida += com;
				if (i < entrada1.getValue().size() - 1)
					salida += ", ";
				i++;
			}
			salida += "}" + "\n";
		}

		return salida;
	}

}
