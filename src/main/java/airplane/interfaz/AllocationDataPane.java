package airplane.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import airplane.Seat;

/**
 * Passenger record data pane
 */
public class AllocationDataPane extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------
    private final static String BUSINESS_CLASS = "Business class";
    private final static String ECONOMIC_CLASS = "Economic class";
    private final static String WINDOW_LOCATION = "Window";
    private final static String HALLWAY_LOCATION = "Hallway";
    private final static String CENTER_LOCATION = "Center";

    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------

    /**
     * Combo box for the class selection
     */
    private JComboBox cbClass;

    /**
     * Combo box for the location selection
     */
    private JComboBox cbLocation;

    /**
     * Text Field for the passenger id
     */
    private JTextField txtId;

    /**
     * Text Field for the passenger name
     */
    private JTextField txtName;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It creates the required data allocation pane
     */
    public AllocationDataPane( )
    {
        setLayout( new GridLayout( 4, 1, 1, 6 ) );
        setBorder( BorderFactory.createTitledBorder( "Passenger Information" ) );

        //Id
        JPanel idPane = new JPanel( );
        idPane.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel labelId = new JLabel( "Id " );
        txtId = new JTextField( );
        txtId.setColumns( 15 );
        idPane.add( labelId );
        idPane.add( txtId );
        add( idPane );

        //Name
        JPanel namePane = new JPanel( );
        namePane.setLayout( new FlowLayout( FlowLayout.RIGHT, 5, 0 ) );
        JLabel etiquetaNombre = new JLabel( "Name " );
        txtName = new JTextField( );
        txtName.setColumns( 15 );
        namePane.add( etiquetaNombre );
        namePane.add( txtName );
        add( namePane );

        //Location on the plane
        JPanel pLocation = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
        pLocation.setPreferredSize( new Dimension( 250, 30 ) );
        JLabel lLocation = new JLabel( "Location " );
        cbLocation = new JComboBox( );
        cbLocation.setAlignmentX( Component.LEFT_ALIGNMENT );
        cbLocation.setPreferredSize( txtId.getPreferredSize( ) );
        cbLocation.addActionListener( this );
        pLocation.add( lLocation );
        pLocation.add( cbLocation );

        //Class of the seat
        JPanel pClass = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
        pClass.setPreferredSize( new Dimension( 250, 30 ) );
        JLabel lClass = new JLabel( "Class " );
        cbClass = new JComboBox( );
        cbClass.setAlignmentX( Component.LEFT_ALIGNMENT );
        cbClass.addActionListener( this );
        cbClass.addItem( ECONOMIC_CLASS );
        cbClass.addItem( BUSINESS_CLASS );
        cbClass.setPreferredSize( txtId.getPreferredSize( ) );
        pClass.add( lClass );
        pClass.add( cbClass );

        add( pClass );
        add( pLocation );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It returns the name text field content
     * @return the name text field value
     */
    public String getPassengerName( )
    {
        return txtName.getText( );
    }

    /**
     * It returns the document text field content
     * @return the document text field value
     */
    public String getId( )
    {
        return txtId.getText( );
    }

    /**
     * It returns the class of the seat chosen on the pane
     * @return class of the seat - class belongs to {BUSINESS_CLASS, ECONOMIC_CLASS}
     */
    public int getClassSeat( )
    {
        String sClass = ( String )cbClass.getSelectedItem( );
        if( sClass.equals( BUSINESS_CLASS ) )
            return Seat.BUSINESS_CLASS;
        else
            return Seat.ECONOMIC_CLASS;
    }

    /**
     * It returns the seat location chosen on the pane
     * @return location - If class = BUSINESS_CLASS then location belongs to {WINDOW, HALLWAY} or If class = ECONOMIC_CLASS then location belongs to {WINDOW,
     *         CENTER, HALLWAY}
     */
    public char getSeatLocation( )
    {
        String sLocation = ( String )cbLocation.getSelectedItem( );
        if( sLocation.equals( WINDOW_LOCATION ) )
            return Seat.WINDOW;
        else if( sLocation.equals( HALLWAY_LOCATION ) )
            return Seat.HALLWAY;
        else
            return Seat.CENTER;
    }

    /**
     * Actions in response to the GUI events
     * @param event - event generated
     */
    public void actionPerformed( ActionEvent event )
    {
        if( event.getSource( ) == cbClass )
        {
            String sClass = ( String )cbClass.getSelectedItem( );

            if( sClass.equals( BUSINESS_CLASS ) )
            {
                cbLocation.removeAllItems( );
                cbLocation.addItem( WINDOW_LOCATION );
                cbLocation.addItem( HALLWAY_LOCATION );
            }
            else
            {
                cbLocation.removeAllItems( );
                cbLocation.addItem( WINDOW_LOCATION );
                cbLocation.addItem( CENTER_LOCATION );
                cbLocation.addItem( HALLWAY_LOCATION );
            }
            cbLocation.validate( );
        }
    }
}