package br.pensario.node;

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
