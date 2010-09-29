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
	
	public boolean addCausalConnector(NCLCausalConnector connector)
	{
		return connectors.add(connector);
		
	}
	
	public boolean removeCausalConnector(NCLCausalConnector connector)
	{
		return connectors.remove(connector);		
	}
	
	public boolean hasCausalConnector(NCLCausalConnector connector)
	{
		return connectors.contains(connector);		
	}
	
	public boolean hasCausalConnector()
	{
		return connectors.size() > 0;		
	}
	
	public Iterable<NCLCausalConnector> getCausalConnectors()
	{
		return connectors;		
	}
	
	public NCLCausalConnector getCausalConnector(String conn_id)
	{
		NCLCausalConnector rconn = null;
		
		for(NCLCausalConnector cconn : connectors)
			if(cconn.getId().equals(conn_id)) rconn = cconn;
		
		return rconn;
	}
	
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<connectorBase ";

		if (getId() != null)
			content += " id='" + getId() + "'";

		content += ">\n";

		if (hasCausalConnector())

			for (NCLCausalConnector connector: getCausalConnectors())
				content += connector.parse(ident + 1);

		content += space + "<connectorBase/>\n";

		return content;
	}

	public String toString() {
		return parse(0);
	}
	
}
