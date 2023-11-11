package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number.
 *
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order.
	 *
	 * @param one the first student
	 * @param two the second student
	 * @return the int result of comparison
	 */
	@Override
	public int compare(Student one, Student two) {
		
        // student one ID is greater than student two ID
		// return a positive number
        if(one.getId() > two.getId()) {
            return 1;
        }
        // student one ID is less than student two ID
		// return a negative number
        else if(one.getId() < two.getId()) {
            return -1;
        }
        // student one ID is equal to student two ID
		// return zero
        else {
            return 0;
        }
    }
}
