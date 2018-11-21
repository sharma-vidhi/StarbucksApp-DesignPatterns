/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Authentication Proxy for App Controller
 */
public class AppAuthProxy implements IApp, IPinAuthObserver {
    
    private IApp app = new AppController() ;
    private KeyPad kp;
    private Passcode pc;
    private Screen ps ;
    private Spacer sp ;
    private boolean authenticated = false ;
    private PinEntryMachine pm ;
    private String alertMsg="";
    private final static String INVALID_PIN="  Invalid Pin";

    public AppAuthProxy() {
        kp = new KeyPad() ;
        pc = new Passcode() ;
        sp = new Spacer() ;
        ps = new Screen("PinScreen") ;
        pm = new PinEntryMachine() ;

        // setup the composite pattern
        ps.addSubComponent( pc ) ;
        ps.addSubComponent( sp ) ;
        ps.addSubComponent( kp ) ;

        // setup the observer pattern
        ((IKeyPadSubject)kp).attach( pc ) ;
        ((IKeyPadSubject)kp).attach( pm ) ;
        ((IPinAuthSubject)pm).registerObserver(this) ;


    }

    /**
    * Switch to Landscape View
    */
    public void landscape() {
        if ( authenticated )
            app.landscape() ;
    }

    /**
     * Switch to Portait View
     */
    public void portrait() {
        if ( authenticated )
            app.portrait() ;
    }

    /**
     * User Touch at X,Y Coordinates
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public void touch(int x, int y) {
        if ( authenticated )
            app.touch(x, y) ;
        else
            ps.touch(x, y) ;
    }

    /**
     * Display Screen Contents to Terminal
     */
    public void display() {
        System.out.println( screenContents() ) ;
    }

    /**
     * Get Class Name of Screen
     * @return Class Name of Current Screen
     */
    public String screen() {
        if ( authenticated )
            return app.screen() ;
        else
            return ps.name() ;
    }

    /**
     * Get Screen Contents as a String
     * @return Screen Contents of Current Screen
     */
    public String screenContents() {
        if ( authenticated ) {
            return app.screenContents() ;
        } else {
            String out = "" ;
            out = "----------------\n" ;
            out += "   " + ps.name() + "  \n" ;
            out += "----------------\n"+alertMsg+"\n\n" ;  //hnote:invalid pin
            out += ps.display() ;
            out += "\n\n\n----------------\n" ;
            return out ;
        }
    }


    /**
     * Select a Menu Command
     * @param c Menu Option (A, B, C, E, or E)
     */
    public void execute( String c ) {
        if ( authenticated )
            app.execute( c ) ;
    }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {
        if ( authenticated )
            app.prev() ;
    }

    /**
     * Navigate to Next Screen
     */
    public void next() {
        if ( authenticated )
            app.next() ;
    }

    /**
     * Receive Authenticated Event from Authenticator
     */
    public void authEvent() {
        this.authenticated = true ;alertMsg="";
    }

    /**
     * login key input
     */
    public void login() {
    	touch(1,5) ; touch(2,5) ;   touch(3,5) ;   touch(1,6) ;
    };
    
    /**
     * trigger command from Main
     * @param cmd A, B, C, D or E
     * @return command message
     */
    public String otherCommands(String cmd) {
    	String msg = "" ;
   	 if ( cmd.startsWith("prev") ) {
	        msg = "cmd: previous" ;
	        prev() ;
	    } else if ( cmd.startsWith("next") ) {
	        msg = "cmd: next" ;
	        next() ;
	    } 
   	   if (cmd.equalsIgnoreCase( "portrait" )) {
	        portrait() ;
	    } else if (cmd.equalsIgnoreCase( "landscape" )) {
	        landscape() ;
	    }  
   	return msg;
    }

    /**
     * Receive Authenticated Fail Event from Authenticator
     * @param seq  sequence: 1st will be just failed, 2nd will be the next keypad input to clear invalid message
     */
    public void authFailEvent(int seq) {if(seq==1) {this.authenticated = false;alertMsg=INVALID_PIN;pc.setContent("Clear Pin");kp.setContent("Clear Pin");} else alertMsg=""; }
}
