package br.pensario;

public class NCLDoc {

	private NCLHead head;
	private NCLBody body;
		
	public NCLDoc(NCLHead head ,NCLBody body)
	{
		setHead(head);
		//if(head == null) throw new InvalidHeadException(); // quais valida��es?
		//if(body == null) ...
	}
	
	public void setHead(NCLHead head) {
		this.head = head;
	}

	public NCLHead getHead() {
		return head;
	}
	
	public void setBody(NCLBody body) {
		this.body = body;
	}
	
	public NCLBody getBody() {
		return body;
	}
	
}
