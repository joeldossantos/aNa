//TODO: nomes que tem parametro o que fazer?
package br.pensario.interfaces;

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
