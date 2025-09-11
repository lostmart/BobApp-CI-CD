package com.openclassrooms.bobapp.controller;

// These are the essential imports for the test
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.openclassrooms.bobapp.model.Joke;
import com.openclassrooms.bobapp.service.JokeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// This annotation initializes mocks and handles the test context without loading the full Spring application.
@ExtendWith(MockitoExtension.class)
public class JokeControllerTest {

    // This mock will be automatically injected into the controller
    @Mock
    private JokeService jokeService;

    // This creates an instance of the controller and injects the mocks into it
    @InjectMocks
    private JokeController jokeController;

    @Test
    public void getRandomJokes_ShouldReturnJokeFromService_WhenServiceProvidesJoke() {
        // 1. ARRANGE - Define the test data and behavior
        Joke expectedJoke = new Joke("Why did the chicken cross the road?", "");
        when(jokeService.getRandomJoke()).thenReturn(expectedJoke);

        // 2. ACT - Call the method you are testing
        ResponseEntity<?> response = jokeController.getRandomJokes();

        // 3. ASSERT - Verify the results
        assertEquals(200, response.getStatusCodeValue()); // Check HTTP status is OK
        assertEquals(expectedJoke, response.getBody()); // Check the body is the joke from the service
    }

    @Test
    public void getRandomJokes_ShouldReturnOk_EvenIfJokeIsEmpty() {
        // 1. ARRANGE - Test with an empty string
        Joke emptyJoke = new Joke("", "");
        when(jokeService.getRandomJoke()).thenReturn(emptyJoke);

        // 2. ACT
        ResponseEntity<?> response = jokeController.getRandomJokes();

        // 3. ASSERT
        assertEquals(200, response.getStatusCodeValue()); // Status should still be 200
        assertEquals(emptyJoke, response.getBody()); // Body should be the empty string
    }

    // This test is optional but good practice for null scenarios
    @Test
    public void getRandomJokes_ShouldReturnOk_WhenJokeIsNull() {
        // 1. ARRANGE - Test with a null value
        when(jokeService.getRandomJoke()).thenReturn(null);

        // 2. ACT
        ResponseEntity<?> response = jokeController.getRandomJokes();

        // 3. ASSERT
        assertEquals(200, response.getStatusCodeValue()); // Status should still be 200
        assertEquals(null, response.getBody()); // Body should be null
    }
}
