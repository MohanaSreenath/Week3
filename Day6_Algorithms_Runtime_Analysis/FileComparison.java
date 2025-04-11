import java.io.*;

public class FileComparison {
    public static void main(String[] args) throws IOException {
        File largeFile = new File("largefile.txt"); // Replace with the path to your 500MB file

        // Measure time for FileReader
        long startTime = System.currentTimeMillis();
        try (FileReader fileReader = new FileReader(largeFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.readLine() != null) {
                // Reading line by line
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("FileReader time: " + (endTime - startTime) + "ms");

        // Measure time for InputStreamReader
        startTime = System.currentTimeMillis();
        try (FileInputStream fileInputStream = new FileInputStream(largeFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (bufferedReader.readLine() != null) {
                // Reading line by line
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("InputStreamReader time: " + (endTime - startTime) + "ms");
    }
}