/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.io.Console;
import java.util.Arrays;

/**
 * Main Entry Point.
 */
final class Main {

    /**
     * Prevent Construction.
     */
    private Main() {
        // Utility Class
        return ;
    }

    /**
     * Main App Entry Point.
     * @param args No args expected.
     */
    public static void main(final String[] args) {
        System.err.println( "Args: " + Arrays.toString(args )) ;
        IApp app = new AppAuthProxy() ;
        Console c = System.console();
        String msg = "" ;
        for (;;) {
            System.out.print("\033[H\033[2J") ; // clear the screen
            System.out.flush() ;
            System.out.println(app.screenContents()) ;
            System.out.println( msg ) ;
            System.out.print("=> ") ;
            String ch = c.readLine() ;       // get user command
            String cmd = ch.toLowerCase() ;  // convert to lower case
            cmd = cmd.replaceAll("\\s","") ; // remove all whitespaces
            msg=processCommand(app,cmd);     
        }
    }
    
    /**
     * Process Command from Main entry
     * @param app app controller.
     * @param cmd command.
     * @return msg command message
     */
    private static String processCommand(IApp app,String cmd ) {
        /* process commands */
    	String msg = "" ;
        msg = cmd ;
        if ( cmd.startsWith("touch") ) {
        	msg=touchCommand(app,cmd);
        //} else if ( cmd.equals("a") || cmd.equals("b") || cmd.equals("c") || cmd.equals("d") || cmd.equals("e") ) {  //cmd from "a" to "e"
        } else if ( cmd.charAt(0)>=97 &&  cmd.charAt(0)<=101) {
        	msg=menuCommand(app,cmd);
    	}
        else if ( cmd.startsWith("login") ) {
	        app.login();  
	    }   else msg=app.otherCommands(cmd);

        return msg;
    }
    
    /**
     * menu Command from Main entry
     * @param app app controller.
     * @param cmd command.
     * @return msg command message
     */
    private static String menuCommand(IApp app,String cmd) {
    	String msg = "" ;
        String selection = cmd.toUpperCase() ;
        msg = "selected: " + selection ;
        app.execute( selection ) ;
        return msg;
    	
    }
    
    
    /**
     * Touch Command from Main entry
     * @param app app controller.
     * @param cmd command.
     * @return msg command message
     */
    private static String touchCommand(IApp app,String cmd) {
    	String msg = "" ;
        String parms = cmd.replaceFirst("touch", "") ;
        parms = parms.substring(1) ;
        parms = parms.substring(0, parms.length() - 1) ;
        String[] values = parms.split(",") ;
        System.err.println( "Value: " +  Arrays.toString(values)) ;
        String x = values[0] ;
        String y = values[1] ;
        msg = "touch: x="+x + " y="+y ; 
        app.touch( Integer.parseInt(x), Integer.parseInt(y) ) ;
        return msg;
    }
    
 
//    /**
//     * Login Command from Main entry
//     * @param app app controller.
//     * @param cmd command.
//     * @return msg command message
//     */
//    private static String loginCommand(IApp app,String cmd) {
//    	String msg = "" ;
//    	if ( cmd.startsWith("login") ) {
//	        app.touch(1,5) ;  // 1
//	        app.touch(2,5) ;  // 2
//	        app.touch(3,5) ;  // 3
//	        app.touch(1,6) ;  // 4
//	    } else msg = "" ;
//    	return msg;
//    }
    
}
