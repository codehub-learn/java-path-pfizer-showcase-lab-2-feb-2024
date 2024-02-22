package com.acme.university.util.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvFileParser implements Parser {
    @Override
    public List<String> load(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return lines;
    }
}
