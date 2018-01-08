package org.eda1.practica03.ejercicio01;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class ProcesarDireccionesTestJUnit4 {

	String directorioEntrada = System.getProperty("user.dir") + File.separator +
								"src" + File.separator +
								"org" + File.separator +
								"eda1" + File.separator +
								"practica03" + File.separator +
								"ejercicio01" + File.separator;
	ProcesarDirecciones proceso = null;

	@Before
	public void setUp() throws Exception {
		String entradas = "192.146.1.234 pedro.ual.es" + "\n" +
				  "192.146.1.234 , , (voltaire.ual.es" + "\n" +
				  "192.146.1.233 +,  pascal.ual.es" + "\n\n" +
				  "192.113.2.4 ,,,jupiter.ual.es" + "\n" +
				  "192.146.1.234 leo.ual.es" + "\n" +
				  "113.213.12.1 epicuro.ual.es" + "\n" +
				  "192.146.1.234 voltaire.ual.es" + "\n" +
				  "113.213.12.1 epi.ual.es" + "\n" +
				  "192.146.1.245 ant.ual.es" + "\n" +
				  "192.146.1.234 voltaire.ual.es" + "\n\n\n" +
				  "192.146.1.234 leo.ual.es" + "\n" +
				  "192.146.1.233 pascal.ual.es" + "\n" +
				  "192.146.1.234 pedro.ual.es" + "\n" +
				  "192.146.1.244                      antonio.ual.es" + "\n" +
				  "192.146.1.233 poisson.ual.es" + "\n" +
				  "113.213.12.1 epicuro.ual.es" + "\n";
		generarArchivo(entradas, directorioEntrada + "entradas.txt");
		proceso = new ProcesarDirecciones();
		proceso.cargarArchivo(directorioEntrada + "entradas.txt");
	}

	@Test
	public void testGenerarDirecciones() throws IOException {
		String salidaEsperada = "", salidaReal = "";


		assertTrue(proceso.size() == 6);

		proceso.generarDirecciones(directorioEntrada + "direcciones.txt");

		salidaEsperada = "113.213.12.1 ==> {epi.ual.es=1, epicuro.ual.es=2} " +
				 		 "192.113.2.4 ==> {jupiter.ual.es=1} " +
				 		 "192.146.1.233 ==> {pascal.ual.es=2, poisson.ual.es=1} " +
				 		 "192.146.1.234 ==> {leo.ual.es=2, pedro.ual.es=2, voltaire.ual.es=3} " +
				 		 "192.146.1.244 ==> {antonio.ual.es=1} " +
				 		 "192.146.1.245 ==> {ant.ual.es=1} ";

		salidaReal = cargarArchivo(directorioEntrada + "direcciones.txt");
		assertEquals(salidaEsperada, salidaReal);

	}

	@Test
	public void testContadores(){

		ArrayList<String> array = null;

		array = proceso.maquinasConContadorMayorQue(1);

		assertTrue(array.size() == 5);
		assertEquals(array.toString(), "[epicuro.ual.es, pascal.ual.es, leo.ual.es, pedro.ual.es, voltaire.ual.es]");

		array = proceso.maquinasConContadorMayorQue(2);
		assertTrue(array.size() == 1);

		assertEquals(array.toString(), "[voltaire.ual.es]");

		assertTrue(proceso.maquinasConContadorIgualA(2) == 4);
		assertTrue(proceso.maquinasConContadorIgualA(3) == 1);
		assertTrue(proceso.maquinasConContadorIgualA(-1) == 0);

		assertTrue(proceso.getContador("192.146.1.233", "pascal.ual.es") == 2);
		assertTrue(proceso.getContador("192.146.1.234", "voltaire.ual.es") == 3);
		assertTrue(proceso.getContador("192.146.1.999", "pascal.ual.es") == 0);
		assertTrue(proceso.getContador("1.1.1.1", "pascual.ual.es")==0);
	}

	@Test
	public void testGenerarIncidencias() throws IOException {

		ArrayList<String> array = null;
		String salidaEsperada = "", salidaReal = "";

		assertTrue(proceso.size() == 6);



		proceso.generarIncidencias(directorioEntrada + "incidencias.txt");


		salidaEsperada = "113.213.12.1 ==> {epi.ual.es=1, epicuro.ual.es=2} " +
		 		  		 "192.146.1.233 ==> {pascal.ual.es=2, poisson.ual.es=1} " +
		 		  		 "192.146.1.234 ==> {leo.ual.es=2, pedro.ual.es=2, voltaire.ual.es=3} ";

		salidaReal = cargarArchivo(directorioEntrada + "incidencias.txt");
		assertEquals(salidaEsperada, salidaReal);

		array = proceso.incidenciasGeneradasPorIP("192.146.1.233");
		assertTrue(array.size() == 2);
		assertEquals(array.toString(), "[pascal.ual.es, poisson.ual.es]");

		array = proceso.incidenciasGeneradasPorIP("192.146.1.999");
		assertTrue(array==null);

		array = proceso.incidenciasGeneradasPorIP("192.146.1.234");
		assertEquals(array.toString(), "[leo.ual.es, pedro.ual.es, voltaire.ual.es]");

		assertTrue(proceso.numeroDeIncidenciasGeneradasPorIP("113.213.12.1") == 2);
		assertTrue(proceso.numeroDeIncidenciasGeneradasPorIP("192.146.1.234") == 3);
		assertTrue(proceso.numeroDeIncidenciasGeneradasPorIP("192") == 0);
	}

	private void generarArchivo(String cadena, String archivo)	throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(archivo);
		pw.print(cadena);
		pw.close();
	}

	private String cargarArchivo(String archivo) throws FileNotFoundException{
		String salida = "";
		Scanner scan = new Scanner(new File(archivo));
		while(scan.hasNextLine()){
			salida += scan.nextLine() + " ";
		}
		scan.close();
		return salida;
	}

}
