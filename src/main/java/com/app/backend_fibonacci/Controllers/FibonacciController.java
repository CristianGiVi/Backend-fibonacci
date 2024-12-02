package com.app.backend_fibonacci.Controllers;


import com.app.backend_fibonacci.Models.Fibonacci;
import com.app.backend_fibonacci.Services.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

    @GetMapping
    public List<Fibonacci> list(){
        return fibonacciService.findAll();
    }

    @GetMapping("/current-time")
    public ResponseEntity<?> generateFibonacci(){
        try{
            String fibonacciSerie = fibonacciService.generateSerie();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("fibonacciSerie", fibonacciSerie);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }


    /*
        {
            "time": "15:49:08"
        }
    */

    @PostMapping("/time")
    public ResponseEntity<?> generateFibonacciWithTime(@RequestBody Map<String, String> requestBody){
        try {
            String timeString = requestBody.get("time");
            String fibonacciSerie = fibonacciService.generateSerieWithSelectedTime(timeString);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("fibonacciSerie", fibonacciSerie);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        } catch (RuntimeException e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }
}