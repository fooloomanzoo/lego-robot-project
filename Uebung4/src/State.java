public abstract class State {
	
	Controller controller = null;
	
	public State (Controller c) {
		this.controller = c;
	}
	
	// Status-Uebergange
	abstract void enter();
	abstract void leave();
	
	// Events
	public void handleBrighter() {};
	public void handleDarker() {};
	public void handleConstant() {};
	
	public void exitProgram() {
		this.controller.stop();
	};
}
