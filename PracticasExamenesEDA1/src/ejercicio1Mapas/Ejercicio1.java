package ejercicio1Mapas;

import java.io.File;

public class Ejercicio1 {




	public static void main(String[] args) {

		String directorioEntrada = System.getProperty("user.dir") + File.separator +
				"src" + File.separator +
				"ejercicio1Mapas" + File.separator +
				"texto";

		Universidad almeria = new Universidad();

		almeria.cargarDatos(directorioEntrada);

		System.out.println("SOLUCIONES");

		System.out.println("El alumno con más asignaturas matriculadas debe ser Jose Perez, pero en realidad es " + almeria.alumnoMasAsignaturas());
		System.out.println("Las asignatura en las que hay más alumnos matriculados es Arquitectura, pero en realidad es " + almeria.asignaturaMasAlumnos());




	}

}
