package br.pensario.link;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLInterface;
import br.pensario.node.NCLNode;

public class NCLBind implements Comparable<NCLBind>{

	private NCLRole role;
	private NCLNode component;
	private NCLInterface interfac;
	private NCLDescriptor descriptor;
	
	private Set<NCLParam> bindParams = new TreeSet<NCLParam>();
	
	
	public NCLBind(NCLRole role, NCLNode component) throws Exception {
		setRole(role);
		setComponent(component);
	}
	
	public boolean setRole(NCLRole role) {
		if (role != null){
			this.role = role;
			return true;
		}
		else
			return false;
	}
	
	public NCLRole getRole() {
		return role;
	}
	
	public void setComponent(NCLNode component) throws Exception {
		if (component != null){
			this.component = component;
		}
		else{
			Exception ex = new NullPointerException("Null descriptor");
			throw ex;
		}
	}
	
	public NCLNode getComponent() {
		return component;
	}
	
	public void setInterface(NCLInterface interfac) throws Exception {
		if (interfac != null){
			this.interfac = interfac;
		}
		else{
			Exception ex = new NullPointerException("Null descriptor");
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
	
	public void setDescriptor(NCLDescriptor descriptor) throws Exception {
		if (descriptor != null){
			this.descriptor = descriptor;
		}
		else{
			Exception ex = new NullPointerException("Null descriptor");
			throw ex;
		}
	}
	
	public NCLDescriptor getDescriptor() {
		return descriptor;
	}
	
	public boolean hasDescriptor() {
		if (descriptor != null)
			return true;
		else
			return false;
	}
	
	/**
	 * retorna true se o parametro foi substituido e falso se nao
	 */
	public boolean addBindParam(NCLConnectorParam name, String value) throws Exception {
		boolean contains = false;
		NCLParam param = new NCLParam(name, value);

		if (!hasBindParam(name))
			contains = true;

		bindParams.add(param);

		return contains;
	}

	public boolean removeBindParam(NCLConnectorParam name) {
		Iterator<NCLParam> it = bindParams.iterator();

		while (it.hasNext()) {
			NCLParam p = it.next();

			if (p.getName().equals(name)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasBindParam(NCLConnectorParam name) {
		Iterator<NCLParam> it = bindParams.iterator();

		while (it.hasNext()) {
			NCLParam p = it.next();

			if (p.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean hasBindParam() {
		return(!bindParams.isEmpty());
	}
	
	public Iterable<NCLParam> getBindParams() {
		return bindParams;
	}
	
	public boolean equals(NCLBind bind) {
		//bind tem role diferente?
		if (!bind.getRole().equals(getRole()))
			return false;
		//bind tem componente diferente?
		if (!bind.getComponent().equals(getComponent()))
			return false;
		//bind ou this não tem interface ou são diferentes
		if (!hasInterface() || !bind.hasInterface() || !bind.getInterface().equals(getInterface()))
			return false;
		//bind ou this não tem descriptor ou são diferentes
		if (!hasDescriptor() || !bind.hasDescriptor() || !bind.getDescriptor().equals(getDescriptor()))
			return false;
		else
			return true;
	}
	
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <bind> element and attributes declaration
		content = space + "<bind";
		content += " role='" + getRole().getId().toString() + "'";
		content += " component='" + getComponent().getId() + "'";
		if (hasInterface())
			content += " interface='" + getInterface().getIdentifier() + "'";
		if (hasDescriptor())
			content += " descriptor='" + getDescriptor().getId() + "'";
		
		// <bind> element content
		if (hasBindParam()){
			content += ">\n";
//			content += "<!-- Bind element parameters -->\n";
			
			Iterator<NCLParam> it = bindParams.iterator();
			while (it.hasNext()) {
				NCLParam b = it.next();
				content += b.parse(ident+1, "bindParam");
			}
			
			// <bind> element end declaration
			content += space + "</bind>\n";
		}
		else
			content += "/>\n";
		
		return content;
	}
	
	public String toString(){
		return parse(0);
	}
	
	public int compareTo(NCLBind bind) {
		if (equals(bind))
			return 0;
		else
			return 1;
	}
}
