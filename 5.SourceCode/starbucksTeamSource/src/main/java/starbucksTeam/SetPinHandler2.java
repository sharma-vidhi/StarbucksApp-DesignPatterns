package starbucksTeam;

import java.util.ArrayList;
import java.util.Map;

import starbucks.*;
/**
 * Add New Card Handler
 */
public class SetPinHandler2 implements IKeyNTouchObserver,IPinChangeSubject {
	private IFrame frame;
	private Map<String, Screen> refScreens;  
	private String oldpin;
	private String newpin="";
	private final static int NEW_PIN_MAX_LENGTH=4;
	//private boolean readyToAdd=false;
	private IDisplayComponent cardComponentScreen;
    
	private ArrayList<IPinChangeObserver> pinObservers ;
	

    public SetPinHandler2(IFrame frame,Map<String, Screen> refScreens, IDisplayComponent cardComponentScreen)
    {
    	newpin="1234";  //initial pin at the begin of app  11/5/2018
    	this.refScreens=refScreens; 
//    	this.screens=new Screen[screens.length];
//    	int i = 0;
//    	for (Screen s : screens) {
//    	    this.screens[i]=s;
//    	    i++;
//    	}
    	
    	this.frame = frame ;
        this.cardComponentScreen = cardComponentScreen;
        pinObservers = new ArrayList<IPinChangeObserver>() ;
        
    }

    /**
     *clear object data for next AddCard Screen triggers; when the add screen gone, all data need to be clear.
     */
	public void init( ) {   //when attached, it will init.
		//backup environment
		oldpin=newpin;
		newpin="";
		
		//readyToAdd=false;
		notifyScreen();
	}
	
	/**
     * Location Touch Event Update
     * @param x Position of X 
     * @param y Position of Y
     */
    public void setPosEvent(int x, int y) {
    	//hnote: use touch(1,0) and     touch(3,0) as app.previous and app.next
    	boolean clickSetting,addValidPin;
    	
    	clickSetting = (x==1 && y==0) || (x==1 && y==1) ;  	addValidPin = (x==3 && y==0) || (x==3 && y==1);  //readyToAdd &&
    	
    	System.err.println( "Touch Screen Touched at (" + x + ", " + y + ")" ) ;
    	if(clickSetting) {
    		newpin=oldpin;init();frame.setCurrentScreen(refScreens.get("Settings"));return;
    	}  //hnote: settings screen
    	
    	if(addValidPin) {setValidPin();return;} //hnote: back to pin screen
    }
    
    
    /**
     * add Valid Card 
     */
    private void setValidPin() {
    	notifyPinObservers(); init();frame.setCurrentScreen(refScreens.get("MyCards"));
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
        //if (finishInputCheck()) {readyToAdd=true;} 
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
	        	if (newpin.length()>0) newpin=newpin.substring(0, newpin.length()-1);
        }
        else 
        	if(newpin.length()<NEW_PIN_MAX_LENGTH) newpin=newpin+key;
	       
    }
    
    /**
     * Check Whether pin is valid or not  (not use now, as we limit it   newpin.length()<NEW_PIN_MAX_LENGTH in setCardIDCode())
     * @return Valid Card or Not Valid Card
     */
    public boolean finishInputCheck() {
    	return(newpin.length()<=NEW_PIN_MAX_LENGTH);
    }
    
    /**
     * Update Add Card Screen Message
     */  
    public void notifyScreen() {
   		cardComponentScreen.setContent("["+oldpin+"]\n"+"["+newpin+"]\n");  
    }
    
    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    public void attach( IPinChangeObserver obj ) {
    	pinObservers.add( obj ) ;
    }

    /**
     * Trigger Events to Observers
     */
    public void notifyPinObservers() {
        for (int i=0; i<pinObservers.size(); i++)
        {
        	IPinChangeObserver observer = pinObservers.get(i) ;
            observer.setPin(newpin);
        }    	
    }

    /**
     * blank method to avoid smell code.
     */
    private void doNothing() { }
}
