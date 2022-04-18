package poal2info.carferry;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;

class TestBoatUtils {
	public static final Driver d1 = new Driver("Martin", "Jeanne", 22);
	public static final Driver d2 = new Driver("Dupont", "Vincent", 55);
	public static final Driver d3 = new Driver("Durand", "Marie", 34);
	public static final Driver d4 = new Driver("Grant", "Philip", 20);
	public static final Driver d5 = new Driver("Scott", "Simon", 55);
	public static final Driver d6 = new Driver("Lambert", "Alain", 44);
	public static final Vehicle v1 = new Car("RM 1054 FF", 1.2, 4.2, d1, 0);
	public static final Vehicle v2 = new Car("PO 377 AA",  1.4, 4.5, d2, 4);
	public static final Vehicle v3 = new Car("WX 456 RT",  1.2, 5.3, d3, 2);
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

	@BeforeEach
	void setUp() throws Exception {
		b = new Boat(25.0, 75.0, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		b = null;
	}

	@Test
	@DisplayName(value = "Test if the boat is empty")
	void testEmptyBoat() {
		assertAll("Storage",
			() -> {
				assertEquals(0, this.b.getVehiculeRow(0).size()+this.b.getVehiculeRow(1).size(),
						() -> "The boat isn't empty!");
			},
			() -> {
				assertEquals(0, this.b.getListingTicket().size(), 0,
						() -> "The boat have a ticket with no passengers");
			}
		);
	}

	@Test
	void testAddVehicle() {
		try {
			b.addVehicle(TestBoatUtils.v1);
		} catch (LoadingException e) {
			e.printStackTrace();
			fail("Adding a vehicle throws an error");
		}
		assertAll("Storage",
			() -> {
				assertEquals(1, this.b.getVehiculeRow(0).size()+this.b.getVehiculeRow(1).size(),
						() -> "The boat don't store the vehicle");
			},
			() -> {
				assertEquals(1, this.b.getListingTicket().size(),
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
		} catch (LoadingException e) {
			e.printStackTrace();
			fail("You must organise vehicle in wedge");
		}
		Throwable exception = assertThrows(LoadingException.class,
				() -> b.addVehicle(TestBoatUtils.v3)
		);
		assertEquals(LoadingException.Reason.NOT_ENOUGTH_SPACE, ((LoadingException)exception).getReason());
		assertEquals("There is no free place for this vehicle", exception.getMessage());
	}
	
	@Test
	void testMaxWeightException() {
		Vehicle HeavyC1 = new Car("RM 1054 FF", 4.0, 3.5, TestBoatUtils.d1, 0);
		Vehicle HeavyT1 = new Truck("PO 377 AA",  5.0, 17.0, TestBoatUtils.d2, 65);
		
		try {
			b.addVehicle(HeavyC1);
			b.addVehicle(HeavyT1);
		} catch (LoadingException e) {
			e.printStackTrace();
			fail("You must organise vehicle in wedge");
		}
		Throwable exception = assertThrows(LoadingException.class,
				() -> b.addVehicle(TestBoatUtils.v3)
		);
		assertEquals(LoadingException.Reason.TOO_HEAVY, ((LoadingException)exception).getReason());
		assertEquals("The boat can't handle more weight", exception.getMessage());
	}
	
	@Test
	void testLoadingPlacement() {
		try {
			b.addVehicle(TestBoatUtils.v3);
			b.addVehicle(TestBoatUtils.c1);
			b.addVehicle(TestBoatUtils.c2);
		} catch (LoadingException e) {
			e.printStackTrace();
			fail("You must organise vehicle in wedge");
		}
		
		Queue<Vehicle> g = this.b.getVehiculeRow(0);
		Queue<Vehicle> d = this.b.getVehiculeRow(1);
		
		assertEquals(3, g.size()+d.size(),
				() -> "There is a problem when adding vehicle to boat");
		assertEquals(TestBoatUtils.v3, g.poll());
		assertEquals(TestBoatUtils.c1, d.poll());
		assertEquals(TestBoatUtils.c2, g.poll());
		}
	
	@Test
	void testLoadingPlacementLikeAsking() {
		try {
			b.addVehicle(TestBoatUtils.c1);
			b.addVehicle(TestBoatUtils.v1);
			b.addVehicle(TestBoatUtils.v2);
			b.addVehicle(TestBoatUtils.c2);
			b.addVehicle(TestBoatUtils.v3);
		} catch (LoadingException e) {
			e.printStackTrace();
			fail("You must organise vehicle in wedge");
		}
		
		Throwable exception = assertThrows(LoadingException.class,
				() -> b.addVehicle(TestBoatUtils.c3)
		);
		assertEquals(LoadingException.Reason.NOT_ENOUGTH_SPACE, ((LoadingException)exception).getReason());
		
		Queue<Vehicle> g = this.b.getVehiculeRow(0);
		Queue<Vehicle> d = this.b.getVehiculeRow(1);
		
		assertEquals(5, g.size()+d.size(),
				() -> "There is a problem when adding vehicle to boat");
		assertEquals(TestBoatUtils.c1, g.poll());
		assertEquals(TestBoatUtils.v1, d.poll());
		assertEquals(TestBoatUtils.v2, d.poll());
		assertEquals(TestBoatUtils.c2, d.poll());
		assertEquals(TestBoatUtils.v3, g.poll());
	}

}
