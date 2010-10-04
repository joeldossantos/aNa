package br.pensario.interfaces;

import br.pensario.NCLValues.NCLSampleType;

/**
 * Amostra de uma âncora de acordo com a norma NCL.
 * A âncora pode ser um frame, um tempo em segundos ou em npt.
 */
public class NCLSample {

	private int value;
	private NCLSampleType type;
	
	
	/**
	 * Construtor da amostra possuindo um valor e um tipo.
	 * 
	 * @param value Inteiro com o valor da amostra.
	 * @param type NCLSampleType com o tipo da amostra.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public NCLSample(int value, NCLSampleType type) throws Exception {
		setType(type);
		setValue(value);
	}
	
	
	/**
	 * Determina o valor da amostra.
	 * 
	 * @param value Inteiro representando o valor da amostra.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setValue(int value) throws Exception {
		if (value >= 0){
			this.value = value;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid value");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o valor da amostra.
	 * 
	 * @return Inteiro com o valor da amostra.
	 */
	public int getValue() {
		return value;
	}
	
	
	/**
	 * Determina o tipo da amostra.
	 * 
	 * @param type NCLSampleType representando o tipo da amostra.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setType(NCLSampleType type) throws Exception {
		if (type != null){
			this.type = type;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid type");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o tipo da amostra.
	 * 
	 * @return NCLSampleType representando o tipo da amostra.
	 */
	public NCLSampleType getType() {
		return type;
	}
	
	
	/**
	 * Determina se duas amostras são iguais.
	 * 
	 * @param sample Amostra com a qual será feita a comparação.
	 * @return True se as amostras forem iguais.
	 */
	public boolean equals(NCLSample sample) {
		if (getValue() != sample.getValue())
			return false;
		if (!getType().equals(sample.getType()))
			return false;
		else
			return true;
	}
	
	
	/**
	 * Retorna a amostra em uma String no formato definido pela norma NCL.
	 * 
	 * @return String representando a amostra.
	 */
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
