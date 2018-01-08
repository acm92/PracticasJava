package org.eda1.prueba01;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import org.eda1.actividad01.Heap;
import org.junit.Before;
import org.junit.Test;

public class PescadorTestJUnit4 {

	private String 	directorioEntrada = System.getProperty("user.dir") + File.separator +
										"src" + File.separator +
										"org" + File.separator +
										"eda1" + File.separator +
										"prueba01" + File.separator;

	@Before
	public void setUp() throws Exception {
		Lonja.idStatic = 0; //Inicializamos la variable estática a 0 antes de la ejecución de cada test()
	}

	@Test
	public void testPersonaje() {
		Pescador p1 = new Pescador(1, "EMilIAno", "PERez");
		Pescador p2 = new Pescador(2, "JuaNa", "PereZ");
		Pescador p3 = new Pescador(3, "Abraham", "Sanchez");
		Pescador p4 = new Pescador(4, "Emiliano", "perez");

		assertEquals(p1.toString(), "1.- emiliano perez");
		assertEquals(p2.getNombre(), "juana");
		assertEquals(p3.getApellido(), "sanchez");
		assertTrue(p4.getId() == 4);

		assertTrue(p1.getKilos() == 0);

		//Actualizamos los kilos que ha pescado el pescador p1 ...
		p1.actualizaKilos(3, 3, 1);
		assertTrue(p1.getKilos() == 7);

		p1.actualizaKilos(10);

		assertTrue(p1.getKilos() == 17);

		//Al ser el numero de kilos distinto de 0, este valor aparecera en la cadena toString()
		assertEquals(p1.toString(), "1.- emiliano perez (17k)");

		p1.actualizaKilos(-17);
		//Ahora el numero de kilos = 0 ==> no se muestra
		assertEquals(p1.toString(), "1.- emiliano perez");


		p1 = p2 = p3 = p4 = null;
	}

	@Test
	public void testOrdenPersonaje() throws Exception{
		ArrayList<Pescador> arr = new ArrayList<Pescador>();
		Pescador p1 = new Pescador(1, "Juana", "Perez");
		Pescador p2 = new Pescador(2, "Emiliano", "sanchez");
		Pescador p3 = new Pescador(3, "emiliano", "PeREZ");
		Pescador p4 = new Pescador(4, "Zacarias", "Amate");

		assertTrue(p1.compareTo(p2) < 0);
		assertTrue(p1.compareTo(p1) == 0);
		assertTrue(p3.compareTo(p1) > 0);

		arr.add(p1);
		arr.add(p2);
		arr.add(p3);
		arr.add(p4);

		arr.sort(null);
		assertEquals(arr.toString(), "[1.- juana perez, 2.- emiliano sanchez, 3.- emiliano perez, 4.- zacarias amate]");

		arr.sort(new Less("nombre"));
		assertEquals(arr.toString(), "[3.- emiliano perez, 2.- emiliano sanchez, 1.- juana perez, 4.- zacarias amate]");


		arr.sort(new Less("apeLLIDO"));
		assertEquals(arr.toString(), "[4.- zacarias amate, 3.- emiliano perez, 1.- juana perez, 2.- emiliano sanchez]");

		Heap.sort(arr, new Greater("noMBre"));
		assertEquals(arr.toString(), "[4.- zacarias amate, 1.- juana perez, 2.- emiliano sanchez, 3.- emiliano perez]");

		try{
			arr.sort(new Greater("otro campo cualquiera"));
		}catch(Exception e){
			assertEquals(e.getMessage(), "Este atributo no existe");
		}

		p2.actualizaKilos(3, 3, 3);

		assertEquals(arr.toString(), "[4.- zacarias amate, 1.- juana perez, 2.- emiliano sanchez (9k), 3.- emiliano perez]");

		arr.clear();
		arr = null;
		p1 = p2 = p3 = p4 = null;
	}

	@Test
	public void testLonja(){
		Lonja l1 = new Lonja("Lonja1");
		Lonja l2 = new Lonja("lonja2");
		l1.add("Emiliano", "Perez");
		l2.add("Emiliano", "Perez");
		l1.add("Emiliano", "Perez");
		l1.add("Emiliano", "Sanchez");
		l1.add("juaNa", "Amate");
		l2.add("juaNa", "Amate");
		l1.add("Zacarias", "Amate");
		l2.add("Susana", "Fernandez");

		assertTrue(l1.size() == 5);
		assertTrue(l2.size() == 3);
		assertEquals(l1.toString(), "1.- Lonja1 => [1.- emiliano perez, 2.- emiliano perez, 3.- emiliano sanchez, 4.- juana amate, 5.- zacarias amate]");
		assertEquals(l2.toString(), "2.- lonja2 => [1.- emiliano perez, 2.- juana amate, 3.- susana fernandez]");

		assertTrue(l1.remove(1));
		assertFalse(l1.remove(20));
		assertTrue(l2.remove(2));

		assertEquals(l1.toString(), "1.- Lonja1 => [2.- emiliano perez, 3.- emiliano sanchez, 4.- juana amate, 5.- zacarias amate]");
		assertEquals(l2.toString(), "2.- lonja2 => [1.- emiliano perez, 3.- susana fernandez]");

		l1.actualizaKilos(3, 3, 3, 3);
		l1.actualizaKilos(3, 5);
		l1.actualizaKilos(5, 2, 3);
		l2.actualizaKilos(1, 3, 4, 2);
		l2.actualizaKilos(1, 2);
		l2.actualizaKilos(2, 1);

		assertFalse(l1.actualizaKilos(19, 0));

		assertTrue(l2.getKilos(1) == 11);
		assertTrue(l2.getKilos(10) == -1);

		assertEquals(l1.toString(), "1.- Lonja1 => [2.- emiliano perez, 3.- emiliano sanchez (14k), 4.- juana amate, 5.- zacarias amate (5k)]");
		assertEquals(l2.toString(), "2.- lonja2 => [1.- emiliano perez (11k), 3.- susana fernandez]");

		l1.clear();
		l2.clear();
		l1 = l2 = null;
	}

	@Test
	public void testLoad() throws Exception{
		GestionLonjas gestion = new GestionLonjas();
		String gestionStr1, gestionStr2;

		gestion.load(directorioEntrada + "datos");
		gestionStr1 = gestion.toString();

		assertTrue(gestion.size() == 3);

		//Volvemos a leer...
		gestion.load(directorioEntrada + "datos");
		gestionStr2 = gestion.toString();

		assertEquals(gestionStr1, gestionStr2);

		assertTrue(gestion.size()==3);

		assertTrue(gestion.getKilos(1, 1) == 18);
		assertTrue(gestion.getKilos(1, 10) == -1);
		assertTrue(gestion.getKilos(18, 1) == -1);
		assertTrue(gestion.getKilos(2, 4) == 4);


		for (Lonja lonja : gestion){
			assertEquals(lonja.toStringWithOrder(null), lonja.toString());
		}

		Lonja lonja = gestion.find(1);
		assertEquals(lonja.toStringWithOrder(new Less("nombre")), "1.- Lonja1  => [4.- angel sanchez, 2.- antonio sanchez (4k), 3.- emilio cuadrado, 1.- juan lopez (18k)]");
		assertEquals(lonja.toStringWithOrder(new Less("apellido")), "1.- Lonja1  => [3.- emilio cuadrado, 1.- juan lopez (18k), 4.- angel sanchez, 2.- antonio sanchez (4k)]");

		gestion.clear();
		gestionStr1 = gestionStr2 = null;
		gestion = null;
	}
}