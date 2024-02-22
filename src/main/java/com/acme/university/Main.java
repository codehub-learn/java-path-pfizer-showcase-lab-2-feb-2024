package com.acme.university;

import com.acme.university.factory.StudentFactory;
import com.acme.university.model.Student;
import com.acme.university.util.parser.CsvFileParser;
import com.acme.university.util.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final String PREPROCESSED_DIRECTORY = "csvFiles/preprocessed/";
    private static final String RAW_DIRECTORY = "csvFiles/raw/";

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = PREPROCESSED_DIRECTORY;

        Parser csvFileParser = new CsvFileParser();
        List<String> studentsListStr = csvFileParser.load(filePath + "students.csv");
        studentsListStr.forEach((studentStr) -> log.info(studentStr));

        StudentFactory studentFactory = new StudentFactory();
        List<Student> studentsList = studentFactory.produce(studentsListStr);
        studentsList.forEach((student) -> log.info("{}", student));
    }
}