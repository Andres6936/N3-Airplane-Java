package edu.jabs.airplane.domain;

/**
 * Passenger airplane
 */
public class Passenger
{

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------
    /**
     * Passenger id
     */
    private int id;
    /**
     * Passenger name
     */
    private String name;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds a passenger with his document and name<br>
     * <b>post: </b>The passenger has his basic data document and name assigned.
     * @param aId - Passenger id - aId >0
     * @param aName - Passenger Name - aName != ""
     */
    public Passenger( int aId, String aName )
    {
        id = aId;
        name = aName;
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It returns the passenger document
     * @return passenger document
     */
    public int getId( )
    {
        return id;
    }

    /**
     * It returns the passenger name
     * @return Passenger name
     */
    public String getName( )
    {
        return name;
    }

    /**
     * It points out if the passenger is like another
     * @param otherPassenger - Passenger to compare - otherPassenger != null
     * @return true if it is the same passenger, false otherwise
     */
    public boolean equalsTo( Passenger otherPassenger )
    {
        if( id == otherPassenger.getId( ) )
            return true;
        else
            return false;
    }
}