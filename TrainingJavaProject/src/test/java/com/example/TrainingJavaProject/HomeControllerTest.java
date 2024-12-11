package com.example.TrainingJavaProject;

import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class HomeControllerTest {
    @Test
    public void testLargeObjectCreation() {
        // Adjust the number to create a large number of objects.
        final int NUM_OBJECTS = 2; // 1 million objects, Stored in stack (primitive value)
        // `objects` is a reference stored in the stack;
        // the `MyObject` instance is stored in the heap
        MyObject[] objects = new MyObject[NUM_OBJECTS];

        // Create instances of MyObject.
        for (int i = 0; i < NUM_OBJECTS; i++) {
            objects[i] = new MyObject();
        }

        // Perform garbage collection to see how it behaves.
        System.gc(); // Suggests that garbage collection should run.

        // Optionally, check if all objects were created.
        assertNotNull("Objects should be created successfully.", objects[0]);
    }

    static class MyObject {
        private final int[] data = new int[1000]; // Array to consume memory. Stored in the heap as part of the object
    }
}
