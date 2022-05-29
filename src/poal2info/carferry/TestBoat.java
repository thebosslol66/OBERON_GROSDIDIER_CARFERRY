package poal2info.carferry;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Queue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;

class TestBoatUtils {
	public static final Driver d1 = new Driver("Martin", "Jeanne", "22FF");
	public static final Driver d2 = new Driver("Dupont", "Vincent", "A55");
	public static final Driver d3 = new Driver("Durand", "Marie", "B34");
	public static final Driver d4 = new Driver("Grant", "Philip", "20FF");
	public static final Driver d5 = new Driver("Scott", "Simon", "B55JG");
	public static final Driver d6 = new Driver("Lambert", "Alain", "C44Djk");
	public static final Vehicle v1 = new Car("RM 1054 FF", 1.2, 4.2, d1, 2);
	public static final Vehicle v2 = new Car("PO 377 AA",  1.4, 4.5, d2, 1);
	public static final Vehicle v3 = new Car("WX 456 RT",  1.2, 5.3, d3, 0);
	public static final Vehicle c1 = new Truck("AZ 678 DF", 4.0, 12.0, d4, 15.0);
	public static final Vehicle c2 = new Truck("QS 543 HJ", 5.2, 13.5, d5, 22.5);
	public static final Vehicle c3 = new Truck("BN 321 XC", 4.5, 15.0, d6, 18.0);
}
@SelectClasses({Boat.class})
class TestBoat {

	Boat b;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Tag("Load")
	@Nested
	class Loading {
		
		@BeforeEach
		void setUp() throws Exception {
			b = new Boat(25.0, 75.0, 2);
		}
	
		@AfterEach
		void tearDown() throws Exception {
			b = null;
		}
	
		@Test
		void testEmptyBoat() {
			assertAll("Storage",
				() -> {
					assertEquals(0, b.getVehiculeRow(0).size()+b.getVehiculeRow(1).size(),
							() -> "The boat isn't empty!");
				},
				() -> {
					assertEquals(0, b.getListingTicket().size(), 0,
							() -> "The boat have a ticket with no passengers");
				}
			);
		}
	
		@Test
		void testAddOneVehicle() {
			try {
				b.addVehicle(TestBoatUtils.v1);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("Adding a vehicle throws an error");
			}
			assertAll("Storage",
				() -> {
					assertEquals(1, b.getVehiculeRow(0).size()+b.getVehiculeRow(1).size(),
							() -> "The boat don't store the vehicle");
				},
				() -> {
					assertEquals(1, b.getListingTicket().size(),
							() -> "The boat don't store correctly the ticket");
				}
			);
		}
		
		@Test
		void testMaxLengthException() {
			Vehicle LongC1 = new Car("RM 1054 FF", 1.2, 23.0, TestBoatUtils.d1, 0);
			Vehicle LongC2 = new Car("PO 377 AA",  1.4, 23.0, TestBoatUtils.d2, 4);
			
			try {
				b.addVehicle(LongC1);
				b.addVehicle(LongC2);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("You must organise vehicle in wedge");
			}
			Throwable exception = assertThrows(BoatException.class,
					() -> b.addVehicle(TestBoatUtils.v3)
			);
			assertEquals(BoatException.Reason.NOT_ENOUGTH_SPACE, ((BoatException)exception).getReason());
			assertEquals("There is no free place for this vehicle", exception.getMessage());
		}
		
		@Tag("BoatExeptions")
		@Test
		void testMaxWeightException() {
			Vehicle HeavyC1 = new Car("RM 1054 FF", 4.0, 3.5, TestBoatUtils.d1, 0);
			Vehicle HeavyT1 = new Truck("PO 377 AA",  5.0, 17.0, TestBoatUtils.d2, 65);
			
			try {
				b.addVehicle(HeavyC1);
				b.addVehicle(HeavyT1);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("You must organise vehicle in wedge");
			}
			Throwable exception = assertThrows(BoatException.class,
					() -> b.addVehicle(TestBoatUtils.v3)
			);
			assertEquals(BoatException.Reason.TOO_HEAVY, ((BoatException)exception).getReason());
			assertEquals("The boat can't handle more weight", exception.getMessage());
		}
		
		@Tag("BoatExeptions")
		@Test
		void testDuplicateVehicleException() {
			try {
				b.addVehicle(TestBoatUtils.v1);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("You must organise vehicle in wedge");
			}
			Throwable exception = assertThrows(BoatException.class,
					() -> b.addVehicle(TestBoatUtils.v1)
			);
			assertEquals(BoatException.Reason.CAR_IS_ALREADY_LOADED, ((BoatException)exception).getReason());
			assertEquals("The vehicle is already in the boat", exception.getMessage());
		}
		
		@Tag("BoatExeptions")
		@Test
		void testSameDriverException() {
			Vehicle vd = new Car("PO 377 AA",  1.4, 4.5, TestBoatUtils.d1, 4);
					
			try {
				b.addVehicle(TestBoatUtils.v1);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("You must organise vehicle in wedge");
			}
			Throwable exception = assertThrows(BoatException.class,
					() -> b.addVehicle(vd)
			);
			assertEquals(BoatException.Reason.DRIVER_HAVE_ALREADY_A_CAR, ((BoatException)exception).getReason());
			assertEquals("The vehicle have a driver which have already a vehicle in the boat", exception.getMessage());
		}
		
		@Tag("BoatExeptions")
		@Test
		void testSameVehicleException() {
			Vehicle vd = new Car("RM 1054 FF", 1.2, 4.2, TestBoatUtils.d2, 0);
			
			try {
				b.addVehicle(TestBoatUtils.v1);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("You must organise vehicle in wedge");
			}
			Throwable exception = assertThrows(BoatException.class,
					() -> b.addVehicle(vd)
			);
			assertEquals(BoatException.Reason.CAR_IS_ALREADY_LOADED, ((BoatException)exception).getReason());
			assertEquals("The vehicle is already in the boat", exception.getMessage());
		}
		
		@Test
		void testLoadingPlacement() throws BoatException {
			try {
				b.addVehicle(TestBoatUtils.v3);
				b.addVehicle(TestBoatUtils.c1);
				b.addVehicle(TestBoatUtils.c2);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("You must organise vehicle in wedge");
			}
			
			Queue<Vehicle> g = b.getVehiculeRow(0);
			Queue<Vehicle> d = b.getVehiculeRow(1);
			
			assertEquals(3, g.size()+d.size(),
					() -> "There is a problem when adding vehicle to boat");
			assertEquals(TestBoatUtils.v3, g.poll());
			assertEquals(TestBoatUtils.c1, d.poll());
			assertEquals(TestBoatUtils.c2, g.poll());
			}
		
		@Test
		void testLoadingPlacementLikeAsking() throws BoatException {
			try {
				b.addVehicle(TestBoatUtils.c1);
				b.addVehicle(TestBoatUtils.v1);
				b.addVehicle(TestBoatUtils.v2);
				b.addVehicle(TestBoatUtils.c2);
				b.addVehicle(TestBoatUtils.v3);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("You must organise vehicle in wedge");
			}
			
			Throwable exception = assertThrows(BoatException.class,
					() -> b.addVehicle(TestBoatUtils.c3)
			);
			assertEquals(BoatException.Reason.NOT_ENOUGTH_SPACE, ((BoatException)exception).getReason());
			
			Queue<Vehicle> g = b.getVehiculeRow(0);
			Queue<Vehicle> d = b.getVehiculeRow(1);
			
			assertEquals(5, g.size()+d.size(),
					() -> "There is a problem when adding vehicle to boat");
			assertEquals(TestBoatUtils.c1, g.poll());
			assertEquals(TestBoatUtils.v1, d.poll());
			assertEquals(TestBoatUtils.v2, d.poll());
			assertEquals(TestBoatUtils.c2, d.poll());
			assertEquals(TestBoatUtils.v3, g.poll());
		}
		
		@Test
		void testLoadingPassengerLikeAsking() throws BoatException {
			try {
				b.addVehicle(TestBoatUtils.c1);
				b.addVehicle(TestBoatUtils.v1);
				b.addVehicle(TestBoatUtils.v2);
				b.addVehicle(TestBoatUtils.c2);
				b.addVehicle(TestBoatUtils.v3);
			} catch (BoatException e) {
				e.printStackTrace();
				fail("You must organise vehicle in wedge");
			}
			
			assertEquals(11, b.getPassengers());
		}
	}
	
	@Tag("Unload")
	@Nested
	class Unloading {
		
		@BeforeEach
		void setUp() throws Exception {
			b = new Boat(25.0, 75.0, 2);
			try {
				b.addVehicle(TestBoatUtils.c1);
				b.addVehicle(TestBoatUtils.v1);
				b.addVehicle(TestBoatUtils.v2);
				b.addVehicle(TestBoatUtils.c2);
				b.addVehicle(TestBoatUtils.v3);
			} catch (Exception e){
				assumeTrue(false, "Loading not complete");
			}
		}
	
		@AfterEach
		void tearDown() throws Exception {
			b = null;
		}
		
		@Test
		void testUnloadingOneVehicle() throws BoatException {
			assertEquals(TestBoatUtils.v1, b.removeVehicle());
			
			Queue<Vehicle> g = b.getVehiculeRow(0);
			Queue<Vehicle> d = b.getVehiculeRow(1);
			
			assertEquals(4, g.size()+d.size(),
					() -> "There is a problem when adding vehicle to boat");
			assertEquals(TestBoatUtils.c1, g.poll());
			assertEquals(TestBoatUtils.v2, d.poll());
			assertEquals(TestBoatUtils.c2, d.poll());
			assertEquals(TestBoatUtils.v3, g.poll());
		}
		
		@Test
		void testCorrectUnloadingOneVehicle() throws BoatException {
			assertEquals(TestBoatUtils.v1, b.removeVehicle());
			assertEquals(TestBoatUtils.v2, b.removeVehicle());
			assertEquals(TestBoatUtils.c2, b.removeVehicle());
			assertEquals(TestBoatUtils.c1, b.removeVehicle());
			assertEquals(TestBoatUtils.v3, b.removeVehicle());
		}
		
		@Tag("BoatExeptions")
		@Test
		void testEmptyException() throws BoatException {
			assertEquals(TestBoatUtils.v1, b.removeVehicle());
			assertEquals(TestBoatUtils.v2, b.removeVehicle());
			assertEquals(TestBoatUtils.c2, b.removeVehicle());
			assertEquals(TestBoatUtils.c1, b.removeVehicle());
			assertEquals(TestBoatUtils.v3, b.removeVehicle());
			Throwable exception = assertThrows(BoatException.class,
					() -> b.removeVehicle()
			);
			assertEquals(BoatException.Reason.BOAT_IS_EMPTY, ((BoatException)exception).getReason());
			assertEquals("Can't unload, the boat is empty", exception.getMessage());
		}
		
		@Tag("BoatExeptions")
		@Test
		void testLoadDuringUnload() throws BoatException {
			assertEquals(TestBoatUtils.v1, b.removeVehicle());
			Throwable exception = assertThrows(BoatException.class,
					() -> b.addVehicle(TestBoatUtils.c1)
			);
			assertEquals(BoatException.Reason.CANT_LOAD_DURING_UNLOAD, ((BoatException)exception).getReason());
			assertEquals("The boat is unloading it's phisically impossible to do that", exception.getMessage());
		}
		
		@Test
		void testLoadAfterCompleteUnload() throws BoatException {
			assertEquals(TestBoatUtils.v1, b.removeVehicle());
			assertEquals(TestBoatUtils.v2, b.removeVehicle());
			assertEquals(TestBoatUtils.c2, b.removeVehicle());
			assertEquals(TestBoatUtils.c1, b.removeVehicle());
			assertEquals(TestBoatUtils.v3, b.removeVehicle());
			try {
				b.addVehicle(TestBoatUtils.c3);
			}
			catch (Exception e) {
				fail("Error adding vehicle after unloading all");
			}
		}
		
		@Test
		void testUnloadingPassenger() throws BoatException {
			assertEquals(11, b.getPassengers());
			assertEquals(TestBoatUtils.v1, b.removeVehicle());
			assertEquals(10, b.getPassengers());
			assertEquals(TestBoatUtils.v2, b.removeVehicle());
			assertEquals(5, b.getPassengers());
			assertEquals(TestBoatUtils.c2, b.removeVehicle());
			assertEquals(4, b.getPassengers());
			assertEquals(TestBoatUtils.c1, b.removeVehicle());
			assertEquals(3, b.getPassengers());
			assertEquals(TestBoatUtils.v3, b.removeVehicle());
			assertEquals(0, b.getPassengers());
		}
	}
	
	@Tag("Ticket")
	@Nested
	class testTicket {
		
		@BeforeEach
		void setUp() throws Exception {
			b = new Boat(25.0, 75.0, 2);
		}
	
		@AfterEach
		void tearDown() throws Exception {
			b = null;
		}
		
		@Tag("ListingTicket")
		@Test
		void testEmptyTicketList() {
			assertEquals(null, b.getTicketFromVehicle(TestBoatUtils.v1));
		}
		
		@Tag("ListingTicket")
		@Test
		void testSearchExistingTicket() throws BoatException {
			b.addVehicle(TestBoatUtils.c1);
			b.addVehicle(TestBoatUtils.v1);
			b.addVehicle(TestBoatUtils.v2);
			Ticket t = b.getTicketFromVehicle(TestBoatUtils.c1);
			assertNotNull(t);
			assertEquals(t.getRegistration(), TestBoatUtils.c1.getRegistration());
			t = b.getTicketFromVehicle(TestBoatUtils.v1);
			assertNotNull(t);
			assertEquals(t.getRegistration(), TestBoatUtils.v1.getRegistration());
			t = b.getTicketFromVehicle(TestBoatUtils.v2);
			assertEquals(t.getRegistration(), TestBoatUtils.v2.getRegistration());
			assertNotNull(t);
		}
		
		@Tag("ListingTicket")
		@Test
		void testPositionOfTicket() throws BoatException {
			b.addVehicle(TestBoatUtils.c1);
			b.addVehicle(TestBoatUtils.v1);
			b.addVehicle(TestBoatUtils.v2);
			Ticket t = b.getTicketFromVehicle(TestBoatUtils.c1);
			assertNotNull(t);
			assertEquals(0, t.getPlaceInWedge().getRow());
			assertEquals(1, t.getPlaceInWedge().getPos());
			t = b.getTicketFromVehicle(TestBoatUtils.v1);
			assertNotNull(t);
			assertEquals(1, t.getPlaceInWedge().getRow());
			assertEquals(1, t.getPlaceInWedge().getPos());
			t = b.getTicketFromVehicle(TestBoatUtils.v2);
			assertNotNull(t);
			assertEquals(1, t.getPlaceInWedge().getRow());
			assertEquals(2, t.getPlaceInWedge().getPos());
		}
		
		@Tag("AttributeTicket")
		@Test
		void testDriverInTicket() throws BoatException {
			b.addVehicle(TestBoatUtils.c1);
			Ticket t = b.getTicketFromVehicle(TestBoatUtils.c1);
			assertNotNull(t);
			assertEquals(TestBoatUtils.c1.getDriver().getFirstName(), t.getFirstName());
			assertEquals(TestBoatUtils.c1.getDriver().getName(), t.getName());
		}
		
		@Tag("AttributeTicket")
		@Test
		void testPriceInTicket() throws BoatException {
			b.addVehicle(TestBoatUtils.c1);
			Ticket t = b.getTicketFromVehicle(TestBoatUtils.c1);
			assertNotNull(t);
			assertEquals(new Accounting().getPrice(TestBoatUtils.c1), t.getPrice());
		}
	}
	
	@Nested
	@Tag("Accounting")
	class testAccounting {
		
		Accounting a;
		
		@BeforeEach
		void setUp() throws Exception {
			a = new Accounting();
		}
	
		@AfterEach
		void tearDown() throws Exception {
			a = null;
		}
		
		@Test
		void TestCarPrice() {
			assertEquals(35, a.getPrice(TestBoatUtils.v1));
			assertEquals(47, a.getPrice(TestBoatUtils.v2));
			assertEquals(41, a.getPrice(TestBoatUtils.v3));
		}
		
		@Test
		void TestTruckPrice() {
			assertEquals(1545, a.getPrice(TestBoatUtils.c1));
			assertEquals(2295, a.getPrice(TestBoatUtils.c2));
			assertEquals(1845, a.getPrice(TestBoatUtils.c3));
		}
		
	}
}
