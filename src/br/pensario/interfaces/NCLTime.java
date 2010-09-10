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
	
	private Boolean absoluteSecond = false;
	
	
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
	
	public Boolean setYear(int year) {
		if (year >= 0){
			this.year = year;
			return true;
		}
		else
			return false;
	}
	
	public int getYear() {
		return year;
	}
	
	public Boolean hasYear() {
		if (year != -1)
			return true;
		else
			return false;
	}
	
	public Boolean setMonth(int month) {
		if (month >= 1 && month <= 12){
			this.month = month;
			return true;
		}
		else
			return false;
	}
	
	public int getMonth() {
		return month;
	}
	
	public Boolean hasMonth() {
		if (month != -1)
			return true;
		else
			return false;
	}
	
	public Boolean setDay(int day) {
		if (day >= 1 && day <= 31){
			this.day = day;
			return true;
		}
		else
			return false;
	}
	
	public int getDay() {
		return day;
	}
	
	public Boolean hasDay() {
		if (day != -1)
			return true;
		else
			return false;
	}
	
	public Boolean setHour(int hour) {
		if (hour >= 0 && hour <= 23){
			this.hour = hour;
			return true;
		}
		else
			return false;
	}
	
	public int getHour() {
		return hour;
	}
	
	public Boolean hasHour() {
		if (hour != -1)
			return true;
		else
			return false;
	}
	
	public Boolean setMinute(int minute) {
		if (minute >= 0 && minute <= 59){
			this.minute = minute;
			return true;
		}
		else
			return false;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public Boolean hasMinute() {
		if (minute != -1)
			return true;
		else
			return false;
	}
	
	public Boolean setSecond(int second) {
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
	
	public Boolean hasSecond() {
		if (second != -1)
			return true;
		else
			return false;
	}
	
	public Boolean setFraction(int fraction) {
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
	
	public Boolean hasFraction() {
		if (fraction != -1)
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
