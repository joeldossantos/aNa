package br.pensario.region;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLRegionContainer {

	private Set<NCLRegion> childs = new TreeSet<NCLRegion>();

	public boolean addRegion(NCLRegion r) {

		boolean contains = false;

		if (!hasChild(r.getId()))
			contains = true;

		childs.add(r);

		return contains;
	}

	public boolean removeRegion(String id) {

		Iterator<NCLRegion> it = childs.iterator();

		while (it.hasNext()) {
			NCLRegion r = it.next();

			if (r.getId().equals(id)) {
				it.remove();
				return true;
			}
		}

		return false;

	}

	public boolean hasChild(String id) {

		Iterator<NCLRegion> it = childs.iterator();

		while (it.hasNext()) {

			NCLRegion r = it.next();

			if (r.getId().equals(id))
				return true;

		}

		return false;
	}

}
