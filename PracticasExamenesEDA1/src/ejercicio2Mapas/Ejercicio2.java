package ejercicio2Mapas;

import java.io.File;
import java.util.Scanner;

public class Ejercicio2 {





	public static void main (String args[]) {

		String directorioEntrada = System.getProperty("user.dir") + File.separator +
				"src" + File.separator +
				"ejercicio2Mapas" + File.separator +
				"texto";

		Equipo pc = new Equipo();

		pc.leerArchivo(directorioEntrada);

		System.out.println(pc.modelosComponente("adsfaf"));
		System.out.println();
		System.out.println(pc.modelosComponente("Wifi"));
		System.out.println();

		System.out.println(pc.listadoComponentes() + "\n");



	}

}
