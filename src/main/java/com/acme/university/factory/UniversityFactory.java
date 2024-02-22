package com.acme.university.factory;

import com.acme.university.model.University;

import java.util.List;

public class UniversityFactory implements AbstractFactory<University> {
    @Override
    public List<University> produce(List<String> lines) {
        return null;
    }
}
