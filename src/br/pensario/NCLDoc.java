package br.pensario;

import br.pensario.NCLValues.NCLNamespace;

public class NCLDoc {

	private String id;
	private String title;
	private NCLNamespace xmlns;
	
	private NCLHead head;
	private NCLBody body;	
	
	//TODO - colocar um construtor com NCLNamespace padrao
	public NCLDoc(String id, NCLNamespace xmlns, NCLHead head ,NCLBody body) throws Exception {
		
		setId(id);
		setXmlns(xmlns);
		setHead(head);
		setBody(body);
		
		/*{
			Exception ex = new Exception("Invalid doc formation");
			throw ex;
		}*/
	}	

	/**
	 * Atribui um id ao documento.
	 * 
	 * @param id String Identificador (compatível com NCLIdentification).
	 * @throws IllegalArgumentException Se o id for inválido.
	 */
	public void setId(String id)  throws Exception{
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	
	/**
	 * Retorna o identificador do documento.
	 * @return Identificador
	 */
	public String getId() {
		return id;
	}
	
	//Obs.: Nao gera excecao pois o titulo pode ser nulo
	/**
	 * Atribui um título ao documento.
	 * 
	 * @param title String Título.
	 */
	public boolean setTitle(String title) {	
		this.title = title;
		return true;
	}
		
	/**
	 * Retorna o título do documento NCL.
	 * @return Título
	 */
	public String getTitle() {
		return title;
	}
	
	
	/**
	 * Indica se o documento possui um título.
	 * @return Verdadeiro caso possua um título.
	 */
	//REV: Método desnecessário, pois é suplementar ao getTitle
	public boolean hasTitle() {
		if (title != null && !"".equals(title.trim()))
			return true;
		else
			return false;
	}
	
	/**
	 * Atribui um namespace ao documento NCL.
	 * 
	 * @param xmlns String Namespace.
	 */	
	//REV: deve ser definido se uma exceção será gerada.
	public boolean setXmlns(NCLNamespace xmlns) {
		if (xmlns != null){
			this.xmlns = xmlns;
			return true;
		}
		else
			return false;
	}
	
	
	/**
	 * Retorna o namespace do documento NCL.
	 * @return Namespace
	 */
	public NCLNamespace getXmlns() {
		return xmlns;
	}
	
	/**
	 * Atribui um cabeçalho ao documento.
	 * 
	 * @param head String Cabeçalho NCL.
	 */		
	public boolean setHead(NCLHead head) {
		if (head != null){
			this.head = head;
			return true;
		}
		else
			return false;
	}

	/**
	 * Retorna o cabeçalho do documento NCL.
	 * @return Cabeçalho NCL.
	 */
	public NCLHead getHead() {
		return head;
	}
	
	/**
	 * Atribui um corpo ao documento.
	 * 
	 * @param body String Corpo NCL.
	 */		
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
	
	/**
	 * Retorna a representação do elemento em XML.
	 * @return Trecho XML referente ao elemento
	 */
	public String parse() {
		String content;
		
		// XML document start declaration
		content = "<?xml version='1.0' encoding='ISO-8859-1'?>\n"; //TODO: ou UTF8??
		content += "<!-- Generated with NCL API -->\n\n";
		
		
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
	
	public String toString()
	{
		return parse();
	}
}
