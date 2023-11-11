package edu.ncsu.csc316.cleaning.manager;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.data.RoomRecord;
import edu.ncsu.csc316.cleaning.dsa.Algorithm;
import edu.ncsu.csc316.cleaning.dsa.DSAFactory;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.cleaning.io.InputReader;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;


public class ReportManager {

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    
    private CleaningManager manager;
    private List<CleaningLogEntry> logs;
    private DataStructure mapType;

    public ReportManager(String pathToRoomFile, String pathToLogFile, DataStructure mapType) throws FileNotFoundException {
        manager = new CleaningManager(pathToRoomFile, pathToLogFile, mapType);
        DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.BUBBLE_SORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.COUNTING_SORT);
        DSAFactory.setMapType(mapType);
        
        // TODO: Complete this constructor
        Map<String, List<CleaningLogEntry>> eventsByRoom = manager.getEventsByRoom();
        if (eventsByRoom == null || eventsByRoom.isEmpty()) {
            throw new NullPointerException("getEventsByRoom() returned null or an empty map");
        }
        
        // Initialize logs as an empty list
        logs = DSAFactory.getIndexedList();
        
        // Iterate over the eventsByRoom map and add all logs to the logs list
        for (List<CleaningLogEntry> roomLogs : eventsByRoom.values()) {
            for (CleaningLogEntry log : roomLogs) {
                logs.addLast(log);
            }
        }
      
//        logs = eventsByRoom.get(mapType.toString());
        
//        if (logs == null || logs.isEmpty()) {
//            throw new NullPointerException("No logs found for the given map type.");
//        }
        
        this.mapType = mapType;
        
    }
    
    public ReportManager(String pathToRoomFile, String pathToLogFile) throws FileNotFoundException {
        this(pathToRoomFile, pathToLogFile, DataStructure.ARRAYBASEDLIST);
    }

    public String getVacuumBagReport(String timestamp) {
    	
        // TODO: Complete this method
        if (logs == null || logs.isEmpty()) {
            return "No logs available.";
        }
        
        LocalDateTime time = LocalDateTime.parse(timestamp, DATE_TIME_FORMAT);
        double totalBags = 0;
        for (int i = 0; i < logs.size(); i++) {
            CleaningLogEntry log = logs.get(i);
            if (log != null && log.getTimestamp().isBefore(time)) {
                totalBags += (double) log.getPercentCompleted() / 100; // One bag for every 100% cleaned
            }
        }
        return "Total vacuum bags used: " + Math.round(totalBags);
    }

    public String getFrequencyReport(int number) {
    	
        // TODO: Complete this method
        if (number <= 0) {
            return "Number of rooms must be greater than 0.";
        }
        
        if (logs == null || logs.isEmpty()) {
            return "No logs available.";
        }
        
    	Comparator<String> comparator = null;
    	if (mapType == DataStructure.UNORDEREDLINKEDMAP || mapType == DataStructure.SEARCHTABLE || mapType == DataStructure.SKIPLIST) {
    	    comparator = Comparator.naturalOrder();
    	} else {
    	    comparator = Comparator.reverseOrder();
    	}
    	Map<String, Integer> frequencyMap = DSAFactory.getMap(comparator);
        for (int i = 0; i < logs.size(); i++) {
            CleaningLogEntry log = logs.get(i);
            String room = log.getRoomID();
            if (frequencyMap.get(room) == null) {
                frequencyMap.put(room, 1);
            } else {
                frequencyMap.put(room, frequencyMap.get(room) + 1);
            }
        }
        List<String> rooms = DSAFactory.getIndexedList();
        
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String room = entry.getKey();
            if (frequencyMap.get(room) >= number) {
            	rooms.add(rooms.size(), room);
                }
        }
        
        if (rooms.isEmpty()) {
            return "No rooms were cleaned at least " + number + " times.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Rooms cleaned at least " + number + " times:\n");
            for (int i = 0; i < rooms.size(); i++) {
                sb.append("- " + rooms.get(i) + "\n");
            }
            return sb.toString();
        }
    }

    public String getRoomReport() {
    	
        // TODO: Complete this method
        if (logs == null || logs.isEmpty()) {
            return "No rooms have been cleaned.";
        }
        
        Comparator<String> comparator = null;
        if (mapType == DataStructure.UNORDEREDLINKEDMAP || mapType == DataStructure.SEARCHTABLE || mapType == DataStructure.SKIPLIST) {
            comparator = Comparator.naturalOrder();
        } else {
            comparator = Comparator.reverseOrder();
        }
        Map<String, Integer> coverageMap = DSAFactory.getMap(comparator);
        for (int i = 0; i < logs.size(); i++) {
            CleaningLogEntry log = logs.get(i);
            String room = log.getRoomID();
            if (coverageMap.get(room) == null) {
                coverageMap.put(room, log.getPercentCompleted());
            } else {
                coverageMap.put(room, coverageMap.get(room) + log.getPercentCompleted());
            }
        }
        if (coverageMap.isEmpty()) {
            return "No rooms were cleaned.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Room Report[ \n");
            for (Map.Entry<String, Integer> entry : coverageMap.entrySet()) {
                String room = entry.getKey();
                sb.append("- " + room + ": " + coverageMap.get(room) + "%\n");
            }
            sb.append("]");
            return sb.toString();
        }
    }
    
    
}