package starbucks;

/** Touch Screen Observer Interface */
public interface ITouchObserver {
	
    /** Set Position Event 
     * @param x     touch at coordinate x
     * @param y     touch at coordinate y
     * */
    void setPosEvent(int x, int y) ;
    
    /** Initialize Observer Object value  */
    void init();

}
