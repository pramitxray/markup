package com.markupmanager.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.*;

public class MarkupCalculatorTest {

    @Test
    public void testApplicationWorkflow() throws Exception {
        // Run the application as a process
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", "target/markup-manager-1.0-SNAPSHOT.jar");
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Read application output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder outputLog = new StringBuilder();
        String line;
        boolean successMessageFound = false;

        while ((line = reader.readLine()) != null) {
            outputLog.append(line).append("\n");
            System.out.println(line);  // Print each line to console for debugging
            if (line.contains("All orders processed successfully.")) {
                successMessageFound = true;
            }
        }

        // Wait for process completion
        int exitCode = process.waitFor();

        // Print the full application output if test fails
        if (exitCode != 0 || !successMessageFound) {
            System.out.println("Application Output:\n" + outputLog);
        }

        // Assert conditions
        assertEquals("Application did not exit cleanly!", 0, exitCode);
        assertTrue("Expected success message not found!", successMessageFound);
    }
}
