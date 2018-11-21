package starbucks;

import java.util.ArrayList;


/**
 * My Cards Pay Handler
 */
public class MyCardsPayHandler implements ITouchObserver,IBalanceObserver,IBalanceChangeSubject {
	private IFrame frame;
	private Screen[] screens;  
	private String cardId="000000000"; 
	private float balance;
	private IDisplayComponent cardComponentScreen;
	private final static double TRANSACTION_FEE = 1.50;
	
	private ArrayList<IBalanceObserver> balanceObservers ;
	
    public MyCardsPayHandler(IFrame frame, Screen[] screens, IDisplayComponent cardComponentScreen)
    {
    	this.screens=new Screen[screens.length];
    	int i = 0;
    	for (Screen s : screens) {
    	    this.screens[i]=s;
    	    i++;
    	}
    	
    	this.frame = frame ;
        this.cardComponentScreen = cardComponentScreen;
        balanceObservers = new ArrayList<IBalanceObserver>() ;
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
    	boolean isClickPay=((x==2 && y==2)||(x==3 && y==2));
    	if(balance>=TRANSACTION_FEE && isClickPay) {
    		balance -= TRANSACTION_FEE;
    		notifyBalanceObservers();
    	}
    	else if(x==3 && y==3) frame.setCurrentScreen(screens[2]);
    }
    
    /**
     * (no use)Set Balance
     * @param balance Balance
     */
    public void setBalance(float balance) {
    	this.balance = balance;
    }    
    /**
     * (no use)Add Balance
     * @param add The balance to add
     */
    public void addBalance(float add) {
    	if(add>1) doNothing(); //hnote: to avoid smell code
    }
    
    /**
     * Set Card Id
     * @param cardId The Card Id
     */
    public void setCardId(String cardId) {
    	this.cardId = cardId;
    	setContent();
    	
    } 
    
    /**
     * Set dynamic content for the display component.
     */
    private void setContent() {
    	cardComponentScreen.setContent("\n["+cardId+"]"+"\n\n\n"+"Scan Now");
    }

    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    public void attach( IBalanceObserver obj ) {
    	balanceObservers.add( obj ) ;
    }

    /**
     * Trigger Events to Observers
     */
    public void notifyBalanceObservers() {
        for (int i=0; i<balanceObservers.size(); i++)
        {
        	IBalanceObserver observer = balanceObservers.get(i) ;
            observer.setBalance(balance);
        }    	
    }
    
    /**
     * blank method to avoid smell code.
     */
    private void doNothing() { }
}
