package edu.jabs.airplane.test;

import junit.framework.TestCase;

import edu.jabs.airplane.domain.Passenger;
import edu.jabs.airplane.domain.Plane;
import edu.jabs.airplane.domain.Seat;

/**
 * Test class for the plane class
 */
public class PlaneTest extends TestCase
{

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Plane
     */
    private Plane plane;
    /**
     * Passenger 1
     */
    private Passenger p1;
    /**
     * Passenger 2
     */
    private Passenger p2;
    /**
     * Passenger 3
     */
    private Passenger p3;
    /**
     * Passenger 4
     */
    private Passenger p4;
    /**
     * Passenger Name 1
     */
    private String name1;
    /**
     * Passenger Id 1
     */
    private int id1;
    /**
     * Passenger name 2
     */
    private String name2;
    /**
     * Passenger id 2
     */
    private int id2;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It prepares the test data to test the airplane. <br>
     * <b>post: </b> Two passengers are created, one of them is assigned to a business seat and the other one is assigned to the economic seat.
     */
    private void setupScenario1( )
    {
        //The plane is built
        plane = new Plane( );

        //The names and ids are prepared
        name1 = "Camilo Pérez";
        id1 = 12345;
        name2 = "Fernando Santander";
        id2 = 23456;

        //The passengers are built
        p1 = new Passenger( id1, name1 );
        p2 = new Passenger( id2, name2 );

        //the first passenger is assigned to a business seat on the window
        plane.assignSeat( Seat.BUSINESS_CLASS, Seat.WINDOW, p1 );

        //the first passenger is assigned to a economic seat on the hallway
        plane.assignSeat( Seat.ECONOMIC_CLASS, Seat.HALLWAY, p2 );

    }

    /**
     * It prepares the test data to test the airplane. <br>
     * <b>post: </b> Two passengers are created, one of them is assigned to a business seat and the other one is assigned to the economic seat.
     */
    private void setupScenario2( )
    {
        String name;
        int id;

        //The scenario 1 is used
        setupScenario1( );

        //The passengers are built
        name = "Clara Martínez";
        id = 34567;
        p3 = new Passenger( id, name );
        name = "Sonia Osorio";
        id = 56789;
        p4 = new Passenger( id, name );

    }

    /**
     * It verifies the correct business seat assignment
     */
    public void testAsignSeat1( )
    {
        Seat seatP1;
        Passenger p;

        //The test data is set
        setupScenario1( );

        seatP1 = plane.searchPassenger( p1 );

        //Passenger 1 travels in business class
        assertEquals( Seat.BUSINESS_CLASS, seatP1.getClassSeat( ) );

        //Passenger 1 travels in window location
        assertEquals( Seat.WINDOW, seatP1.getLocation( ) );

        //The first business seat on window is the number 1
        assertEquals( 1, seatP1.getNumber( ) );

        //The passenger must be the same
        p = seatP1.getPassenger( );
        assertTrue( p1.equalsTo( p ) );

    }

    /**
     * It verifies the correct economic seat assignment
     */
    public void testAsignSeat( )
    {
        Seat seatP2;
        Passenger p;

        //The test data is set
        setupScenario1( );

        seatP2 = plane.searchPassenger( p2 );

        //Passenger 2 travels in economic class
        assertEquals( Seat.ECONOMIC_CLASS, seatP2.getClassSeat( ) );

        //Passenger 2 travels on the hallway
        assertEquals( Seat.HALLWAY, seatP2.getLocation( ) );

        //The first economic seat on the hallway is number 11
        assertEquals( 11, seatP2.getNumber( ) );

        //The passenger must be the same
        p = seatP2.getPassenger( );
        assertTrue( p2.equalsTo( p ) );

    }

    /**
     * It verifies a economic passenger search that exists
     */
    public void testSearchPassenger1( )
    {
        Passenger p;
        Seat s;

        //The test data is set
        setupScenario1( );

        s = plane.searchEconomicPassenger( p2 );
        if( s == null )
            fail( "The passenger should exist" );
        else
        {
            p = s.getPassenger( );
            assertEquals( id2, p.getId( ) );
            assertEquals( name2, p.getName( ) );
        }
    }

    /**
     * It verifies a economic passenger search that does not exist
     */
    public void testSearchPassenger2( )
    {
        Seat s;

        //The test data is set
        setupScenario1( );

        s = plane.searchEconomicPassenger( p1 );
        if( s == null )
            assertTrue( true );
        else
        {
            fail( "The passenger should NOT exist" );
        }
    }

    /**
     * It verifies a business passenger search that exists
     */
    public void testSearchPassenger3( )
    {
        Passenger p;
        Seat s;

        //The test data is set
        setupScenario1( );

        s = plane.searchBusinessPassenger( p1 );
        if( s == null )
            fail( "The passenger should exist" );
        else
        {
            p = s.getPassenger( );
            assertEquals( id1, p.getId( ) );
            assertEquals( name1, p.getName( ) );
        }
    }

    /**
     * It verifies a business passenger search that does not exist
     */
    public void testSearchPassenger4( )
    {
        Seat s;

        //The test data is set
        setupScenario1( );

        s = plane.searchBusinessPassenger( p2 );
        if( s == null )
            assertTrue( true );
        else
        {
            fail( "The passenger should NOT exist" );
        }
    }

    /**
     * It searches for the next economic seat available
     */
    public void testSearchAvailableEconomicSeat1( )
    {
        Seat s;

        //The test data is set
        setupScenario1( );

        //The next hallway economic seat available is number 12
        s = plane.searchAvailableEconomicSeat( Seat.HALLWAY );
        assertEquals( 12, s.getNumber( ) );

        //The next window economic seat available is number 9
        s = plane.searchAvailableEconomicSeat( Seat.WINDOW );
        assertEquals( 9, s.getNumber( ) );

        //The next center economic seat available is number 12
        s = plane.searchAvailableEconomicSeat( Seat.HALLWAY );
        assertEquals( 12, s.getNumber( ) );
    }

    /**
     * It searches for the next business seat available
     */
    public void testSearchAvailableBusinessSeat1( )
    {
        Seat s;

        //The test data is set
        setupScenario1( );

        //The next hallway business seat available is number 2
        s = plane.searchAvailableBusinessSeat( Seat.HALLWAY );
        assertEquals( 2, s.getNumber( ) );

        //The next window business seat available is number 4
        s = plane.searchAvailableBusinessSeat( Seat.WINDOW );
        assertEquals( 4, s.getNumber( ) );

    }

    /**
     * It test the occupancy rate
     */
    public void testComputeOccupancyRate1( )
    {
        double expectedRate, rate;

        //The test data is set
        setupScenario2( );

        //Initially the occupancy rate is 2*100/50
        expectedRate = ( 2 * 100 ) / 50;
        rate = plane.calculateOccupancyPercentage( );
        assertEquals( expectedRate, rate, 0 );

        //Two others passengers are assigned
        plane.assignSeat( Seat.ECONOMIC_CLASS, Seat.CENTER, p3 );
        plane.assignSeat( Seat.BUSINESS_CLASS, Seat.WINDOW, p4 );

        //Now the rate is 4*100/50
        expectedRate = ( 4 * 100 ) / 50;
        rate = plane.calculateOccupancyPercentage( );
        assertEquals( expectedRate, rate, 0 );
    }

    /**
     * It counts the occupied economic seats
     */
    public void testCountOccupiedEconomicSeats1( )
    {

        //The test data is set
        setupScenario2( );

        //Initially there is 1 occupied seat
        assertEquals( 1, plane.countOccupiedEconomicSeats( ) );

        //Two others passengers are assigned
        plane.assignSeat( Seat.ECONOMIC_CLASS, Seat.CENTER, p3 );
        plane.assignSeat( Seat.ECONOMIC_CLASS, Seat.WINDOW, p4 );

        //Now the number of occupied seats is 3
        assertEquals( 3, plane.countOccupiedEconomicSeats( ) );
    }

    /**
     * It counts the occupied business seats
     */
    public void testCountOccupiedBusinessSeats1( )
    {

        Seat s;

        //The test data is set
        setupScenario2( );

        //Initially there is 1 occupied seat
        assertEquals( 1, plane.countOccupiedBusinessSeats( ) );

        //Two others passengers are assigned
        s = plane.assignSeat( Seat.BUSINESS_CLASS, Seat.HALLWAY, p3 );
        if( s == null )
            fail( "Seat number 1 had to be assigned" );

        s = plane.assignSeat( Seat.BUSINESS_CLASS, Seat.WINDOW, p4 );
        if( s == null )
            fail( "Seat number 2 had to be assigned" );

        //Now the number of occupied seats is 3
        assertEquals( 3, plane.countOccupiedBusinessSeats( ) );
    }

    /**
     * It verifies the seat deallocation
     */
    public void testDeallocateSeat1( )
    {
        Seat s;

        //The test data is set
        setupScenario1( );

        plane.deallocateSeat( p1 );

        //The passenger must not be on the plane
        s = plane.searchPassenger( p1 );

        if( s == null )
            assertTrue( true );
        else
            fail( "The passenger should not be" );
    }

}