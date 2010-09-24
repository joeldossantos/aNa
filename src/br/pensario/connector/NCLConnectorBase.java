package br.pensario.connector;

import java.util.Set;
import java.util.TreeSet;

import br.pensario.descriptor.NCLDescriptor;

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
	
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<connectorBase ";

		if (getId() != null)
			content += " id='" + getId();

		content += "'>\n";

		if (hasConnector())

			for (NCLCausalConnector connector: getConnectors())
				content += connector.parse(ident + 1);

		content += space + "<connectorBase/>\n";

		return content;
	}

	public String toString() {
		return parse(0);
	}
	
}
