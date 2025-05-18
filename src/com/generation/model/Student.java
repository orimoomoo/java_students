package com.generation.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Student
        extends Person
        implements Evaluation
{
    private double average;


    private final List<Course> courses = new ArrayList<>();


    private final Map<String, Course> approvedCourses = new HashMap<>();


    // added this for the grade declaration.
    private final Map<String, Integer> courseCreditsAwarded = new HashMap<>();




    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }


    public List<Course> getCourses() {
        return courses;
    }


    public boolean enrollToCourse(Course course )
    {
        //aad the student to the attribute "courses"
        if(courses.contains(course)) return false;


        this.courses.add(course);
        return true;
    }


    // added to store the grade value.
    public boolean gradeCourse(String courseCode, Integer grade) {
        if (courseCreditsAwarded.containsKey(courseCode)) {
            return false; // already graded
        }
        // only allow 0 or 9
        if (grade != 0 && grade != 9) {
            return false;
        }

        this.courseCreditsAwarded.put(courseCode, grade);
        return true;
    }

    public void registerApprovedCourse( Course course ) {
        approvedCourses.put(course.getCode(), course);
    }


    public boolean isCourseApproved( String courseCode ) {


        if (approvedCourses.containsKey(courseCode)) {
            return true;
        }


        return false;
    }


    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve.
    public List<Course> findPassedCourses( Course course )
    {
        //Extra TODO implement this method


        return null;
    }


    public boolean isAttendingCourse( String courseCode )
    {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }


    public String getGrade(String courseCode)
    {
        if(courseCreditsAwarded.containsKey(courseCode)) {
            return (Integer.toString(courseCreditsAwarded.get(courseCode)));
        }
        return "Not graded";
    }

    @Override
    public double getAverage()
    {
        int totalCredits = 0;
        int totalCourses = courses.size();

        for (Course course : courses) {
            Integer grade = courseCreditsAwarded.get(course.getCode());
            if (grade != null && grade == 9) {
                totalCredits += course.getCredits();
            }
        }

        if (totalCourses > 0) {
            return (double) totalCredits / totalCourses;
        } else {
            return 0;
        }
    }


    @Override
    public List<Course> getApprovedCourses()
    {
        return courses;
    }


    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}
