/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.util.ArrayList;

/** Key Pad */
public class KeyPad implements ITouchEventHandler, IDisplayComponent, IKeyPadSubject
{
    ITouchEventHandler nextHandler ;
    private ArrayList<IKeyPadObserver> observers ;
    int countPinDigits = 0 ;
    String lastKey = "" ;

    public KeyPad()
    {
        observers = new ArrayList<IKeyPadObserver>() ;
    }

    /**
     * Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) { 
    	boolean isKeyPadInput = false;
    	isKeyPadInput = (y > 4 && y<9) && (x<=3 && x>=1);
        if ( isKeyPadInput )   //hnote:20181017 fixed:  && x must less than 4: touch(4,7) return not handle
        {
            System.err.println( "KeyPad Touched at (" + x + ", " + y + ")" ) ; 
            this.lastKey = getKey( x, y ) ;
            if ( x==3 && y==8 && countPinDigits>0  )  //hnote:20181017 fixed:  && countPinDigits>0
            {
                countPinDigits-- ;
            }
            else if ( y < 8 || (x==2 && y==8))  
            {
                countPinDigits++ ;
            }
            notifyObservers() ;            
        }
        else
        {
            if ( nextHandler != null )
                nextHandler.touch(x,y) ;
        }
    }

    /**
     *  Get Last Key Pressed 
     * @return Lasy Key
     */
    public String lastKey() { 
        System.err.println( "Key Pressed: " + this.lastKey ) ;
        return this.lastKey ; 
    }

    /**
     * Get Key Number from (X,Y) Touch Coord's
     * @param  x [description]
     * @param  y [description]
     * @return   [description]
     */
    private String getKey( int x, int y )
    {
        int kx = 0, ky = 0 ;
        kx = x;
        ky = y-4 ;
        boolean isX,is0,isBlank;
        isX= ( kx==3 && ky ==4 ); is0=( kx==2 && ky == 4); isBlank= ( kx==1 && ky ==4);
        if (isX)
            return "X" ;
        else if (is0 )
            return "0" ;
        else if (isBlank )
            return " " ;
        else
            return Integer.toString(kx+3*(ky-1)) ;   
    }

    /*
    kx = 1, ky = 1  ==> 1
    kx = 1, ky = 2  ==> 4
    kx = 1, ky = 3  ==> 7

    kx = 2, ky = 1  ==> 2
    kx = 2, ky = 2  ==> 5
    kx = 2, ky = 3  ==> 8

    kx = 3, ky = 1  ==> 3
    kx = 3, ky = 2  ==> 6
    kx = 3, ky = 3  ==> 9

    n = kx + 3 * (ky-1)

    */

    /**
     * Set Next Touch Event Handler
     * @param next Event Handler
     */
    public void setNext( ITouchEventHandler next) { 
        nextHandler = next ;
    }

    /**
     * Get Key Pad Display
     * @return Key Pad View Contents
     */
    public String display() 
    {
        //return " [1] [2] [3]\n [4] [5] [6]\n [7] [8] [9]\n [_] [0] [X]"  ;
        
        String output =  " [1] [2] [3]\n" ;
               output += " [4] [5] [6]\n" ;
               output += " [7] [8] [9]\n" ;
               output += " [_] [0] [X]" ;  //changed x to X

        return output ;
    }

    /**
     * Add Sub Component (Not used)
     * @param c Display Component
     */
    public void addSubComponent( IDisplayComponent c ) 
    {
    }

    /**
     * Attach a Key Pad Observer
     * @param obj Observer
     */
    public void attach( IKeyPadObserver obj ) 
    {
        observers.add( obj ) ;
    }

    /**
     * Remove Key Pad Observer
     * @param obj Observer
     */
    public void removeObserver( IKeyPadObserver obj )
    {
        int i = observers.indexOf(obj) ;
        if ( i >= 0 )
            observers.remove(i) ;
    }

    /**
     * Notify all Observers of Update Event
     */
    public void notifyObservers( )
    {
        for (int i=0; i<observers.size(); i++)
        {
            IKeyPadObserver observer = observers.get(i) ;
            observer.keyEventUpdate( countPinDigits, lastKey ) ;
        }
    }  
    
    /**
     * (no use)Set display content //hnote add
     * @param content Display Content  
     */
    public void setContent(String content) {
    	//if(content.equals("nothing")) System.err.println("test");//hnote: to void smellcode
    	if(content.equals("Clear Pin")) countPinDigits=0;
    }

}
