package br.pensario;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLContainer<T extends NCLIdentifiable> extends NCLIdentifiable {

	protected Set<T> childs = new TreeSet<T>();

	public boolean addChild(T r) {

		boolean contains = false;

		if (!hasChild( r.getId()))
			contains = true;

		childs.add(r);

		return contains;
	}

	public boolean removeChild(String id) {

		Iterator<T> it = childs.iterator();

		while (it.hasNext()) {
			
			T r = (T) it.next();

			if (r.getId().equals(id)) {
				it.remove();
				return true;
			}
		}

		return false;

	}

	public boolean hasChild(String id) {

		Iterator<T> it = childs.iterator();

		while (it.hasNext()) {

			T r = it.next();

			if (r.getId().equals(id))
				return true;

		}

		return false;
	}

}
