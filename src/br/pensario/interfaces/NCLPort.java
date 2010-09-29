package br.pensario.interfaces;

import br.pensario.NCLIdentification;
import br.pensario.node.NCLNode;

public class NCLPort extends NCLInterface implements Comparable<NCLPort> {

	private String id;
	private NCLNode component;
	private NCLInterface interfac;
	
	
	public NCLPort(String id, NCLNode component) throws Exception {
		setId(id);
		setComponent(component);
	}
	
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	public String getId() {
		return id.toString();
	}
	
	public String getIdentifier(){
		return getId();
	}
	
	/**
	 * define o component que a porta mapeia
	 * @param component componente relacionado pela porta
	 * @return true se valido falso contrario
	 */
	public void setComponent(NCLNode component) throws Exception {
		if (component != null){
			this.component = component;
		}
		else{
			Exception ex = new NullPointerException("Null component");
			throw ex;
		}
	}
	
	public NCLNode getComponent() {
		return component;
	}
	
	/**
	 * define uma interface do componente
	 * @param interfac interface do componente
	 * @return true se valido falso contrario
	 */
	public void setInterface(NCLInterface interfac) throws Exception {
		if (interfac != null){
			this.interfac = interfac;
		}
		else{
			Exception ex = new NullPointerException("Null component");
			throw ex;
		}
	}
	
	public NCLInterface getInterface() {
		return interfac;
	}
	
	public boolean hasInterface() {
		if (interfac != null)
			return true;
		else
			return false;
	}
	
	public boolean equals(NCLPort port) {
		if (getId().equals(port.getId()))
			return true;
		else
			return false;
	}
	
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <port> element and attributes declaration
		content = space + "<port";
		content += " id='" + getId() + "'";
		content += " component='" + getComponent().getId() + "'";
		if (hasInterface())
			content += " interface='" + getInterface().getIdentifier() + "'";
		content += "/>\n";
		
		return content;
	}
	
	public String toString() {
		return parse(0);
	}

	public int compareTo(NCLPort port) {
		return getId().compareTo(port.getId());
	}
}
