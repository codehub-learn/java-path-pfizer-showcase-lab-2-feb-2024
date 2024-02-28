package com.acme.university.model;

import java.util.List;

public class University extends BaseModel {
    private String name;
    private List<Department> departments;

    public University(long id, String name, List<Department> departments) {
        super(id);
        this.name = name;
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", departments=" + departments +
                '}';
    }
}
