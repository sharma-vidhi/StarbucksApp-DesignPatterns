package starbucks;

import java.util.ArrayList;

/**
 * Touch Screen Part Display Component
 */
public class TouchScreen implements ITouchEventHandler,ITouchScreenSubject,IDisplayComponent {
	ITouchEventHandler nextHandler ;
	//String lastKey = "" ;
	int x;
	int y;
	String content;
	private ArrayList<ITouchObserver> observers ;
	private boolean fullScreen=true;  //hnote: no keypad diplay item
    public TouchScreen(boolean fullScreen)
    {
        observers = new ArrayList<ITouchObserver>() ;
        this.fullScreen = fullScreen;
    }
    
    
    /**
     * Get Touch Display (Not used); Always return "";
     * @return Touch Screen View Contents
     */
    public String display() {return content;}
    
    /**
     * Add Sub Component (Not used)
     * @param c Display Component
     */
    public void addSubComponent( IDisplayComponent c ) 
    {
    }
    
    /**
     * Set Next Touch Event Handler
     * @param next Event Handler
     */
    public void setNext( ITouchEventHandler next) { 
        nextHandler = next ;
    }
    
    
    /**
     * Attach a Touch Screen Observer
     * @param obj Observer
     */
    public void attach( ITouchObserver obj ) 
    {
        obj.init();
        observers.add( obj ) ;
    }
    
    /**
     * Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
    	if (fullScreen || y <= 4 ) {	
	        System.err.println( "Screen Touched at (" + x + ", " + y + ")" ) ; 
	        this.x=x;
	        this.y=y;
	        notifyObservers() ;  
    	}        
        else
        {
            if ( nextHandler != null )
                nextHandler.touch(x,y) ;
        }
    }
    
    
    /**
     * Notify all Observers of Update Event
     */
    public void notifyObservers( )
    {
        for (int i=0; i<observers.size(); i++)
        {
        	ITouchObserver observer = observers.get(i) ;
            observer.setPosEvent(x,y) ;
        }
    } 
    
    
    /**
     * (no use)Set display content //hnote add
     * @param content Display Content  
     */
    public void setContent(String content) {
    	this.content=content;
    }
    
}
