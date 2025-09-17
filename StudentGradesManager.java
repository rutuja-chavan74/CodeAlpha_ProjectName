package Javacode;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradesManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input student data
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = sc.nextLine();

            System.out.print("Enter grade of " + name + ": ");
            double grade = sc.nextDouble();
            sc.nextLine(); // consume newline

            students.add(new Student(name, grade));
        }

        // Processing grades
        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        String topStudent = "";
        String lowStudent = "";

        for (Student s : students) {
            total += s.grade;
            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s.name;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                lowStudent = s.name;
            }
        }

        double average = total / students.size();

        // Display report
        System.out.println("\n===== Student Grades Report =====");
        for (Student s : students) {
            System.out.println(s.name + " - " + s.grade);
        }
        System.out.println("----------------------------------");
        System.out.printf("Average Score: %.2f\n", average);
        System.out.println("Highest Score: " + highest + " (by " + topStudent + ")");
        System.out.println("Lowest Score: " + lowest + " (by " + lowStudent + ")");
        System.out.println("==================================");

        sc.close();
    }
}
