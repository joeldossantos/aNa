package br.pensario.node;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.interfaces.NCLPort;
import br.pensario.interfaces.NCLProperty;

public class NCLContext extends NCLNode {

	private Set<NCLPort> ports = new TreeSet<NCLPort>();
	private Set<NCLProperty> properties = new TreeSet<NCLProperty>();
	private Set<NCLNode> childs = new TreeSet<NCLNode>();
	
	
	public NCLContext(String id) throws Exception {
		if (!setId(id)){
			Exception ex = new Exception("Invalid id");
			throw ex;
		}
	}
	
	/**
	 * retorna true se a porta foi substituida e falso se nao
	 */
	public boolean addPort(NCLPort port) {
		boolean contains = false;

		if (!hasPort(port.getId()))
			contains = true;

		ports.add(port);

		return contains;
	}

	public boolean removePort(String id) {
		Iterator<NCLPort> it = ports.iterator();

		while (it.hasNext()) {
			NCLPort p = it.next();

			if (p.getId().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasPort(String id) {
		Iterator<NCLPort> it = ports.iterator();

		while (it.hasNext()) {
			NCLPort p = it.next();

			if (p.getId().equals(id))
				return true;
		}
		return false;
	}
	
	public boolean hasPort() {
		return(!ports.isEmpty());
	}
	
	/**
	 * retorna true se a propriedade foi substituida e falso se nao
	 */
	public boolean addProperty(NCLProperty property) {
		boolean contains = false;

		if (!hasProperty(property.getName()))
			contains = true;

		properties.add(property);

		return contains;
	}

	public boolean removeProperty(String name) {
		Iterator<NCLProperty> it = properties.iterator();

		while (it.hasNext()) {
			NCLProperty p = it.next();

			if (p.getName().equals(name)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasProperty(String name) {
		Iterator<NCLProperty> it = properties.iterator();

		while (it.hasNext()) {
			NCLProperty p = it.next();

			if (p.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean hasProperty() {
		return(!properties.isEmpty());
	}
	
	/**
	 * retorna true se o no foi substituido e falso se nao
	 */
	public boolean addNode(NCLNode node) {
		boolean contains = false;

		if (!hasNode(node.getId()))
			contains = true;

		childs.add(node);

		return contains;
	}

	public boolean removeNode(String id) {
		Iterator<NCLNode> it = childs.iterator();

		while (it.hasNext()) {
			NCLNode n = it.next();

			if (n.getId().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasNode(String id) {
		Iterator<NCLNode> it = childs.iterator();

		while (it.hasNext()) {
			NCLNode n = it.next();

			if (n.getId().equals(id))
				return true;
		}
		return false;
	}
	
	public boolean hasNode() {
		return(!childs.isEmpty());
	}
	
	public String parse(int ident) {
		//a cada filho que entra adiciona 1 em ident
		return "";
	}
}
