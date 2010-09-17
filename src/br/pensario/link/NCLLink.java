package br.pensario.link;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentification;
import br.pensario.connector.NCLCausalConnector;
import br.pensario.connector.NCLConnectorParam;

public class NCLLink {

	private NCLIdentification id;
	private NCLCausalConnector xconnector;
	
	private Set<NCLLinkParam> linkParams = new TreeSet<NCLLinkParam>();
	private Set<NCLBind> binds = new TreeSet<NCLBind>();
	
	
	public NCLLink(NCLCausalConnector xconnector, NCLBind bind1, NCLBind bind2) throws Exception {
		if (!setXconnector(xconnector) || !addBind(bind1) || !addBind(bind2)){
			Exception ex = new Exception("Invalid xconnector");
			throw ex;
		}
	}
	
	public boolean setId(String id) {
		try{
			this.id = new NCLIdentification(id);
			return true;
		}
		catch(Exception ex){
			System.err.println(ex);
			return false;
		}
	}
	
	public String getId() {
		return id.toString();
	}
	
	public boolean hasId() {
		if (id != null)
			return true;
		else
			return false;
	}
	
	public boolean setXconnector(NCLCausalConnector xconnector) {
		if (xconnector != null){
			this.xconnector = xconnector;
			return true;
		}
		else
			return false;
	}
	
	public NCLCausalConnector getXconnector() {
		return xconnector;
	}
	
	/**
	 * retorna true se o parametro foi substituido e falso se nao
	 */
	public boolean addLinkParam(NCLLinkParam linkParam) {
		boolean contains = false;

		if (!hasLinkParam(linkParam.getName()))
			contains = true;

		linkParams.add(linkParam);

		return contains;
	}

	public boolean removeLinkParam(String name) {
		Iterator<NCLLinkParam> it = linkParams.iterator();

		while (it.hasNext()) {
			NCLLinkParam p = it.next();

			if (p.getName().equals(name)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasLinkParam(NCLConnectorParam name) {
		Iterator<NCLLinkParam> it = linkParams.iterator();

		while (it.hasNext()) {
			NCLLinkParam p = it.next();

			if (p.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean hasLinkParam() {
		return(!linkParams.isEmpty());
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

	public boolean removeBind(NCLBind bind) {//TODO: receber o bind ou alguma identificacao?
		Iterator<NCLBind> it = binds.iterator();

		while (it.hasNext()) {
			NCLBind b = it.next();

			if (b.equals(bind) && numBinds() > 2) {//so remove se tiver mais de 2 binds
				it.remove();
				return true;
			}
		}
		return false;
	}

	public boolean hasBind(NCLBind bind) {//TODO: receber o bind ou alguma identificacao?
		Iterator<NCLBind> it = binds.iterator();

		while (it.hasNext()) {
			NCLBind b = it.next();

			if (b.equals(bind))
				return true;
		}
		return false;
	}
	
	public int numBinds() {
		return(binds.size());
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
			content += "<!-- Link element parameters -->\n";
			
			Iterator<NCLLinkParam> it = linkParams.iterator();
			while (it.hasNext()) {
				NCLLinkParam p = it.next();
				content += p.parse(ident+1);
			}
		}
		
		content += "<!-- Link element binds -->\n";
		
		Iterator<NCLBind> it = binds.iterator();
		while (it.hasNext()) {
			NCLBind b = it.next();
			content += b.parse(ident+1);
		}
		
		
		// <link> element end declaration
		content += space + "</link>\n";
		
		return content;
	}
}
