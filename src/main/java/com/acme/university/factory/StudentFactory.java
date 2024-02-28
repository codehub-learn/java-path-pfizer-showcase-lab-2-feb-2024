package com.acme.university.factory;

import com.acme.university.model.Enrollment;
import com.acme.university.model.Student;
import com.acme.university.model.Unit;
import com.acme.university.model.University;
import com.acme.university.util.DateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void procudeEnrollments(List<Student> students, List<University> universities, List<String> lines) {
        Enrollment enrollment;
        for (String line : lines) {
            try {
                String[] enrollmentAsArray = line.split(",", 4);
                long enrollmentID = Long.parseLong(enrollmentAsArray[0]);
                int grade = Integer.parseInt(enrollmentAsArray[1]);
                long unitID = Long.parseLong(enrollmentAsArray[2]);
                long studentID = Long.parseLong(enrollmentAsArray[3]);
                Optional<Student> firstStudent = students.stream().filter(student -> student.getId() == (studentID)).findFirst();
                Student student = firstStudent.orElseThrow(() -> new RuntimeException("Student not found."));
                Optional<Unit> firstUnit = universities.stream()
                        .flatMap(university -> university.getDepartments().stream())
                        .flatMap(department -> department.getUnits().stream())
                        .filter(unit -> unit.getId() == (unitID))
                        .findFirst();
                Unit unit = firstUnit.orElseThrow(() -> new RuntimeException("Unit not found."));
                enrollment = new Enrollment(enrollmentID, unit, grade);
                student.getEnrollments().add(enrollment);
            } catch (
                    RuntimeException e) { // should not be runtime, should be custom exception (i.e. called BusinessException)
                log.error("Validation/formatting for enrollment failed: {} Line failed: {}", e.getMessage(), line);
            } catch (Exception e) { // for any other unpredictable exception during parsing
                log.error("Enrollment cannot be loaded: {}", line, e);
            }
        }
    }
}
