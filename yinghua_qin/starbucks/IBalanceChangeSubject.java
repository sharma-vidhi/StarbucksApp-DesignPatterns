package starbucks;

/** Card Balance Subject Interface */
public interface IBalanceChangeSubject {

    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    void attach( IBalanceObserver obj ) ;

    /**
     * Trigger Events to Observers
     */
    void notifyBalanceObservers() ;
}
