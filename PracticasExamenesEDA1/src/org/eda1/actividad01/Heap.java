package org.eda1.actividad01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Heap<T> {

	private static <T> void swap(ArrayList<T> datos, int a, int b) {
		T aux = datos.get(a);
		datos.set(a, datos.get(b));
		datos.set(b, aux);
	}

	private static <T extends Comparable<T>> int compare(T a, T b, Comparator<T> comp) {
		return (comp == null) ? a.compareTo(b) : comp.compare(a, b);
	}

	private static <T extends Comparable<T>> void siftDown(ArrayList<T> datos, int start, int end, Comparator<T> comp) {
		
		
		int n, nizq, nder, pos;

		n = start;
		boolean hundir = true;
		while (n <= end && hundir) {
			nizq = 2 * n + 1;
			if (nizq > end)
				hundir = false;
			else {
				nder = 2 * n + 2;
				if (nder > end)
					pos = nizq;
				else {
					if (compare(datos.get(nizq), datos.get(nder), comp) < 0)
						pos = nizq;
					else
						pos = nder;
				}
				if (compare(datos.get(n), datos.get(pos), comp) < 0)
					hundir = false;
				else {
					swap(datos, n, pos);
					n = pos;
				}
			}
		}
	}

	public static <T extends Comparable<T>> void sort(ArrayList<T> datos, Comparator<T> comp) {

		int pos = datos.size() / 2;
		for (int i = pos; i >= 0; i--) {
			siftDown(datos, i, datos.size() - 1, comp);
		}
		
		for (int i = datos.size() - 1; i > 0; i--) {
			
			swap(datos, 0, i);
			siftDown(datos, 0, i - 1, comp);
		}
	
		Collections.reverse(datos);
		
	}
}