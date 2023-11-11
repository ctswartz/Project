package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 */
public class Student implements Comparable<Student> {
    
    /** The first. */
    private String first;
    
    /** The last. */
    private String last;
    
    /** The id. */
    private int id;
    
    /** The credit hours. */
    private int creditHours;
    
    /** The gpa. */
    private double gpa;
    
    /** The unity ID. */
    private String unityID;

    /**
     * Instantiates a new student.
     *
     * @param first the first
     * @param last the last
     * @param id the id
     * @param creditHours the credit hours
     * @param gpa the gpa
     * @param unityID the unity ID
     */
    public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
        this.first = first;
        this.last = last;
        this.id = id;
        this.creditHours = creditHours;
        this.gpa = gpa;
        this.unityID = unityID;
    }

    /**
     * Gets the first name.
     *
     * @return the first name.
     */
    public String getFirst() {
        return first;
    }

    /**
     * Sets the first name.
     *
     * @param first the first name
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLast() {
        return last;
    }

    /**
     * Sets the last name
     *
     * @param last the last name
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the credit hours.
     *
     * @return the credit hours
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * Sets the credit hours.
     *
     * @param creditHours the new credit hours
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /**
     * Gets the gpa.
     *
     * @return the gpa
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Sets the gpa.
     *
     * @param gpa the new gpa
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * Gets the unity ID.
     *
     * @return the unity ID
     */
    public String getUnityID() {
        return unityID;
    }

    /**
     * Sets the unity ID.
     *
     * @param unityID the new unity ID
     */
    public void setUnityID(String unityID) {
        this.unityID = unityID;
    }

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
	    int result = first.hashCode();
	    result = 31 * result + last.hashCode();
	    result = 31 * result + id;
	    return result;
	}

	/**
	 * Compares for equality.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Student student = (Student) obj;

        return id == student.id &&
               first.equals(student.first) &&
               last.equals(student.last);
    }

    /**
     * Compares a student object to another.
     * Natural ordering of students is based on last name, first name, then student ID.
     *
     * @param other the other to compare
     * @return the int comparison result
     */
    @Override
	public int compareTo(Student other) {
	    if (this.last.compareTo(other.last) != 0) {
	        return this.last.compareTo(other.last);
	    } else if (this.first.compareTo(other.first) != 0) {
	        return this.first.compareTo(other.first);
	    } else {
	        return Integer.compare(this.id, other.id);
	    }
	}

	/**
	 * To string method for student object.
	 *
	 * @return the string of the student info.
	 */
	@Override
    public String toString() {
        return "[Student]\n" +
               "First Name: " + first + "\n" +
               "Last Name: " + last + "\n" +
               "ID: " + id + "\n" +
               "Credit Hours: " + creditHours + "\n" +
               "GPA: " + gpa + "\n" +
               "Unity ID: " + unityID + "\n";
    }
}
	