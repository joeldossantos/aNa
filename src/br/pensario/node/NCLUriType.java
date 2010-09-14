/**
 * file: ///file_path/#fragment_identifier
 * http://server_identifier/file_path/#fragment_identifier
 * https://server_identifier/file_path/#fragment_identifier
 * rstp://server_identifier/file_path/#fragment_identifier
 * rtp://server_identifier/file_path/#fragment_identifier
 * nclmirror://media_element_identifier
 * sbtvd-ts://program_number.component_tag
 */
package br.pensario.node;

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
