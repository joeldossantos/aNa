package br.pensario.node;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.interfaces.NCLProperty;
import br.pensario.interfaces.NCLPort;
import br.pensario.link.NCLLink;

public class NCLContext extends NCLNode {

	private Set<NCLPort> ports = new TreeSet<NCLPort>();
	private Set<NCLProperty> properties = new TreeSet<NCLProperty>();
	private Set<NCLNode> nodes = new TreeSet<NCLNode>();
	private Set<NCLLink> links = new TreeSet<NCLLink>();
	
	
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

		nodes.add(node);

		return contains;
	}

	public boolean removeNode(String id) {
		Iterator<NCLNode> it = nodes.iterator();

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
		Iterator<NCLNode> it = nodes.iterator();

		while (it.hasNext()) {
			NCLNode n = it.next();

			if (n.getId().equals(id))
				return true;
		}
		return false;
	}
	
	public boolean hasNode() {
		return(!nodes.isEmpty());
	}
	
	/**
	 * retorna true se o link foi substituido e falso se nao
	 */
	public boolean addLink(NCLLink link) {
		boolean contains = false;

		if (!hasLink(link))
			contains = true;

		links.add(link);

		return contains;
	}

	public boolean removeLink(NCLLink link) {//TODO: receber link ou algum identificador??
		Iterator<NCLLink> it = links.iterator();

		while (it.hasNext()) {
			NCLLink l = it.next();

			if (l.equals(link)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasLink(NCLLink link) {//TODO: receber link ou algum identificador??
		Iterator<NCLLink> it = links.iterator();

		while (it.hasNext()) {
			NCLLink l = it.next();

			if (l.equals(link))
				return true;
		}
		return false;
	}
	
	public boolean hasLink() {
		return(!links.isEmpty());
	}
	
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <context> element and attributes declaration
		content = space + "<context";
		content += " id='" + getId() + "'";
		
		// Test if the media has content
		if (hasPort() || hasProperty() || hasNode() || hasLink()){
			content += ">\n";
			
			
			// <context> element content
			if (hasPort()){
				content += "<!-- Context element ports -->\n";
				
				Iterator<NCLPort> it = ports.iterator();
				while (it.hasNext()) {
					NCLPort p = it.next();
					content += p.parse(ident+1);
				}
			}
			
			if (hasProperty()){
				content += "<!-- Context element properties -->\n";
				
				Iterator<NCLProperty> it = properties.iterator();
				while (it.hasNext()) {
					NCLProperty p = it.next();
					content += p.parse(ident+1);
				}
			}
			
			if (hasNode()){
				content += "<!-- Context element nodes -->\n";
				
				Iterator<NCLNode> it = nodes.iterator();
				while (it.hasNext()) {
					NCLNode n = (NCLNode) it.next();
					content += n.parse(ident+1);
				}
			}
			
			if (hasLink()){
				content += "<!-- Context element links -->\n";
				
				Iterator<NCLLink> it = links.iterator();
				while (it.hasNext()) {
					NCLLink l = (NCLLink) it.next();
					content += l.parse(ident+1);
				}
			}
			
			
			// <context> element end declaration
			content += space + "</context>\n";
		}
		else
			content += "/>\n";
		
		return content;
	}
}
