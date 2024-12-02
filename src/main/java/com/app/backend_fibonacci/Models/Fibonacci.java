package com.app.backend_fibonacci.Models;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name="fibonacci")
public class Fibonacci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fibonacciSerie;

    private LocalTime time;

    public Fibonacci() {
    }

    public Fibonacci(Long id, LocalTime time, String fibonacciSerie) {
        this.id = id;
        this.time = time;
        this.fibonacciSerie = fibonacciSerie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFibonacciSerie() {
        return fibonacciSerie;
    }

    public void setFibonacciSerie(String fibonacciSerie) {
        this.fibonacciSerie = fibonacciSerie;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
