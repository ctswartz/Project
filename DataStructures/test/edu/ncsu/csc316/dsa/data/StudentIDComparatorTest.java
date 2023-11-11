package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the StudentIDComparator class.
 * 
 * @author Courtney T Swartz (ctswartz)
 */
public class StudentIDComparatorTest {

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

	/** The comparator. */
	private StudentIDComparator comparator;

	/**
	 * Set up.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Test compare.
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);

		// less than
		assertTrue(comparator.compare(sTwo, sThree) < 0); // sTwo < sThree
		assertTrue(comparator.compare(sThree, sFour) < 0); // sThree < sFour
		assertTrue(comparator.compare(sFour, sFive) < 0); // sFour < than sFive

		//greater than
		assertTrue(comparator.compare(sFive, sThree) > 0); // sFive > sThree
		assertTrue(comparator.compare(sFour, sTwo) > 0); // sFour > sTwo

		// all should be equal
		assertEquals(0, comparator.compare(sOne, sOne)); 
		assertEquals(0, comparator.compare(sTwo, sTwo));
		assertEquals(0, comparator.compare(sThree, sThree));
		assertEquals(0, comparator.compare(sFour, sFour));
		assertEquals(0, comparator.compare(sFive, sFive));
	}


}
