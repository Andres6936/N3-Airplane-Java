package edu.jabs.airplane.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import edu.jabs.airplane.domain.Plane;
import edu.jabs.airplane.domain.Seat;

/**
 * Drawing of the plane Pane
 */
public class PlanePane extends JPanel
{
    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Plane of the world model
     */
    private Plane plane;

    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------

    /**
     * Plane image
     */
    private ImageIcon image;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds a plane pane <br>
     * <b>post: <b>All the seats and plane are drawn
     * @param plane1 - plane to be drawn - plane1 != null
     */
    public PlanePane( Plane plane1 )
    {
        super( new BorderLayout( ) );
        plane = plane1;
        image = new ImageIcon( "data/images/PlanePlan.gif" );
        setPreferredSize( new Dimension( image.getIconWidth( ), image.getIconHeight( ) ) );
        setOpaque( false );

        //The business seat images are added
        JPanel businessPane = new JPanel( );
        drawBusinessSeats( businessPane );
        add( businessPane, BorderLayout.NORTH );

        //The economic seat images are added
        JPanel economicPane = new JPanel( );
        drawEconomicSeats( economicPane );
        add( economicPane, BorderLayout.CENTER );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It draws the graph of the plane
     * @param graph - graph of the plane - graph != null
     */
    public void paint( Graphics graph )
    {
        graph.drawImage( image.getImage( ), 0, 0, null, null );
        super.paint( graph );
    }

    /**
     * It creates the graphs of the business seats of the plane<br>
     * <b>pre: </b> plane != null. <br>
     * <b>post: </b> The business seats of the plane are drawn.
     * @param pane - pane where the seats are drawn
     */
    private void drawBusinessSeats( JPanel pane )
    {
        pane.setLayout( new GridLayout( Plane.BUSINESS_SEATS / 4, 5, 8, 5 ) );
        pane.setBorder( new EmptyBorder( 90, 210, 20, 210 ) );

        Seat[] business = plane.getBusinessSeats( );
        Seat seat;

        for( int i = 0; i < business.length; i++ )
        {
            seat = business[ i ];
            GraphicSeat seatG = new GraphicSeat( seat );
            pane.add( seatG );
            if( i % 4 == 1 )
            {
                pane.add( new JLabel( " " ) );
            }
        }
        pane.setOpaque( false );
    }

    /**
     * It creates the graphs of the economic seats of the plane<br>
     * <b>pre: </b> plane != null. <br>
     * <b>post: </b> The economic seats of the plane are drawn.
     * @param panel - pane where the seats are drawn
     */
    private void drawEconomicSeats( JPanel panel )
    {
        panel.setLayout( new GridLayout( Plane.ECONOMIC_SEATS / 6, 7, 5, 4 ) );
        panel.setBorder( new EmptyBorder( 0, 200, 100, 200 ) );

        Seat[] economics = plane.getEconomicSeats( );
        Seat seat;

        for( int i = 0; i < economics.length; i++ )
        {
            seat = economics[ i ];

            GraphicSeat seatG = new GraphicSeat( seat );
            panel.add( seatG );
            if( i % 6 == 2 )
            {
                panel.add( new JLabel( " " ) );
            }
        }
        panel.setOpaque( false );
    }
}