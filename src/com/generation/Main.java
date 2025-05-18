package com.generation;


import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;


import java.text.ParseException;
import java.util.Scanner;


public class Main
{


    public static void main( String[] args )
            throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
            }
        }
        while ( option != 7 );
    }


    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        boolean status = studentService.enrollToCourse(studentId, course);
        if (status)
            System.out.println("Student with ID: " + studentId + " enrolled successful to  " + courseId);
        else
            System.out.println("Unable to enrol Student with ID: " + studentId + ". Already enrolled");


    }


    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }


    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }


    private static void gradeStudent( StudentService studentService, Scanner scanner ) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student found: " + student);
        System.out.println("Enrolled courses:");
        for (Course course : student.getCourses()) {
            System.out.println(course.getCode() + " - " + course.getName() + " | Current grade: " + student.getGrade(course.getCode()));
        }

        System.out.println("Enter course name to grade:");
        String courseCode = scanner.next();

        if (!student.isAttendingCourse(courseCode)) {
            System.out.println("Student is not enrolled in that course.");
            return;
        }

        System.out.println("Enter grade (0 for fail, 9 for pass):");
        int grade;
        try {
            grade = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade input.");
            return;
        }

        if (grade != 0 && grade != 9) {
            System.out.println("Invalid grade. Only 0 (fail) or 9 (pass) are allowed.");
            return;
        }

        boolean success = student.gradeCourse(courseCode, grade);
        if (success) {
            System.out.println("Grade recorded successfully.");
        } else {
            System.out.println("Failed to record grade. Already graded or invalid grade :)");
        }
    }


    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }


    private static void registerStudent( StudentService studentService, Scanner scanner )
            throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}