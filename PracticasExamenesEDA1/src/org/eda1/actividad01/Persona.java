package org.eda1.actividad01;

public class Persona implements Comparable<Persona>{

		private String nombre;
		private String cod;

		public Persona(String nombre, String cod){
			this.nombre = nombre;
			this.cod = cod;
		}

		@Override
		public String toString(){
			return this.nombre + " [" + this.cod + "]";
		}

		//Orden natural -> orden de menor a mayor segun el atributo nombre; 
		//en caso de que los nombres sean iguales, 
		//se ordenaría de menor a mayor segun el atributo codigo
		@Override
		public int compareTo(Persona o) {
			if (nombre.compareTo(o.nombre) < 0)
				return -1;
			if (nombre.compareTo(o.nombre) > 0)
				return 1;
			if (cod.compareTo(o.cod) < 0)
				return -1;
			return cod.compareTo(o.cod);
				 
		}

		
		//Dos objetos son iguales si tienen, exactamente, el mismo nombre y codigo

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			
			Persona other = (Persona) obj;
			
			if (cod == null) {
				if (other.cod != null)
					return false;
			} else if (!cod.equals(other.cod))
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			return true;
		}

		public String getNombre(){
			return this.nombre;
		}

		public String getCod(){
			return this.cod;
		}
}
