package br.pensario.descriptor;

import br.pensario.NCLIdentification;
import br.pensario.NCLValues;
import br.pensario.region.NCLRegion;

public class NCLDescriptor implements Comparable{

	private String id;

	private String player;
	private String explicitDur; 
	
	private Boolean freeze;

	private NCLDescriptor moveLeft; 
									
	private NCLDescriptor moveRight;
	private NCLDescriptor moveUp;
	private NCLDescriptor moveDown;

	private Integer focusIndex;
	private NCLValues.NCLColor focusBorderColor;
	private Integer focusBorderWidth;
	private Integer focusBorderTransparency;
	private String focusSrc;
	private String focusSelSrc;

	private NCLValues.NCLColor selBorderColor;

	private NCLRegion region;
	
	/**
	 * Construtor padrão
	 * @param id Identificador do descritor
	 * @throws Exception Caso o identificador não seja conformante com NCLIdentification.validate()
	 */
	public NCLDescriptor(String id) throws Exception
	{
		NCLIdentification.validate(id);
		setId(id);		
	}	

	/**
	 * Identifica a ferramenta de apresentação utilizada
	 * @param player Nome da ferramenta
	 */
	public void setPlayer(String player) {
		this.player = player;
	}

	/**
	 * Retorna qual ferramenta de apresentação é utilizada pelo descritor
	 * @return Nome da ferramenta
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * Atribui uma duração ao descritor
	 * @param explicitDur Duração
	 */
	//TODO - verificar se é um "timing"
	public void setExplicitDur(String explicitDur) {
		this.explicitDur = explicitDur;
	}

	/**
	 * Retorna a duração do descritor
	 * @return Duração
	 */
	public String getExplicitDur() {
		return explicitDur;
	}

	/**
	 * Indica se ???
	 * @param freeze
	 */
	public void setFreeze(Boolean freeze) {
		this.freeze = freeze;
	}

	/**
	 * Indica se ???
	 * @param freeze
	 */
	public Boolean isFreeze() {
		return freeze;
	}

	/**
	 * Atribui qual o próximo descritor para quando a seta para a esquerda for pressionada.
	 * @param descriptor Descritor 
	 */
	public void setMoveLeft(NCLDescriptor descriptor) {
		this.moveLeft = descriptor;
	}
	
	/**
	 * Retorna qual o próximo descritor de foco quando a seta para esquerda for pressionada
	 * 
	 * @return Descritor
	 */

	public NCLDescriptor getMoveLeft() {
		return moveLeft;
	}

	/**
	 * Retorna qual o próximo descritor de foco quando a seta para direitafor pressionada
	 * 
	 * @return Descritor
	 */
	
	public NCLDescriptor getMoveRight() {
		return moveRight;
	}


	/**
	 * Atribui qual o próximo descritor para quando a seta para a direita for pressionada.
	 * @param descriptor Descritor 
	 */	
	public void setMoveRight(NCLDescriptor descriptor) {
		this.moveRight = descriptor;
	}

	/**
	 * Retorna qual o próximo descritor de foco quando a seta para cima for pressionada
	 * 
	 * @return Descritor
	 */
	public NCLDescriptor getMoveUp() {
		return moveUp;
	}


	/**
	 * Atribui qual o próximo descritor para quando a seta para a cima for pressionada.
	 * @param descriptor Descritor 
	 */
	
	public void setMoveUp(NCLDescriptor descriptor) {
		this.moveUp = descriptor;
	}

	
	/**
	 * Retorna qual o próximo descritor de foco quando a seta para baixo for pressionada
	 * 
	 * @return Descritor
	 */
	public NCLDescriptor getMoveDown() {
		return moveDown;
	}

	/**
	 * Atribui qual o próximo descritor para quando a seta para baixo for pressionada.
	 * @param descriptor Descritor 
	 */	
	public void setMoveDown(NCLDescriptor descriptor) {
		this.moveDown = descriptor;
	}	
	
	
	/**
	 * Retorna qual o índice de foco atribuído ao descritor
	 * @return Inteiro relativo ao índice de foco
	 */
	public Integer getFocusIndex() {
		return focusIndex;
	}

	/**
	 * Atribui um novo índice de foco para o descritor.
	 * @param focusIndex Índice de foco
	 */
	public void setFocusIndex(Integer focusIndex) {
		this.focusIndex = focusIndex;
	}

	/**
	 * Retorna qual a cor de borda utilizada pelo descritor quando este está em foco.
	 * 
	 * @return Cor
	 */
	
	public NCLValues.NCLColor getFocusBorderColor() {
		return focusBorderColor;
	}

	/**
	 * Atribui a cor da borda do descritor quando este está em foco.
	 * 
	 * @param focusBorderColor Cor
	 */
	public void setFocusBorderColor(NCLValues.NCLColor focusBorderColor) {
		this.focusBorderColor = focusBorderColor;
	}

	/**
	 * Retorna a largura da borda que é apresentada quando o descritor está em foco 
	 * @return Inteiro relativo a largura da borda em pixels
	 */
	public Integer getFocusBorderWidth() {
		return focusBorderWidth;
	}

	/**
	 * Artribui a largura da borda que é apresentada quando o descritor está em foco
	 * @param focusBorderWidth Largura da borda
	 */
	public void setFocusBorderWidth(Integer focusBorderWidth) {
		this.focusBorderWidth = focusBorderWidth;
	}

	/**
	 * Retorna a transparência da borda que é apresentada quando o descritor está em foco 
	 * @return Percentagem relativa a transparência da borda
	 */
	public Integer getFocusBorderTransparency() {
		return focusBorderTransparency;
	}

	/**
	 * Atribui a transparência da borda que é apresentada quando o descritor está em foco
	 * @param focusBorderTransparency Percentagem relativa a transparência da borda
	 */
	public void setFocusBorderTransparency(Integer focusBorderTransparency) {
		this.focusBorderTransparency = focusBorderTransparency;
	}

	/**
	 * Retorna o conteúdo alternativo que é exibido quando o descritor está em foco 
	 * @return Caminho do conteúdo alternativo
	 */
	public String getFocusSrc() {
		return focusSrc;
	}

	/**
	 * Atribui o conteúdo alternativo que é exibido quando o descritor está em foco
	 * @param focusSrc Caminho do conteúdo alternativo
	 */
	public void setFocusSrc(String focusSrc) {
		this.focusSrc = focusSrc;
	}

	/**
	 * Retorna o conteúdo alternativo que é exibido quando o descritor é selecionado
	 * @return Caminho do conteúdo alternativo
	 */	
	public String getFocusSelSrc() {
		return focusSelSrc;
	}

	/**
	 * Atribui o conteúdo alternativo que é exibido quando o descritor é selecionado
	 * @param focusSelSrc Caminho do conteúdo alternativo
	 */
	public void setFocusSelSrc(String focusSelSrc) {
		this.focusSelSrc = focusSelSrc;
	}

	public NCLValues.NCLColor getSelBorderColor() {
		return selBorderColor;
	}

	/**
	 * Atribui a cor da borda que é exibida quando o descritor é selecionado
	 * @param selBorderColor Cor da borda
	 */
	public void setSelBorderColor(NCLValues.NCLColor selBorderColor) {
		this.selBorderColor = selBorderColor;
	}

	/**
	 * Atribui uma região ao descritor NCL
	 * @param region Região NCL
	 */
	public void setRegion(NCLRegion region) {
		this.region = region;
	}

	/**
	 * Retorna a região NCL relacionada ao descritor NCL
	 * @return Região NCL
	 */
	public NCLRegion getRegion() {
		return region;
	}

	//TODO - será colocado na superclasse
	public String getId() {
		return id;
	}

	//TODO - será colocado na superclasse
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna a representação do elemento em XML.
	 * @return Trecho XML referente ao elemento
	 */
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<descriptor";
		content += " id='" + getId() + "'";
	
		if (getRegion() != null)
			content += " region='" + getRegion().getId() + "'";

		if (getExplicitDur() != null)
			content += " explicitDur='" + getExplicitDur() + "'";

		if (isFreeze() != null)
			content += " freeze='" + isFreeze() + "'";

		if (getPlayer() != null)
			content += " player='" + getPlayer() + "'";

		if (getMoveLeft() != null)
			content += " moveLeft='" + getMoveLeft().getId() + "'";

		if (getMoveRight() != null)
			content += " moveRight='" + getMoveRight().getId() + "'";
		
		if (getMoveDown() != null)
			content += " moveDown='" + getMoveDown().getId() + "'";
		
		if (getMoveUp() != null)
			content += " moveUp='" + getMoveUp().getId() + "'";
		
		if (getFocusIndex() != null)
			content += " focusIndex='" + getFocusIndex() + "'";

		if (getFocusBorderColor() != null)
			content += " focusBorderColor='" + getFocusBorderColor() + "'";

		if (getFocusBorderWidth() != null)
			content += " focusBorderWidth='" + getFocusBorderWidth() + "'";

		if (getFocusBorderTransparency() != null)
			content += " focusBorderTransparency='" + getFocusBorderTransparency() + "'";

		if (getFocusSrc() != null)
			content += " focusSrc='" + getFocusSrc() + "'";
		
		if (getFocusSelSrc() != null)
			content += " focusSelSrc='" + getFocusSelSrc() + "'";

		if (getSelBorderColor() != null)
			content += " focusSelBorderColor='" + getSelBorderColor() + "'";

		
		content += " />\n";

		return content;
	}
	
	public int compareTo(Object arg0) {
		return getId().compareTo(((NCLDescriptor) arg0).getId());
	}

}
