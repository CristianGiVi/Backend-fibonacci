package com.app.backend_fibonacci.Services;

import com.app.backend_fibonacci.Models.Fibonacci;

import java.util.List;

public interface FibonacciService {

    List<Fibonacci> findAll();

    String generateSerie();

    String generateSerieWithSelectedTime(String time);
}