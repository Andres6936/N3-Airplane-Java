package edu.jabs.airplane.domain;

/**
 * Seat of the plane
 */
public class Seat
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------
    /**
     * Business seat class
     */
    public final static int BUSINESS_CLASS = 1;
    /**
     * Economic seat class
     */
    public final static int ECONOMIC_CLASS = 2;
    /**
     * Location of the seat in the window
     */
    public final static int WINDOW = 1;
    /**
     * Location of the seat in the center
     */
    public final static int CENTER = 2;
    /**
     * Location of the seat in the hallway
     */
    public final static int HALLWAY = 3;

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------
    /**
     * Seat number
     */
    private int number;
    /**
     * Class of the seat
     */
    private int classSeat;
    /**
     * Seat location
     */
    private int location;
    /**
     * Passenger assigned to the seat
     */
    private Passenger passenger;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * The seat is created with his identifying number <br>
     * <b>post: </b> The object seat has his basic data number, class and location assigned. The passenger is not assign and has null value
     * @param aNumber - number's seat - aNumber > 0
     * @param aClassSeat - class of the seat - aClassSeat belongs to {BUSINESS_CLASS,ECONOMIC_CLASS}
     * @param aLocation - Location's seat - aLocation belongs to {WINDOW, CENTER, HALLWAY}
     */
    public Seat( int aNumber, int aClassSeat, int aLocation )
    {
        number = aNumber;
        classSeat = aClassSeat;
        location = aLocation;
        // Initially there is no passenger assigned to the seat
        passenger = null;
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It assigns the passenger seat. <br>
     * <b>post: </b> passenger = aPassenger
     * @param p - passenger to be assigned to the seat - p !=null
     */
    public void assignAPassenger( Passenger p )
    {
        passenger = p;
    }

    /**
     * It deallocates the passenger seat. The seat is available again. <br>
     * <b>post: </b> passenger == null
     */
    public void deallocateSeat( )
    {
        passenger = null;
    }

    /**
     * It points out if the seat is assigned.
     * @return true if the seat is assign, false otherwise
     */
    public boolean isAssigned( )
    {
        if( null == passenger )
            return false;
        else
            return true;
    }

    /**
     * It points out if the seat is assigned to the given passenger.
     * @param p - passenger to compare to the passenger seat
     * @return true if the passenger occupies the seat, false if the seat is empty or the passengers does not match .
     */
    public boolean seatAssignedPassenger( Passenger p )
    {
        if( null == passenger )
            return false;
        else if( passenger.equalsTo( p ) )
            return true;
        else
            return false;
    }

    /**
     * It returns the seat number
     * @return seat number
     */
    public int getNumber( )
    {
        return number;
    }

    /**
     * It returns the class of the seat
     * @return class
     */
    public int getClassSeat( )
    {
        return classSeat;
    }

    /**
     * It returns the seat location
     * @return location
     */
    public int getLocation( )
    {
        return location;
    }

    /**
     * It returns the passenger assigned to the seat
     * @return passenger assigned to the seat - If there is no passenger it returns null.
     */
    public Passenger getPassenger( )
    {
        return passenger;
    }
}