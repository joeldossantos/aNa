package br.pensario.descriptor;

import java.util.Set;
import java.util.TreeSet;

import br.pensario.region.NCLRegion;

public class NCLDescriptorBase{

	private String id;
	Set<NCLDescriptor> descriptors = new TreeSet<NCLDescriptor>();
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean addDescriptor(NCLDescriptor descriptor)
	{
		return descriptors.add(descriptor);
		
	}
	
	public boolean removeDescriptor(NCLDescriptor descriptor)
	{
		return descriptors.remove(descriptor);		
	}
	
	public boolean hasDescriptor(NCLDescriptor descriptor)
	{
		return descriptors.contains(descriptor);		
	}
	
	public boolean hasDescriptor()
	{
		return descriptors.size() > 0;		
	}
	
	public Iterable<NCLDescriptor> getDescriptors()
	{
		return descriptors;		
	}
	
}
