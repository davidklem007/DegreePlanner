package com.example.DegreePlanner.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SemesterTest {

    // Test 1: A course can be successfully enrolled in an open semester
    @Test
    void enrollCourse_addsCourseTosemester() {
        Semester semester = new Semester(1);
        Course course = new Course("Algorithms", 3);

        semester.enroll(course);

        assertEquals(1, semester.getCourseCount());
        assertEquals("Algorithms", semester.getCourses().get(0).getName());
    }


    // Test 2: Enrolling beyond the max course cap throws an exception
    @Test
    void enrollCourse_throwsWhenMaxCapReached() {
        Semester semester = new Semester(1);

        for (int i = 0; i < Semester.MAX_COURSES; i++) {
            semester.enroll(new Course("Course " + i, 3));
        }

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
            semester.enroll(new Course("One Too Many", 3))
        );
        assertTrue(ex.getMessage().contains("maximum"));
    }


    // Test 3: A semester cannot be completed if any course is missing a grade
    @Test
    void completeSemester_throwsWhenCoursesAreUngraded() {
        Semester semester = new Semester(1);
        semester.enroll(new Course("Algorithms", 3));

        IllegalStateException ex = assertThrows(IllegalStateException.class,
            semester::complete
        );
        assertTrue(ex.getMessage().contains("graded"));
    }


    // Test 4: GPA is calculated correctly from course grades and credit weights
    @Test
    void calculateGpa_returnsCorrectWeightedAverage() {
        Semester semester = new Semester(1);

        Course c1 = new Course("Algorithms", 3);       // A  = 4.0 x 3 = 12.0
        c1.assignGrade(new Grade("A"));

        Course c2 = new Course("English Comp", 3);     // B  = 3.0 x 3 = 9.0
        c2.assignGrade(new Grade("B"));

        semester.enroll(c1);
        semester.enroll(c2);

        // Expected GPA: (12.0 + 9.0) / 6 = 3.5
        assertEquals(3.5, semester.calculateGpa(), 0.01);
    }


    // Test 5: Enrolling in a completed semester throws an exception
    @Test
    void enrollCourse_throwsWhenSemesterIsCompleted() {
        Semester semester = new Semester(1);
        Course course = new Course("Algorithms", 3);
        course.assignGrade(new Grade("A"));
        semester.enroll(course);
        semester.complete();

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
            semester.enroll(new Course("Late Addition", 3))
        );
        assertTrue(ex.getMessage().contains("completed"));
    }
}