package com.acme.university.model;

import java.util.List;

public class Unit extends BaseModel {
    private String name;
    private String tutorName;

    public Unit(long id, String name, String tutorName) {
        super(id);
        this.name = name;
        this.tutorName = tutorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }
}
