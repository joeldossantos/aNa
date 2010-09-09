package br.pensario.region;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLRegionBase {

	private String id;
	private String device;

	private Set<NCLRegion> regions = new TreeSet<NCLRegion>();

	// private String region; // TODO - ????

	public NCLRegionBase(String id) {
		setId(id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getDevice() {
		return device;
	}

	public boolean addRegion(NCLRegion r) {

		boolean contains = false;
		
		if (!hasRegion(r.getId()))		
			contains = true;		
			
		regions.add(r);
			
		return contains;
	}

	public boolean removeRegion(String id) {

		Iterator<NCLRegion> it = regions.iterator();

		while (it.hasNext()) {
			NCLRegion r = it.next();

			if (r.getId().equals(id)) {
				it.remove();
				return true;
			}
		}

		return false;

	}

	public boolean hasRegion(String id) {

		Iterator<NCLRegion> it = regions.iterator();

		while (it.hasNext()) {

			NCLRegion r = it.next();

			if (r.getId().equals(id))
				return true;
			
			if(r.hasChild(id)) return true;	

		}

		return false;
	}
	
	//public boolean hasOverlayedRegions();
}
