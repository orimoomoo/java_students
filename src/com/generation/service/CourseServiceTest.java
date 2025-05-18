package com.generation.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    // We do not need to inject courses because it is already fulfilled when CourseService is instantiated
    // when the constructor for the instance of CourseService is invoked
    CourseService courseService = new CourseService();

    @Test
    @DisplayName("getCourse() method should return the same course code - INTRO-CS-1")
    void getCourse() {
        assertEquals("INTRO-CS-1", courseService.getCourse("INTRO-CS-1").getCode());
    }

    @Test
    @DisplayName("getInvalidCourse() method should return null for not registered course name")
    void getInvalidCourse() {
        assertNull(courseService.getCourse(""));
    }

}