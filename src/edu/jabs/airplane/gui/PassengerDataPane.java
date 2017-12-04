package edu.jabs.airplane.gui;

import java.awt.*;

import javax.swing.*;

import edu.jabs.airplane.domain.Seat;
import edu.jabs.airplane.domain.Passenger;

/**
 * Passenger information Pane
 */
public class PassengerDataPane extends JPanel
{
    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------

    /**
     * Text field with the passenger id
     */
    private JTextField txtId;

    /**
     * Text filed with the passenger name
     */
    private JTextField txtName;

    /**
     * Text field with the class of the seat
     */
    private JTextField txtClass;

    /**
     * Text field with the location
     */
    private JTextField txtLocation;

    /**
     * Text field with the seat
     */
    private JTextField txtSeat;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds the passenger information pane
     * @param passenger - passenger which information will be represented
     * @param seat passenger seat
     */
    public PassengerDataPane( Passenger passenger, Seat seat )
    {
        setLayout( new GridLayout( 5, 1, 1, 1 ) );
        setBorder( BorderFactory.createTitledBorder( "Passenger Information" ) );

        //Id
        JPanel idPane = new JPanel( );
        idPane.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel labelId = new JLabel( "Id " );
        txtId = new JTextField( Integer.toString( passenger.getId( ) ) );
        txtId.setColumns( 15 );
        txtId.setEditable( false );
        idPane.add( labelId );
        idPane.add( txtId );
        add( idPane );

        //Name
        JPanel namePane = new JPanel( );
        namePane.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel labelName = new JLabel( "Name " );
        txtName = new JTextField( passenger.getName( ) );
        txtName.setColumns( 15 );
        txtName.setEditable( false );
        namePane.add( labelName );
        namePane.add( txtName );
        add( namePane );

        //Seat
        JPanel seatPane = new JPanel( );
        seatPane.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel labelSeat = new JLabel( "Seat " );
        txtSeat = new JTextField( Integer.toString( seat.getNumber( ) ) );
        txtSeat.setColumns( 15 );
        txtSeat.setEditable( false );
        seatPane.add( labelSeat );
        seatPane.add( txtSeat );
        add( seatPane );

        //Class
        JPanel classPane = new JPanel( );
        classPane.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel labelClass = new JLabel( "Class " );
        String sClass;
        if( seat.getClassSeat( ) == Seat.ECONOMIC_CLASS )
        {
            sClass = "Economic";
        }
        else
        {
            sClass = "Business";
        }
        txtClass = new JTextField( sClass );
        txtClass.setColumns( 15 );
        txtClass.setEditable( false );
        classPane.add( labelClass );
        classPane.add( txtClass );
        add( classPane );

        //Location
        JPanel locationPane = new JPanel( );
        locationPane.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel labelLocation = new JLabel( "Location " );
        String sUbicacion;
        if( seat.getLocation( ) == Seat.CENTER )
        {
            sUbicacion = "Center";
        }
        else if( seat.getLocation( ) == Seat.HALLWAY )
        {
            sUbicacion = "Hallway";
        }
        else
        {
            sUbicacion = "Window";
        }
        txtLocation = new JTextField( sUbicacion );
        txtLocation.setColumns( 15 );
        txtLocation.setEditable( false );
        locationPane.add( labelLocation );
        locationPane.add( txtLocation );
        add( locationPane );
    }
}