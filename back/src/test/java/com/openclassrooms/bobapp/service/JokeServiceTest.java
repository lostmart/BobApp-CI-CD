package com.openclassrooms.bobapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.bobapp.data.JsonReader;
import com.openclassrooms.bobapp.model.Joke;

@ExtendWith(MockitoExtension.class)
@DisplayName("JokeService Tests")
class JokeServiceTest {

    @Mock
    private JsonReader jsonReader;

    private JokeService jokeService;

    @BeforeEach
    void setUp() {
        jokeService = new JokeService(jsonReader);
    }

    @Test
    @DisplayName("Should return a joke when jokes list is not empty")
    void getRandomJoke_WhenJokesExist_ShouldReturnJoke() {
        // Given
        Joke joke1 = new Joke("Why don't scientists trust atoms?", "Because they make up everything!");
        Joke joke2 = new Joke("What do you call a fake noodle?", "An impasta!");
        Joke joke3 = new Joke("Why did the scarecrow win an award?", "He was outstanding in his field!");
        
        List<Joke> jokes = Arrays.asList(joke1, joke2, joke3);
        when(jsonReader.getJokes()).thenReturn(jokes);

        // When
        Joke result = jokeService.getRandomJoke();

        // Then
        assertNotNull(result);
        assertTrue(jokes.contains(result));
        verify(jsonReader, times(1)).getJokes();
    }

    @Test
    @DisplayName("Should return the only joke when jokes list has one element")
    void getRandomJoke_WhenOnlyOneJoke_ShouldReturnThatJoke() {
        // Given
        Joke singleJoke = new Joke("Why don't eggs tell jokes?", "They'd crack each other up!");
        List<Joke> jokes = Arrays.asList(singleJoke);
        when(jsonReader.getJokes()).thenReturn(jokes);

        // When
        Joke result = jokeService.getRandomJoke();

        // Then
        assertNotNull(result);
        assertEquals(singleJoke, result);
        verify(jsonReader, times(1)).getJokes();
    }

    @Test
    @DisplayName("Should throw IndexOutOfBoundsException when jokes list is empty")
    void getRandomJoke_WhenNoJokes_ShouldThrowException() {
        // Given
        List<Joke> emptyJokes = new ArrayList<>();
        when(jsonReader.getJokes()).thenReturn(emptyJokes);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            jokeService.getRandomJoke();
        });
        verify(jsonReader, times(1)).getJokes();
    }

    @Test
    @DisplayName("Should handle multiple calls and potentially return different jokes")
    void getRandomJoke_MultipleCalls_ShouldReturnValidJokes() {
        // Given
        Joke joke1 = new Joke("What's the best thing about Switzerland?", "I don't know, but the flag is a big plus!");
        Joke joke2 = new Joke("How do you organize a space party?", "You planet!");
        Joke joke3 = new Joke("Why don't skeletons fight each other?", "They don't have the guts!");
        
        List<Joke> jokes = Arrays.asList(joke1, joke2, joke3);
        when(jsonReader.getJokes()).thenReturn(jokes);

        // When
        Joke result1 = jokeService.getRandomJoke();
        Joke result2 = jokeService.getRandomJoke();
        Joke result3 = jokeService.getRandomJoke();

        // Then
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
        
        assertTrue(jokes.contains(result1));
        assertTrue(jokes.contains(result2));
        assertTrue(jokes.contains(result3));
        
        verify(jsonReader, times(3)).getJokes();
    }

    @Test
    @DisplayName("Should handle null jokes list gracefully")
    void getRandomJoke_WhenJokesListIsNull_ShouldThrowException() {
        // Given
        when(jsonReader.getJokes()).thenReturn(null);

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            jokeService.getRandomJoke();
        });
        verify(jsonReader, times(1)).getJokes();
    }

    @Test
    @DisplayName("Constructor should initialize with JsonReader")
    void constructor_ShouldInitializeWithJsonReader() {
        // Given
        JsonReader mockJsonReader = mock(JsonReader.class);

        // When
        JokeService service = new JokeService(mockJsonReader);

        // Then
        assertNotNull(service);
        // We can't directly test the private field, but we can verify it works
        // by testing the behavior when getRandomJoke is called
    }

    @Test
    @DisplayName("Should verify JsonReader is called exactly once per getRandomJoke call")
    void getRandomJoke_ShouldCallJsonReaderExactlyOnce() {
        // Given
        Joke joke = new Joke("Test question?", "Test answer!");
        List<Joke> jokes = Arrays.asList(joke);
        when(jsonReader.getJokes()).thenReturn(jokes);

        // When
        jokeService.getRandomJoke();

        // Then
        verify(jsonReader, times(1)).getJokes();
        verifyNoMoreInteractions(jsonReader);
    }
}