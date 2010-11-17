package br.pensario;

import br.pensario.NCLValues.NCLNamespace;

public class NCLDoc extends NCLIdentifiable {

	private String title;
	private NCLNamespace xmlns;
	
	private NCLHead head;
	private NCLBody body;	
	
        
	/**
         * Construtor do documento NCL.
         * 
         * @param xmlns namespace usado pelo documento.
         * @throws java.lang.Exception se o atributo xmlns for nulo.
         */
	public NCLDoc(NCLNamespace xmlns) throws Exception {
		setXmlns(xmlns);
	}	

        
	/**
	 * Atribui um id ao documento.
	 * 
	 * @param id String Identificador (compatível com NCLIdentification).
	 * @throws IllegalArgumentException Se o id for inválido.
	 */
	public void setId(String id)  throws Exception{
		setIdentification(id);
	}
	
	
	/**
	 * Retorna o identificador do documento.
         * 
	 * @return Identificador
	 */
	public String getId() {
		return getIdentification();
	}
        
        
        /**
         * Verifica se o documento possui um id.
         * 
         * @return Verdadeiro caso possua um id.
         */
        public boolean hasId() {
            return (getIdentification() != null);
        }
	
	
	/**
	 * Atribui um título ao documento.
	 * 
	 * @param title String Título.
	 */
	public void setTitle(String title) throws Exception {
            if (title == null){
                Exception ex = new NullPointerException("Null title");
                throw ex;
            }
            if ("".equals(title.trim())){
                Exception ex = new IllegalArgumentException("Empty title");
                throw ex;
            }
            
            this.title = title;
	}
	
        
	/**
	 * Retorna o título do documento NCL.
         * 
	 * @return Título
	 */
	public String getTitle() {
		return title;
	}
	
	
	/**
	 * Indica se o documento possui um título.
         * 
	 * @return Verdadeiro caso possua um título.
	 */
	public boolean hasTitle() {
            return (title != null);
	}
	
        
	/**
	 * Atribui um namespace ao documento NCL.
	 * 
	 * @param xmlns String Namespace.
	 */
	public void setXmlns(NCLNamespace xmlns) throws Exception {
		if (xmlns == null){
                    Exception ex = new NullPointerException("Null xmlns");
                    throw ex;
		}
                
                this.xmlns = xmlns;
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
	public void setHead(NCLHead head) throws Exception {
		if (head == null){
                    Exception ex = new NullPointerException("Null head");
                    throw ex;
		}
                
                this.head = head;
	}

        
	/**
	 * Retorna o cabeçalho do documento NCL.
         * 
	 * @return Cabeçalho NCL.
	 */
	public NCLHead getHead() {
		return head;
	}
        
        
        /**
         * Verifica se o documento possui cabeçalho.
         * 
         * @return Verdadeiro se possuir cabeçalho.
         */
        public boolean hasHead() {
            return (head != null);
        }
	
        
	/**
	 * Atribui um corpo ao documento.
	 * 
	 * @param body Corpo NCL.
	 */		
	public void setBody(NCLBody body) throws Exception {
		if (head == null){
                    Exception ex = new NullPointerException("Null head");
                    throw ex;
		}
                
                this.body = body;
	}
	
        
        /**
         * Retorna o corpo de um documento NCL.
         * 
         * @return Corpo do documento NCL.
         */
	public NCLBody getBody() {
		return body;
	}
        
        
        /**
         * Verifica se o documento possui um corpo.
         * 
         * @return Verdadeiro se o documento possuir um corpo.
         */
        public boolean hasBody() {
            return (body != null);
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
