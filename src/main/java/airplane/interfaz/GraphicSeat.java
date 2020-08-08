package airplane.interfaz;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

import airplane.Seat;

/**
 * Graphic representation of a plane seat
 */
public class GraphicSeat extends JPanel
{
    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------
    /**
     * Seat Image
     */
    private ImageIcon image;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds the graphic representation of the seat<br>
     * <b>post: </b> the seat image distinguish if it is available or occupied
     * @param seat - seat to be represented - seat != null
     */
    public GraphicSeat( Seat seat )
    {
        super(new BorderLayout());
        JLabel lSeat = new JLabel();

        ClassLoader loader = getClass().getClassLoader();

        if (seat.getClassSeat() == Seat.BUSINESS_CLASS && seat.isAssigned())
            image = new ImageIcon(Objects.requireNonNull(loader.getResource("data/images/SeatBusinAssig.gif")));
        else
            if (seat.getClassSeat() == Seat.BUSINESS_CLASS && !seat.isAssigned())
                image = new ImageIcon(Objects.requireNonNull(loader.getResource("data/images/SeatBusinFree.gif")));
            else
                if (seat.getClassSeat() == Seat.ECONOMIC_CLASS && seat.isAssigned())
                    image = new ImageIcon(Objects.requireNonNull(loader.getResource("data/images/SeatEconAssig.gif")));
                else
                    if (seat.getClassSeat() == Seat.ECONOMIC_CLASS && !seat.isAssigned()) {
                        image = new ImageIcon(Objects.requireNonNull(loader.getResource("data/images/SeatEconFree.gif")));
                        lSeat.setForeground(Color.white);
        }
        setPreferredSize( new Dimension( image.getIconWidth( ), image.getIconHeight( ) ) );
        setOpaque( false );
        lSeat.setPreferredSize( new Dimension( image.getIconWidth( ), image.getIconHeight( ) ) );
        lSeat.setText( "" + seat.getNumber( ) );
        lSeat.setHorizontalAlignment( SwingConstants.CENTER );
        add( lSeat );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------
    /**
     * It draws the seat image
     * @param graph graph of the seat
     */
    public void paint( Graphics graph )
    {
        graph.drawImage( image.getImage( ), 0, 0, null, null );
        super.paint( graph );
    }
}