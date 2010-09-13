package br.pensario.region;

import br.pensario.NCLContainer;
import br.pensario.NCLIdentifiableException;
import br.pensario.descriptor.NCLDescriptor;

public class NCLRegionBase extends NCLContainer<NCLRegion> {

	private String device; //TODO - verificar domínio de valores

	private NCLRegion parent_region;

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
	
	//public boolean hasOverlayedRegions(); 
	// Camada de validação
}
