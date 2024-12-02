package com.app.backend_fibonacci.Services;


import com.app.backend_fibonacci.Models.Fibonacci;
import com.app.backend_fibonacci.Repositories.FibonacciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FibonacciServiceImpl implements FibonacciService{

    @Autowired
    private FibonacciRepository fibonacciRepository;

    @Override
    public List<Fibonacci> findAll() {
        return (List<Fibonacci>) fibonacciRepository.findAll();
    }

    @Override
    public String generateSerie() {
        try {
            LocalTime currentTime = LocalTime.now();
            List<Integer> fibonacciList = getFibonacciList(currentTime);
            Collections.reverse(fibonacciList);
            Fibonacci fibonacci = new Fibonacci();
            fibonacci.setTime(currentTime);
            fibonacci.setFibonacciSerie(fibonacciList.toString());
            fibonacciRepository.save(fibonacci);

            return fibonacciList.toString();
        } catch (Exception e){
            throw new RuntimeException("Ocurrió un error interno al generar la serie de Fibonacci.");
        }
    }

    @Override
    public String generateSerieWithSelectedTime(String inputTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            LocalTime time = LocalTime.parse(inputTime, formatter);
            List<Integer> fibonacciList = getFibonacciList(time);
            Collections.reverse(fibonacciList);

            Fibonacci fibonacci = new Fibonacci();
            fibonacci.setTime(time);
            fibonacci.setFibonacciSerie(fibonacciList.toString());
            fibonacciRepository.save(fibonacci);

            return fibonacciList.toString();

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato del tiempo es inválido. Debe ser HH:mm:ss.");
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error interno al generar la serie de Fibonacci.");
        }
    }

    private static List<Integer> getFibonacciList(LocalTime time) {
        int hours = time.getHour();
        int minutes = time.getMinute();
        int seconds = time.getSecond();

        List<Integer> fibonacciList = new ArrayList<>();

        fibonacciList.add(seconds);
        fibonacciList.add(minutes);

        for (int i = 0; i < hours; i++) {
            Integer nextNumber = fibonacciList.get(i) + fibonacciList.get(i + 1);
            fibonacciList.add(nextNumber);
        }
        return fibonacciList;
    }
}
