package com.generation.service;

import com.generation.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class StudentServiceTest {

    @Test
    @DisplayName("getNotRegisteredStudent() should return null because student don't exist")
    void getNotRegisteredStudent() {
        StudentService studentService = new StudentService();

        // By Default, should have no student. "1" will be valid ID for this test.
        Student result = studentService.findStudent("1");

        // return none for "1" ID student.
        assertNull(result);
    }

    @Test
    @DisplayName("getRegisteredStudent() should find the Student we create ID - 1")
    void getRegisteredStudent() {
        StudentService studentService = new StudentService();

        // Register Student
        Student student = new Student("1", "Loopy", "Loopy@email.com", new java.util.Date());

        // Add Student
        studentService.subscribeStudent(student);

        // Checking for ID
        Student result = studentService.findStudent("1");

        // Check Student Exist
        assertNotNull(result);
        assertEquals("Loopy", result.getName());
    }
}