package br.pensario;

public class NCLDoc {

	private NCLHead head;
	//private NCLBody body;
		
	public NCLDoc(NCLHead head /*,NCLBody body*/)
	{
		setHead(head);
		//if(head == null) throw new InvalidHeadException(); // quais validações?
	}
	
	public void setHead(NCLHead head) {
		this.head = head;
	}

	public NCLHead getHead() {
		return head;
	}
	
}
