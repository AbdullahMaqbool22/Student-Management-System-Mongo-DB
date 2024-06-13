package com.example;

import com.mongodb.client.MongoDatabase;
import java.util.Scanner;

public class StudentPortalManagementSystem {
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    private EnrollmentDAO enrollmentDAO;
    private FeesDAO feesDAO;
    private GradeDAO gradeDAO;
    private AttendanceDAO attendanceDAO;
    private TeacherDAO teacherDAO;
    private FineDAO fineDAO;
    private DatabaseConnection dbConnection;

    public StudentPortalManagementSystem() {
        dbConnection = new DatabaseConnection("mongodb://localhost:27017", "student_portal");
        MongoDatabase database = dbConnection.getDatabase();
        studentDAO = new StudentDAO(database.getCollection("students"));
        courseDAO = new CourseDAO(database.getCollection("courses"));
        enrollmentDAO = new EnrollmentDAO(database.getCollection("enrollments"));
        feesDAO = new FeesDAO(database.getCollection("fees"));
        gradeDAO = new GradeDAO(database.getCollection("grades"));
        attendanceDAO = new AttendanceDAO(database.getCollection("attendance"));
        teacherDAO = new TeacherDAO(database.getCollection("teachers"));
        fineDAO = new FineDAO(database.getCollection("fines"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter command:");
            command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "add student":
                    addStudent(scanner);
                    break;
                case "get student":
                    getStudent(scanner);
                    break;
                case "update student":
                    updateStudent(scanner);
                    break;
                case "delete student":
                    deleteStudent(scanner);
                    break;
                case "add course":
                    addCourse(scanner);
                    break;
                case "get course":
                    getCourse(scanner);
                    break;
                case "update course":
                    updateCourse(scanner);
                    break;
                case "delete course":
                    deleteCourse(scanner);
                    break;
                case "enroll student":
                    enrollStudent(scanner);
                    break;
                case "delete enrollment":
                    deleteEnrollment(scanner);
                    break;
                case "add fees":
                    addFees(scanner);
                    break;
                case "get fees":
                    getFees(scanner);
                    break;
                case "update fees":
                    updateFees(scanner);
                    break;
                case "delete fees":
                    deleteFees(scanner);
                    break;
                case "add grade":
                    addGrade(scanner);
                    break;
                case "get grade":
                    getGrade(scanner);
                    break;
                case "update grade":
                    updateGrade(scanner);
                    break;
                case "delete grade":
                    deleteGrade(scanner);
                    break;
                case "add attendance":
                    addAttendance(scanner);
                    break;
                case "get attendance":
                    getAttendance(scanner);
                    break;
                case "update attendance":
                    updateAttendance(scanner);
                    break;
                case "delete attendance":
                    deleteAttendance(scanner);
                    break;
                case "add teacher":
                    addTeacher(scanner);
                    break;
                case "get teacher":
                    getTeacher(scanner);
                    break;
                case "update teacher":
                    updateTeacher(scanner);
                    break;
                case "delete teacher":
                    deleteTeacher(scanner);
                    break;
                case "add fine":
                    addFine(scanner);
                    break;
                case "get fine":
                    getFine(scanner);
                    break;
                case "update fine":
                    updateFine(scanner);
                    break;
                case "delete fine":
                    deleteFine(scanner);
                    break;
                case "Get grades distribution":
                    gradeDAO.getGradesDistribution();
                    break;
                case "exit":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Try again.");
                    break;
            }
        }
    }

    // Student CRUD
    private void addStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        String id = scanner.nextLine().trim();
        System.out.println("Enter student name:");
        String name = scanner.nextLine().trim();
        System.out.println("Enter student email:");
        String email = scanner.nextLine().trim();

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        studentDAO.addStudent(student);

        System.out.println("Student added successfully.");
    }

    private void getStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        String id = scanner.nextLine().trim();

        Student student = studentDAO.getStudent(id);
        if (student != null) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Email: " + student.getEmail());
        } else {
            System.out.println("Student not found.");
        }
    }

    private void updateStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        String id = scanner.nextLine().trim();

        Student student = studentDAO.getStudent(id);
        if (student != null) {
            System.out.println("Enter new student name:");
            String name = scanner.nextLine().trim();
            System.out.println("Enter new student email:");
            String email = scanner.nextLine().trim();

            student.setName(name);
            student.setEmail(email);
            studentDAO.updateStudent(student);

            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        String id = scanner.nextLine().trim();

        studentDAO.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    }

    // Course CRUD
    private void addCourse(Scanner scanner) {
        System.out.println("Enter course ID:");
        String id = scanner.nextLine().trim();
        System.out.println("Enter course name:");
        String name = scanner.nextLine().trim();
        System.out.println("Enter course description:");
        String description = scanner.nextLine().trim();

        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setDescription(description);
        courseDAO.addCourse(course);

        System.out.println("Course added successfully.");
    }

    private void getCourse(Scanner scanner) {
        System.out.println("Enter course ID:");
        String id = scanner.nextLine().trim();

        Course course = courseDAO.getCourse(id);
        if (course != null) {
            System.out.println("Course ID: " + course.getId());
            System.out.println("Course Name: " + course.getName());
            System.out.println("Course Description: " + course.getDescription());
        } else {
            System.out.println("Course not found.");
        }
    }

    private void updateCourse(Scanner scanner) {
        System.out.println("Enter course ID:");
        String id = scanner.nextLine().trim();

        Course course = courseDAO.getCourse(id);
        if (course != null) {
            System.out.println("Enter new course name:");
            String name = scanner.nextLine().trim();
            System.out.println("Enter new course description:");
            String description = scanner.nextLine().trim();

            course.setName(name);
            course.setDescription(description);
            courseDAO.updateCourse(course);

            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private void deleteCourse(Scanner scanner) {
        System.out.println("Enter course ID:");
        String id = scanner.nextLine().trim();

        courseDAO.deleteCourse(id);
        System.out.println("Course deleted successfully.");
    }

    // Enrollment CRUD
    private void enrollStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollmentDAO.enrollStudent(enrollment);

        System.out.println("Student enrolled in course successfully.");
    }

    private void deleteEnrollment(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        enrollmentDAO.deleteEnrollment(studentId, courseId);
        System.out.println("Enrollment deleted successfully.");
    }

    // Fees CRUD
    private void addFees(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();
        System.out.println("Enter fee amount:");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        Fees fees = new Fees();
        fees.setStudentId(studentId);
        fees.setCourseId(courseId);
        fees.setAmount(amount);
        feesDAO.addFees(fees);

        System.out.println("Fees added successfully.");
    }

    private void getFees(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        Fees fees = feesDAO.getFees(studentId, courseId);
        if (fees != null) {
            System.out.println("Student ID: " + fees.getStudentId());
            System.out.println("Course ID: " + fees.getCourseId());
            System.out.println("Fee Amount: " + fees.getAmount());
        } else {
            System.out.println("Fees not found.");
        }
    }

    private void updateFees(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        Fees fees = feesDAO.getFees(studentId, courseId);
        if (fees != null) {
            System.out.println("Enter new fee amount:");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            fees.setAmount(amount);
            feesDAO.updateFees(fees);

            System.out.println("Fees updated successfully.");
        } else {
            System.out.println("Fees not found.");
        }
    }

    private void deleteFees(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        feesDAO.deleteFees(studentId, courseId);
        System.out.println("Fees deleted successfully.");
    }

    // Grade CRUD
    private void addGrade(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();
        System.out.println("Enter grade:");
        String gradeValue = scanner.nextLine().trim();

        Grade grade = new Grade();
        grade.setStudentId(studentId);
        grade.setCourseId(courseId);
        grade.setGrade(gradeValue);
        gradeDAO.addGrade(grade);

        System.out.println("Grade added successfully.");
    }

    private void getGrade(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        Grade grade = gradeDAO.getGrade(studentId, courseId);
        if (grade != null) {
            System.out.println("Student ID: " + grade.getStudentId());
            System.out.println("Course ID: " + grade.getCourseId());
            System.out.println("Grade: " + grade.getGrade());
        } else {
            System.out.println("Grade not found.");
        }
    }

    private void updateGrade(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        Grade grade = gradeDAO.getGrade(studentId, courseId);
        if (grade != null) {
            System.out.println("Enter new grade:");
            String gradeValue = scanner.nextLine().trim();

            grade.setGrade(gradeValue);
            gradeDAO.updateGrade(grade);

            System.out.println("Grade updated successfully.");
        } else {
            System.out.println("Grade not found.");
        }
    }

    private void deleteGrade(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        gradeDAO.deleteGrade(studentId, courseId);
        System.out.println("Grade deleted successfully.");
    }

    // Attendance CRUD
    private void addAttendance(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();
        System.out.println("Enter date (yyyy-mm-dd):");
        String dateStr = scanner.nextLine().trim();
        System.out.println("Is present? (true/false):");
        boolean isPresent = Boolean.parseBoolean(scanner.nextLine().trim());

        Attendance attendance = new Attendance();
        attendance.setStudentId(studentId);
        attendance.setCourseId(courseId);
        attendance.setDate(java.sql.Date.valueOf(dateStr));
        attendance.setPresent(isPresent);
        attendanceDAO.addAttendance(attendance);

        System.out.println("Attendance added successfully.");
    }

    private void getAttendance(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        Attendance attendance = attendanceDAO.getAttendance(studentId, courseId);
        if (attendance != null) {
            System.out.println("Student ID: " + attendance.getStudentId());
            System.out.println("Course ID: " + attendance.getCourseId());
            System.out.println("Date: " + attendance.getDate());
            System.out.println("Present: " + attendance.isPresent());
        } else {
            System.out.println("Attendance not found.");
        }
    }

    private void updateAttendance(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        Attendance attendance = attendanceDAO.getAttendance(studentId, courseId);
        if (attendance != null) {
            System.out.println("Enter new date (yyyy-mm-dd):");
            String dateStr = scanner.nextLine().trim();
            System.out.println("Is present? (true/false):");
            boolean isPresent = Boolean.parseBoolean(scanner.nextLine().trim());

            attendance.setDate(java.sql.Date.valueOf(dateStr));
            attendance.setPresent(isPresent);
            attendanceDAO.updateAttendance(attendance);

            System.out.println("Attendance updated successfully.");
        } else {
            System.out.println("Attendance not found.");
        }
    }

    private void deleteAttendance(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine().trim();

        attendanceDAO.deleteAttendance(studentId, courseId);
        System.out.println("Attendance deleted successfully.");
    }

    // Teacher CRUD
    private void addTeacher(Scanner scanner) {
        System.out.println("Enter teacher ID:");
        String id = scanner.nextLine().trim();
        System.out.println("Enter teacher name:");
        String name = scanner.nextLine().trim();
        System.out.println("Enter teacher email:");
        String email = scanner.nextLine().trim();
        System.out.println("Enter teacher department:");
        String department = scanner.nextLine().trim();

        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setDepartment(department);
        teacherDAO.addTeacher(teacher);

        System.out.println("Teacher added successfully.");
    }

    private void getTeacher(Scanner scanner) {
        System.out.println("Enter teacher ID:");
        String id = scanner.nextLine().trim();

        Teacher teacher = teacherDAO.getTeacher(id);
        if (teacher != null) {
            System.out.println("Teacher ID: " + teacher.getId());
            System.out.println("Teacher Name: " + teacher.getName());
            System.out.println("Teacher Email: " + teacher.getEmail());
            System.out.println("Teacher Department: " + teacher.getDepartment());
        } else {
            System.out.println("Teacher not found.");
        }
    }

    private void updateTeacher(Scanner scanner) {
        System.out.println("Enter teacher ID:");
        String id = scanner.nextLine().trim();

        Teacher teacher = teacherDAO.getTeacher(id);
        if (teacher != null) {
            System.out.println("Enter new teacher name:");
            String name = scanner.nextLine().trim();
            System.out.println("Enter new teacher email:");
            String email = scanner.nextLine().trim();
            System.out.println("Enter new teacher department:");
            String department = scanner.nextLine().trim();

            teacher.setName(name);
            teacher.setEmail(email);
            teacher.setDepartment(department);
            teacherDAO.updateTeacher(teacher);

            System.out.println("Teacher updated successfully.");
        } else {
            System.out.println("Teacher not found.");
        }
    }

    private void deleteTeacher(Scanner scanner) {
        System.out.println("Enter teacher ID:");
        String id = scanner.nextLine().trim();

        teacherDAO.deleteTeacher(id);
        System.out.println("Teacher deleted successfully.");
    }

    // Fine CRUD
    private void addFine(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();
        System.out.println("Enter fine description:");
        String description = scanner.nextLine().trim();
        System.out.println("Enter fine amount:");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        Fine fine = new Fine();
        fine.setStudentId(studentId);
        fine.setDescription(description);
        fine.setAmount(amount);
        fineDAO.addFine(fine);

        System.out.println("Fine added successfully.");
    }

    private void getFine(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();

        Fine fine = fineDAO.getFine(studentId);
        if (fine != null) {
            System.out.println("Student ID: " + fine.getStudentId());
            System.out.println("Description: " + fine.getDescription());
            System.out.println("Amount: " + fine.getAmount());
        } else {
            System.out.println("Fine not found.");
        }
    }

    private void updateFine(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();

        Fine fine = fineDAO.getFine(studentId);
        if (fine != null) {
            System.out.println("Enter new fine description:");
            String description = scanner.nextLine().trim();
            System.out.println("Enter new fine amount:");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            fine.setDescription(description);
            fine.setAmount(amount);
            fineDAO.updateFine(fine);

            System.out.println("Fine updated successfully.");
        } else {
            System.out.println("Fine not found.");
        }
    }

    private void deleteFine(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine().trim();

        fineDAO.deleteFine(studentId);
        System.out.println("Fine deleted successfully.");
    }
}
