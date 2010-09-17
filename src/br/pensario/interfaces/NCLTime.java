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
		if (!setYear(year) || !setMonth(month) || !setDay(day) || !setHour(hour) || !setMinute(minute) || !setSecond(second) || !setFraction(fraction)){
			Exception ex = new Exception("Invalid date format");
			throw ex;
		}
	}
	
	public NCLTime(int hour, int minute, int second, int fraction) throws Exception {
		if (!setHour(hour) || !setMinute(minute) || !setSecond(second) || !setFraction(fraction)){
			Exception ex = new Exception("Invalid time format");
			throw ex;
		}
	}
	
	public NCLTime(int second, int fraction) throws Exception {
		absoluteSecond = true;
		if (!setSecond(second) || !setFraction(fraction)){
			Exception ex = new Exception("Invalid second format");
			throw ex;
		}
	}
	
	public NCLTime(int second) throws Exception {
		absoluteSecond = true;
		if (!setSecond(second)){
			Exception ex = new Exception("Invalid second format");
			throw ex;
		}
	}
	
	public boolean setYear(int year) {
		if (year >= 0){
			this.year = year;
			absoluteSecond = false;
			return true;
		}
		else
			return false;
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
	
	public boolean setMonth(int month) {
		if (month >= 1 && month <= 12){
			this.month = month;
			absoluteSecond = false;
			return true;
		}
		else
			return false;
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
	
	public boolean setDay(int day) {
		if (day >= 1 && day <= 31){
			this.day = day;
			absoluteSecond = false;
			return true;
		}
		else
			return false;
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
	
	public boolean setHour(int hour) {
		if (hour >= 0 && (!hasDay() || hour <= 23)){
			this.hour = hour;
			absoluteSecond = false;
			return true;
		}
		else
			return false;
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
	
	public boolean setMinute(int minute) {
		if (minute >= 0 && minute <= 59){
			this.minute = minute;
			absoluteSecond = false;
			return true;
		}
		else
			return false;
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
	
	public boolean setSecond(int second) {
		if (second >= 0 && (absoluteSecond || second <= 59)){
			this.second = second;
			return true;
		}
		else
			return false;
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
	
	public boolean setFraction(int fraction) {
		if (fraction >= 0){
			this.fraction = fraction;
			return true;
		}
		else
			return false;
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
