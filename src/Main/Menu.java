package Main;

import Bo.StudentManage;
import Validate.Validation;
import java.util.List;
import model.Student;

/**
 *
 * @author Admin
 */
public class Menu {

    private StudentManage students;

    public Menu() {
        students = new StudentManage();
    }

    private final String ops[] = {
        "Create",
        "Find and Sort",
        "Update/Delete",
        " Report",
        " Exit",
        "(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program)."
    };

    public int getChoice() {
        for (int i = 0; i < ops.length; i++) {
            System.out.println((i + 1) + ". " + ops[i]);
        }
        return Validation.getChoice(1, 5);
    }

    public void display() {
        int choice;
        int count = 1;
        String id, studentName;
        do {
            System.out.println("==========STUDENT MANAGEMENT==========");
            choice = getChoice();

            switch (choice) {
                case 1:
                    // Nếu nhập hơn 10 học sinh sẽ hỏi có muốn nhập tiếp hay không
                    if (count > 10) {
                        if (!Validation.checkPutYN()) {
                            return;
                        }
                    }
                    while (true) { // hàm kiểm tra xem có bị trùng ID không
                        try {
                            id = Validation.getString("Input ID: ");
                            studentName = Validation.getString("Input student name: ");
                            boolean isDuplicateId = false;

                            for (Student t : students.getStudents()) {
                                if (t.getId().equalsIgnoreCase(id)) {
                                    if (!t.getStudentName().equalsIgnoreCase(studentName)) {
                                        isDuplicateId = true;
                                        break;
                                    }
                                }
                            }

                            if (isDuplicateId) {
                                System.out.println("ID was existed");
                            } else {
                                break;
                            }

                        } catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                    }

                    int semester = Validation.getInt("Input semester: ");
                    String courseName = Validation.getCourse();
                    Student student = new Student(id, studentName, semester, courseName, count);
                    // Kiểm tra xem học sinh nếu đã đăng kí môn học nào đó trong kì đấy rồi thì trong kì đấy 
                    // chỉ được đăng kí học môn khác chứ không được đăng kí học môn đã đăng kí trong kì đấy nữa
                    if (!students.ExitCourseAndSemes(student)) {
                        students.create(student);
                        System.out.println("ADDED");
                    } else {
                        System.out.println("The student was in the same semester enrolled and course already.");
                    }
                    count++;

                    break;

                case 2:
                    String name = Validation.getString("Input your name to find: ");
                    if (students.getStudents().isEmpty()) {
                        System.out.println("The list was empty");
                    }
                    // Tìm kiếm và sắp xếp học sinh theo tên hoặc 1 phần kí tự của tên bằng collections sort với hàm  compare to
                    List<Student> sorted = students.FindAndSort(name);
                    Validation.displayList(sorted);
                    break;
                case 3:

                    String id1 = Validation.getString("Enter ID to find student: ");
                    if (students.getStudents().isEmpty()) {
                        System.out.println("The list was empty");
                    } else {
                        //Tạo ra danh sách để lưu học sinh được tìm thấy bằng ID
                        List<Student> matchingStudents = students.getStudentsById(id1);
                        // Nếu danh sách có độ dài là 1 thì chỉ có 1 học sinh sẽ được cập nhật hoặc xóa                       
                        if (matchingStudents.size() == 1) {
                            // vì chỉ có 1 phần tử nên không cần xóa theo CODE, do vậy chỉ cập nhật hoặc xóa theo ID.
                            // vì class student có thêm phần mã CODE nên vẫn phải thêm vào.
                            int stt = matchingStudents.get(0).getNumberOflist();
                            UpdateAndDelete(id1, stt);
                            // Nếu danh sách có độ lớn hơn 1 thì có nhiều phần tử sẽ được update hoặc xóa
                        } else if (matchingStudents.size() > 1) {
                            System.out.println("List of multiple students found with the same ID:");
                            Validation.displayListUpdate(matchingStudents);
                            // Do nhiều phần tử nên cần có CODE để biết được nên cập nhật hay xóa phần tử nào
                            int code = Validation.getInt("Input the CODE of this student to update or delete: ");
                            for (Student x : matchingStudents) {
                                if (x.getNumberOflist() == code) {
                                    x.showProFile12();
                                    UpdateAndDelete(null, code);
                                    break;
                                }
                            }
                        } else {
                            System.out.println("No found student to update and delete");
                        }
                    }
                    break;
                case 4:
                    Validation.displayReport(students.report2());
                    break;
                case 5:
                    break;

            }

        } while (choice != 5);

    }

    public void UpdateAndDelete(String id, int code) {
        String studentName;
        String idToUpdate;
        int semester;
        String courseName;
        String action = Validation.getString("Do you want to update (U) or delete (D) student: ");

        if (action.equalsIgnoreCase("u")) {
            idToUpdate = Validation.getString("Input ID: ");
            studentName = Validation.getString("Input student name: ");
            semester = Validation.getInt("Input semester: ");
            courseName = Validation.getCourse();
            Student student1 = new Student(idToUpdate, studentName, semester, courseName, code);
            Student temp = students.getBySTTToUpdate(code); 
            
            if (!students.ExitCourseAndSemes(student1)) {
                students.update(code, student1);
                if(temp.getId().equalsIgnoreCase(idToUpdate)){
                    students.update1(code, student1);
                }
                System.out.println("UPDATED");

            } else {
                if(temp.getId().equalsIgnoreCase(idToUpdate)){
                    students.update1(code, student1);          
                    System.out.println("UPDATEDD");
                    return;
                }
                System.out.println("The student was exist in the same semester enrolled and course already.");
            }

        }
        if (action.equalsIgnoreCase("d")) {
            if (id != null) {
                students.delete(id);
            } else {
                students.delete1(code);
            }

            System.out.println("DELETED");
        }
    }

}
