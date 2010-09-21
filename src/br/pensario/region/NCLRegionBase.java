package br.pensario.region;

import java.util.Set;
import java.util.TreeSet;

public class NCLRegionBase {

	private String id;
	private String device; //TODO - verificar domínio de valores
	private NCLRegion parent_region;
	
	Set<NCLRegion> regions= new TreeSet<NCLRegion>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getDevice() {
		return device;
	}	

	public void setParentRegion(NCLRegion region) {
		this.parent_region = region;
	}

	public NCLRegion getParentRegion() {
		return parent_region;
	}
	
	public boolean addRegion(NCLRegion region)
	{
		return regions.add(region);
		
	}
	
	public boolean removeRegion(NCLRegion region)
	{
		return regions.remove(region);		
	}
	
	public boolean hasRegion(NCLRegion region)
	{
		return regions.contains(region);		
	}
	
	public boolean hasRegion()
	{
		return regions.size() > 0;		
	}
	
	public Iterable<NCLRegion> getRegions()
	{
		return regions;		
	}
	
	//public boolean hasOverlayedRegions(); 
	// Camada de validação
}
