package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for InsertionSorter.
 * 
 * @author Courtney T Swartz (ctswartz)
 */
public class InsertionSorterTest {

	/** The data ascending. */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	
	/** The data descending. */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	
	/** The data random. */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/** The integer sorter. */
	private InsertionSorter<Integer> integerSorter;

	/**
	 * Set up.
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<>();
	}

	/**
	 * Test sort integers.
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(Integer.valueOf(1), dataAscending[0]);
		assertEquals(Integer.valueOf(2), dataAscending[1]);
		assertEquals(Integer.valueOf(3), dataAscending[2]);
		assertEquals(Integer.valueOf(4), dataAscending[3]);
		assertEquals(Integer.valueOf(5), dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals(Integer.valueOf(1), dataDescending[0]);
		assertEquals(Integer.valueOf(2), dataDescending[1]);
		assertEquals(Integer.valueOf(3), dataDescending[2]);
		assertEquals(Integer.valueOf(4), dataDescending[3]);
		assertEquals(Integer.valueOf(5), dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals(Integer.valueOf(1), dataRandom[0]);
		assertEquals(Integer.valueOf(2), dataRandom[1]);
		assertEquals(Integer.valueOf(3), dataRandom[2]);
		assertEquals(Integer.valueOf(4), dataRandom[3]);
		assertEquals(Integer.valueOf(5), dataRandom[4]);
	}

	/**
	 * Test sort student.
	 */
	@Test
	public void testSortStudent() {
	    Student s1 = new Student("Ella", "Fitzgerald", 1001, 15, 3.5, "efitz");
	    Student s2 = new Student("Miles", "Davis", 1002, 12, 3.8, "mdavis");
	    Student s3 = new Student("Billie", "Holiday", 1000, 18, 3.9, "bholiday");
	    Student s4 = new Student("Duke", "Ellington", 1003, 16, 2.8, "dukeE");
	    Student s5 = new Student("Louis", "Armstrong", 999, 14, 3.2, "larmstrong");

	    Student[] students = {s1, s2, s3, s4, s5};
	    
	    InsertionSorter<Student> studentSorter = new InsertionSorter<>();
	    studentSorter.sort(students);

	    // Natural ordering
	    assertEquals(s5, students[0]); // Armstrong, Louis
	    assertEquals(s2, students[1]); // Davis, Miles
	    assertEquals(s4, students[2]); // Ellington, Duke
	    assertEquals(s1, students[3]); // Fitzgerald, Ella
	    assertEquals(s3, students[4]); // Holiday, Billie
	}
}
