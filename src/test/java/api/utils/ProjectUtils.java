package api.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectUtils {

    public static void takeScreenshot(String className, String methodName) {
        // Get current date and time
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        // Create file name with date appended
        String fileName = "%s.%s.%s.png".formatted(className, methodName, currentDate);

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("screenshots", fileName))) {
            fileOutputStream.write(((methodName)).getBytes(StandardCharsets.UTF_8));
            System.out.println("Screenshot created: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
