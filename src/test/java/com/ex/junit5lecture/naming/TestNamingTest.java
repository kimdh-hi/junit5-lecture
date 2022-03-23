package com.ex.junit5lecture.naming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TestNamingTest {

    @DisplayName("TestMethod1 name")
    @Test
    void testMethod1() {
        System.out.println("testMethod1...");
    }

    @DisplayName("TestMethod2 name")
    @Test
    void testMethod2() {
        System.out.println("testMethod2...");
    }
}
