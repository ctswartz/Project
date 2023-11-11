package edu.ncsu.csc316.cleaning.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Scanner;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.data.RoomRecord;
import edu.ncsu.csc316.cleaning.dsa.Algorithm;
import edu.ncsu.csc316.cleaning.dsa.DSAFactory;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.cleaning.io.InputReader;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.UnorderedLinkedMap;


public class CleaningManager {
	
	// added
    private Map<String, List<CleaningLogEntry>> eventsByRoom;
    private PositionalList<CleaningLogEntry> logs;

    public CleaningManager(String pathToRoomFile, String pathToLogFile, DataStructure mapType) throws FileNotFoundException {
        
    	//added
        if (mapType == null) {
            mapType = DataStructure.UNORDEREDLINKEDMAP; // Set a default value
        }
    	
    	DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.BUBBLE_SORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.COUNTING_SORT);
        DSAFactory.setMapType(mapType);
        
        // TODO: Complete this constructor
        //InputReader rr = new InputReader();
        
        List<RoomRecord> rooms = InputReader.readRoomFile(pathToRoomFile);
        logs = readLogFile(pathToLogFile);
        
        Comparator<String> comparator = null;
        if (mapType == DataStructure.UNORDEREDLINKEDMAP || mapType == DataStructure.SEARCHTABLE || mapType == DataStructure.SKIPLIST) {
            comparator = Comparator.naturalOrder();
        } else {
            comparator = Comparator.reverseOrder();
        }

        eventsByRoom = DSAFactory.getMap(comparator);

        for (int i = 0; i < rooms.size(); i++) {
            eventsByRoom.put(rooms.get(i).getRoomID(), DSAFactory.getIndexedList());
        }

        for (Position<CleaningLogEntry> pos : logs.positions()) {
            CleaningLogEntry log = pos.getElement();
            eventsByRoom.get(log.getRoomID()).addLast(log);
        }
    }
    
    public CleaningManager(String pathToRoomFile, String pathToLogFile) throws FileNotFoundException {
        this(pathToRoomFile, pathToLogFile, DataStructure.ARRAYBASEDLIST);
    }

    public Map<String, List<CleaningLogEntry>> getEventsByRoom() {
        // TODO: Complete this method
    	//UnorderedLinkedMap<String, List<CleaningLogEntry>> eventsByRoom = new UnorderedLinkedMap<>();
    	return eventsByRoom;
    }

    public int getCoverageSince(LocalDateTime time) {
        // TODO: Complete this method
        int totalCoverage = 0;
        for (CleaningLogEntry log : logs) {
            if (log.getTimestamp().isAfter(time)) {
                totalCoverage += log.getPercentCompleted();
            }
        }
        return totalCoverage;
    }
    
    // added private helper method
    private PositionalList<CleaningLogEntry> readLogFile(String pathToLogFile) throws FileNotFoundException {
    	
    	// returns a new instance of a positional list from W2
        PositionalList<CleaningLogEntry> logs = DSAFactory.getPositionalList();

        Scanner fileScan = new Scanner(new File(pathToLogFile));
        fileScan.nextLine(); // Skip the first line (header)

        while (fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            String[] tokens = line.split(",");
            LocalDateTime timestamp = LocalDateTime.parse(tokens[0], ReportManager.DATE_TIME_FORMAT);
            String roomID = tokens[1];
            int percentCompleted = Integer.parseInt(tokens[2]);
            CleaningLogEntry log = new CleaningLogEntry(timestamp, roomID, percentCompleted);
            logs.addLast(log);
        }

        fileScan.close();
        return logs;
    }

}