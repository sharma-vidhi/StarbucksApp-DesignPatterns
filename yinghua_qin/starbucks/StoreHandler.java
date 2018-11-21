package starbucks;


/**
 * Store Handler
 */
public class StoreHandler implements ITouchObserver {
	private IFrame frame;
	private Screen[] screens;  
	private IDisplayComponent cardComponentScreen;
	
    public StoreHandler(IFrame frame, Screen[] screens, IDisplayComponent cardComponentScreen)
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
     *(no use) as no data store requires for the Screen.
     */
    public void init() {
    	//TODO hnote:keep frame for future useful,but actually useless now
    	//frame.doNothing(0,0); 
    } 
	
	/**
     * Location Touch Event Update
     * @param x Position of X 
     * @param y Position of Y
     */
    public void setPosEvent(int x, int y) {
    	//TODO 
    	if(x==1 && y==1) doNothing(); //hnote: to avoid smell code
    	//if(y==1) frame.setCurrentScreen(screens[0]);
    }
    
    /**
     * Set dynamic content for the display component.
     */
    private void setContent() {
    	cardComponentScreen.setContent("\n         X\n   X\n       X\n      X\n  X\n           X\n  X");
    }


    /**
     * blank method to avoid smell code.
     */
    private void doNothing() {
    	frame.doNothing();
    }
}
