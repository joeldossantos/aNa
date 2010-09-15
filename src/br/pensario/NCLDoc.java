package br.pensario;

import br.pensario.NCLValues.NCLNamespace;

public class NCLDoc {

	private String id;
	private String title;
	private NCLNamespace xmlns;
	
	private NCLHead head;
	private NCLBody body;
	
	
	public NCLDoc(String id, NCLNamespace xmlns, NCLHead head ,NCLBody body) throws Exception {
		if (!setId(id) || !setXmlns(xmlns) || !setHead(head) || !setBody(body)){
			Exception ex = new Exception("Invalid doc formation");
			throw ex;
		}
	}
	
	public boolean setId(String id) {
		//TODO: colocar a validacao do id
		this.id = id;
		return true;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean setTitle(String title) {
		//TODO: colocar a validacao do id
		this.title = title;
		return true;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean hasTitle() {
		if (title != null)
			return true;
		else
			return false;
	}
	
	public boolean setXmlns(NCLNamespace xmlns) {
		if (xmlns != null){
			this.xmlns = xmlns;
			return true;
		}
		else
			return false;
	}
	
	public NCLNamespace getXmlns() {
		return xmlns;
	}
	
	public boolean setHead(NCLHead head) {
		if (head != null){
			this.head = head;
			return true;
		}
		else
			return false;
	}

	public NCLHead getHead() {
		return head;
	}
	
	public boolean setBody(NCLBody body) {
		if (body != null){
			this.body = body;
			return true;
		}
		else
			return false;
	}
	
	public NCLBody getBody() {
		return body;
	}
	
	public String parse() {
		String content;
		
		// XML document start declaration
		content = "<?xml version='1.0' encoding='ISO-8859-1'?>\n"; //TODO: ou UTF8??
		content += "<!-- Created with NCL API -->\n\n";
		
		
		// <ncl> element and attributes declaration
		content += "<ncl";
		content += " id='" + getId() + "'";
		if (hasTitle())
			content += " title='" + getTitle() + "'";
		content += " xmlns='" + getXmlns() + "'";
		content += ">\n";
		
		
		// <ncl> element content
		content += getHead().parse(1);
		content += getBody().parse(1);
		
		
		// <ncl> element end declaration
		content += "</ncl>\n";
		
		return content;
	}
}
