package ch.heigvd.api;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FileManager {

    private ArrayList<Victim> content;

    void getVictimsFromFile(File file, Charset encoding) throws RuntimeException {
        StringBuilder content = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), encoding
                )
        )) {
            String line;
            while((line = reader.readLine()) != null) {
                content.append(line);
                content.append(new Victim(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
