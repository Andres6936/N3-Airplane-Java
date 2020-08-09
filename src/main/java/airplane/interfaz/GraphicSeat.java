package airplane.interfaz;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;

import airplane.Seat;

/**
 * Graphic representation of a plane seat
 */
public class GraphicSeat extends JButton {
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
     *
     * @param seat - seat to be represented - seat != null
     */
    public GraphicSeat(Seat seat) {
        ClassLoader loader = getClass().getClassLoader();

        try {
            // Prepare the image for modify the color if is needed
            BufferedImage bufferedImage = ImageIO.read(new File(
                    Objects.requireNonNull(loader.getResource(
                            "Icons/Expansion/Black/1x/figurine.png")).getFile()));

            bufferedImage = scaleImage(bufferedImage, 25, 25);

            if (seat.getClassSeat() == Seat.BUSINESS_CLASS && seat.isAssigned()) {
                image = new ImageIcon(bufferedImage);
            } else if (seat.getClassSeat() == Seat.BUSINESS_CLASS && !seat.isAssigned()) {
                image = new ImageIcon(bufferedImage);
            } else if (seat.getClassSeat() == Seat.ECONOMIC_CLASS && seat.isAssigned()) {
                image = new ImageIcon(bufferedImage);
            } else if (seat.getClassSeat() == Seat.ECONOMIC_CLASS && !seat.isAssigned()) {
                image = new ImageIcon(bufferedImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setOpaque(false);
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It draws the seat image
     *
     * @param graph graph of the seat
     */
    public void paint(Graphics graph) {
        graph.drawImage(image.getImage(), 0, 0, null, null);
        super.paint(graph);
    }

    private static BufferedImage scaleImage(final BufferedImage img, final int width, final int height)
    {
        Image resize = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = buffer.createGraphics();

        graphics2D.drawImage(resize, 0, 0, null);
        graphics2D.dispose();

        return buffer;
    }
}