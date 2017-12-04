package edu.jabs.airplane.gui;

import java.awt.*;

import javax.swing.*;

import edu.jabs.airplane.domain.Plane;
import edu.jabs.airplane.domain.Seat;
import edu.jabs.airplane.domain.Passenger;

/**
 * Form for the allocation of seats
 */
public class AllocationDialog extends JDialog
{

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Plane
     */
    private Plane plane;

    //-----------------------------------------------------------------
    // Graphical user interface Fields
    //-----------------------------------------------------------------

    /**
     * Main window
     */
    private PlaneGUI window;

    /**
     * Button Pane
     */
    private AllocationButtonPane buttonPane;

    /**
     * Data Pane
     */
    private AllocationDataPane dataPane;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds the allocation form
     * @param theWindow - parent window or frame of the dialogue - theWindow !=null
     * @param thePlane - plane on which the allocation will be done - thePlane != null
     */
    public AllocationDialog( PlaneGUI theWindow, Plane thePlane )
    {

        //The plane reference is saved
        plane = thePlane;

        //The parent window reference is saved
        window = theWindow;

        //Graphic properties
        setLayout( new BorderLayout( ) );

        //The data pane is built
        dataPane = new AllocationDataPane( );
        add( dataPane, BorderLayout.CENTER );

        //The button pane is built
        buttonPane = new AllocationButtonPane( this );
        add( buttonPane, BorderLayout.SOUTH );

        setTitle( "Passenger Record" );
        pack( );
        //setResizable( false );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );

    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It processes the registration of a passenger.It collects the passengers data, class and location where he wants his seat and processes the registration
     * <b>post: </b> If the passenger is not registered and there are available seats with the required characteristics the seat is assigned to the passenger.
     * If the registration can not be done, it shows a message
     */
    public void register( )
    {
        int id, classSeat;
        char location;
        String name, sId;
        Passenger passenger;

        name = dataPane.getPassengerName( );
        sId = dataPane.getId( );

        if( sId == null || sId.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "The id is required", "Registration", JOptionPane.ERROR_MESSAGE );
            return;
        }

        try
        {
            id = Integer.parseInt( sId );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "The id is numeric", "Registration", JOptionPane.ERROR_MESSAGE );
            return;
        }

        if( name == null || name.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "The name is required", "Registration", JOptionPane.ERROR_MESSAGE );
            return;
        }

        //The passenger is created
        passenger = new Passenger( id, name );

        //it verifies that the passenger is not on the plane
        Seat seat = plane.searchPassenger( passenger );

        if( seat != null )
        {
            JOptionPane.showMessageDialog( this, "Passengers already have an assigned seat", "Registration", JOptionPane.ERROR_MESSAGE );
            return;
        }

        //It adds a passenger
        classSeat = dataPane.getClassSeat( );
        location = dataPane.getSeatLocation( );
        seat = plane.assignSeat( classSeat, location, passenger );

        if( seat == null )
        {
            JOptionPane.showMessageDialog( this, "There are no seats available with these characteristics", "Registration", JOptionPane.ERROR_MESSAGE );
            return;
        }
        window.update( );
        dispose( );

    }

    /**
     * It cancels the registration process
     */
    public void cancel( )
    {
        window.update( );
        dispose( );
    }
}