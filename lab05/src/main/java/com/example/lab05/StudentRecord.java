package com.example.lab05;

public class StudentRecord {
    private String SID;
    private float midterm;
    private float assignments;
    private float finalExam;
    private float finalMark;
    private char letterGrade;

    public StudentRecord(String SID, float m, float a, float fE) {
        this.SID = SID;
        this.midterm = m;
        this.assignments = a;
        this.finalExam = fE;

        this.finalMark = 0.2f*this.assignments + 0.3f*this.midterm + 0.5f*this.finalExam;

        if (this.finalMark >= 80) {
            this.letterGrade = 'A';
        }

        else if (this.finalMark >= 70) {
            this.letterGrade = 'B';
        }

         else if (this.finalMark >= 60) {
             this.letterGrade = 'C';
        }

         else if (this.finalMark >= 50) {
             this.letterGrade = 'D';
        }

         else {
             this.letterGrade = 'F';
        }
    }

    public String getSID() {
        return SID;
    }

    public float getMidterm() {
        return midterm;
    }

    public float getAssignments() {
        return assignments;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public char getLetterGrade() {
        return letterGrade;
    }
}
