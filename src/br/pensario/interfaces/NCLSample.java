package br.pensario.interfaces;

import br.pensario.NCLValues.NCLSampleType;

public class NCLSample {

	private int value;
	private NCLSampleType type;
	
	
	public NCLSample(int value, NCLSampleType type) throws Exception {
		if (!setType(type) || !setValue(value)){
			Exception ex = new Exception("Invalid sample value");
			throw ex;
		}
	}
	
	public boolean setValue(int value) {
		if (value >= 0){
			this.value = value;
			return true;
		}
		else
			return false;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean setType(NCLSampleType type) {
		if (type != null){
			this.type = type;
			return true;
		}
		else
			return false;
	}
	
	public NCLSampleType getType() {
		return type;
	}
	
	public String toString() {
		switch (type){
		case S:
			return value + "s";
		case F:
			return value + "f";
		case NPT:
			return value + "npt";
		default:
			return "";
		}
	}
}
