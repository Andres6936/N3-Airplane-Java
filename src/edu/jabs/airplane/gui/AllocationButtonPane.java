package edu.jabs.airplane.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Button Panel for dialogue assignment
 */
public class AllocationButtonPane extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * Accept option
     */
    public final static String ACCEPT = "ACCEPT";

    /**
     * Cancel option
     */
    public final static String CANCEL = "CANCEL";

    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------

    /**
     * Accept button
     */
    private JButton acceptButton;

    /**
     * Cancel button
     */
    private JButton cancelButton;

    /**
     * Dialogue which is part of the panel
     */
    private AllocationDialog dialog;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds a button pane for the seat assignment on the plane
     * @param theDialog - Dialog owned by the button pane - theDialog != null
     */
    public AllocationButtonPane( AllocationDialog theDialog )
    {
        setLayout( new GridLayout( 1, 2, 8, 1 ) );
        dialog = theDialog;

        //Accept
        acceptButton = new JButton( );
        acceptButton.setText( "Accept" );
        acceptButton.setActionCommand( ACCEPT );
        acceptButton.addActionListener( this );
        add( acceptButton );

        //Cancel
        cancelButton = new JButton( );
        cancelButton.setText( "Cancel" );
        cancelButton.setActionCommand( CANCEL );
        cancelButton.addActionListener( this );
        add( cancelButton );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------
    /**
     *
     * Actions in response to the GUI events
     * @param event - event generated by an gui element
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );

        if( command.equals( ACCEPT ) )
        {
            dialog.register( );
        }
        else if( command.equals( CANCEL ) )
        {
            dialog.cancel( );
        }
    }
}