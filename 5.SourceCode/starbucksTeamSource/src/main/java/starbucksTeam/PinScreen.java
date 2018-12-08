package starbucksTeam;

import java.util.Map;

import starbucks.*;
public class PinScreen implements IItemJar {
    //for new setPinScreen requirement  11/5/2018
    private TouchScreen tsf9;
    private Screen setpin= new Screen("SetPin");;
    private IKeyNTouchObserver setpinh;
    private KeyPad kp9;
    
    @Override
    public void load(IApp authApp,IFrame frame,Map<String, Screen> refScreens) {

    	
    	  //setpin = new Screen("SetPin");   //for new setPinScreen requirement  11/5/2018
		  kp9 = new KeyPad();  //give a new keypad for setPin, in case notify to wrong AddCard and AppController
		  tsf9 = new TouchScreen(false); //false - half screen; true  - full screen
		  setpin.addSubComponent( tsf9 ) ;  //hnote: must first, as screen.display show diplay items by sequence
		  setpin.addSubComponent( kp9 ) ;
		  setpinh = new SetPinHandler2(frame,refScreens,tsf9);
		  ((ITouchScreenSubject)tsf9).attach( setpinh ) ;
		  ((IKeyPadSubject)kp9).attach( setpinh ) ;  
		  ((IPinChangeSubject)setpinh).attach((IPinChangeObserver)authApp.getPinEntryMachin()); //attached the pin observer
		  ((IPinChangeSubject)setpinh).attach((IPinChangeObserver)authApp); 
	}
	@Override
	public void accept(IAppVisitor visitor) {
		// TODO Auto-generated method stub
		 visitor.visit(this);
	}
 
	@Override
	public Screen screen() {
		 return setpin;
	}
	

}
