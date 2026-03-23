package com.example.DegreePlanner.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//Represents an  academic semester
//Rules around course enrollment limits and semester completion

public class Semester {

    public static final int MAX_COURSES = 5;

    private final int number;
    private boolean completed;
    private final List<Course> courses = new ArrayList<>();

    public Semester(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Semester number must be positive");
        }
        this.number = number;
        this.completed = false;
    }

    /**
     * Enrolls a course in this semester.
     * Enforces the maximum course cap and prevents enrollment in a completed semester.
     */
    public void enroll(Course course) {
        if (completed) {
            throw new IllegalStateException("Cannot enroll in a completed semester");
        }
        if (courses.size() >= MAX_COURSES) {
            throw new IllegalStateException("Semester already has the maximum of " + MAX_COURSES + " courses");
        }
        courses.add(course);
    }

    /**
     * Marks the semester as complete. All enrolled courses must have a grade.
     */
    public void complete() {
        boolean allGraded = courses.stream().allMatch(Course::isGraded);
        if (!allGraded) {
            throw new IllegalStateException("All courses must be graded before completing a semester");
        }
        this.completed = true;
    }

    /**
     * Calculates the GPA for this semester.
     * Only meaningful once the semester is completed.
     */
    public double calculateGpa() {
        if (courses.isEmpty()) return 0.0;

        double totalPoints = 0;
        int totalCredits = 0;

        for (Course c : courses) {
            if (c.isGraded()) {
                totalPoints += c.getGrade().getPoints() * c.getCredits();
                totalCredits += c.getCredits();
            }
        }

        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    public int getNumber() { return number; }
    public boolean isCompleted() { return completed; }
    public List<Course> getCourses() { return Collections.unmodifiableList(courses); }
    public int getCourseCount() { return courses.size(); }
}