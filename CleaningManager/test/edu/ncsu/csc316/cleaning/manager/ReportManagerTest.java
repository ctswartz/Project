package edu.ncsu.csc316.cleaning.manager;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.cleaning.manager.ReportManager;

public class ReportManagerTest {

    private ReportManager manager;

    @Before
    public void setUp() throws FileNotFoundException {
        String validRoomFile = "input/room-info.txt";
        String validLogFile = "input/cleaning-events.txt";
        
        // Check if files exist
        File roomFile = new File(validRoomFile);
        File logFile = new File(validLogFile);
        if (!roomFile.exists() || !logFile.exists()) {
            throw new FileNotFoundException("File not found");
        }
        manager = new ReportManager(validRoomFile, validLogFile, DataStructure.UNORDEREDLINKEDMAP);
    }

    @Test
    public void testGetVacuumBagReport() {
        String timestamp = "06/01/2021 13:39:01";
        String report = manager.getVacuumBagReport(timestamp);
        assertEquals("Total vacuum bags used: 12", report);
    }

    @Test
    public void testGetFrequencyReport() {
        int number = 2;
        String report = manager.getFrequencyReport(number);
        assertEquals("Rooms cleaned at least 2 times:\n" +
                     "- Dining Room\n" +
                     "- Guest Bedroom\n" +
                     "- Guest Bathroom\n" +
                     "- Living Room\n", report);
    }

    @Test
    public void testGetRoomReport() {
        String report = manager.getRoomReport();
        assertEquals("Room Report[ \n" +
                     "- Office: 78%\n" +
                     "- Dining Room: 264%\n" +
                     "- Guest Bedroom: 151%\n" +
                     "- Guest Bathroom: 182%\n" +
                     "- Foyer: 93%\n" +
                     "- Living Room: 502%\n" +
                     "]", report);
        
    }

}