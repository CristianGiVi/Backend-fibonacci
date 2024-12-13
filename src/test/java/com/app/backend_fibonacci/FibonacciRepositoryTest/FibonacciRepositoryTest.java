package com.app.backend_fibonacci.FibonacciRepositoryTest;

import com.app.backend_fibonacci.Models.Fibonacci;
import com.app.backend_fibonacci.Repositories.FibonacciRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FibonacciRepositoryTest {

    @Mock
    private FibonacciRepository fibonacciRepository;

    private Fibonacci fibonacci;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fibonacci = new Fibonacci();
        fibonacci.setId(1L);
        fibonacci.setFibonacciSerie("[0, 1, 1, 2, 3, 5]");
        fibonacci.setTime(LocalTime.of(23, 30, 0));
    }

    @Test
    void testSave(){

        // Give
        given(fibonacciRepository.save(fibonacci)).willReturn(fibonacci);

        // When
        Fibonacci savedFibonacci = fibonacciRepository.save(fibonacci);

        // Then
        assertNotNull(savedFibonacci);
        assertEquals(fibonacci.getId(), savedFibonacci.getId());
        assertEquals(fibonacci.getFibonacciSerie(), savedFibonacci.getFibonacciSerie());
    }

    @Test
    void testFindAll(){
        // Given
        Fibonacci fibonacci1 = new Fibonacci();
        fibonacci.setId(2L);
        fibonacci.setFibonacciSerie("[0, 1, 1, 2, 3, 5,6]");
        fibonacci.setTime(LocalTime.of(1, 30, 0));

        given(fibonacciRepository.findAll()).willReturn(List.of(fibonacci1));

        // When
        List<Fibonacci> fibonacciList = (List<Fibonacci>) fibonacciRepository.findAll();

        //Then
        assertNotNull(fibonacciList);
        assertFalse(((Collection<?>) fibonacciList).isEmpty());
    }
}
