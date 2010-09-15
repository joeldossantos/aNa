package br.pensario;

public abstract class NCLValues {
	public enum NCLColor {
		gray, white, black, silver, red, maroon, fuchsia, purple, lime, green, yellow, olive, blue, navy, aqua, teal
	}
	
	public enum NCLQualifiers {
		PAR {
			public String toString() {
				return("par");
			}
		},
		SEQ {
			public String toString() {
				return("seq");
			}
		},
		AND {
			public String toString() {
				return("and");
			}
		},
		OR {
			public String toString() {
				return("or");
			}
		}
	}
	
	public enum NCLMimeType {
		TEXT_HTML {
			public String toString() {
				return("text/html");
			}
		},
		TEXT_PLAIN {
			public String toString() {
				return("text/plain");
			}
		},
		TEXT_CSS {
			public String toString() {
				return("text/css");
			}
		},
		TEXT_XML {
			public String toString() {
				return("text/xml");
			}
		},
		IMAGE_BMP {
			public String toString() {
				return("image/bmp");
			}
		},
		IMAGE_PNG {
			public String toString() {
				return("image/png");
			}
		},
		IMAGE_GIF {
			public String toString() {
				return("image/gif");
			}
		},
		IMAGE_JPEG {
			public String toString() {
				return("image/jpeg");
			}
		},
		AUDIO_BASIC {
			public String toString() {
				return("audio/basic");
			}
		},
		AUDIO_MP3 {
			public String toString() {
				return("audio/mp3");
			}
		},
		AUDIO_MP2 {
			public String toString() {
				return("audio/mp2");
			}
		},
		AUDIO_MPEG {
			public String toString() {
				return("audio/mpeg");
			}
		},
		AUDIO_MPEG4 {
			public String toString() {
				return("audio/mpeg4");
			}
		},
		VIDEO_MPEG {
			public String toString() {
				return("video/mpeg");
			}
		},
		APPLICATION_X_GINGA_NCL {
			public String toString() {
				return("application/x-ginga-NCL");
			}
		},
		APPLICATION_X_GINGA_NCLUA {
			public String toString() {
				return("application/x-ginga-NCLua");
			}
		},
		APPLICATION_X_GINGA_NCLET {
			public String toString() {
				return("application/x-ginga-NCLet");
			}
		},
		APPLICATION_X_GINGA_SETTINGS {
			public String toString() {
				return("application/x-ginga-settings");
			}
		},
		APPLICATION_X_GINGA_TIME {
			public String toString() {
				return("application/x-ginga-time");
			}
		}
	}
	
	public enum NCLSystemVariable {

		SYSTEM_LANGUAGE {
			public String toString() {
				return("system.language");
			}
		},
		SYSTEM_CAPTION {
			public String toString() {
				return("system.caption");
			}
		},
		SYSTEM_SUBTITLE {
			public String toString() {
				return("system.subtitle");
			}
		},
		/*SYSTEM_RETURNBITRATE(i) {
			public String toString() {
				return("system.returnBitRate(i)");
			}
		},*/
		SYSTEM_SCREENSIZE {
			public String toString() {
				return("system.screenSize");
			}
		},
		SYSTEM_SCREENGRAPHICSIZE {
			public String toString() {
				return("system.screenGraphicSize");
			}
		},
		SYSTEM_AUDIOTYPE {
			public String toString() {
				return("system.audioType");
			}
		},
		/*SYSTEM_SCREENSIZE(i) {
			public String toString() {
				return("system.screenSize(i)");
			}
		},
		SYSTEM_SCREENGRAPHICSIZE(i) {
			public String toString() {
				return("system.screenGraphicSize(i)");
			}
		},
		SYSTEM_AUDIOTYPE(i) {
			public String toString() {
				return("system.audioType(i)");
			}
		},
		SYSTEM_DEVNUMBER(i) {
			public String toString() {
				return("system.devNumber(i)");
			}
		},
		SYSTEM_CLASSTYPE(i) {
			public String toString() {
				return("system.classType(i)");
			}
		},
		SYSTEM_INFO(i) {
			public String toString() {
				return("system.info(i)");
			}
		},*/
		SYSTEM_CLASSNUMBER {
			public String toString() {
				return("system.classNumber");
			}
		},
		SYSTEM_CPU {
			public String toString() {
				return("system.CPU");
			}
		},
		SYSTEM_MEMORY {
			public String toString() {
				return("system.memory");
			}
		},
		SYSTEM_OPERATINGSYSTEM {
			public String toString() {
				return("system.operatingSystem");
			}
		},
		SYSTEM_JAVACONFIGURATION {
			public String toString() {
				return("system.javaConfiguration");
			}
		},
		SYSTEM_JAVAPROFILE {
			public String toString() {
				return("system.javaProfile");
			}
		},
		SYSTEM_LUAVERSION {
			public String toString() {
				return("system.luaVersion");
			}
		},
		DEFAULT_FOCUSBORDERCOLOR {
			public String toString() {
				return("default.focusBorderColor");
			}
		},
		DEFAULT_SELBORDERCOLOR {
			public String toString() {
				return("default.selBorderColor");
			}
		},
		DEFAULT_FOCUSBORDERWIDTH {
			public String toString() {
				return("default.focusBorderWidth");
			}
		},
		DEFAULT_FOCUSBORDERTRANSPARENCY {
			public String toString() {
				return("default.focusBorderTransparency");
			}
		},
		SERVICE_CURRENTFOCUS {
			public String toString() {
				return("service.currentFocus");
			}
		},
		SERVICE_CURRENTKEYMASTER {
			public String toString() {
				return("service.currentKeyMaster");
			}
		},
		SI_NUMBEROFSERVICES {
			public String toString() {
				return("si.numberOfServices");
			}
		},
		SI_NUMBEROFPARTIALSERVICES {
			public String toString() {
				return("si.numberOfPartialServices");
			}
		},
		SI_CHANNELNUMBER {
			public String toString() {
				return("si.channelNumber");
			}
		},
		CHANNEL_KEYCAPTURE {
			public String toString() {
				return("channel.keyCapture");
			}
		},
		CHANNEL_VIRTUALKEYBOARD {
			public String toString() {
				return("channel.virtualKeyboard");
			}
		},
		CHANNEL_KEYBOARDBOUNDS {
			public String toString() {
				return("channel.keyboardBounds");
			}
		}
	}
	
	public enum NCLSampleType {

		S, F, NPT
	}
	
	/**
	 * file: ///file_path/#fragment_identifier
	 * http://server_identifier/file_path/#fragment_identifier
	 * https://server_identifier/file_path/#fragment_identifier
	 * rstp://server_identifier/file_path/#fragment_identifier
	 * rtp://server_identifier/file_path/#fragment_identifier
	 * nclmirror://media_element_identifier
	 * sbtvd-ts://program_number.component_tag
	 */
	public enum NCLUriType {

		FILE {
			public String toString() {
				return("file://");
			}
		},
		HTTP {
			public String toString() {
				return("http://");
			}
		},
		HTTPS {
			public String toString() {
				return("https://");
			}
		},
		RSTP {
			public String toString() {
				return("rstp://");
			}
		},
		RTP {
			public String toString() {
				return("rtp://");
			}
		},
		NCLMIRROR {
			public String toString() {
				return("nclmirror://");
			}
		},
		SBTVD_TS {
			public String toString() {
				return("sbtvd-ts://");
			}
		}
	}
}