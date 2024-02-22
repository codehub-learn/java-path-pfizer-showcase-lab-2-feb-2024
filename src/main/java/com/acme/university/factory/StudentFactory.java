package com.acme.university.factory;

import com.acme.university.model.Student;
import com.acme.university.util.DateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentFactory implements AbstractFactory<Student> {

    private static final Logger log = LoggerFactory.getLogger(StudentFactory.class);

    @Override
    public List<Student> produce(List<String> lines) {
        List<Student> students = new ArrayList<>();
        for (String line : lines) {
            try {
                String[] studentAsArray = line.split(",");
                //@formatter:off
                students.add(new Student(
                        Long.parseLong(studentAsArray[0]),
                        studentAsArray[1],
                        studentAsArray[2],
                        DateTool.parseDate(studentAsArray[3], null),
                        studentAsArray[4],
                        new ArrayList<>()));
                //@formatter:on
            }  catch (Exception e) {
                log.error("Student cannot be loaded: {}", line, e);
            }
        }
        return students;
    }
}
