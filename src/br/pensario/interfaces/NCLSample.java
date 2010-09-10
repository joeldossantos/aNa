/*
   http://www.ietf.org/rfc/rfc2326.txt
   
 * Normal Play Time

   Normal play time (NPT) indicates the stream absolute position
   relative to the beginning of the presentation. The timestamp consists
   of a decimal fraction. The part left of the decimal may be expressed
   in either seconds or hours, minutes, and seconds. The part right of
   the decimal point measures fractions of a second.

   The beginning of a presentation corresponds to 0.0 seconds. Negative
   values are not defined. The special constant now is defined as the
   current instant of a live event. It may be used only for live events.

   NPT is defined as in DSM-CC: "Intuitively, NPT is the clock the
   viewer associates with a program. It is often digitally displayed on
   a VCR. NPT advances normally when in normal play mode (scale = 1),
   advances at a faster rate when in fast scan forward (high positive
   scale ratio), decrements when in scan reverse (high negative scale
   ratio) and is fixed in pause mode. NPT is (logically) equivalent to
   SMPTE time codes." [5]

   npt-range    =   ( npt-time "-" [ npt-time ] ) | ( "-" npt-time )
   npt-time     =   "now" | npt-sec | npt-hhmmss
   npt-sec      =   1*DIGIT [ "." *DIGIT ]
   npt-hhmmss   =   npt-hh ":" npt-mm ":" npt-ss [ "." *DIGIT ]
   npt-hh       =   1*DIGIT     ; any positive number
   npt-mm       =   1*2DIGIT    ; 0-59
   npt-ss       =   1*2DIGIT    ; 0-59

   Examples:
     npt=123.45-125
     npt=12:05:35.3-
     npt=now-

     The syntax conforms to ISO 8601. The npt-sec notation is optimized
     for automatic generation, the ntp-hhmmss notation for consumption
     by human readers. The "now" constant allows clients to request to
     receive the live feed rather than the stored or time-delayed
     version. This is needed since neither absolute time nor zero time
     are appropriate for this case.
*/
//TODO: now é um valor válido??????
package br.pensario.interfaces;

public class NCLSample {

	private String value;
	private NCLSampleType type;
	
	
	public NCLSample(int value, NCLSampleType type) throws Exception {
		if (!setType(type) || !setValue(value)){
			Exception ex = new Exception("Invalid sample value");
			throw ex;
		}
	}
	
	public NCLSample(int hour, int minute, int second, int fraction, NCLSampleType type) throws Exception {
		if (!setType(type) || !setValue(hour, minute, second, fraction)){
			Exception ex = new Exception("Invalid sample value");
			throw ex;
		}
	}
	
	public NCLSample(String value, NCLSampleType type) throws Exception {
		if (!setType(type) || !setValue(value)){
			Exception ex = new Exception("Invalid sample value");
			throw ex;
		}
	}
	
	public Boolean setValue(int value) {
		if (value >= 0){
			this.value = "" + value;
			return true;
		}
		else
			return false;
	}
	
	public Boolean setValue(int hour, int minute, int second, int fraction) {
		if (type == NCLSampleType.NPT && hour >= 0 && minute >= 0 && minute <= 59 && second >= 0 && second <= 59 && fraction >= 0){
			value = hour + ":" + minute + ":" + second + "." + fraction;
			return true;
		}
		else
			return false;
	}
	
	public Boolean setValue(String value) {
		if (type == NCLSampleType.NPT && value == "now"){
			this.value = value;
			return true;
		}
		else
			return false;
	}
	
	public String getValue() {
		return value;
	}
	
	public Boolean setType(NCLSampleType type) {
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
