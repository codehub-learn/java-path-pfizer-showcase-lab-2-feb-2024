package com.acme.university.model;

public class Enrollment extends BaseModel {
    private Unit unit;
    private int grade;

    public Enrollment(long id, Unit unit, int grade) {
        super(id);
        this.unit = unit;
        this.grade = grade;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
