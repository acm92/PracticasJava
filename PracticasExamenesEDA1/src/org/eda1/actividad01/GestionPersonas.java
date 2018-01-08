package org.eda1.actividad01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class GestionPersonas {
	private ArrayList<Persona> listaPersonas;

	public GestionPersonas(){
		this.listaPersonas = new ArrayList<Persona>();
	}
	public boolean add(Persona p){
		
		if (listaPersonas.contains(p))
			return false;
		listaPersonas.add(p);
		return true;
	}

	public int size(){
		return listaPersonas.size();
	}

	public void shuffle(){
		Collections.shuffle(listaPersonas);
	}
	
	public void sort1(String att, boolean ordenAsc){
		this.listaPersonas.sort(new ComparatorPersona(att, ordenAsc));
	}

	public void sort2(String att, boolean ordenAsc){
		PriorityQueue<Persona> pq = new PriorityQueue<Persona>(new ComparatorPersona(att, ordenAsc));

		for (Persona p : listaPersonas)
			pq.add(p);
		listaPersonas.clear();
		
		while(!pq.isEmpty())
			this.listaPersonas.add(pq.poll());
	}

	public void sort3(String att, boolean ordenAsc){
		Heap.sort(listaPersonas, new ComparatorPersona(att, ordenAsc));
	}

	@Override
	public String toString(){
		return listaPersonas.toString();
	}

}