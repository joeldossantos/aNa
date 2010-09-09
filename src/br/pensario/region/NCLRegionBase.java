package br.pensario.region;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLRegionBase {

	private String id; //TODO - "[\i-[:]][\c-[:]]*"
	private String device; //TODO - verificar domínio de valores

	private Set<NCLRegion> regions = new TreeSet<NCLRegion>();

	private NCLRegion parent_region;

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

	public boolean addRegion(NCLRegion region) {

		boolean contains = false;
		
		if(hasRegion(region.getId()))		
			contains = true;		
		
		//true if this set did not already contain the specified element
		regions.add(region); 
			
		return !contains;
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
	
	public NCLRegion getRegion(String id) {

		Iterator<NCLRegion> it = regions.iterator();

		while (it.hasNext()) {

			NCLRegion r = it.next();

			if (r.getId().equals(id))
				return r;

		}

		return null;
	}

	public void setParentRegion(NCLRegion region) {
		this.parent_region = region;
	}

	public NCLRegion getParentRegion() {
		return parent_region;
	}
	
	//public boolean hasOverlayedRegions(); 
	// Camada de validação
}
