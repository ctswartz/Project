package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Student class.
 * 
 * @author Courtney T Swartz (ctswartz)
 */
public class StudentTest {

	/** The s one. */
	private Student sOne;
	
	/** The s two. */
	private Student sTwo;
	
	/** The s three. */
	private Student sThree;
	
	/** The s four. */
	private Student sFour;
	
	/** The s five. */
	private Student sFive;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Test set first.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Test set last.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Test set id.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Test set gpa.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Test set unity ID.
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Test compare to.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}
	
	/**
	 * Test equals.
	 */
	@Test
	public void testEquals() {
	    Student sSame = new Student("OneFirst", "OneLast", 1, 1, 1.0, "UnityID");
	    Student sDifferentId = new Student("OneFirst", "OneLast", 10, 1, 1.0, "UnityID");
	    Student sDifferentFirst = new Student("DifferentFirst", "OneLast", 1, 1, 1.0, "UnityID");
	    Student sDifferentLast = new Student("OneFirst", "DifferentLast", 1, 1, 1.0, "UnityID");

	    assertTrue(sOne.equals(sSame));
	    assertFalse(sOne.equals(sTwo));
	    assertFalse(sOne.equals(sDifferentId));
	    assertFalse(sOne.equals(sDifferentFirst));
	    assertFalse(sOne.equals(sDifferentLast));
	    assertFalse(sOne == null);
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
	    String expected = "[Student]\n" +
	                      "First Name: OneFirst\n" +
	                      "Last Name: OneLast\n" +
	                      "ID: 1\n" +
	                      "Credit Hours: 1\n" +
	                      "GPA: 1.0\n" +
	                      "Unity ID: oneUnityID\n";
	    assertEquals(expected, sOne.toString());
	}

	/**
	 * Test hash code.
	 */
	@Test
	public void testHashCode() {
	    Student sSame = new Student("OneFirst", "OneLast", 1, 1, 1.0, "UnityID");
	    assertEquals(sOne.hashCode(), sSame.hashCode());
	    assertNotEquals(sOne.hashCode(), sTwo.hashCode());
	}

	/**
	 * Test set credit hours.
	 */
	@Test
	public void testSetCreditHours() {
	    sOne.setCreditHours(15);
	    assertEquals(15, sOne.getCreditHours());
	}
	
	/**
	 * Test set using all students.
	 */
	@Test
	public void testSetUsingAllStudents() {
	    sOne.setFirst("newOne");
	    assertEquals("newOne", sOne.getFirst());

	    sThree.setFirst("newThree");
	    assertEquals("newThree", sThree.getFirst());

	    sFour.setFirst("newFour");
	    assertEquals("newFour", sFour.getFirst());

	    sFive.setFirst("newFive");
	    assertEquals("newFive", sFive.getFirst());
	}

}
