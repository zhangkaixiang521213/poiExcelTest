package pattern.chainofresponsibility;

public class ConcreteHandler extends Handler {

	@Override
	public void handleRequest() {
		if(super.getSuccessor()!=null){
			System.out.println("The request is passed to "+super.getSuccessor());
			super.getSuccessor().handleRequest();
		}else{
			System.out.println("The request is handled here");
		}
		
	}

}
