/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */

//Student name, Course and Total of Course,
public class Report {
    private String studentName;
    private String course;
    private int totalOfCourse;
    private String idCheck;

    public Report(String idCheck, String studentName, String course, int totalOfCourse) {
        this.studentName = studentName;
        this.course = course;
        this.totalOfCourse = totalOfCourse;
        this.idCheck = idCheck;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getTotalOfCourse() {
        return totalOfCourse;
    }

    public String getIdCheck() {
        return idCheck;
    }
    

    public void setTotalOfCourse(int totalOfCourse) {
        this.totalOfCourse = totalOfCourse;
    }

    @Override
    public String toString() {
        return  studentName + " | " + course + " | " + totalOfCourse;
    }
    
    
    
    
    
}
