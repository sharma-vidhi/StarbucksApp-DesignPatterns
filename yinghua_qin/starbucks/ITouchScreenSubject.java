package starbucks;

/**
 * Touch Pad Observer Interface
 */
public interface ITouchScreenSubject {

    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    void attach( ITouchObserver obj ) ;

    /**
     * Trigger Events to Observers
     */
    void notifyObservers() ;
}
