package br.pensario.link;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentification;
import br.pensario.connector.NCLCausalConnector;
import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLInterface;
import br.pensario.node.NCLNode;

public class NCLLink implements Comparable<NCLLink>{

	private String id;
	private NCLCausalConnector xconnector;
	
	private Set<NCLParam> linkParams = new TreeSet<NCLParam>();
	private Set<NCLBind> binds = new TreeSet<NCLBind>();
	
	
	public NCLLink(NCLCausalConnector xconnector, NCLBind bind1, NCLBind bind2) throws Exception {
		setXconnector(xconnector);
		addBind(bind1);
		addBind(bind2);
	}
	
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean hasId() {
		if (id != null)
			return true;
		else
			return false;
	}
	
	public void setXconnector(NCLCausalConnector xconnector) throws Exception {
		if (xconnector != null){
			this.xconnector = xconnector;
		}
		else{
			Exception ex = new NullPointerException("Null xconnector");
			throw ex;
		}
	}
	
	public NCLCausalConnector getXconnector() {
		return xconnector;
	}
	
	/**
	 * retorna true se o parametro foi substituido e falso se nao
	 * Excecao se os valores forem nulos
	 */
	public boolean addLinkParam(NCLConnectorParam name, String value) throws Exception {
		boolean contains = false;
		NCLParam param = new NCLParam(name, value);

		if (!hasLinkParam(name))
			contains = true;

		linkParams.add(param);

		return contains;
	}

	public boolean removeLinkParam(NCLConnectorParam name) {
		Iterator<NCLParam> it = linkParams.iterator();

		while (it.hasNext()) {
			NCLParam p = it.next();

			if (p.getName().equals(name.getName())) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasLinkParam(NCLConnectorParam name) {
		Iterator<NCLParam> it = linkParams.iterator();

		while (it.hasNext()) {
			NCLParam p = it.next();

			if (p.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean hasLinkParam() {
		return(!linkParams.isEmpty());
	}
	
	public Iterable<NCLParam> getLinkParams() {
		return linkParams;
	}
	
	public int numLinkParams() {
		return(linkParams.size());
	}
	
	/**
	 * retorna true se o bind foi substituido e falso se nao
	 */
	public boolean addBind(NCLBind bind) {
		boolean contains = false;

		if (!hasBind(bind))
			contains = true;

		binds.add(bind);

		return contains;
	}

	public boolean removeBind(NCLBind bind) {
		Iterator<NCLBind> it = binds.iterator();

		while (it.hasNext()) {
			NCLBind b = it.next();

			//so remove se tiver mais de 2 binds
			if (b.equals(bind) && numBinds() > 2) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean removeBind(NCLRole role, NCLNode component, NCLInterface interfac, NCLDescriptor descriptor) {
		try{
			NCLBind b = new NCLBind(role, component);
			if (interfac != null)
				b.setInterface(interfac);
			if (descriptor != null)
				b.setDescriptor(descriptor);
			
			return removeBind(b);
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean hasBind(NCLBind bind) {
		Iterator<NCLBind> it = binds.iterator();

		while (it.hasNext()) {
			NCLBind b = it.next();

			if (b.equals(bind))
				return true;
		}
		return false;
	}
	
	public boolean hasBind(NCLRole role, NCLNode component, NCLInterface interfac, NCLDescriptor descriptor) {
		try{
			NCLBind b = new NCLBind(role, component);
			if (interfac != null)
				b.setInterface(interfac);
			if (descriptor != null)
				b.setDescriptor(descriptor);
			
			return hasBind(b);
		}
		catch (Exception e){
			return false;
		}
	}
	
	public Iterable<NCLBind> getBinds() {
		return binds;
	}
	
	public int numBinds() {
		return(binds.size());
	}
	
	public boolean equals(NCLLink link) {
		// Link tem id? é diferente?
		if (!hasId() || !link.hasId() || !getId().equals(link.getId()))
			return false;
		// The xconnector attributes are different
		if (!getXconnector().equals(link.getXconnector()))
			return false;
		//TODO - testar link params
		if (numLinkParams() != link.numLinkParams())
			return false;
		// tem o mesmo numero de binds
		if (numBinds() != link.numBinds())
			return false;
		// testa se os binds sao iguais
		if (!compareBinds(link))
			return false;
		else
			return true;
	}
	
	private boolean compareBinds(NCLLink link) {
		int equalBinds = 0;
		Iterator<NCLBind> bindsIterator = binds.iterator();

		while (bindsIterator.hasNext()){
			NCLBind bind = bindsIterator.next();

			Iterator<NCLBind> it = binds.iterator();
			
			while (it.hasNext()){
				NCLBind b = it.next();
				
				if (bind.equals(b))
					equalBinds++;
			}
		}
		
		if (equalBinds == numBinds())
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
		
		
		// <link> element and attributes declaration
		content = space + "<link";
		if (hasId())
			content += " id='" + getId() + "'";
		content += " xconnector='" + getXconnector().getId() + "'";
		content += ">\n";
		
		
		// <link> element content
		if (hasLinkParam()){
//			content += "<!-- Link element parameters -->\n";
			
			Iterator<NCLParam> it = linkParams.iterator();
			while (it.hasNext()) {
				NCLParam p = it.next();
				content += p.parse(ident+1, "linkParam");
			}
		}
		
//		content += "<!-- Link element binds -->\n";
		
		Iterator<NCLBind> it = binds.iterator();
		while (it.hasNext()) {
			NCLBind b = it.next();
			content += b.parse(ident+1);
		}
		
		
		// <link> element end declaration
		content += space + "</link>\n";
		
		return content;
	}
	
	public String toString() {
		return parse(0);
	}

	public int compareTo(NCLLink link) {
		if (equals(link))
			return 0;
		else
			return 1;
	}
}
