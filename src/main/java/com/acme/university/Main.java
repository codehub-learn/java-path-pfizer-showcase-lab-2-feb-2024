package com.acme.university;

import com.acme.university.factory.StudentFactory;
import com.acme.university.factory.UniversityFactory;
import com.acme.university.model.Student;
import com.acme.university.model.University;
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
        String chosenDirectory = PREPROCESSED_DIRECTORY;
        Parser csvFileParser = new CsvFileParser();

        log.info("Loading universities...");
        log.info("Creating university objects");
        List<String> universitiesCsv = csvFileParser.load(chosenDirectory + "universities.csv");
        UniversityFactory universityFactory = new UniversityFactory();
        List<University> universities = universityFactory.produce(universitiesCsv);
        for (University university : universities) {
            log.info("{}", university);
        }

        log.info("Creating department objects");
        List<String> departmentsCsv = csvFileParser.load(chosenDirectory + "departments.csv");
        universityFactory.produceDepartments(universities, departmentsCsv);
        for (University university : universities) {
            log.info("{}", university);
        }

        log.info("Creating unit objects");
        List<String> unitsCsv = csvFileParser.load(chosenDirectory + "units.csv");
        universityFactory.procudeUnits(universities, unitsCsv);
        for (University university : universities) {
            log.info("{}", university);
        }

        log.info("Loading students...");

        log.info("Creating student objects");
        List<String> studentsCsv = csvFileParser.load(chosenDirectory + "students.csv");
        StudentFactory studentFactory = new StudentFactory();
        List<Student> students = studentFactory.produce(studentsCsv);
        for (Student student : students) {
            log.info("{}", student);
        }

        log.info("Creating enrollment objects");
        List<String> enrollmentsCsv = csvFileParser.load(chosenDirectory + "enrollments.csv");
        studentFactory.procudeEnrollments(students, universities, enrollmentsCsv);
        for (Student student : students) {
            log.info("{}", student);
        }
    }
}