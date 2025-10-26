package Javacode;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> grades;

    Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    void addGrade(int grade) {
        grades.add(grade);
    }

    double getAverage() {
        if (grades.isEmpty()) return 0;
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    int getHighest() {
        int max = Integer.MIN_VALUE;
        for (int g : grades) {
            if (g > max) max = g;
        }
        return max;
    }

    int getLowest() {
        int min = Integer.MAX_VALUE;
        for (int g : grades) {
            if (g < min) min = g;
        }
        return min;
    }

    void displayReport() {
        System.out.println("Student: " + name);
        System.out.println("Grades: " + grades);
        System.out.println("Average: " + getAverage());
        System.out.println("Highest: " + getHighest());
        System.out.println("Lowest: " + getLowest());
        System.out.println("-------------------------------------");
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("===== Student Grade Tracker =====");
        while (true) {
            System.out.print("\nEnter student name (or type 'exit' to finish): ");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("exit")) break;

            Student s = new Student(name);

            while (true) {
                System.out.print("Enter grade for " + name + " (-1 to stop): ");
                int grade = sc.nextInt();
                if (grade == -1) break;
                s.addGrade(grade);
            }
            sc.nextLine(); // consume newline
            students.add(s);
        }

        // Display summary report
        System.out.println("\n===== Summary Report =====");
        for (Student s : students) {
            s.displayReport();
        }

        System.out.println("Total Students: " + students.size());
        System.out.println("===== End of Report =====");
        sc.close();
    }
}

