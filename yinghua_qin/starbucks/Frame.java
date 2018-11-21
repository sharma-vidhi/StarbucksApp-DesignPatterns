/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame
{
    private IScreen current ;
    private IMenuInvoker menuA = new MenuOption() ;
    private IMenuInvoker menuB = new MenuOption() ;
    private IMenuInvoker menuC = new MenuOption() ;
    private IMenuInvoker menuD = new MenuOption() ;
    private IMenuInvoker menuE = new MenuOption() ;
    private IOrientationStrategy portraitStrategy = new IOrientationStrategy() 
    {
        /**
         * Display Screen Contents
         * @param s Reference to Screen
         */
        public void display(IScreen s)
        {
            System.out.println( contents(s) ) ;
        }         

            /**
         * Return String / Lines for Frame and Screen
         * @param  s [description]
         * @return   [description]
         */
        public String contents(IScreen s) 
        { 
            String out ="===============\n" ;
            int nameLen = s.name().length() ;
            if (nameLen < 14 ) {
                int pad = (14 - nameLen) / 2 ;  out += StringHelper.padSpaces( pad ) ;
            }
            out += s.name() + "\n" + "===============\n" ;
            String screen = s.display() + "\n" ;
            int cnt1 = StringHelper.countLines( screen ) ;
            int pad1 = (10 - cnt1) / 2;
            //System.err.println( "cnt1: " + cnt1 ) ;                
            //System.err.println( "pad1: " + pad1 ) ;
            out += StringHelper.padLines( pad1 ) ;            out += screen  ;
            //dumpLines( out ) ;                
            int cnt2 = StringHelper.countLines( out ) ;            int pad2 = 13 - cnt2 ;
            //System.err.println( "cnt2: " + cnt2 ) ;                
            //System.err.println( "pad2: " + pad2 ) ;
            //dumpLines( out ) ;
            String padlines = StringHelper.padLines( pad2 ) ;
            out += padlines ;
            out +=  "===============\n" ;
            out +=  "[A][B][C][D][E]\n" ;
            StringHelper.dumpLines( out ) ;
            return out ;             
        }

        /** Select Command A */
        public void selectA() { menuA.invoke() ; }

        /** Select Command B */
        public void selectB() { menuB.invoke() ; }

        /** Select Command C */
        public void selectC() { menuC.invoke() ; }

        /** Select Command D */
        public void selectD() { menuD.invoke() ; }

        /** Select Command E */
        public void selectE() { menuE.invoke() ; }

    } ;
    private IOrientationStrategy landscapeStrategy = new IOrientationStrategy() 
    {
        /**
         * Display Screen Contents
         * @param s Reference to Screen
         */
        public void display(IScreen s)
        {
            System.out.println( contents(s) ) ;
        }         

       /**
         * Display Contents of Frame + Screen 
         * @param  s Screen to Display
         * @return   Contents for Screen
         */
        public String contents(IScreen s) 
        { 
            String out = "" ;
            out += "================================\n" ;
            out += "  " + s.name() + "  \n" ;
            out += "================================" ;  //add \n for  //fixed? 20181015 testPriyal083Fails
            if(s.name().equals("AddCard") || s.name().equals("Settings")) out +="\n";
            out += s.display() + "\n"  ;
            out += "================================\n" ;
            StringHelper.dumpLines( out ) ;
            return out ;
        }

         /** Don't Respond in Landscaope Mode */
        public void selectA() {  }

        /** Don't Respond in Landscaope Mode */
        public void selectB() {  }

        /** Don't Respond in Landscaope Mode */
        public void selectC() {  }

        /** Don't Respond in Landscaope Mode */
        public void selectD() {  }

        /** Don't Respond in Landscaope Mode */
        public void selectE() {  }

   } ; 
    private IOrientationStrategy currentStrategy ;
//    private IScreen previous ;
//    private IScreen next ;
    
    /**
     * Return Screen Name
     * @return Screen Name
     */
    public String screen() { return current.name() ; }

    /** Switch to Landscape Strategy */
    public void landscape() { currentStrategy = landscapeStrategy ; }

    /** Switch to Portrait Strategy */
    public void portrait()  { currentStrategy = portraitStrategy ; }  

    /** Nav to Previous Screen */
    public void previousScreen() {
        touch(1,0);
        //setCurrentScreen(previous);
    }

    /** Nav to Next Screen */
    public void nextScreen() {
    	touch(3,0);
    	//setCurrentScreen(next);
    }



 
    /** Constructor */
    public Frame(IScreen initial)
    {
        current = initial ;

        

            

        /* set default layout strategy */
        currentStrategy = portraitStrategy ;
    }

    /**
     * Change the Current Screen
     * @param s Screen Object
     */
    public void setCurrentScreen( IScreen s )
    {
        current = s ;
    }

    /**
     * Configure Selections for Command Pattern 
     * @param slot A, B, ... E
     * @param c    Command Object
     */
    public void setMenuItem( String slot, IMenuCommand c )
    {
        if ( "A".equals(slot) ) { menuA.setCommand(c) ;  }
        if ( "B".equals(slot) ) { menuB.setCommand(c) ; }
        if ( "C".equals(slot) ) { menuC.setCommand(c) ; }
        if ( "D".equals(slot) ) { menuD.setCommand(c) ; } 
        if ( "E".equals(slot) ) { menuE.setCommand(c) ; }   
    }

    /** 
     * Send Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y)
    {
        if ( current != null )
            current.touch(x,y) ;

    }

    /**
     * Get Contents of the Frame + Screen 
     * @return Frame + Screen Contents
     */
    public String contents() 
    { 
        if ( current != null )
        {
            return currentStrategy.contents( current ) ; 
        } 
        else 
        {
            return "" ;
        }
    }

    /** Display Contents of Frame + Screen */
    public void display()
    {
        if ( current != null )
        {
            currentStrategy.display( current ) ;
        }
    }
 
    /**
     *  Execute a Command 
     * @param c Command
     */
    public void cmd( String c ) 
    {
        if ( "A".equals(c) ) { selectA() ; }
        if ( "B".equals(c) ) { selectB() ; }
        if ( "C".equals(c) ) { selectC() ; }
        if ( "D".equals(c) ) { selectD() ; }        
        if ( "E".equals(c) ) { selectE() ; }        
    }

    /** Select Command A */
    public void selectA() { currentStrategy.selectA() ;  }

    /** Select Command B */
    public void selectB() { currentStrategy.selectB() ;  }

    /** Select Command C */
    public void selectC() { currentStrategy.selectC() ;  }

    /** Select Command D */
    public void selectD() { currentStrategy.selectD() ;  }

    /** Select Command E */
    public void selectE() { currentStrategy.selectE(); }    
    
    /** For future enhance */
    public void doNothing() {}
    
   
   
}
