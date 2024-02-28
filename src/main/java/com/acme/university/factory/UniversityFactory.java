package com.acme.university.factory;

import com.acme.university.model.Department;
import com.acme.university.model.Unit;
import com.acme.university.model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniversityFactory implements AbstractFactory<University> {
    private static final Logger log = LoggerFactory.getLogger(UniversityFactory.class);

    @Override
    public List<University> produce(List<String> lines) {
        List<University> universities = new ArrayList<>();
        for (String line : lines) {
            try {
                String[] universityAsArray = line.split(",", 2);
                universities.add(new University(Long.parseLong(universityAsArray[0]), universityAsArray[1], new ArrayList<>()));
            } catch (RuntimeException e) { // should not be runtime, should be custom exception (i.e. called BusinessException)
                log.error("Validation/formatting for university failed: {} Line failed: {}", e.getMessage(), line);
            } catch (Exception e) { // for any other unpredictable exception during parsing
                log.error("University cannot be loaded: {}", line, e);
            }
        }
        return universities;
    }

    public void produceDepartments(List<University> universities, List<String> lines) {
        Department department;
        for (String line : lines) {
            try {
                String[] departmentAsArray = line.split(",", 3);
                department = new Department(Long.parseLong(departmentAsArray[0]), departmentAsArray[1], new ArrayList<>());
                long universityID = Long.parseLong(departmentAsArray[2]);
                Optional<University> first = universities.stream().filter(university -> university.getId() == (universityID)).findFirst();
                University university = first.orElseThrow(() -> new RuntimeException("University not found."));
                university.getDepartments().add(department);
            } catch (RuntimeException e) { // should not be runtime, should be custom exception (i.e. called BusinessException)
                log.error("Validation/formatting for department failed: {} Line failed: {}", e.getMessage(), line);
            } catch (Exception e) { // for any other unpredictable exception during parsing
                log.error("Department cannot be loaded: {}", line, e);
            }
        }
    }

    public void procudeUnits(List<University> universities, List<String> lines) {
        Unit unit;
        for (String line : lines) {
            try {
                String[] unitAsArray = line.split(",", 4);
                unit = new Unit(Long.parseLong(unitAsArray[0]), unitAsArray[1], unitAsArray[2]);
                long departmentID = Long.parseLong(unitAsArray[3]);
                Optional<Department> first = universities.stream()
                        .flatMap(university -> university.getDepartments().stream())
                        .filter(department -> department.getId() == (departmentID))
                        .findFirst();
                Department department = first.orElseThrow(() -> new RuntimeException("Department not found."));
                department.getUnits().add(unit);
            } catch (RuntimeException e) { // should not be runtime, should be custom exception (i.e. called BusinessException)
                log.error("Validation/formatting for unit failed: {} Line failed: {}", e.getMessage(), line);
            } catch (Exception e) { // for any other unpredictable exception during parsing
                log.error("Unit cannot be loaded: {}", line, e);
            }
        }
    }
}
