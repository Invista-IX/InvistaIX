package br.com.invistaix.InvistaIX.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculoIRServiceTest {

    @InjectMocks
    private CalculoIRService service;

    @Test
    void testIRPessoaFisica() {
        double ir = service.calcularIRPessoaFisica(3000.00, 0.00);
        double esperado = (3000 * 0.15) - 370.40;
        assertEquals(esperado, ir, 0.01);
    }

    @Test
    void testIRPessoaJuridica() {
        double ir = service.calcularIRPessoaJuridica(100000);
        double lucro = 100000 * 0.32;
        double esperado = (lucro * 0.15) + ((lucro - 20000) * 0.10) + (lucro * 0.09);
        assertEquals(esperado, ir, 0.01);
    }
}
