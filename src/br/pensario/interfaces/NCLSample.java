package br.pensario.interfaces;

import br.pensario.NCLValues.NCLSampleType;

public class NCLSample {

	private int value;
	private NCLSampleType type;
	
	
	public NCLSample(int value, NCLSampleType type) throws Exception {
		setType(type);
		setValue(value);
	}
	
	public void setValue(int value) throws Exception {
		if (value >= 0){
			this.value = value;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid value");
			throw ex;
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public void setType(NCLSampleType type) throws Exception {
		if (type != null){
			this.type = type;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid type");
			throw ex;
		}
	}
	
	public NCLSampleType getType() {
		return type;
	}
	
	public boolean equals(NCLSample sample) {
		if (getValue() != sample.getValue())
			return false;
		if (!getType().equals(sample.getType()))
			return false;
		else
			return true;
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
