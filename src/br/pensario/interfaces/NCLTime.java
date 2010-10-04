package br.pensario.interfaces;

/**
 * Indicado de tempo definido de acordo com a norma NCL.
 * O indicador pode ter um dos seguintes formatos:
 *    - ano:mês:dia:hora:minuto:segundo.fração
 *    - hora:minuto:segundo.fração
 *    - segundo.fração
 *    - segundos
 */
public class NCLTime {

	private int year = -1;
	private int month = -1;
	private int day = -1;
	private int hour = -1;
	private int minute = -1;
	private int second = -1;
	private int fraction = -1;
	
	private boolean absoluteSecond = false;
	
	
	/**
	 * Construtor do indicador de tempo possuindo ano, mês, dia, hora, minuto, segundo e fração.
	 * 
	 * @param year Inteiro com o valor de ano.
	 * @param month Inteiro com o valor de mês.
	 * @param day Inteiro com o valor de dia.
	 * @param hour Inteiro com o valor de hora.
	 * @param minute Inteiro com o valor de minuto.
	 * @param second Inteiro com o valor de segundo.
	 * @param fraction Inteiro com o valor das frações de segundo.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public NCLTime(int year, int month, int day, int hour, int minute, int second, int fraction) throws Exception {
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		setFraction(fraction);
	}
	
	
	/**
	 * Construtor do indicador de tempo possuindo hora, minuto, segundo e fração.
	 * 
	 * @param hour Inteiro com o valor de hora.
	 * @param minute Inteiro com o valor de minuto.
	 * @param second Inteiro com o valor de segundo.
	 * @param fraction Inteiro com o valor das frações de segundo.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public NCLTime(int hour, int minute, int second, int fraction) throws Exception {
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		setFraction(fraction);
	}
	
	
	/**
	 * Construtor do indicador de tempo possuindo segundo e fração.
	 * 
	 * @param second Inteiro que determina o valor em segundos.
	 * @param fraction Inteiro que determina as frações de segundo.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public NCLTime(int second, int fraction) throws Exception {
		absoluteSecond = true;
		setSecond(second);
		setFraction(fraction);
	}
	
	
	/**
	 * Construtor do indicador de tempo que só possui segundo.
	 * 
	 * @param second Inteiro que determina o valor em segundos.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public NCLTime(int second) throws Exception {
		absoluteSecond = true;
		setSecond(second);
	}
	
	
	/**
	 * Determina a parte de ano do indicador de tempo.
	 * 
	 * @param year Inteiro que determina o valor da parte de ano.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setYear(int year) throws Exception {
		if (year >= 0){
			this.year = year;
			absoluteSecond = false;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid year");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém a parte de ano do indicador de tempo.
	 * 
	 * @return Inteiro com o ano.
	 */
	public int getYear() {
		return year;
	}
	
	
	/**
	 * Verifica se o indicador de tempo tem a parte de ano.
	 * 
	 * @return True se o indicador possui ano.
	 */
	public boolean hasYear() {
		return (year != -1);
	}
	
	
	/**
	 * Determina a parte de mês do indicador de tempo.
	 * 
	 * @param month Inteiro que determina o valor da parte de mês.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setMonth(int month) throws Exception {
		if (month >= 1 && month <= 12){
			this.month = month;
			absoluteSecond = false;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid month");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém a parte de mês do indicador de tempo.
	 * 
	 * @return Inteiro com o mês.
	 */
	public int getMonth() {
		return month;
	}
	
	
	/**
	 * Verifica se o indicador de tempo tem a parte de mês.
	 * 
	 * @return True se o indicador possui mês.
	 */
	public boolean hasMonth() {
		return (month != -1);
	}
	
	
	/**
	 * Determina a parte de dia do indicador de tempo.
	 * 
	 * @param day Inteiro que determina o valor da parte de dia.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setDay(int day) throws Exception {
		if (day >= 1 && day <= 31){
			this.day = day;
			absoluteSecond = false;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid day");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém a parte de dia do indicador de tempo.
	 * 
	 * @return Inteiro com o dia.
	 */
	public int getDay() {
		return day;
	}
	
	
	/**
	 * Verifica se o indicador de tempo tem a parte de dia.
	 * 
	 * @return True se o indicador possui dia.
	 */
	public boolean hasDay() {
		return (day != -1);
	}
	
	
	/**
	 * Determina a parte de hora do indicador de tempo.
	 * 
	 * @param hour Inteiro que determina o valor da parte de hora.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setHour(int hour) throws Exception {
		if (hour >= 0 && (!hasDay() || hour <= 23)){
			this.hour = hour;
			absoluteSecond = false;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid hour");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém a parte de hora do indicador de tempo.
	 * 
	 * @return Inteiro com a hora.
	 */
	public int getHour() {
		return hour;
	}
	
	
	/**
	 * Verifica se o indicador de tempo tem a parte de hora.
	 * 
	 * @return True se o indicador possui hora.
	 */
	public boolean hasHour() {
		return (hour != -1);
	}
	
	
	/**
	 * Determina a parte de minuto do indicador de tempo.
	 * 
	 * @param minute Inteiro que determina o valor da parte de minuto.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setMinute(int minute) throws Exception {
		if (minute >= 0 && minute <= 59){
			this.minute = minute;
			absoluteSecond = false;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid minute");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém a parte de minuto do indicador de tempo.
	 * 
	 * @return Inteiro com o minuto.
	 */
	public int getMinute() {
		return minute;
	}
	
	
	/**
	 * Verifica se o indicador de tempo tem a parte de minuto.
	 * 
	 * @return True se o indicador possui minuto.
	 */
	public boolean hasMinute() {
		return (minute != -1);
	}
	
	
	/**
	 * Determina a parte de segundo do indicador de tempo.
	 * 
	 * @param second Inteiro que determina o valor da parte de segundo.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setSecond(int second) throws Exception {
		if (second >= 0 && (absoluteSecond || second <= 59)){
			this.second = second;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid second");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém a parte de segundo do indicador de tempo.
	 * 
	 * @return Inteiro com o segundo.
	 */
	public int getSecond() {
		return second;
	}
	
	
	/**
	 * Verifica se o indicador de tempo tem a parte de segundo.
	 * 
	 * @return True se o indicador possui segundo.
	 */
	public boolean hasSecond() {
		return (second != -1);
	}
	
	
	
	/**
	 * Determina a parte de fração do indicador de tempo.
	 * 
	 * @param fraction Inteiro que determina o valor da parte de fração.
	 * @throws IllegalArgumentException se o valor não estiver no formato definido pela norma.
	 */
	public void setFraction(int fraction) throws Exception {
		if (fraction >= 0){
			this.fraction = fraction;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid fraction");
			throw ex;
		}
	}
	
	
	
	/**
	 * Obtém a parte de fração do indicador de tempo.
	 * 
	 * @return Inteiro com a fração.
	 */
	public int getFraction() {
		return fraction;
	}
	
	
	
	/**
	 * Verifica se o indicador de tempo tem a parte de fração.
	 * 
	 * @return True se o indicador possui fração.
	 */
	public boolean hasFraction() {
		return (fraction != -1);
	}
	
	
	
	/**
	 * Verifica se o indicador de tempo está no formato UTC (ano:mês:dia:hora:minuto:segundo:fração).
	 * 
	 * @return True se o indicador estiver no formato UTC.
	 */
	public boolean isUTC() {
		if (hasYear() && hasMonth() && hasDay() && hasHour() && hasMinute() && hasSecond() && hasFraction())
			return true;
		else
			return false;
	}
	
	
	
	/**
	 * Compara dois indicadores de tempo.
	 * 
	 * @param time Indicador de tempo com o qual comparar.
	 * @return True se os indicadores forem iguais.
	 */
	public boolean equals(NCLTime time) {
		if (!hasYear() || !time.hasYear() || getYear() != time.getYear())
			return false;
		if (!hasMonth() || !time.hasMonth() || getMonth() != time.getMonth())
			return false;
		if (!hasDay() || !time.hasDay() || getDay() != time.getDay())
			return false;
		if (!hasHour() || !time.hasHour() || getHour() != time.getHour())
			return false;
		if (!hasMinute() || !time.hasMinute() || getMinute() != time.getMinute())
			return false;
		if (!hasSecond() || !time.hasSecond() || getSecond() != time.getSecond())
			return false;
		if (!hasFraction() || !time.hasFraction() || getFraction() != time.getFraction())
			return false;
		else
			return true;
	}
	
	
	
	/**
	 * Retorna o indicador de tempo em uma string de acordo com o formato especificado na norma NCL.
	 * 
	 * @return String representando o indicador de tempo.
	 */
	public String toString() {
		if (hasSecond()){
			if (hasFraction()){
				if (hasHour() && hasMinute()){
					if (hasYear() && hasMonth() && hasDay()){
						return year + ":" + month + ":" + day + ":" + hour + ":" + minute + ":" + second + "." + fraction;
					}
					else
						return hour + ":" +	minute + ":" + second + "." + fraction;
				}
				else
					return second + "." + fraction + "s";
			}
			else
				return second + "s";
		}
		return null;
	}
}
