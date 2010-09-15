package br.pensario;

public class NCLDoc {

	private NCLHead head;
	private NCLBody body;
		
	public NCLDoc(NCLHead head ,NCLBody body) throws Exception {
		if (!setHead(head) || !setBody(body)){
			Exception ex = new Exception("Invalid head or body");
			throw ex;
		}
	}
	
	public boolean setHead(NCLHead head) {
		if (head != null){
			this.head = head;
			return true;
		}
		else
			return false;
	}

	public NCLHead getHead() {
		return head;
	}
	
	public boolean setBody(NCLBody body) {
		if (body != null){
			this.body = body;
			return true;
		}
		else
			return false;
	}
	
	public NCLBody getBody() {
		return body;
	}
	
	public String parse(int ident) {
		//a cada filho que entra adiciona 1 em ident
		return "";
	}
}
