/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Main App Controller Class
 */
public class AppController implements IApp {

    private Screen mycards ;
    private Screen store ;
    private Screen rewards ;
    private Screen payments ;
    private Screen settings;
    private Screen mycardspay;
    private Screen mycardsoptions;
    private Screen  mycardsmoreoptions;
    private IMenuCommand displayMyCards,displayPayments,displayRewards,doStore,doSetting ;
    private IFrame frame ;
    private KeyPad kp;
    private TouchScreen tsf,tsf2,tsf3,tsf4,tsf5,tsf6,tsf7,tsf8; //hnote: full screen is touch, no keypad
    private TouchScreen tsh; //hnote: above half is touch, bottom half is keypad
    private Screen addcard;
    private ITouchObserver sh,mch,ph,rh,mcph,mcoh,sth,mcmoh;  //hnote:settingshandler, mycardshandler
    
    private IKeyNTouchObserver ah;
    
    public AppController() {
        
    	//init screen
    	initScreens();
        
        // setup command pattern
        displayMyCards  = new MenuCommand() ; 
        displayPayments = new MenuCommand() ;
        displayRewards  = new MenuCommand() ; 
        doStore         = new MenuCommand() ;
        doSetting       = new MenuCommand() ;
        
        //setup Touch Screen/pad and Key key pad to receive touch events
        kp = new KeyPad();
        setupScreens1(); setupScreens2(); setupScreens3(); setupMenu();
        
        frame.setMenuItem ( "A", displayMyCards ) ;
        frame.setMenuItem ( "B", displayPayments ) ;
        frame.setMenuItem ( "C", displayRewards ) ;
        frame.setMenuItem ( "D", doStore ) ;
        frame.setMenuItem ( "E", doSetting ) ;
    }
    
    /**
     * Init screens 
     */
    private void initScreens() {
        mycards = new Screen("MyCards");store = new Screen("Store") ;
        rewards = new Screen("Rewards") ;payments = new Screen("Payments") ;
        settings = new Screen("Settings");frame = new Frame( mycards ) ;
        addcard= new Screen("AddCard");mycardspay = new Screen("MyCardsPay");
        mycardsoptions = new Screen("MyCardsOptions");
        mycardsmoreoptions  = new Screen("MyCardsMoreOptions");
    }

    /**
     * setup screens 1
     */
    private void setupScreens1() {
    	
        //setup Settings Screen handler
        tsf = new TouchScreen(true);
        sh = new SettingsHandler(frame,new Screen[] {addcard},tsf);
        settings.addSubComponent( tsf ) ;
        ((ITouchScreenSubject)tsf).attach( sh ) ;
        
        //setup AddCard Screen handler
        tsh = new TouchScreen(false);
        addcard.addSubComponent( tsh ) ;  //hnote: must first, as screen.display show diplay items by sequence
        addcard.addSubComponent( kp ) ;
        ah = new AddCardHandler(frame,new Screen[] {settings,mycards},tsh);
        ((ITouchScreenSubject)tsh).attach( ah ) ;
        ((IKeyPadSubject)kp).attach( ah ) ;
        
        //setup MyCards screen
        tsf2 = new TouchScreen(true);
        mycards.addSubComponent(tsf2);
        mch = new MyCardsHandler(frame,new Screen[] {null,addcard,mycardspay,mycardsoptions},tsf2);
        ((IBalanceChangeSubject)ah).attach((IBalanceObserver)mch);
        ((ITouchScreenSubject)tsf2).attach( mch ) ;
    	
    }
    
    /**
     * setup screens 2
     */
    private void setupScreens2() {
        //setup payments options screen
        tsf3 = new TouchScreen(true);
        payments.addSubComponent(tsf3);
        ph = new PaymentsHandler(frame,new Screen[] {store},tsf3);
        ((ITouchScreenSubject)tsf3).attach( ph ) ;
        
        //setup rewards screen
        tsf4 = new TouchScreen(true);
        rewards.addSubComponent(tsf4);
        rh = new RewardsHandler(frame,new Screen[] {},tsf4);
        ((ITouchScreenSubject)tsf4).attach( rh ) ;

        
        //setup mycardspay screen
        tsf5 = new TouchScreen(true);
        mycardspay.addSubComponent(tsf5);
        mcph = new MyCardsPayHandler(frame,new Screen[] {null,null,mycards},tsf5);
        ((IBalanceChangeSubject)mcph).attach((IBalanceObserver)mch);
        ((ITouchScreenSubject)tsf5).attach( mcph) ;
        ((IBalanceChangeSubject)ah).attach((IBalanceObserver)mcph);
    }
    
    /**
     * setup screens 3
     */
    private void setupScreens3() {
        //setup MyCardsOptions screen
        tsf6 = new TouchScreen(true);
        mycardsoptions.addSubComponent(tsf6);
        mcoh = new MyCardsOptionsHandler(frame,new Screen[] {null,null,mycardsmoreoptions},tsf6);        
        ((ITouchScreenSubject)tsf6).attach( mcoh) ;
        
        //setup Store screen
        tsf7 = new TouchScreen(true);
        store.addSubComponent(tsf7);
        sth = new StoreHandler(frame,new Screen[] {},tsf7);        
        ((ITouchScreenSubject)tsf7).attach(sth) ;      
  
        //setup MyCardsMoreOptions screen
        tsf8 = new TouchScreen(true);
        mycardsmoreoptions.addSubComponent(tsf8);
        mcmoh = new MyCardsMoreOptionsHandler(frame,new Screen[] {},tsf8);        
        ((ITouchScreenSubject)tsf8).attach(mcmoh) ; 
    }
    /**
     * setup Menu
     */
    private void setupMenu() {

        setupMenu1(); setupMenu2();
        doSetting.setReceiver(
        new IMenuReceiver() {
            /** Command Action */
            public void doAction() {
                frame.setCurrentScreen( settings ) ;
            }
        }
        ) ; 

    }
    
    /**
     * setup Menu receiver 1
     */
    private void setupMenu1() {
        displayMyCards.setReceiver(
                new IMenuReceiver() {
                    /** Command Action */
                    public void doAction() {
                        frame.setCurrentScreen( mycards ) ;
                    }
              }
              ) ;
              displayPayments.setReceiver(
                new IMenuReceiver() {
                    /** Command Action */
                    public void doAction() {
                        frame.setCurrentScreen( payments ) ;
                    }
              }
              ) ;
    }
    
    /**
     * setup Menu receiver 2
     */
    private void setupMenu2() {
        displayRewards.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( rewards ) ;
              }
        }
        ) ;
        doStore.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( store ) ;
              }
        }
        ) ;
       

    }

    /**
      * Switch to Landscape Mode
      */
    public void landscape() { frame.landscape() ; }

    /**
     * Switch to Portait Mode
     */
    public void portrait() { frame.portrait() ; }

    /**
     * Send In Touch Events
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) { frame.touch(x, y) ; }

    /**
     * Display Current Screen
     */
    public void display() { frame.display() ;  }

    /**
     * Execute Menu Bar Command
     * @param c Menu Bar Option (A, B, C, D or E)
     */
    public void execute( String c ) { frame.cmd( c ) ;  }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {  frame.previousScreen() ;  }

    /**
     * Navigate to Next Screen
     */
    public void next() {
        frame.nextScreen() ;
    }

    /**
     * Get Current Screen Name
     * @return Screen Name
     */
    public String screen() {
        return frame.screen() ;
    }

    /**
     * Get Current Screen Contents
     * @return Current Screen Contents
     */
    public String screenContents() {
        return frame.contents() ;
    }

    
    /**
     * login key input
     */
    public void login() {	touch(1,5) ; touch(2,5) ;   touch(3,5) ;   touch(1,6) ;  };

    /**
     * trigger command from Main
     * @param cmd A, B, C, D or E
     * @return command message
     */
    public String otherCommands(String cmd) {
    	String msg = "" ;boolean isPre,isNext,isPort,isLand;
     isPre=cmd.startsWith("prev");isNext= cmd.startsWith("next");
     isPort=cmd.equalsIgnoreCase( "portrait" );isLand=cmd.equalsIgnoreCase( "landscape" );
   	 if ( isPre ) {
	        msg = "cmd: previous" ;  prev() ;
	    } else if (isNext ) { msg = "cmd: next" ;  next() ;
	    }  if (isPort) {portrait() ;   } else if (isLand) {
	        landscape() ;  }    	return msg;  }
}
