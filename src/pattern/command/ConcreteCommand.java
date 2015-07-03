package pattern.command;

public class ConcreteCommand extends Command {
	
	private Receiver receiver;
	
	public ConcreteCommand(){
	}
	
	public ConcreteCommand(Receiver receiver){
		this.receiver=receiver;
	}

	@Override
	void execute() {
		receiver.action();
	}

}
