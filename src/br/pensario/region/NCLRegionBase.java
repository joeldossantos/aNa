package br.pensario.region;

import java.util.Set;
import java.util.TreeSet;

public class NCLRegionBase {

	private String id;
	private String device;
	private NCLRegion parent_region;
	
	Set<NCLRegion> regions= new TreeSet<NCLRegion>();
	
	//REV: falta gerar excecoes nos metodos de set
	public NCLRegionBase(String id) {
		setId(id);
	}
	
	public NCLRegionBase(){}

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

	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<regionBase ";
		
		if(getId()!=null)
			content += " id='" + getId() + "'";

		if (getDevice() != null) 						
			content += " device='" + getDevice() + "'";
		
		if (getParentRegion() != null) 						
			content += " region='" + getParentRegion().getId() + "'";
		
		if (hasRegion()) {
			
			content += ">\n";
			for (NCLRegion region : getRegions()) {
				content += region.parse(ident + 1);
			}
			content += space + "</regionBase>\n";
		}
		else
			content += "/>\n";

		return content;
	}
	
	//public boolean hasOverlayedRegions(); 
	// Camada de validacao
}
