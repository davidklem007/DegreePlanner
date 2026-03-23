package com.example.DegreePlanner.domain;

//Represents a course enrolled in a semester
//A course has a name and credit value, a grade is present once the semester is done

public class Course {

    private final String name;
    private final int credits;
    private Grade grade;

    public Course(String name, int credits) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be blank");
        }
        if (credits < 1 || credits > 6) {
            throw new IllegalArgumentException("Credits must be between 1 and 6");
        }
        this.name = name;
        this.credits = credits;
    }

    public void assignGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null");
        }
        this.grade = grade;
    }

    public boolean isGraded() {
        return grade != null;
    }

    public String getName() { return name; }
    public int getCredits() { return credits; }
    public Grade getGrade() { return grade; }
}