package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order
	 */
	@Override
	public int compare(Student one, Student two) {
        // Compare GPAs
        int gpaComparison = Double.compare(two.getGpa(), one.getGpa());
        if (gpaComparison != 0) {
        	// Return the result if not equal
            return gpaComparison;
        } else {
            // If GPAs are equal, use the natural ordering
            return one.compareTo(two);  
        }
    }
}
