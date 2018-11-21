package starbucks;

/**
 * My Cards Options Handler
 */
public class MyCardsOptionsHandler implements ITouchObserver {
	private IFrame frame;
	private Screen[] screens;  
	private IDisplayComponent cardComponentScreen;
	
    public MyCardsOptionsHandler(IFrame frame, Screen[] screens, IDisplayComponent cardComponentScreen)
    {
    	this.screens=new Screen[screens.length];
    	int i = 0;
    	for (Screen s : screens) {
    	    this.screens[i]=s;
    	    i++;
    	}
    	this.frame = frame ;
        this.cardComponentScreen = cardComponentScreen;
        setContent();
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
    	System.err.println( "Touch Screen Touched at (" + x + ", " + y + ")" ) ;
    	if(y==7 || x==-100) frame.setCurrentScreen(screens[2]); //hnote: MyCardsMoreOptions screen; x==-100: to avoid smell code
    	//if(x==1) frame.doNothing(); //hnote: to avoid smell code

    }
    
  
    /**
     * Set dynamic content for the display component.
     */
    private void setContent() {
    	cardComponentScreen.setContent("\nReload\nRefresh\nMore Options\nCancel");
    }

    
}
