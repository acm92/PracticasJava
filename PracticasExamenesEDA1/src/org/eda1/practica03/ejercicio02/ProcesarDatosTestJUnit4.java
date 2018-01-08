package org.eda1.practica03.ejercicio02;

import static org.junit.Assert.*;

import java.io.File;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class ProcesarDatosTestJUnit4 {

	String directorioEntrada = System.getProperty("user.dir") + File.separator +
								"src" + File.separator +
								"org" + File.separator +
								"eda1" + File.separator +
								"practica03" + File.separator +
								"ejercicio02" + File.separator;

	ProcesarDatos proceso = null;;

	@Before
	public void setUp() throws Exception {
		proceso = new ProcesarDatos();
		proceso.cargarArchivo(directorioEntrada + "nuevasEmpresasProyectosCiudades.txt");
	}

	@Test
	public void testProcesarDatos() {

		String salidaEsperada = "";
		TreeSet<String> resultado = null;
		TreeMap<String, TreeSet<String>> auxiliar = null;

		assertTrue(proceso.size() == 6);

		assertTrue(proceso.numeroProyectosEmpresa("Google") == 0);
		assertTrue(proceso.numeroProyectosEmpresa("Adobe") == 3);
		assertTrue(proceso.numeroCiudadesProyecto("Xcode") == 7);
		assertTrue(proceso.numeroCiudadesProyecto("EDAI") == 0);
		assertTrue(proceso.numeroCiudadesEmpresa("Adobe") == 9);
		assertTrue(proceso.numeroCiudadesEmpresa("Martinika") == 0);


		salidaEsperada = "Adobe: Flash <Boston, Charleston, Washington>; Illustrator <Miami, New_Orleans, Sacramento>; Photoshop <Houston, San_Antonio, Seattle>" + "\n" +
					     "Apple: Xcode <Atlanta, Berkeley, Detroit, Houston, Miami, Stanford, Washington>; iOS <Dallas, Los_Angeles, Miami, New_York, Washington>; iWork <Atlanta, Chicago, Los_Angeles, Miami, New_Orleans, New_York, Stanford>" + "\n" +
					     "Borland: C++Builder <Berkeley, Ohio, Portland, Washington, Wisconsin>; Delphi <Chicago, Detroit, Jackson, Miami, Milwaukee>; JBuilder <Denver, Miami, Santa_Fe, Tucson>" + "\n" +
						 "Microsoft: Excel <Las_Vegas, Los_Angeles, Phoenix, Sacramento, San_Francisco>; OutLook <Atlanta, Miami, New_Jersey, New_York, Washington>; PowerPoint <Augusta, Dallas, Helena, Miami, Washington>; VisualC++ <Miami, New_York, Philadelphia, Stanford, Washington>; Word <Maryland, Memphis, Miami, New_York, Orlando, Washington>" + "\n" +
						 "Oracle: Database_11g <Augusta, Denver, Los_Angeles, Miami, Redwood_City>; Java <Dallas, Miami, New_York, Sacramento, Washington>; Solaris <Atlanta, Berkeley, New_York, Washington>" + "\n" +
						 "Ramsoft: EZJava <New_York, Stanford, Washington>" + "\n";

		assertEquals(proceso.toString(), salidaEsperada);
		assertTrue(proceso.devolverEmpresas().size() == 6);
		assertTrue(proceso.devolverProyectos().size() == 18);
		assertTrue(proceso.devolverCiudades().size() == 36);

		resultado = proceso.devolverEmpresasCiudad("Miami");
		assertTrue(resultado.size() == 5);
		assertEquals(resultado.toString(), "[Adobe, Apple, Borland, Microsoft, Oracle]");


		resultado = proceso.devolverProyectosCiudad("Washington");
		assertTrue(resultado.size() == 11);
		assertEquals(resultado.toString(), "[C++Builder, EZJava, Flash, Java, OutLook, PowerPoint, Solaris, VisualC++, Word, Xcode, iOS]");

		assertEquals(proceso.devolverProyectosCiudad("Madrid").toString(),"[]");


		resultado= proceso.devolverCiudadesEmpresa("Oracle");
		assertTrue(resultado.size() == 11);
		assertEquals(resultado.toString(), "[Atlanta, Augusta, Berkeley, Dallas, Denver, Los_Angeles, Miami, New_York, Redwood_City, Sacramento, Washington]");
		
		
		assertTrue(proceso.devolverCiudadesEmpresa("Borland").size() == 13);
		
		assertEquals(proceso.devolverCiudadesEmpresa("Toshiba"),null);
		assertEquals(proceso.devolverProyectoConMayorNumeroDeCiudades(), "Xcode");
		assertEquals(proceso.devolverEmpresaConMayorNumeroDeProyectos(), "Microsoft");
		assertEquals(proceso.devolverCiudadConMayorNumeroDeProyectos(), "Miami");
		
		//Ejercicio adicional: implementad devolverCiudadesEmpresas()
		//Devuelve una estructura de tipo TreeMap<Ciudad, TreeSet<Empresa>> en el que se detalla el conjunto de empresas que operan en cada una de las ciudades existentes.
		//La estructura ordenara el conjunto de ciudades por orden ascendente, mientras que las empresas se almacenaran siguiendo un orden inverso (mayor a menor).
		
		auxiliar = proceso.devolverCiudadesEmpresas();

		assertEquals(auxiliar.get("Berkeley").toString(), "[Oracle, Borland, Apple]");
		assertEquals(auxiliar.get("Chicago").toString(), "[Borland, Apple]");
		assertEquals(auxiliar.keySet(), proceso.devolverCiudades());
		assertTrue(auxiliar.values().size() == 36);
		
	}
}
