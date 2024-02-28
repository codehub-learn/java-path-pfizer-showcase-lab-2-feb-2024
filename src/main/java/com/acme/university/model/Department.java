package com.acme.university.model;

import java.util.List;

public class Department extends BaseModel {
    private String name;
    private List<Unit> units;

    public Department(long id, String name, List<Unit> units) {
        super(id);
        this.name = name;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", units=" + units +
                '}';
    }
}
