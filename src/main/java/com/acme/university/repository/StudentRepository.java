package com.acme.university.repository;

import com.acme.university.Main;
import com.acme.university.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class StudentRepository implements CRUDRepository<Student, Long>{

    private static final Logger log = LoggerFactory.getLogger(StudentRepository.class);
    @Override
    public void create(Student student) throws Exception {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO STUDENT(name, address, dateOfBirth) VALUES (?, ?, ?)", new String[]{"id"})) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setDate(3, new Date(student.getDateOfBirth().getTime()));
            preparedStatement.executeUpdate();
            log.trace("Created student {}.", student);

            // setting id
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next(); // we only suppose that there is a single generated key
            student.setId(generatedKeys.getLong(1));

        } catch (SQLException e) {
            throw new Exception("Could not create student.", e);
        }
    }

    @Override
    public List<Student> findAll() throws Exception {
        return null;
    }

    @Override
    public Optional<Student> findByID(Long aLong) throws Exception {
        return Optional.empty();
    }

    @Override
    public boolean update(Student student) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Student student) throws Exception {
        return false;
    }
}
