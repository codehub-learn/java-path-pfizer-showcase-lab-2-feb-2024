package com.acme.university.factory;

import com.acme.university.model.Student;

import java.util.List;

public class StudentFactory implements AbstractFactory<Student> {
    @Override
    public List<Student> produce(List<String> lines) {
        return null;
    }
}
