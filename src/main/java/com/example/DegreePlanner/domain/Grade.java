package com.example.DegreePlanner.domain;


//Object representing a letter grade
//Encapsulates the rules for grades and gpa
 
public class Grade {

    private final String letter;

    private static final java.util.Map<String, Double> POINTS = java.util.Map.ofEntries(
        java.util.Map.entry("A",  4.0),
        java.util.Map.entry("A-", 3.7),
        java.util.Map.entry("B+", 3.3),
        java.util.Map.entry("B",  3.0),
        java.util.Map.entry("B-", 2.7),
        java.util.Map.entry("C+", 2.3),
        java.util.Map.entry("C",  2.0),
        java.util.Map.entry("C-", 1.7),
        java.util.Map.entry("D",  1.0),
        java.util.Map.entry("F",  0.0)
    );

    public Grade(String letter) {
        if (!POINTS.containsKey(letter)) {
            throw new IllegalArgumentException("Invalid grade: " + letter);
        }
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public double getPoints() {
        return POINTS.get(letter);
    }

    @Override
    public String toString() {
        return letter;
    }
}