package br.pensario;

import br.pensario.region.NCLRegionBase;

public class NCLHead {
	
	private NCLRegionBase rbase;
	//private NCLDescriptorBase dbase;
	//private NCLConnectorBase cbase;

	
	public NCLHead(NCLRegionBase region_base /*, NCLDescriptorBase dbase, NCLConnector cbase*/)
	{
		setRegionBase(region_base);
	}
	
	public void setRegionBase(NCLRegionBase rbase) {
		this.rbase = rbase;
	}

	public NCLRegionBase getRegionBase() {
		return rbase;
	}	
	
	
}
