package airplane;

/**
 * Plane
 */
public class Plane
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------
    /**
     * Number of business seats
     */
    public final static int BUSINESS_SEATS = 8;
    /**
     * Number of economic seats
     */
    public final static int ECONOMIC_SEATS = 42;

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------
    /**
     * Seats of the business class
     */
    private Seat[] businessSeats;
    /**
     * Seats of the economic class
     */
    private Seat[] economicSeats;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds a plane
     */
    public Plane( )
    {
        int location;

        // The business seats are built
        businessSeats = new Seat[BUSINESS_SEATS];

        // The economic seats are built
        economicSeats = new Seat[ECONOMIC_SEATS];

        businessSeats[ 0 ] = new Seat( 1, Seat.BUSINESS_CLASS, Seat.WINDOW );
        businessSeats[ 1 ] = new Seat( 2, Seat.BUSINESS_CLASS, Seat.HALLWAY );
        businessSeats[ 2 ] = new Seat( 3, Seat.BUSINESS_CLASS, Seat.HALLWAY );
        businessSeats[ 3 ] = new Seat( 4, Seat.BUSINESS_CLASS, Seat.WINDOW );
        businessSeats[ 4 ] = new Seat( 5, Seat.BUSINESS_CLASS, Seat.WINDOW );
        businessSeats[ 5 ] = new Seat( 6, Seat.BUSINESS_CLASS, Seat.HALLWAY );
        businessSeats[ 6 ] = new Seat( 7, Seat.BUSINESS_CLASS, Seat.HALLWAY );
        businessSeats[ 7 ] = new Seat( 8, Seat.BUSINESS_CLASS, Seat.WINDOW );

        for( int numSeat = 1 + BUSINESS_SEATS, j = 1; j <= ECONOMIC_SEATS; numSeat++, j++ )
        {
            //Window seats
            if( j % 6 == 1 || j % 6 == 0 )
                location = Seat.WINDOW;
                //Center seats
            else if( j % 6 == 2 || j % 6 == 5 )
                location = Seat.CENTER;
                //Hallway seats
            else
                location = Seat.HALLWAY;

            economicSeats[ j - 1 ] = new Seat( numSeat, Seat.ECONOMIC_CLASS, location );
        }
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It assigns a seat to a passenger, according to his class and location preferences<br>
     * <b>post: </b> If there is a seat with the class and location given, the passenger is assigned in the first seat according to numerical order.
     * @param classSeat - class chosen by the passenger - class belongs to {BUSINESS_CLASS ,ECONOMIC_CLASS}
     * @param location - location chosen by the passenger - if class = ECONOMIC_CLASS then location belongs to {WINDOW, CENTER, HALLWAY}, or if class = BUSINESS_CLASS
     *        then location belongs to {WINDOW, HALLWAY}
     * @param passenger - the passenger to be assigned - passenger != null and it has no seat on the plane
     * @return assigned passenger seat - If there is no assigned seat it returns null
     */
    public Seat assignSeat( int classSeat, int location, Passenger passenger )
    {
        //Finds an available seat
        Seat seat = null;
        if( classSeat == Seat.BUSINESS_CLASS )
        {
            seat = searchAvailableBusinessSeat( location );
        }
        else if( classSeat == Seat.ECONOMIC_CLASS )
        {
            seat = searchAvailableEconomicSeat( location );
        }
        if( seat != null )
        {
            seat.assignAPassenger( passenger );
        }
        return seat;
    }

    /**
     * It searches for the next available business seat having the given location.
     * @param location - location to find the seat  - location belongs to {WINDOW, HALLWAY}
     * @return The available found seat. If there is no seat it returns null.
     */
    public Seat searchAvailableBusinessSeat( int location )
    {
        boolean found = false;
        Seat seat = null;
        for( int i = 0; i < BUSINESS_SEATS && !found; i++ )
        {
            seat = businessSeats[ i ];
            if( ! ( seat.isAssigned( ) ) && seat.getLocation( ) == location )
            {
                found = true;
            }
        }
        if( found )
            return seat;
        else
            return null;
    }

    /**
     * It searches for the next available economic seat having the given location.
     * @param location - location to find the seat  - location belongs to {WINDOW, CENTER, HALLWAY}
     * @return The available found seat. If there is no seat it returns null.
     */
    public Seat searchAvailableEconomicSeat( int ubicacion )
    {
        boolean found = false;
        Seat seat = null;
        for( int i = 0; i < ECONOMIC_SEATS && !found; i++ )
        {
            seat = economicSeats[ i ];
            if( ! ( seat.isAssigned( ) ) && seat.getLocation( ) == ubicacion )
            {
                found = true;
            }
        }
        if( found )
            return seat;
        else
            return null;
    }

    /**
     * It searches for a passenger on the plane
     * @param passenger - passenger to be found - passenger != null
     * @return passenger seat. If there is no passenger it returns null
     */
    public Seat searchPassenger( Passenger passenger )
    {
        //It searches the passenger in business class
        Seat seat = searchBusinessPassenger( passenger );
        //If it was not  found in business class
        if( null == seat )
            //It searches in economic class
            seat = searchEconomicPassenger( passenger );
        return seat;

    }

    /**
     * It searches for a passenger in business class
     * @param passenger - passenger to be found - passenger != null
     * @return the seat in which the passenger has been found. If there is no passenger it returns null
     */
    public Seat searchBusinessPassenger( Passenger passenger )
    {
        boolean found = false;
        Seat seat = null;
        for( int i = 0; i < BUSINESS_SEATS && !found; i++ )
        {
            seat = businessSeats[ i ];
            if( seat.isAssigned( ) && seat.getPassenger( ).equalsTo( passenger ) )
            {
                found = true;
            }
        }
        if( found )
            return seat;
        else
            return null;
    }

    /**
     * It searches for a passenger in economic class
     * @param passenger - passenger to be found - passenger != null
     * @return the seat in which the passenger has been found. If there is no passenger it returns null
     */
    public Seat searchEconomicPassenger( Passenger pasajero )
    {
        boolean found = false;
        Seat seat = null;
        for( int i = 0; i < ECONOMIC_SEATS && !found; i++ )
        {
            seat = economicSeats[ i ];
            if( seat.isAssigned( ) && seat.getPassenger( ).equalsTo( pasajero ) )
            {
                found = true;
            }
        }
        if( found )
            return seat;
        else
            return null;
    }

    /**
     * Deallocates a passenger seat <br>
     * <b>post: </b> If the passenger seat is found, the seat will have its passenger == null
     * @param passenger - passenger to withdraw - passenger != null
     * @return true if the passenger is found and the seat is deallocated, false otherwise
     */
    public boolean deallocateSeat( Passenger passenger )
    {
        //It searches for the passenger on the plane
        Seat seat = searchPassenger( passenger );
        //If the passenger is found
        if( seat != null )
        {
            seat.deallocateSeat( );
            return true;
        }
        else
            return false;
    }

    /**
     * It returns the number of occupied business class
     * @return number of occupied business class
     */
    public int countOccupiedBusinessSeats( )
    {
        int counter = 0;
        for( int i = 0; i < BUSINESS_SEATS; i++ )
        {
            if( businessSeats[ i ].isAssigned( ) )
            {
                counter++;
            }
        }
        return counter;
    }

    /**
     * It returns the number of occupied economic class
     * @return number of occupied economic seats
     */
    public int countOccupiedEconomicSeats( )
    {
        int counter = 0;
        for( int i = 0; i < ECONOMIC_SEATS; i++ )
        {
            if( economicSeats[ i ].isAssigned( ) )
            {
                counter++;
            }
        }
        return counter;
    }

    /**
     * It computes the percentage of occupancy of the plane
     * @return the total percentage of occupancy
     */
    public double calculateOccupancyPercentage( )
    {
        double percentage;
        int totalSeats = ECONOMIC_SEATS + BUSINESS_SEATS;
        int occupiedSeats = countOccupiedEconomicSeats( ) + countOccupiedBusinessSeats( );
        percentage = ( double )occupiedSeats / totalSeats * 100;
        return percentage;
    }

    /**
     * It returns the business seats of the plane
     * @return business seats
     */
    public Seat[] getBusinessSeats( )
    {
        return businessSeats;
    }

    /**
     * It returns the economic seats of the plane
     * @return economic seats
     */
    public Seat[] getEconomicSeats( )
    {
        return economicSeats;
    }

    /**
     * Extension method 1
     * @return answer
     */
    public String method1( )
    {
        return "answer 1";
    }

    /**
     * Extension method 2
     * @return answer
     */
    public String method2( )
    {
        return "answer 2";
    }
}