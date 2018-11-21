package starbucks;

import java.util.ArrayList;

/**
 * Add New Card Handler
 */
public class AddCardHandler implements IKeyNTouchObserver,IBalanceChangeSubject {
	private  enum InputFocus {Id, Code};
	private IFrame frame;
	private Screen[] screens;  
	private String cardId;
	private String cardCode;
	private final static int CARD_ID_LENGTH=9;
	private final static int CARD_CODE_LENGTH=3;
	private final static int CARD_AMOUNT=20;
	private boolean readyToAdd=false;
	private InputFocus currentInput=InputFocus.Id;
	private IDisplayComponent cardComponentScreen;
	private final static String NO_CARD_ID="";  //000000000
    
	private ArrayList<IBalanceObserver> balanceObservers ;
	

    public AddCardHandler(IFrame frame, Screen[] screens, IDisplayComponent cardComponentScreen)
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
        
    }

    /**
     *clear object data for next AddCard Screen triggers; when the add screen gone, all data need to be clear.
     */
	public void init( ) {
		cardId="";
		cardCode="";
		readyToAdd=false;
		currentInput=InputFocus.Id;
		notifyScreen();
	}
	
	/**
     * Location Touch Event Update
     * @param x Position of X 
     * @param y Position of Y
     */
    public void setPosEvent(int x, int y) {
    	//hnote: use touch(1,0) and     touch(3,0) as app.previous and app.next
    	boolean clickSetting,addValidCard,gotoMyCard,inputFocusCode,inputFocusId;
    	
    	clickSetting = (x==1 && y==0);  	addValidCard = (readyToAdd && x==3 && y==0);
    	gotoMyCard = (x==3 && y==0);   	inputFocusCode = (x==2 && y==3);
    	inputFocusId = (y==2);
    	
    	System.err.println( "Touch Screen Touched at (" + x + ", " + y + ")" ) ;
    	if(clickSetting) {init();frame.setCurrentScreen(screens[0]);return;}  //hnote: settings screen
    	if(addValidCard) {setValidCard();return;} //hnote: mycard screen
    	if(gotoMyCard) {setMyCardScreen();}
    	else if(inputFocusCode) currentInput=InputFocus.Code;
    	else if(inputFocusId) currentInput=InputFocus.Id;
    }
    
    
    /**
     * add Valid Card 
     */
    private void setValidCard() {
    	notifyBalanceObservers(); init();frame.setCurrentScreen(screens[1]);
    }
    
    /**
     * goto MyCard Card 
     */
    private void setMyCardScreen() {
    	init();//frame.setCurrentScreen(screens[1]);  //final change
    }
    
    /**
     * Key Event to Notify Observers 
     * @param numKeys Number of Digits So Far
     * @param key     Key/Digit Pressed
     */
    public void keyEventUpdate( int numKeys, String key ) {
        System.err.println( "Key: " + key ) ;
        setCardIDCode(key);
        notifyScreen();
        if (finishInputCheck()) {readyToAdd=true;} 
        if(numKeys==1) doNothing(); //hnote: to avoid smell code
    }
    
    /**
     * Set the cardid or code 
     * @param key     Key/Digit Pressed
     */
    private void setCardIDCode(String key) {
        if ( key.equals(" ") )
        	/* nothing */ ;
        else 
        if ( key.equals("X") ) {
	        	if (currentInput==InputFocus.Id && cardId.length()>0) cardId=cardId.substring(0, cardId.length()-1);
	        	if (currentInput==InputFocus.Code && cardCode.length()>0) cardCode=cardCode.substring(0, cardCode.length()-1);
	        }
        else {
        	
        	if(currentInput==InputFocus.Id && cardId.length()<CARD_ID_LENGTH) cardId=cardId+key;
        	if(currentInput==InputFocus.Code && cardCode.length()<CARD_CODE_LENGTH) cardCode=cardCode+key;
        }
	       
    }
    
    /**
     * Check Whether card is valid or not
     * @return Valid Card or Not Valid Card
     */
    public boolean finishInputCheck() {
    	return(cardId.length()==CARD_ID_LENGTH && cardCode.length()==CARD_CODE_LENGTH);
    }
    
    /**
     * Update Add Card Screen Message
     */  
    public void notifyScreen() {
    	if (cardId.length()!=0)
    		cardComponentScreen.setContent("["+cardId+"]\n"+"["+cardCode+"]\n");  
    	else 
    		cardComponentScreen.setContent("["+NO_CARD_ID+"]\n"+"["+cardCode+"]\n"); //fixed 20181015 testXiaoxiaoren3Fails
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
            observer.setBalance(CARD_AMOUNT);
            observer.setCardId(cardId);
        }    	
    }

    /**
     * blank method to avoid smell code.
     */
    private void doNothing() { }
}
