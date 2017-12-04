package edu.jabs.airplane.gui;

import java.awt.*;
import java.text.*;

import javax.swing.*;

import edu.jabs.airplane.domain.Plane;
import edu.jabs.airplane.domain.Seat;
import edu.jabs.airplane.domain.Passenger;

/**
 * Main window
 */
public class PlaneGUI extends JFrame
{

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Plane
     */
    private Plane plane;

    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------

    /**
     * Pane containing the plane
     */
    private PlanePane planePane;

    /**
     * Button Pane
     */
    private ButtonPlanePane buttonPane;

    /**
     * New passenger dialog
     */
    private AllocationDialog dAsignment;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds the plane gui <br>
     * <b>post: </b> The plane is built and his initial state is represented on a graph.
     */
    public PlaneGUI( )
    {
        //The plane is built
        plane = new Plane( );

        //Graphical properties
        setLayout( new BorderLayout( ) );

        //Button Pane
        buttonPane = new ButtonPlanePane( this );
        add( buttonPane, BorderLayout.NORTH );

        //Plane pane
        planePane = new PlanePane( plane );
        add( planePane, BorderLayout.CENTER );

        String titulo = "The Plane";
        setTitle( titulo );
        pack( );
        setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It processes a passenger registration
     */
    public void passengerRecord( )
    {
        dAsignment = new AllocationDialog( this, plane );
        dAsignment.setLocation( computeCentralPosition( this, dAsignment ) );
        dAsignment.setModal( true );
        dAsignment.setVisible( true );
        update( );
    }

    /**
     * It cancels the registration process of a passenger
     */
    public void cancelPassenger( )
    {
        //the id number is asked
        int id;
        String sId = JOptionPane.showInputDialog( this, "Enter the id number" );
        try
        {
            id = Integer.parseInt( sId );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "Error in the id number.", "Cancellation", JOptionPane.ERROR_MESSAGE );
            return;
        }

        Passenger passenger = new Passenger( id, "doesnt matter" );
        if( !plane.deallocateSeat( passenger ) )
        {
            JOptionPane.showMessageDialog( this, "The passenger had no assigned seat", "Cancellation", JOptionPane.ERROR_MESSAGE );
            return;
        }

        update( );
    }

    /**
     * It shows the occupancy rate
     */
    public void showOccupancyRate( )
    {
        double rate;
        rate = plane.calculateOccupancyPercentage( );
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "###.##" );
        JOptionPane.showMessageDialog( this, "The occupancy rate is " + df.format( rate ) + "%", "Occupation of the plane", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * It processes a passenger search
     */
    public void searchPassenger( )
    {
        // the id is asked
        int id;
        String sId = JOptionPane.showInputDialog( this, "Enter the id number" );
        try
        {
            id = Integer.parseInt( sId );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "Error in the id number.", "Cancellation", JOptionPane.ERROR_MESSAGE );
            return;
        }
        Passenger passenger = new Passenger( id, "doesnt matter" );

        Seat seat = plane.searchPassenger( passenger );

        if( seat != null )
        {
            PassengerDataWindow vData = new PassengerDataWindow( seat );
            vData.setLocation( computeCentralPosition( this, vData ) );
            vData.setVisible( true );

        }
        else
        {
            JOptionPane.showMessageDialog( this, "The passenger is not registered", "Record search", JOptionPane.INFORMATION_MESSAGE );
            return;
        }
    }

    /**
     * Extension Method 1
     */
    public void reqFuncOption1( )
    {
        String answer = plane.method1( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extension Method 2
     */
    public void reqFuncOption2( )
    {
        String answer = plane.method2( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Repaint the plane graph
     */
    public void update( )
    {
        remove( planePane );

        //Plane Pane
        planePane = new PlanePane( plane );
        add( planePane, BorderLayout.CENTER );
        validate( );
    }

    /**
     * It computes the central point between two components
     * @param parentComponent - Parent component that calculates the center  - parentComponent != null
     * @param childComponent - CChild component to be centered on the parent component - childComponent != null
     * @return Central point to locate the child component
     */
    private Point computeCentralPosition( Component parentComponent, Component childComponent )
    {

        //Centers the window and verifies that it is not greater than the present resolution
        Dimension screenSize = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int maxY = screenSize.height;
        int minY = 0;

        //Screen resolution size
        Dimension parentSize = parentComponent.getSize( );
        Point parentLocation = parentComponent.getLocation( );
        Dimension childSize = childComponent.getSize( );
        int x = ( parentSize.width - childSize.width ) / 2 + parentLocation.x;
        int y = ( parentSize.height - childSize.height ) / 2 + parentLocation.y;

        // Setting down
        if( y + childSize.height > maxY )
        {
            y = maxY - childSize.height;
        }

        // Setting up
        if( y < minY )
        {
            y = 0;
        }
        return new Point( x, y );
    }

    /**
     * Main execution method
     * @param args - execution arguments - not required
     */
    public static void main( String[] args )
    {
        PlaneGUI gui = new PlaneGUI( );
        gui.setVisible( true );
    }
}