package br.pensario.connector;


import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLCausalConnector implements Comparable<NCLCausalConnector> {

	private String id;
	private NCLCondition condition;
	private NCLAction action;
	private Set<NCLConnectorParam> conn_params = new TreeSet<NCLConnectorParam>();

	public NCLCausalConnector(String id, NCLCondition condition,
			NCLAction action) {
		this.setId(id);
		this.setCondition(condition);
		this.setAction(action);
	}

	public NCLCondition getCondition() {
		return condition;
	}

	// REV: gerar excecao nos metodos set
	public void setCondition(NCLCondition condition) {
		this.condition = condition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NCLAction getAction() {
		return action;
	}

	public void setAction(NCLAction action) {
		this.action = action;
	}

	public NCLConnectorParam getConnectorParam(String name) {

		for (NCLConnectorParam connp : conn_params)
			if (connp.getName().equals(name))
				return connp;

		return null;
	}

	public void removeConnectorParam(String name) {

		Iterator<NCLConnectorParam> it = conn_params.iterator();

		while (it.hasNext()) {
			NCLConnectorParam connp = it.next();
			if (connp.getName().equals(name))
				it.remove();
		}
		
	}

	public void addConnectorParam(NCLConnectorParam param) {

		conn_params.add(param);
	}

	public String toString() {
		return parse(0);
	}

	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<causalConnector";

		if (getId() != null)
			content += " id='" + getId() + "'";

		content += ">\n";

		for(NCLConnectorParam connp : conn_params)
			content += connp.parse(ident + 1);
		
		content += getCondition().parse(ident + 1);
		content += getAction().parse(ident + 1);

		content += space + "<causalConnector/>\n";

		return content;
	}

	@Override
	public int compareTo(NCLCausalConnector cconn) {

		return cconn.getId().compareTo(this.getId());

	}

}
