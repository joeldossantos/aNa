package br.pensario.descriptor;

import br.pensario.NCLValues;
import br.pensario.region.NCLRegion;

public class NCLDescriptor implements Comparable{

	// pegar valores padrao
	//: o que nao existe nao aparece no codigo XML, nao precisa setar um valor padrao para os atributos
	//RES: ok, então isto dependerá da implementação da máquina de execução, né?
	
	//REV: falta gerar excecao nos metodos de set

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

	
	public NCLDescriptor(String id)
	{
		setId(id);		
	}
	

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}

	//REV: usar o NCLTime??
	public void setExplicitDur(String explicitDur) {
		this.explicitDur = explicitDur;
	}

	public String getExplicitDur() {
		return explicitDur;
	}

	//: mudar para b minusculo
	//RES: n�o pois pode ser nulo
	public void setFreeze(Boolean freeze) {
		this.freeze = freeze;
	}

	//: mudar para b minusculo e criar metodos hasFreeze
	//RES: n�o pois pode ser nulo
	public Boolean isFreeze() {
		return freeze;
	}

	public void setMoveLeft(NCLDescriptor moveLeft) {
		this.moveLeft = moveLeft;
	}

	public NCLDescriptor getMoveLeft() {
		return moveLeft;
	}

	public NCLDescriptor getMoveRight() {
		return moveRight;
	}

	public void setMoveRight(NCLDescriptor moveRight) {
		this.moveRight = moveRight;
	}

	public NCLDescriptor getMoveUp() {
		return moveUp;
	}

	public void setMoveUp(NCLDescriptor moveUp) {
		this.moveUp = moveUp;
	}

	public NCLDescriptor getMoveDown() {
		return moveDown;
	}

	public void setMoveDown(NCLDescriptor moveDown) {
		this.moveDown = moveDown;
	}

	public Integer getFocusIndex() {
		return focusIndex;
	}

	public void setFocusIndex(Integer focusIndex) {
		this.focusIndex = focusIndex;
	}

	public NCLValues.NCLColor getFocusBorderColor() {
		return focusBorderColor;
	}

	public void setFocusBorderColor(NCLValues.NCLColor focusBorderColor) {
		this.focusBorderColor = focusBorderColor;
	}

	public Integer getFocusBorderWidth() {
		return focusBorderWidth;
	}

	public void setFocusBorderWidth(Integer focusBorderWidth) {
		this.focusBorderWidth = focusBorderWidth;
	}

	public Integer getFocusBorderTransparency() {
		return focusBorderTransparency;
	}

	public void setFocusBorderTransparency(Integer focusBorderTransparency) {
		this.focusBorderTransparency = focusBorderTransparency;
	}

	public String getFocusSrc() {
		return focusSrc;
	}

	public void setFocusSrc(String focusSrc) {
		this.focusSrc = focusSrc;
	}

	public String getFocusSelSrc() {
		return focusSelSrc;
	}

	public void setFocusSelSrc(String focusSelSrc) {
		this.focusSelSrc = focusSelSrc;
	}

	public NCLValues.NCLColor getSelBorderColor() {
		return selBorderColor;
	}

	public void setSelBorderColor(NCLValues.NCLColor selBorderColor) {
		this.selBorderColor = selBorderColor;
	}

	public void setRegion(NCLRegion region) {
		this.region = region;
	}

	public NCLRegion getRegion() {
		return region;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//: nao usar o get pra testar se eh nulo, criar metodos hasAlgumacoisa
	//RES: Ser� que esta � a melhor alternativa? 
	//pois pode ser documentado que o atributo fica null caso nao tenha sido setado (por isso o Boolean com B maiusculo)
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
