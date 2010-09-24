package br.pensario.interfaces;

/**
 * pode ser um horário nos formatos:
 *  ano:mes:dia:hora:minuto:segundo.fracao
 *  hora:minuto:segundo.fracao
 *  segundos
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
	
	
	public NCLTime(int year, int month, int day, int hour, int minute, int second, int fraction) throws Exception {
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		setFraction(fraction);
	}
	
	public NCLTime(int hour, int minute, int second, int fraction) throws Exception {
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		setFraction(fraction);
	}
	
	public NCLTime(int second, int fraction) throws Exception {
		absoluteSecond = true;
		setSecond(second);
		setFraction(fraction);
	}
	
	public NCLTime(int second) throws Exception {
		absoluteSecond = true;
		setSecond(second);
	}
	
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
	
	public int getYear() {
		return year;
	}
	
	public boolean hasYear() {
		if (year != -1)
			return true;
		else
			return false;
	}
	
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
	
	public int getMonth() {
		return month;
	}
	
	public boolean hasMonth() {
		if (month != -1)
			return true;
		else
			return false;
	}
	
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
	
	public int getDay() {
		return day;
	}
	
	public boolean hasDay() {
		if (day != -1)
			return true;
		else
			return false;
	}
	
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
	
	public int getHour() {
		return hour;
	}
	
	public boolean hasHour() {
		if (hour != -1)
			return true;
		else
			return false;
	}
	
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
	
	public int getMinute() {
		return minute;
	}
	
	public boolean hasMinute() {
		if (minute != -1)
			return true;
		else
			return false;
	}
	
	public void setSecond(int second) throws Exception {
		if (second >= 0 && (absoluteSecond || second <= 59)){
			this.second = second;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid second");
			throw ex;
		}
	}
	
	public int getSecond() {
		return second;
	}
	
	public boolean hasSecond() {
		if (second != -1)
			return true;
		else
			return false;
	}
	
	public void setFraction(int fraction) throws Exception {
		if (fraction >= 0){
			this.fraction = fraction;
		}
		else{
			Exception ex = new IllegalArgumentException("Invalid fraction");
			throw ex;
		}
	}
	
	public int getFraction() {
		return fraction;
	}
	
	public boolean hasFraction() {
		if (fraction != -1)
			return true;
		else
			return false;
	}
	
	public boolean isUTC() {
		if (hasYear() && hasMonth() && hasDay() && hasHour() && hasMinute() && hasSecond() && hasFraction())
			return true;
		else
			return false;
	}
	
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
