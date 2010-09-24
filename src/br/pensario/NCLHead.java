package br.pensario;

import br.pensario.connector.NCLConnectorBase;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.descriptor.NCLDescriptorBase;
import br.pensario.region.NCLRegion;
import br.pensario.region.NCLRegionBase;

public class NCLHead {
	
	private NCLRegionBase rbase;
	private NCLDescriptorBase dbase;
	private NCLConnectorBase cbase; 

	public NCLHead(NCLRegionBase region_base, NCLDescriptorBase descriptor_base, NCLConnectorBase connector_base)
	{
		setRegionBase(region_base);
		setDescriptorBase(descriptor_base);
		setConnectorBase(connector_base);
	}
	
	public void setRegionBase(NCLRegionBase rbase) {
		this.rbase = rbase;
	}

	public NCLRegionBase getRegionBase() {
		return rbase;
	}

	public void setDescriptorBase(NCLDescriptorBase dbase) {
		this.dbase = dbase;
	}

	public NCLDescriptorBase getDescriptorBase() {
		return dbase;
	}

	public void setConnectorBase(NCLConnectorBase cbase) {
		this.cbase = cbase;
	}

	public NCLConnectorBase getConnectorBase() {
		return cbase;
	}	
	
		
	public String parse(int ident) {
		
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		content = space + "<head>\n";	
		
		content += rbase.parse(ident + 1);		
		content += dbase.parse(ident + 1);
		
	/*	for(NCLCausalConnector connector : cbase.getConnectors())
		{				
			content += connector.parse(ident+1);				
		}*/	
		
		content += space + "</head>\n";
		
		return content;
	}
	
	
}
