/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Report;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentManage {
    // khong co gi ca, chi la test github 1 chut 10:20pm 27/08/2023 
    
    List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    // khởi tạo 1 list students
    public StudentManage() {
        students = new ArrayList<>();
    }

    // kiểm tra học sinh trong 1 kì chỉ được học mỗi môn 1 lần
    public boolean ExitCourseAndSemes(Student x) {
        for (Student existingStudent : students) {
            if (existingStudent.getSemester() == x.getSemester() && existingStudent.getCourseName().equals(x.getCourseName())
                    && existingStudent.getId().equalsIgnoreCase(x.getId())) {
                return true;
            }
        }
        return false;
    }

//    public boolean ExitCourseAndSemes2(Student x) {
//        for (Student existingStudent : students) {
//            if (existingStudent.getStudentName().equalsIgnoreCase(x.getStudentName()) && existingStudent.getSemester() == x.getSemester() && existingStudent.getCourseName().equals(x.getCourseName())
//                    && existingStudent.getId().equalsIgnoreCase(x.getId())) {
//                return true;
//            }
//            
//        }
//        return false;
//    }

    // thêm học sinh vào danh sách
    public void create(Student x) {
        students.add(x);

    }

    public List<Student> FindAndSort(String name) {
        // khởi tạo 1 danh sách để lưu những học sinh được tìm thấy vào 1 danh sách mới để sắp xếp
        List<Student> sort = new ArrayList<>();
        for (Student x : students) {
            if (x.getStudentName().contains(name)) {
                sort.add(x);
            }
        }
        // sau khi tìm được những học sinh theo tên hoặc 1 phần kí tự của tên, thực hiện sort theo thứ tự aphalbet, tăng dần
        Collections.sort(sort, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentName().compareTo(o2.getStudentName());
            }
        });

        return sort;
    }

    // Tạo ra 1 danh sách có các phần tử có cùng ID để xử lý cho việc update và delete
    public List<Student> getStudentsById(String id) {
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }

    // Hàm xóa học sinh theo ID  
    public void delete(String id) {
        students.remove(getByIDToUpdate(id));
    }

    // Tìm ra học sinh có ID cần cập nhật
    public Student getByIDToUpdate(String id) {
        for (Student x : students) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        return null;
    }

    // Tìm ra học sinh có mã CODE cần cập nhật
    public Student getBySTTToUpdate(int stt) {
        for (Student x : students) {
            if (x.getNumberOflist() == stt) {
                return x;
            }
        }
        return null;
    }

    public void update(int code, Student updatedStudent) {
        Student studentToUpdate = getBySTTToUpdate(code); // lấy ra học sinh cần tìm bằng CODE
        
        studentToUpdate.setStudentName(updatedStudent.getStudentName());
        

        studentToUpdate.setId(updatedStudent.getId());

        studentToUpdate.setSemester(updatedStudent.getSemester());
        studentToUpdate.setCourseName(updatedStudent.getCourseName());
    
    }
    // Hàm cập nhật hoặc xóa khi có nhiều phần tử
    public void update1(int code, Student updatedStudent) {
        Student studentToUpdate = getBySTTToUpdate(code); // lấy ra học sinh cần tìm bằng CODE
        // quá trình update học sinh được tìm thấy bằng CODE

        // sau khi update xong 
        // Nếu chỉ thay đổi mỗi tên còn ID giữ nguyên thì phải thay đổi tất cả tên phần tử có trong danh sách có cùng ID
        // Vì mỗi ID sẽ xác định 1 học sinh, không thể chỉ sửa tên mà ID vẫn giữ nguyên vì như vậy sẽ tạo ra 2 học sinh có cùng ID
//        if (updatedStudent.getId().equalsIgnoreCase(studentToUpdate.getId()) && updatedStudent.getCourseName().equalsIgnoreCase(studentToUpdate.getCourseName())
//                && updatedStudent.getSemester() == studentToUpdate.getSemester()) {
                
            for (Student student : students) {
                if (student.getId().equalsIgnoreCase(updatedStudent.getId())) {
                    student.setStudentName(updatedStudent.getStudentName());
                    //studentToUpdate.setStudentName(updatedStudent.getStudentName());
                }
            }

//        } else {
//            studentToUpdate.setStudentName(updatedStudent.getStudentName());
//        }

        studentToUpdate.setId(updatedStudent.getId());
        studentToUpdate.setSemester(updatedStudent.getSemester());
        studentToUpdate.setCourseName(updatedStudent.getCourseName());

    }

    public void delete1(int stt) {
        students.remove(getBySTTToUpdate(stt));
    }

    public List<Report> report2() {
        List<Report> lr = new ArrayList<>();
        int total;

        for (Student x : students) {
            total = 0;
            String id = x.getId();
            String studentName = x.getStudentName();
            String courseName = x.getCourseName();
            int semesterCheck = x.getSemester();
            for (Student m : students) {
                if (id.equals(m.getId()) && studentName.equalsIgnoreCase(m.getStudentName()) && courseName.equals(m.getCourseName()) && semesterCheck == m.getSemester()) {
                    total++;
                }
            }

            boolean flag = false;
            for (Report report : lr) {
                if (id.equalsIgnoreCase(report.getIdCheck()) && studentName.equalsIgnoreCase(report.getStudentName()) && courseName.equals(report.getCourse())) {
                    report.setTotalOfCourse(report.getTotalOfCourse() + total);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                lr.add(new Report(id, studentName, courseName, total));
            }

        }
        return lr;
    }

}
