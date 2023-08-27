/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import Bo.StudentManage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Report;
import model.Student;

/**
 *
 * @author Admin
 */
public class Validation {
    private StudentManage students;

    public Validation() {
        students = new StudentManage();
    }
    
    public static int getInt(String mess) {
        while (true) {
            try {
                System.out.print(mess);
                int x = Integer.parseInt(new Scanner(System.in).nextLine().trim());
                return x;
            } catch (Exception e) {
                System.out.println("Please input the integer number!!!");
            }
        }
    }

    public static int getChoice(int min, int max) {
        while (true) {
            try {
                int x = Integer.parseInt(new Scanner(System.in).nextLine().trim());
                if (x < min || x > max) {
                    throw new Exception();
                }
                return x;
            } catch (Exception e) {
                System.out.println("Input the choice from( " + min + " to " + max + " )");
            }
        }

    }

    public static String getString(String mess) {
        while (true) {
            try {
                System.out.print(mess);
                String x = new Scanner(System.in).nextLine().trim();
                return x;
            } catch (Exception e) {
                System.out.println("Please input a string!!!");
            }
        }

    }

    public static String getID(List<Student> list) {
        while (true) {
            try {
                String n = getString("Input ID: ");
                for (Student t : list) {
                    if (t.getId().equalsIgnoreCase(n)) {
                        throw new Exception();
                    }
                }

                return n;
            } catch (Exception e) {
                System.out.println("ID was exist, please input again!!!");
            }
        }

    }

    public static boolean isExist(List<Student> list, String id) {
        for (Student student : list) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static List<Student> getStudentsById(List<Student> list, String id) {
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id)) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }

  
    

//    public static boolean ExitCourseAndSemes(List<Student> list, Student x) {
//        for (Student existingStudent : list) {
//            if (existingStudent.getSemester() == x.getSemester() && existingStudent.getCourseName().equals(x.getCourseName())
//                    && existingStudent.getId().equalsIgnoreCase(x.getId())) {
//                return true;
//            }
//
//        }
//        return false;
//    }
    
    

    public static String getCourse() {
        while (true) {
            try {
                String n = getString("Input course: ");
                if (!((n.equals("Java") || n.equals(".Net") || n.equals("C/C++")))) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println("Please input three courses: Java, .Net, C/C++");
            }
        }

    }

    public static boolean checkReportExist(List<Report> lr, String id, String name, String course, int total) {
        for (Report report : lr) {
            if (id.equalsIgnoreCase(report.getIdCheck()) && name.equalsIgnoreCase(report.getStudentName()) && course.equals(report.getCourse())) {
                report.setTotalOfCourse(report.getTotalOfCourse() + total);
                return false;
            }
        }
        return true;

    }

    public static boolean checkPutYN() {
        while (true) {
            String x = getString("Do you want to continue (Y/N)? Choose Y to continue, N to return main screen.");
            if (x.equalsIgnoreCase("Y")) {
                return true;
            }
            if (x.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");

        }
    }
    
    public static void displayList(List<Student> list) {
        for (Student x : list) {
            System.out.println(x.showProFile() + " ");
        }

    }

    public static void displayListUpdate(List<Student> list) {
        System.out.printf("|%-4s|%-10s|%-25s|%-8s|%-6s|\n", "CODE", "Id", "Name", "Semester", "Course");
        System.out.println("-------------------------------------------------------------------------");
        for (Student x : list) {
            
            x.showProFile12();
        }

    }

    public static void displayReport(List<Report> lr) {
        System.out.printf("|%-11s|%-10s|%-5s|\n", "StudentName", "CourseName", "Total");
        System.out.println("---------------------------------------------------------");
        for (Report report : lr) {
            System.out.printf("|%-11s|%-10s|%-5d|\n", report.getStudentName(), report.getCourse(), report.getTotalOfCourse());
        }
    }

}
