package br.pensario.interfaces;

import br.pensario.NCLIdentification;

public class NCLArea extends NCLInterface {

	private NCLIdentification id;
	private int[] coords;
	private NCLTime begin;
	private NCLTime end;
	private String text;
	private int position = -1;
	private NCLSample first;
	private NCLSample last;
	private String label;
	
	
	public NCLArea(String id) throws Exception {
		if (!setId(id)){
			Exception ex = new Exception("Invalid id");
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
	
	public String getIdentifier(){
		return getId();
	}
	
	/*
	 * Assim, ele permite a definição de âncoras de conteúdo representando
	 * porções espaciais, através de atributo coords (como em XHTML);
	 * 
	 * The position and shape of an image map element on the screen.
	 * 
	 * x1,y1,x2,y2,...,xN,yN
	 *
	 */
	/**
	 * define ancora de conteudo representando porcoes espaciais.
	 * @param coords uma coordenada ver: NCLCoords
	 * @return true se cordenada existe falso contrario
	 */
	public boolean setCoords(int[] coords) {
		for (int i = 0; i < coords.length; i++){
			if (coords[i] < 0){
				return false;
			}
		}
		this.coords = coords;
		return true;
	}
	
	public int[] getCoords() {
		return coords;
	}
	
	public boolean hasCoords() {
		if (coords.length != 0)
			return true;
		else
			return false;
	}
	
	/**
	 * define ancora de conteudo representando porcoes temporais
	 * se a media for application/x-ginga-time o tempo deve ser completo
	 * @param begin um tempo de inicio ver:NCLTime
	 * @return true se tempo existe falso contrario
	 */
	public boolean setBegin(NCLTime begin) {
		if (begin != null){
			this.begin = begin;
			return true;
		}
		else
			return false;
	}
	
	public NCLTime getBegin() {
		return begin;
	}
	
	public boolean hasBegin() {
		if (begin != null)
			return true;
		else
			return false;
	}
	
	/**
	 * define ancora de conteudo representando porcoes temporais
	 * se a media for application/x-ginga-time o tempo deve ser completo
	 * @param end um tempo de fim ver:NCLTime
	 * @return true se tempo existe falso contrario
	 */
	public boolean setEnd(NCLTime end) {
		if (end != null){
			this.end = end;
			return true;
		}
		else
			return false;
	}
	
	public NCLTime getEnd() {
		return end;
	}
	
	public boolean hasEnd() {
		if (end != null)
			return true;
		else
			return false;
	}
	
	/**
	 * definicao de ancora textual
	 * @param text padrao de texto usado como ancora
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean hasText() {
		if (text != null)
			return true;
		else
			return false;
	}
	
	/**
	 * definicao de ancora textual
	 * @param position posicao do texto
	 * @return true se posicao valida falso contrario
	 */
	public boolean setPosition(int position) {
		if (position >= 0){
			this.position = position;
			return true;
		}
		else
			return false;
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean hasPosition() {
		if (position != -1)
			return true;
		else
			return false;
	}
	
	/**
	 * definicao de ancora de conteudo com base em amostra
	 * @param first inicio da ancora ver:NCLSample
	 * @return true se valido falso contrario
	 */
	public boolean setFirst(NCLSample first) {
		if (first != null){
			this.first = first;
			return true;
		}
		else
			return false;
	}
	
	public NCLSample getFirst() {
		return first;
	}
	
	public boolean hasFirst() {
		if (first != null)
			return true;
		else
			return false;
	}
	
	/**
	 * definicao de ancora de conteudo com base em amostra
	 * @param last fim da ancora var:NCLSample
	 * @return true se valido falso contrario
	 */
	public boolean setLast(NCLSample last) {
		if (last != null){
			this.last = last;
			return true;
		}
		else
			return false;
	}
	
	public NCLSample getLast() {
		return last;
	}
	
	public boolean hasLast() {
		if (last != null)
			return true;
		else
			return false;
	}
	
	/**
	 * definir uma ancora de conteudo baseada no atributo label,
	 * que especifica uma cadeia de caracteres que deve ser utilizada
	 * pelo exibidor de midias para identificar uma regiao de conteudo
	 * @param label string de identificacao
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public boolean hasLabel() {
		if (label != null)
			return true;
		else
			return false;
	}
	
	public String parse(int ident) {
		//TODO: Verificar os atributos que a ancora tem para fazer algum tipo de validacao?
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <area> element and attributes declaration
		content = space + "<area";
		content += " id='" + getId() + "'";
		if (hasCoords())
			content += " coords='" + coordsToString() + "'";
		if (hasBegin())
			content += " begin='" + getBegin().toString() + "'";
		if (hasEnd())
			content += " end='" + getEnd().toString() + "'";
		if (hasText())
			content += " text='" + getText() + "'";
		if (hasPosition())
			content += " position='" + getPosition() + "'";
		if (hasFirst())
			content += " first='" + getFirst().toString() + "'";
		if (hasLast())
			content += " last='" + getLast().toString() + "'";
		if (hasLabel())
			content += " label='" + getLabel() + "'";
		content += "/>\n";
		
		return content;
	}
	
	private String coordsToString() {
		String result = "";
		for (int i = 0; i < coords.length; i++){
			result += coords[i];
			if (i < coords.length - 1)
				result += ",";
		}
		return result;
	}
}
