package com.acme.university.model;

import java.util.Date;
import java.util.List;

public class Student extends BaseModel {
    private String name;
    private String address;
    private Date dateOfBirth;
    private String telephoneNumber;
    private List<Enrollment> enrollments;

    public Student(long id, String name, String address, Date dateOfBirth, String telephoneNumber, List<Enrollment> enrollments) {
        super(id);
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNumber = telephoneNumber;
        this.enrollments = enrollments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
