package org.eda1.practica03.ejercicio02;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eda1.edaAuxiliar.Greater;

import java.io.File;
import java.io.IOException;

public class ProcesarDatos {

	private TreeMap<String, TreeMap<String, TreeSet<String>>> mapa;

	public ProcesarDatos() {
		this.mapa = new TreeMap<String, TreeMap<String, TreeSet<String>>>();
	}

	public void cargarArchivo(String archivo) {

		Scanner scan = null;
		;
		String linea;
		String[] items;
		try {
			scan = new Scanner(new File(archivo));
		} catch (IOException e) {
			System.out.println("Error al cargar el archivo");
			System.exit(-1);
		}
		while (scan.hasNextLine()) {
			linea = scan.nextLine();
			if (linea.startsWith("@") || linea.isEmpty())
				continue;

			items = linea.split("[- >.]+");

			if (mapa.containsKey(items[0])) {
				TreeMap<String, TreeSet<String>> elemento = mapa.get(items[0]);

				if (elemento.containsKey(items[1])) {
					TreeSet<String> ciudades = elemento.get(items[1]);

					if (!(ciudades.contains(items[2]))) {
						ciudades.add(items[2]);
					}
				} else {
					TreeSet<String> ciudadesNuevas = new TreeSet<String>();
					ciudadesNuevas.add(items[2]);
					elemento.put(items[1], ciudadesNuevas);

				}
			} else {
				TreeMap<String, TreeSet<String>> nuevo = new TreeMap<String, TreeSet<String>>();
				TreeSet<String> nuevoSet = new TreeSet<String>();
				nuevoSet.add(items[2]);
				nuevo.put(items[1], nuevoSet);
				mapa.put(items[0], nuevo);

			}

		}
		scan.close();
	}

	public int size() {
		return mapa.size();
	}

	public TreeSet<String> devolverCiudades() {
		TreeSet<String> ciudadesDiferentes = new TreeSet<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				for (String entrada3 : entrada2.getValue()) {
					if (!(ciudadesDiferentes.contains(entrada3)))
						ciudadesDiferentes.add(entrada3);
				}

			}

		}

		return ciudadesDiferentes;
	}

	public TreeSet<String> devolverProyectos() {
		TreeSet<String> proyectosDiferentes = new TreeSet<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				if (!(proyectosDiferentes.contains(entrada2.getKey())))
					proyectosDiferentes.add(entrada2.getKey());
			}

		}

		return proyectosDiferentes;
	}

	public TreeSet<String> devolverEmpresas() {
		TreeSet<String> empresas = new TreeSet<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet())
			empresas.add(entrada1.getKey());

		return empresas;
	}

	public int numeroProyectosEmpresa(String empresa) {
		ArrayList<String> proyectos = new ArrayList<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				if (entrada1.getKey().equals(empresa)) {
					proyectos.add(entrada2.getKey());
				}
			}
		}
		return proyectos.size();
	}

	public int numeroCiudadesProyecto(String proyecto) {

		TreeSet<String> ciudades = new TreeSet<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				for (String entrada3 : entrada2.getValue()) {
					if (entrada2.getKey().equals(proyecto) && !(ciudades.contains(entrada3)))
						ciudades.add(entrada3);
				}

			}
		}

		return ciudades.size();

	}

	public int numeroCiudadesEmpresa(String empresa) {
		TreeSet<String> listaCiudades = new TreeSet<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				for (String entrada3 : entrada2.getValue()) {
					if (entrada1.getKey().equals(empresa) && !(listaCiudades.contains(entrada3)))
						listaCiudades.add(entrada3);
				}

			}
		}

		return listaCiudades.size();
	}

	@Override
	public String toString() {

		String salida = "";
		int i = 0;
		int k = 0;

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			salida += entrada1.getKey() + ": ";
			k = 0;
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				salida += entrada2.getKey() + " <";
				i = 0;
				for (String entrada3 : entrada2.getValue()) {
					salida += entrada3;
					if (i < entrada2.getValue().size() - 1)
						salida += ", ";
					i++;
				}
				salida += ">";

				if (k < entrada1.getValue().size() - 1)
					salida += "; ";
				k++;
			}
			salida += "\n";
		}

		// Adobe: Flash <Boston, Charleston, Washington>;
		// Illustrator <Miami, New_Orleans, Sacramento>;
		// Photoshop <Houston, San_Antonio, Seattle>" + "\n"

		return salida;
	}

	public TreeSet<String> devolverEmpresasCiudad(String ciudad) {
		TreeSet<String> listaEmpresas = new TreeSet<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				for (String entrada3 : entrada2.getValue()) {
					if (entrada3.equals(ciudad))
						listaEmpresas.add(entrada1.getKey());
				}

			}

		}

		return listaEmpresas;
	}

	public TreeSet<String> devolverProyectosCiudad(String ciudad) {
		TreeSet<String> listaProyectos = new TreeSet<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				for (String entrada3 : entrada2.getValue()) {
					if (entrada3.equals(ciudad))
						listaProyectos.add(entrada2.getKey());
				}

			}

		}

		return listaProyectos;
	}

	public TreeSet<String> devolverCiudadesEmpresa(String empresa) {
		TreeSet<String> listaCiudades = new TreeSet<String>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> entrada1 : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> entrada2 : entrada1.getValue().entrySet()) {
				for (String entrada3 : entrada2.getValue()) {
					if (entrada1.getKey().equals(empresa) && !(listaCiudades.contains(entrada3)))
						listaCiudades.add(entrada3);
				}

			}

		}

		if (listaCiudades.size() == 0)
			return null;

		return listaCiudades;
	}

	public String devolverProyectoConMayorNumeroDeCiudades() {
		String resultado = "";
		int numResultado = -1;

		for (Entry<String, TreeMap<String, TreeSet<String>>> emp : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> proy : emp.getValue().entrySet()) {
				if (numResultado < proy.getValue().size()) {
					resultado = proy.getKey();
					numResultado = proy.getValue().size();
				}
			}
		}

		return resultado;
	}

	public String devolverEmpresaConMayorNumeroDeProyectos() {
		String resultado = "";
		int numResultado = -1;

		for (Entry<String, TreeMap<String, TreeSet<String>>> emp : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> proy : emp.getValue().entrySet()) {
				if (numResultado < emp.getValue().size()) {
					resultado = emp.getKey();
					numResultado = emp.getValue().size();
				}
			}
		}
		return resultado;
	}

	public String devolverCiudadConMayorNumeroDeProyectos() {
		String resultado = "";
		int numResultado = -1;

		TreeMap<String, Integer> proyectosDeCiudad = new TreeMap<String, Integer>();

		for (Entry<String, TreeMap<String, TreeSet<String>>> emp : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> proy : emp.getValue().entrySet()) {
				for (String ciu : proy.getValue()) {
					if (proyectosDeCiudad.containsKey(ciu) && proy.getValue().contains(ciu)) {
						Integer contadorProyectos = proyectosDeCiudad.get(ciu);
						proyectosDeCiudad.put(ciu, contadorProyectos + 1);
					} else {
						proyectosDeCiudad.put(ciu, 1);
					}

				}
			}
		}

		for (Entry<String, Integer> entrada1 : proyectosDeCiudad.entrySet()) {
			if (entrada1.getValue() > numResultado) {
				resultado = entrada1.getKey();
				numResultado = entrada1.getValue();
			}

		}

		return resultado;
	}



	public TreeMap<String, TreeSet<String>> devolverCiudadesEmpresas() {
		TreeMap<String, TreeSet<String>> aux = new TreeMap<String, TreeSet<String>>();



		for (Entry<String, TreeMap<String, TreeSet<String>>> emp : mapa.entrySet()) {
			for (Entry<String, TreeSet<String>> proy : emp.getValue().entrySet()) {
				for (String ciu : proy.getValue()) {
					if (aux.containsKey(ciu))
						aux.get(ciu).add(emp.getKey());
					else {
						TreeSet<String> empresas = new TreeSet<String>(new Greater<String>());
						empresas.add(emp.getKey());
						aux.put(ciu, empresas);
					}
				}
			}
		}
		return aux;
	}

}
