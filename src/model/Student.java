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
public class Student {
    private String id;
    private String studentName;
    private int semester;
    private String courseName;
    private int numberOflist;

    public Student(String id, String studentName, int semester, String courseName, int numberOflist) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
        this.numberOflist = numberOflist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public int getNumberOflist() {
        return numberOflist;
    }

    private void setNumberOflist(int numberOflist) {
        this.numberOflist = numberOflist;
    }
//Student name, semester and Course Name. 
    @Override
    public String toString() {
        return "Student[" + "studentName: " + studentName + ", semester: " + semester + ", courseName: " + courseName + ']';
    }
    //Student name, semester and Course Name.
    public String showProFile() {
        return "ID: " +  id + " | " + studentName + " | " + semester + " | " + courseName;
        
        
    }
    public String showProFileUpdate() {
        return  "Code: " + numberOflist + " | ID: " +  id + " | Name: " + studentName + " | Semester:" + semester + " | Course:" + courseName;
        
    }
    
    public void showProFile12() {
        System.out.printf("|%-4s|%-10s|%-25s|%-8s|%-6s|\n", numberOflist, id, studentName, semester, courseName);
    }
    
    

    
}
