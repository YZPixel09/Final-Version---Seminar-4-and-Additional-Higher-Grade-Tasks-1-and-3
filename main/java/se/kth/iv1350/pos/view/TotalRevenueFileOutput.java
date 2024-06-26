package se.kth.iv1350.pos.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Outputs the total revenue to a file.
 */
public class TotalRevenueFileOutput extends RevenueObserver {
    private static final String REVENUE_LOG_FILE_NAME = "totalRevenue.txt";
    private PrintWriter revenueLogFile;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);

    /**
     * Creates a new instance and opens the revenue log file.
     * @throws IOException If there is an issue opening the file.
     */
    public TotalRevenueFileOutput() throws IOException {
        revenueLogFile = new PrintWriter(new FileWriter(REVENUE_LOG_FILE_NAME, true));
        System.out.println("TotalRevenueFileOutput: Log file created/opened successfully.");
    }

    @Override
    protected void doShowTotalRevenue(double totalRevenue) {
        String currentTime = LocalDateTime.now().format(formatter);
        revenueLogFile.println("Total revenue at " + currentTime + " is: " + totalRevenue);
        revenueLogFile.flush();
        System.out.println("TotalRevenueFileOutput: New sale registered, total revenue: " + totalRevenue);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.err.println("Could not write total revenue to file. Reason: " + e.getMessage());
    }

    /**
     * Closes the revenue log file.
     */
    public void closeLogFile() {
        if (revenueLogFile != null) {
            revenueLogFile.close();
        }
    }
}
