package com.app.backend_fibonacci.Controllers;


import com.app.backend_fibonacci.Models.Fibonacci;
import com.app.backend_fibonacci.Services.FibonacciService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
        summary = "Obtiene la lista de series de Fibonacci generadas",
        description = "Devuelve una lista de objetos Fibonacci previamente generados y almacenados en la base de" +
            " datos mysql."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Fibonacci.class),
                examples = @ExampleObject(
                    value = """
                        [
                        {
                            "id": 1,
                            "fibonacciSerie": "[53243, 32906, 20337, 12569, 7768, 4801, 2967, 1834, 1133, 701, 432, 269,
                             163, 106, 57, 49, 8]",
                            "time": "15:49:08"
                        }
                        ]
                    """
                )
            )
        )
    })
    public List<Fibonacci> list(){
        return fibonacciService.findAll();
    }


    @GetMapping("/current-time")
    @Operation(
        summary = "Genera una serie de Fibonacci con la hora actual",
        description = "Calcula una serie de Fibonacci usando la hora actual del sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Serie de Fibonacci generada correctamente",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"success\": true, \"fibonacciSerie\": \"52, 32, 20, 12, 8\" }")
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"Error interno del servidor\" }")
            )
        )
    })
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
    @Operation(
        summary = "Genera una serie de Fibonacci basada en un tiempo específico",
        description = "Calcula una serie de Fibonacci usando un valor de tiempo " +
            "proporcionado en el cuerpo de la solicitud."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Serie de Fibonacci generada correctamente",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"success\": true, \"fibonacciSerie\": \"52, 32, 20, 12, 8\" }")
            )
        ),
    @ApiResponse(
        responseCode = "400",
        description = "Error en los datos de entrada",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"Hora no válida.\" }")
        )
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(value = "{ \"success\": false, \"message\": \"Error interno del servidor\" }")
        )
    )})
    public ResponseEntity<?> generateFibonacciWithTime(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Tiempo en formato HH:mm:ss para generar la serie de Fibonacci.",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(value = "{ \"time\": \"15:49:08\" }")
        )
        )@RequestBody Map<String, String> requestBody){
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