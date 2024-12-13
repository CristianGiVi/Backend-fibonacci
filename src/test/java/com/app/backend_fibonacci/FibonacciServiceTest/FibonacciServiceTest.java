package com.app.backend_fibonacci.FibonacciServiceTest;

import com.app.backend_fibonacci.Models.Fibonacci;

import com.app.backend_fibonacci.Repositories.FibonacciRepository;
import com.app.backend_fibonacci.Services.FibonacciServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {

    @InjectMocks
    private FibonacciServiceImpl fibonacciServiceImpl;

    @Mock
    private FibonacciRepository fibonacciRepository;

    @Test
    void testFindAll() {
        // Given
        Fibonacci fibonacci1 = new Fibonacci(1L, LocalTime.of(12, 30, 0), "[0, 1, 1, 2, 3, 5]");
        Fibonacci fibonacci2 = new Fibonacci(2L, LocalTime.of(15, 45, 0), "[0, 1, 1, 2, 3, 5, 8]");
        List<Fibonacci> fibonacciList = Arrays.asList(fibonacci1, fibonacci2);

        given(fibonacciRepository.findAll()).willReturn(fibonacciList);

        // When
        List<Fibonacci> result = fibonacciServiceImpl.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("[0, 1, 1, 2, 3, 5]", result.get(0).getFibonacciSerie());
    }

    @Test
    void testGenerateSerieWithSelectedTime() {
        // Given
        String inputTime = "15:45:30";
        LocalTime parsedTime = LocalTime.of(15, 45, 30);
        List<Integer> expectedSerie = Arrays.asList(62715, 38760, 23955, 14805, 9150, 5655, 3495, 2160, 1335, 825, 510, 315, 195, 120, 75, 45, 30);
        Fibonacci savedFibonacci = new Fibonacci(2L, parsedTime, expectedSerie.toString());

        given(fibonacciRepository.save(Mockito.any(Fibonacci.class))).willReturn(savedFibonacci);

        // When
        String result = fibonacciServiceImpl.generateSerieWithSelectedTime(inputTime);

        // Then
        assertNotNull(result);
        assertEquals(expectedSerie.toString(), result);
    }

}
