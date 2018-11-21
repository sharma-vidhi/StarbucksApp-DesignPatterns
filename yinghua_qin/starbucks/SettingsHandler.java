package starbucks;


/**
 * Settings Handler
 */
public class SettingsHandler implements ITouchObserver {
	private IFrame frame;
	private Screen[] screens;  
	private IDisplayComponent cardComponentScreen;    
	 public SettingsHandler(IFrame frame, Screen[] screens, IDisplayComponent cardComponentScreen)
	    {
	        this.frame = frame ;
	    	this.screens=new Screen[screens.length];
	    	int i = 0;
	    	for (Screen s : screens) {
	    	    this.screens[i]=s;
	    	    i++;
	    	}  
	        this.cardComponentScreen = cardComponentScreen;
	        this.setContent();
	    }
	
    /**
     *(no use) as no data store requires for Settings Screen.
     */
	public void init( ) {}
	
	/**
     * Location Touch Event Update
     * @param x Position of X 
     * @param y Position of Y
     */
    public void setPosEvent(int x, int y) {
    	if(y==1) frame.setCurrentScreen(screens[0]);
    	if(x==1 && y==1) doNothing(); //hnote: to avoid smell code
    }
    
    /**
     * blank method to avoid smell code.
     */
    private void doNothing() { }
    
    /**
     *set screen content.
     */
    private void setContent() {
    	cardComponentScreen.setContent("Add Card\nDelete Card\nBilling\nPasscode\n \nAbout|Terms\nHelp");
    }


}
