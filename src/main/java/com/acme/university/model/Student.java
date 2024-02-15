package com.acme.university.model;

import java.util.Date;
import java.util.List;

public class Student extends BaseModel {
    private String name;
    private String address;
    private Date dateOfBirth;
    private String telephoneNumber;
    private List<Enrollment> enrollments;
}
