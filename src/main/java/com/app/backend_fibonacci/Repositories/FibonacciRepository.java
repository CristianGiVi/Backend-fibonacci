package com.app.backend_fibonacci.Repositories;

import com.app.backend_fibonacci.Models.Fibonacci;
import org.springframework.data.repository.CrudRepository;

public interface FibonacciRepository extends CrudRepository<Fibonacci, Long> {
}
