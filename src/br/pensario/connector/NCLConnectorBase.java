package br.pensario.connector;

import java.util.Set;
import java.util.TreeSet;

public class NCLConnectorBase {

	Set<NCLCausalConnector> connectors = new TreeSet<NCLCausalConnector>();
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean addConnector(NCLCausalConnector connector)
	{
		return connectors.add(connector);
		
	}
	
	public boolean removeConnector(NCLCausalConnector connector)
	{
		return connectors.remove(connector);		
	}
	
	public boolean hasConnector(NCLCausalConnector connector)
	{
		return connectors.contains(connector);		
	}
	
	public boolean hasConnector()
	{
		return connectors.size() > 0;		
	}
	
	public Iterable<NCLCausalConnector> getConnectors()
	{
		return connectors;		
	}
	
}
