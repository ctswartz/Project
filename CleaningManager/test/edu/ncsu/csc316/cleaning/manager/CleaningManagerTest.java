package edu.ncsu.csc316.cleaning.manager;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.data.RoomRecord;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.cleaning.manager.CleaningManager;
import edu.ncsu.csc316.cleaning.manager.ReportManager;

public class CleaningManagerTest {

    private CleaningManager manager;

    @Before
    public void setUp() throws FileNotFoundException {
        String validRoomFile = "input/room-info.txt";
        String validLogFile = "input/cleaning-events.txt";

        manager = new CleaningManager(validRoomFile, validLogFile, DataStructure.UNORDEREDLINKEDMAP);
    }
    
    @Test
    public void testGetEventsByRoom() {
        assertEquals(7, manager.getEventsByRoom().size());
    }

    @Test
    public void testGetCoverageSince() {
        LocalDateTime time1 = LocalDateTime.of(2021, 4, 1, 0, 0);
        assertEquals(1270, manager.getCoverageSince(time1));
    } 
}