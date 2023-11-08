package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Output {

    public static void writeOutput(String message) {
        String fileName = "output.txt";
        try (FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
