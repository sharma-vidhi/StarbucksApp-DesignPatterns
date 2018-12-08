/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;
import processing.core.*;

/** Menu Command Interface */
public interface IMenuCommand
{
    /** Execute the Command */
    void execute() ;

    /** 
     * Configure the Receiver for the Command
     * @param target Receiver
     */
    void setReceiver( IMenuReceiver target ) ;
}
