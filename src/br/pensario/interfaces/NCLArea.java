package br.pensario.interfaces;

import br.pensario.NCLIdentification;

/**
 * Classe representando o elemento area de NCL.
 */
public class NCLArea extends NCLInterface implements Comparable<NCLArea> {

	private String id;
	private int[] coords;
	private NCLTime begin;
	private NCLTime end;
	private String text;
	private int position = -1;
	private NCLSample first;
	private NCLSample last;
	private String label;
	
	
	/**
	 * Construtor da âncora.
	 * 
	 * @param id id da âncora.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public NCLArea(String id) throws Exception {
		setId(id);
	}
	
	
	/**
	 * Determina o id da âncora.
	 * 
	 * @param id id da âncora
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	
	/**
	 * Obtém o id da âncora.
	 * 
	 * @return String com o id da âncora.
	 */
	public String getId() {
		return id.toString();
	}
	
	
	/**
	 * Obtém o identificador da âncora, nesse caso seu id.
	 * 
	 * @return String com o id da âncora.
	 */
	public String getIdentifier(){
		return getId();
	}
	
	
	/**
	 * Define uma âncora de conteudo representando porções espaciais.
	 * x1,y1,x2,y2,...,xN,yN
	 * 
	 * @param coords coordenadas da âncora
	 * @throws Exception se a coordenada for inválida.
	 * 
	 */
	//TODO - @see NCLCoords -- colocar quando gerar a classe
	public void setCoords(int[] coords) throws Exception {
		for (int i = 0; i < coords.length; i++){
			if (coords[i] < 0){
				Exception ex = new IllegalArgumentException("Invalid coordenate: " + coords[i]);
				throw ex;
			}
		}
		this.coords = coords;
	}
	
	
	/**
	 * Obtém as coordenadas da âncora espacial.
	 * 
	 * @return Array de inteiros com as coordenadas.
	 */
	public int[] getCoords() {
		return coords;
	}
	
	
	/**
	 * Verifica se a âncora possui coordenadas espaciais.
	 * 
	 * @return True caso a âncora possua.
	 */
	public boolean hasCoords() {
		return (coords != null && coords.length != 0);
	}
	
	
	/**
	 * Define uma âncora de conteúdo representando porções temporais.
	 * Caso a media que contém a âncora for do tipo "application/x-ginga-time" o tempo deve ser completo.
	 * 
	 * @param begin um tempo de inicio.
	 * @throws NullPointerException se o tempo for nulo.
	 * @see NCLTime
	 */
	public void setBegin(NCLTime begin) throws Exception {
		if (begin != null){
			this.begin = begin;
		}
		else{
			Exception ex = new NullPointerException("Null begin");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o valor do atributo begin da âncora.
	 * 
	 * @return NCLTime com o valor
	 */
	public NCLTime getBegin() {
		return begin;
	}
	
	
	/**
	 * Verifica se a âncora possui o atributo begin.
	 * 
	 * @return True se a âncora possuir o atributo.
	 */
	public boolean hasBegin() {
		return (begin != null);
	}
	
	
	/**
	 * Define uma âncora de conteúdo representando porções temporais.
	 * Caso a media que contém a âncora for do tipo "application/x-ginga-time" o tempo deve ser completo.
	 * 
	 * @param end um tempo de fim.
	 * @throws NullPointerException se o tempo for nulo.
	 * @see NCLTime
	 */
	public void setEnd(NCLTime end) throws Exception {
		if (end != null){
			this.end = end;
		}
		else{
			Exception ex = new NullPointerException("Null end");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o atributo end da âncora.
	 * 
	 * @return NCLTime com o valor do atributo.
	 */
	public NCLTime getEnd() {
		return end;
	}
	
	
	/**
	 * Verifica se a âncora possui o atributo end.
	 * 
	 * @return True se a âncora possuir o atributo.
	 */
	public boolean hasEnd() {
		return (end != null);
	}
	
	
	/**
	 * Define uma ancora textual.
	 * 
	 * @param text padrao de texto usado como ancora.
	 * @throws NullPointerException se o texto for nulo.
	 */
	public void setText(String text) throws Exception {
		if (text != null){
			this.text = text;
		}
		else{
			Exception ex = new NullPointerException("Null text");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o atributo text da âncora.
	 * 
	 * @return String com o valor do atributo.
	 */
	public String getText() {
		return text;
	}
	
	
	/**
	 * Verifica se a âncora possui o atributo text.
	 * 
	 * @return Verdadeiro caso a âncora possua algum texto
	 */
	public boolean hasText() {
		return (text != null);
	}
	
	
	/**
	 * Define a posição do texto na ancora textual.
	 * 
	 * @param position posicao do texto
	 * @throws IllegalArgumentException se a posição for inválida.
	 */
	public void setPosition(int position) throws Exception {
		if (position >= 0){
			this.position = position;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid position");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o valor do atributo position.
	 * 
	 * @return int com o valor.
	 */
	public int getPosition() {
		return position;
	}
	
	
	/**
	 * Verifica se a âncora possui posição.
	 * 
	 * @return True se a âncora possui posição.
	 */
	public boolean hasPosition() {
		return (position != -1);
	}
	
	
	/**
	 * Define uma ancora de conteudo com base em uma amostra.
	 * 
	 * @param first inicio da ancora
	 * @throws NullPointerException se o inicio for nulo.
	 * @see NCLSample
	 */
	public void setFirst(NCLSample first) throws Exception {
		if (first != null){
			this.first = first;
		}
		else{
			Exception ex = new NullPointerException("Null first");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o atributo first da âncora.
	 * 
	 * @return NCLSample com o valor do atributo.
	 */
	public NCLSample getFirst() {
		return first;
	}
	
	
	/**
	 * Verifica se a âncora possui o atributo first.
	 * 
	 * @return True se a âncora possuir o atributo.
	 */
	public boolean hasFirst() {
		return (first != null);
	}
	
	
	/**
	 * define uma ancora de conteudo com base em uma amostra
	 * 
	 * @param last fim da ancora.
	 * @throws NullPointerException se a amostra for nula.
	 * @see NCLSample
	 */
	public void setLast(NCLSample last) throws Exception {
		if (last != null){
			this.last = last;
		}
		else{
			Exception ex = new NullPointerException("Null last");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o atributo last da âncora.
	 * 
	 * @return NCLSample com o valor do atributo.
	 */
	public NCLSample getLast() {
		return last;
	}
	
	
	/**
	 * Verifica se a âncora possui o atributo last.
	 * 
	 * @return True se o atributo estiver presente.
	 */
	public boolean hasLast() {
		return (last != null);
	}
	
	
	/**
	 * define uma ancora de conteudo baseada no atributo label,
	 * que especifica uma cadeia de caracteres que deve ser utilizada
	 * pelo exibidor de midias para identificar uma regiao de conteudo
	 * 
	 * @param label string de identificacao
	 * @throws NullPointerException se o label for nulo.
	 */
	public void setLabel(String label) throws Exception {
		if (label != null){
			this.label = label;
		}
		else{
			Exception ex = new NullPointerException("Null label");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o atributo label de uma âncora.
	 * 
	 * @return String com o valor do atributo.
	 */
	public String getLabel() {
		return label;
	}
	
	
	/**
	 * Verifica se a âncora possui o atributo label.
	 * 
	 * @return True se o atributo estiver presente.
	 */
	public boolean hasLabel() {
		return (label != null);
	}
	
	
	/**
	 * Verifica se duas âncoras são iguais.
	 * 
	 * @param area âncora com a qual comparar.
	 * @return True se as âncoras forem iguais.
	 */
	public boolean equals(NCLArea area) {
		return (getId().equals(area.getId()));
	}
	
	
	/**
	 * Cria o código XML da âncora.
	 * 
	 * @param ident nivel de indentação do código.
	 * @return String com o código XML.
	 */
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
	
	
	/**
	 * Transforma as coordenadas em uma string.
	 * 
	 * @return String representando as coordenadas espaciais.
	 */
	private String coordsToString() {
		String result = "";
		for (int i = 0; i < coords.length; i++){
			result += coords[i];
			if (i < coords.length - 1)
				result += ",";
		}
		return result;
	}
	
	
	/**
	 * Cria o código XML da âncora desconsiderando indentação.
	 * 
	 * @return Código XML da âncora.
	 */
	public String toString() {
		return parse(0);
	}
	
	
	/**
	 * Compara duas âncoras.
	 * 
	 * @param area âncora com a qual comparar.
	 * @return Inteiro indicando se as âncoras são iguais. O valor será 0 se as âncoras forem iguais.
	 */
	public int compareTo(NCLArea area) {
		return getId().compareTo(area.getId());
	}
}
