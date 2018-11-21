/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Pin Auth Observer Interface */
public interface IPinAuthObserver
{
    /** Auth Event */
    void authEvent() ;
    
    /**
     * Auth Event Failed and rekey notify
     * @param seq  sequence: 1st will be just failed, 2nd will be the next keypad input to clear invalid message
     */
    void authFailEvent(int seq) ;
}
