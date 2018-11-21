package starbucks;

/**
 * Card Balance Observer
 */
public interface IBalanceObserver {
 
    /**
     * Set Balance
     * @param balance Balance
     */
    void setBalance(float balance) ;
    
    /**
     * Add Balance
     * @param add The balance to add
     */
    void addBalance(float add);
    
    /**
     * Set Card Id
     * @param cardId The Card Id
     */
    void setCardId(String cardId);
}