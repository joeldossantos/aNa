package br.pensario.link;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.connector.NCLRole;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLInterface;
import br.pensario.node.NCLNode;

public class NCLBind {

	private NCLRole role;
	private NCLNode component;
	private NCLInterface interfac;
	private NCLDescriptor descriptor;
	
	private Set<NCLBindParam> bindParams = new TreeSet<NCLBindParam>();
	
	
	public NCLBind(NCLRole role, NCLNode component) throws Exception {
		if (!setRole(role) || !setComponent(component)){
			Exception ex = new Exception("invalid role or component");
			throw ex;
		}
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
	
	public boolean setComponent(NCLNode component) {
		if (component != null){
			this.component = component;
			return true;
		}
		else
			return false;
	}
	
	public NCLNode getComponent() {
		return component;
	}
	
	public boolean setInterface(NCLInterface interfac) {
		if (interfac != null){
			this.interfac = interfac;
			return true;
		}
		else
			return false;
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
	
	public boolean setDescriptor(NCLDescriptor descriptor) {
		if (descriptor != null){
			this.descriptor = descriptor;
			return true;
		}
		else
			return false;
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
	public boolean addBindParam(NCLBindParam bindParam) {
		boolean contains = false;

		if (!hasBindParam(bindParam.getName()))
			contains = true;

		bindParams.add(bindParam);

		return contains;
	}

	public boolean removeBindParam(String name) {
		Iterator<NCLBindParam> it = bindParams.iterator();

		while (it.hasNext()) {
			NCLBindParam p = it.next();

			if (p.getName().equals(name)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasBindParam(String name) {
		Iterator<NCLBindParam> it = bindParams.iterator();

		while (it.hasNext()) {
			NCLBindParam p = it.next();

			if (p.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean hasBindParam() {
		return(!bindParams.isEmpty());
	}
	
	public boolean equals(NCLBind bind) {
		//bind tem role diferente?
		if (!bind.getRole().equals(getRole()))
			return false;
		//bind tem componente diferente?
		else if (!bind.getComponent().equals(getComponent()))
			return false;
		//bind ou this não tem interface ou são diferentes
		else if (!hasInterface() || !bind.hasInterface() || !bind.getInterface().equals(getInterface()))
			return false;
		else
			return true;
	}
	
	public String parse(int ident) {
		//a cada filho que entra adiciona 1 em ident
		return "";
	}
}
