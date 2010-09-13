package br.pensario.descriptor;

import br.pensario.NCLIdentifiable;
import br.pensario.enums.NCLValues;
import br.pensario.region.NCLRegion;

public class NCLDescriptor extends NCLIdentifiable {

	//pegar valores padrão
	
	private String player;
	private String explicitDur;
	private boolean freeze = false;
	private NCLDescriptor moveLeft;
	private NCLDescriptor moveRight;

	private NCLDescriptor moveUp;
	private NCLDescriptor moveDown;
	private int focusIndex;
	private NCLValues.NCLColor focusBorderColor;
	private int focusBorderWidth;
	private int focusBorderTransparency;
	private String focusSrc;
	private String focusSelSrc;
	
	private NCLValues.NCLColor selBorderColor;
	
	private NCLRegion region;

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}

	public void setExplicitDur(String explicitDur) {
		this.explicitDur = explicitDur;
	}

	public String getExplicitDur() {
		return explicitDur;
	}

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}

	public boolean isFreeze() {
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

	public int getFocusIndex() {
		return focusIndex;
	}

	public void setFocusIndex(int focusIndex) {
		this.focusIndex = focusIndex;
	}

	public NCLValues.NCLColor getFocusBorderColor() {
		return focusBorderColor;
	}

	public void setFocusBorderColor(NCLValues.NCLColor focusBorderColor) {
		this.focusBorderColor = focusBorderColor;
	}

	public int getFocusBorderWidth() {
		return focusBorderWidth;
	}

	public void setFocusBorderWidth(int focusBorderWidth) {
		this.focusBorderWidth = focusBorderWidth;
	}

	public int getFocusBorderTransparency() {
		return focusBorderTransparency;
	}

	public void setFocusBorderTransparency(int focusBorderTransparency) {
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

}
