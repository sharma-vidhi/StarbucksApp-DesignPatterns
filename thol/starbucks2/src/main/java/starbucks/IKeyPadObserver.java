/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;
import processing.core.*;

/**
 * Key Pad Observer Interface
 */
public interface IKeyPadObserver
{
    /**
     * Key Event to Notify Observers 
     * @param numKeys Number of Digits So Far
     * @param key     Key/Digit Pressed
     */
    void keyEventUpdate( int numKeys, String key ) ;
}
