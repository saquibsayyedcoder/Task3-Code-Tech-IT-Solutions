import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private Map<String, Integer> subjectMarks;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public Map<String, Integer> getSubjectMarks() {
        return subjectMarks;
    }

    public void addSubjectMark(String subject, int mark) {
        subjectMarks.put(subject, mark);
    }

    public double calculateOverallPercentage() {
        if (subjectMarks.isEmpty()) {
            return 0.0;
        }
    
        int totalMarks = 0;
        int totalSubjects = 0;
    
        for (int mark : subjectMarks.values()) {
            totalMarks += mark;
            totalSubjects++;
        }
    
        return ((double) totalMarks / (totalSubjects * 100)) * 100;
    }

    public String calculateGrade() {
        double percentage = calculateOverallPercentage();

        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}

public class Studentgrade {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Student Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. Delete Student");
            System.out.println("4. Calculate Overall Percentage and Grade");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");

            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    calculateOverallPercentageAndGrade();
                    break;
                case 5:
                    viewAllStudents();
                    break;
                case 6:
                    System.out.println("Exiting Student Grade Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void addStudent() {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();

        Student newStudent = new Student(name, rollNumber);
        students.add(newStudent);

        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found. Please add a student first.");
            return;
        }

        System.out.print("Enter student roll number to update: ");
        int rollNumber = scanner.nextInt();

        Student student = findStudentByRollNumber(rollNumber);

        if (student == null) {
            System.out.println("Student not found. Please check the roll number and try again.");
            return;
        }

        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter new name for student: ");
        String newName = scanner.nextLine();
        student = new Student(newName, rollNumber);
        students.remove(findStudentByRollNumber(rollNumber));
        students.add(student);

        System.out.println("Student information updated successfully.");
    }

    private static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found. Please add a student first.");
            return;
        }

        System.out.print("Enter student roll number to delete: ");
        int rollNumber = scanner.nextInt();

        Student student = findStudentByRollNumber(rollNumber);

        if (student == null) {
            System.out.println("Student not found. Please check the roll number and try again.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully.");
    }

    private static void calculateOverallPercentageAndGrade() {
        if (students.isEmpty()) {
            System.out.println("No students found. Please add a student first.");
            return;
        }

        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();

        Student student = findStudentByRollNumber(rollNumber);

        if (student == null) {
            System.out.println("Student not found. Please check the roll number and try again.");
            return;
        }

        System.out.println("Calculating overall percentage and grade for " + student.getName());
        double overallPercentage = student.calculateOverallPercentage();
        String grade = student.calculateGrade();

        System.out.println("Overall Percentage: " + overallPercentage);
        System.out.println("Grade: " + grade);
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("List of all students:");
        for (Student student : students) {
            System.out.println("Name: " + student.getName() +
                    ", Roll Number: " + student.getRollNumber());
        }
    }

    private static Student findStudentByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }
}
