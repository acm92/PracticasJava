package org.eda1.practica03.ejercicio01;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ProcesarDirecciones {

	private TreeMap<String, TreeMap<String, Integer>> mapa;

	public ProcesarDirecciones() {
		this.mapa = new TreeMap<String, TreeMap<String, Integer>>();
		// TreeMap <DireccionIp, TreeMap<NombredeMaquina, contadorMaquina>>
		// Porque una direccion Ip puede tener asociadas varias maquinas, y
		// encima repetidas.
	}

	/**
	 * Metodo que lee las lineas (IP, maquina) del archivo de entrada
	 * correspondiente y genera un TreeMap de objetos Direccion-Maquina
	 *
	 * @param ruta
	 *            del archivo de las lineas (IP, maquina)
	 * @return
	 */
	public void cargarArchivo(String archivo) {

		String[] items = null;
		String linea;
		Scanner scan = null;
		mapa.clear();
		try {
			scan = new Scanner(new File(archivo));
		} catch (IOException e) {
			System.out.println("Error al cargar archivo");
			System.exit(-1);
		}
		while (scan.hasNextLine()) {
			linea = scan.nextLine();

			if (linea.isEmpty())
				continue;

			items = linea.split("[ ,(+]+"); // Atencion al patron que se
											// utiliza..

			/*
			 * Si en la coleccion de Ip's la cual cada una tiene asociada una o
			 * varias maquinas (epicuro, etc), existe la que esta en items[0],
			 * luego comprobamos si ya está esa maquina. Si está le sumamos 1 al
			 * contador, si no, añadimos esa maquina.
			 *
			 * Si no existe esa Ip en la coleccion, se añade y ya esta.
			 */

			if (mapa.containsKey(items[0])) {
				TreeMap<String, Integer> maquinaYContador = mapa.get(items[0]);

				if (maquinaYContador.containsKey(items[1])) {
					/*
					 * Consigue el valor asociado al nombre de la maquina. En
					 * este caso habiamos especificado que el valor asociado era
					 * un Integer, es decir el contador de las veces que se
					 * repite esa maquina en esa Ip. Obtenemos pues ese valor
					 * para sumarle 1, porque se repite la maquina, segun el
					 * containsKey de antes.
					 */
					int contador = maquinaYContador.get(items[1]);
					/*
					 * Aqui es donde incrementamos el contador. El metodo put()
					 * lo que hace es poner el valor que quieras (contador + 1)
					 * en el lugar donde se encuentra items[1]. En otras
					 * palabras, items[1] es el nombre de la maquina que se
					 * repite, asi que incrementamos el contador que tiene
					 * asociado.
					 */
					maquinaYContador.put(items[1], contador + 1);
					mapa.put(items[0], maquinaYContador);
				} else {
					/*
					 * La maquina no existe pero la Ip si, asi que creamos una
					 * nueva maquina y le ponemos el contador a 1
					 */
					maquinaYContador.put(items[1], 1);
					mapa.put(items[0], maquinaYContador);
				}

			} else {
				TreeMap <String, Integer> nuevaIp = new TreeMap<String, Integer>();
				nuevaIp.put(items[1], 1);
				mapa.put(items[0], nuevaIp);
			}


		}
		scan.close();
	}

	public int size() {
		return mapa.size();
	}

	public void generarDirecciones(String archivo) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(archivo);
		} catch (IOException e) {
			System.out.println("Error al construir archivo de direcciones");
			System.exit(-1);
		}
		String cadena = "";
		/*
		 * Para iterar sobre el mapa, hay que convertirlo en una coleccion, por lo tanto
		 * mapa.entrySet(), además para recorrer cada elemento debemos convertirlo en un
		 * Entry o elemento.
		 */
		for(Entry<String, TreeMap<String, Integer>> entrada1 : mapa.entrySet()) {

			/*
			 * Segun la estructura <K,V> cuando llamas al Key se refiere al String
			 * de la direccionIp.
			 */
			cadena += entrada1.getKey() + " ==> {";
			int j = 0;

			/*
			 *Entrada1.getValue().entrySet() significa que el V del <K,V> de la entrada1
			 * es otro arbol que contiene los nombres de las maquinas y sus contadores.
			 * Entonces como es otro arbol que no se puede iterar, se convierte en un Set
			 * o coleccion con el metodo entrySet().
			 *
			 */
			for (Entry<String, Integer> entrada2 : entrada1.getValue().entrySet()) {
				cadena += entrada2.getKey() + "=" + entrada2.getValue();
				if(j < entrada1.getValue().size() - 1)
					cadena += ", ";
				j++;
			}
			cadena += "}" + "\n";
		}

		out.print(cadena);
		out.close();

	}

	public void generarIncidencias(String archivo) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(archivo);
		} catch (IOException e) {
			System.out.println("Error al construir archivo de incidencias");
			System.exit(-1);
		}
		String cadena = "";

		for(Entry<String, TreeMap<String, Integer>> entrada1 : mapa.entrySet()) {
			if (entrada1.getValue().size() > 1) {
			cadena += entrada1.getKey() + " ==> {";
			int j = 0;


			for (Entry<String, Integer> entrada2 : entrada1.getValue().entrySet()) {
				cadena += entrada2.getKey() + "=" + entrada2.getValue();
				if(j < entrada1.getValue().size() - 1)
					cadena += ", ";
				j++;
			}
			cadena += "}" + "\n";
		}
		}
		out.print(cadena);
		out.close();

	}

	public ArrayList<String> maquinasConContadorMayorQue(int contador) {
		ArrayList<String> arr = new ArrayList<String>();

		for (Entry <String, TreeMap<String, Integer>> entrada1 : mapa.entrySet()) {
			for (Entry <String, Integer> entrada2 : entrada1.getValue().entrySet()) {
				if(entrada2.getValue() > contador)
					arr.add(entrada2.getKey());
			}
		}

		return arr;
	}

	public int maquinasConContadorIgualA(int contador) {
		ArrayList<String> arr = new ArrayList<String>();

		for (Entry <String, TreeMap<String, Integer>> entrada1 : mapa.entrySet()) {
			for (Entry <String, Integer> entrada2 : entrada1.getValue().entrySet()) {
				if(entrada2.getValue().equals(contador))
					arr.add(entrada2.getKey());
			}
		}

		return arr.size();
	}

	public int getContador(String direccion, String maquina) {


		for (Entry <String, TreeMap<String, Integer>> entrada1 : mapa.entrySet()) {
			for (Entry <String, Integer> entrada2 : entrada1.getValue().entrySet()) {
				if(entrada1.getKey().equals(direccion) && entrada2.getKey().equals(maquina))
					return entrada2.getValue();
			}
		}
		return 0;
	}

	public ArrayList<String> incidenciasGeneradasPorIP(String direccion) {
		ArrayList<String> arr = new ArrayList<String>();

		for (Entry <String, TreeMap<String, Integer>> entrada1 : mapa.entrySet()) {
			for (Entry <String, Integer> entrada2 : entrada1.getValue().entrySet()) {
				if(entrada1.getKey().equals(direccion))
					arr.add(entrada2.getKey());
			}
		}
		if(arr.isEmpty())
			return null;

		return arr;
	}

	public int numeroDeIncidenciasGeneradasPorIP(String direccion) {
		ArrayList<String> arr = new ArrayList<String>();

		for (Entry <String, TreeMap<String, Integer>> entrada1 : mapa.entrySet()) {
			for (Entry <String, Integer> entrada2 : entrada1.getValue().entrySet()) {
				if(entrada1.getKey().equals(direccion))
					arr.add(entrada2.getKey());
			}
		}


		return arr.size();
	}

	//IMPORTANTE

	/* Notas aclaratorias
	 * TreeMap <DireccionIp, TreeMap <NombreDeLaMaquina, NumeroDeMaquinasAsociadasAEsaIp>
	 *
	 * En principio no se pueden iterar por los mapas. Asi que si quieres recorrer
	 * sus elementos debemos convertirlos en Entrys que son elementos iterables.
	 * Y despues se debe convertir el mapa en un Set o coleccion con el metodo .entrySet()
	 *
	 * Metodos muy importantes de los mapas:
	 *
	 * containsKey(), containsValue(),
	 * put(posicion, elementoAColocar)
	 * get(llave) devuelve el elemento que se encuentra en la llave o posicion.
	 *
	 * Situacion especial:
	 *
	 * Ejemplo: estamos en un segundo bucle for anidado, y tenemos un mapa dentro de otro mapa
	 * para recorrer un mapa dentro de otro mapa, debemos convertirlo en un set de la
	 * siguente forma:
	 *
	 * for (Entry <String, Integer> entrada2 : entrada1.getValue().entrySet())
	 * Es decir: entrada1 es un entry del elemento de entrada1.
	 * El valor de entrada1 es otro arbol, (getValue()) y lo convertimos en un set: entrySet()

	 */





}