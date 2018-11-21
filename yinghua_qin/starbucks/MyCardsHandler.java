package starbucks;

/**
 * My Cards Handler
 */
public class MyCardsHandler implements ITouchObserver,IBalanceObserver {
	private IFrame frame;
	private Screen[] screens;  
	private float balance=0;
	private IDisplayComponent cardComponentScreen;
	
    public MyCardsHandler(IFrame frame, Screen[] screens, IDisplayComponent cardComponentScreen)
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
    	System.err.println( "Touch Screen Touched at (" + x + ", " + y + ")" ) ;
    	//hnote: this should be Add New, but seems teacher is not used it: ScreenFlowTest-> line 185 app.touch(x,y)
    	//if(x==3 && y==1) frame.setCurrentScreen(screens[1]); //hnote: addcard screen
    	//else 
    	//if(x==2 && y==1) frame.setCurrentScreen(screens[1]); //hnote: addcard screen
    	//else    	
    	if(x==3 && y==3) frame.setCurrentScreen(screens[2]); //hnote: mycardspay screen
    	else if(x==2 && y==4) frame.setCurrentScreen(screens[3]); //hnote: mycardspay screen
    }
    
    /**
     * Set Balance
     * @param balance Balance
     */
    public void setBalance(float balance) {
    	this.balance = balance;
    	setContent();
    }
    
    /**
     * Add Balance
     * @param add The balance to add
     */
    public void addBalance(float add) {
    	this.balance += add;
    	setContent();
    }
    
    /**
     * Set dynamic content for the display component.
     */
    private void setContent() {
    	String bs=String.format(java.util.Locale.US,"$%.2f", balance);
    	cardComponentScreen.setContent("\n"+bs);
    }

    /**
     * (no use) Set Card Id
     * @param cardId The Card Id
     */
    public void setCardId(String cardId) {
    	if(cardId.equals("nothing")) System.err.println("test");//hnote: to void smellcode
    }
    
}
