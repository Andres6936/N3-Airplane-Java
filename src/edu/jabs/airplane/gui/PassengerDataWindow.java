package edu.jabs.airplane.gui;

import java.awt.*;

import javax.swing.*;

import edu.jabs.airplane.domain.Seat;
import edu.jabs.airplane.domain.Passenger;

/**
 * Form to submit data and registration of passenger
 */
public class PassengerDataWindow extends JFrame
{
    //-----------------------------------------------------------------
    // Graphical user interface Fields
    //-----------------------------------------------------------------

    /**
     * Button Pane
     */
    private PassengerDataButtonPane buttonPane;

    /**
     * Passenger data Pane
     */
    private PassengerDataPane passengerDataPane;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds a new window with the passenger data
     * @param seat - the passenger seat
     */
    public PassengerDataWindow( Seat seat )
    {
        Passenger passenger = seat.getPassenger( );
        setLayout( new BorderLayout( ) );

        //the Passenger data Pane is built
        passengerDataPane = new PassengerDataPane( passenger, seat );
        add( passengerDataPane, BorderLayout.NORTH );

        //the button pane is built
        buttonPane = new PassengerDataButtonPane( this );
        add( buttonPane, BorderLayout.SOUTH );

        setTitle( "Passenger Registration Information" );
        pack( );
        setResizable( false );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Closes the window
     */
    public void accept( )
    {
        dispose( );
    }
}