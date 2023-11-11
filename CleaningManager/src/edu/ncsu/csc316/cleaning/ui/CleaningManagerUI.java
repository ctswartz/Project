package edu.ncsu.csc316.cleaning.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.cleaning.manager.ReportManager;

public class CleaningManagerUI {

    public CleaningManagerUI() {
        // Constructor code (if needed)
    }

    @SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter path to room file: ");
        String roomFile = console.nextLine();
        System.out.print("Enter path to log file: ");
        String logFile = console.nextLine();

        // Check if the log file exists
        if (!new File(logFile).exists()) {
            System.out.println("Log file not found. Please provide a valid path.");
            return;
        }

        ReportManager manager = new ReportManager(roomFile, logFile, DataStructure.ARRAYBASEDLIST);

        // Process the header line of the log file
        console.nextLine();

        System.out.println("Enter a command:\n" + "  (1) Get vacuum bag report\n" + "  (2) Get frequency report\n"
                + "  (3) Get room report\n" + "  (4) Quit");
        int command = console.nextInt();
        
        while (command != 4) {
            switch (command) {
                case 1:
                    System.out.print("Enter a timestamp (MM/dd/yyyy HH:mm:ss): ");
                    String timestamp = console.nextLine();
                    System.out.println(manager.getVacuumBagReport(timestamp));
                    break;
                case 2:
                    System.out.print("Enter a frequency: ");
                    int frequency = console.nextInt();
                    console.nextLine();
                    System.out.println(manager.getFrequencyReport(frequency));
                    break;
                case 3:
                    System.out.println(manager.getRoomReport());
                    break;
                default:
                    System.out.println("Invalid command.");
                    continue;
            }
            System.out.println("Enter a command:\n" + "  (1) Get vacuum bag report\n" + "  (2) Get frequency report\n"
                    + "  (3) Get room report\n" + "  (4) Quit");
            command = console.nextInt();
        }
        console.close();
    }
}
