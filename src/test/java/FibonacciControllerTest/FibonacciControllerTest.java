package FibonacciControllerTest;

import com.app.backend_fibonacci.Controllers.FibonacciController;
import com.app.backend_fibonacci.Services.FibonacciServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(FibonacciController.class)
public class FibonacciControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FibonacciServiceImpl fibonacciServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testGenerateFibonacci() throws Exception {

    }


}
