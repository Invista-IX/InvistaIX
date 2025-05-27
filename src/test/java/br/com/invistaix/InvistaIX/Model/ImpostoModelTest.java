package br.com.invistaix.InvistaIX.Model;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ImpostoModelTest {

    @Test
    void testaConstrutorComParametros() {
        LocalDate data = LocalDate.of(2024, 5, 25);
        ImpostoModel imposto = new ImpostoModel(1, 1500.0, data, 10L);

        assertEquals(1, imposto.getIdiptu());
        assertEquals(1500.0, imposto.getValor());
        assertEquals(data, imposto.getData());
        assertEquals(10L, imposto.getIdimovel());
    }

    @Test
    void testaSettersEGetters() {
        ImpostoModel imposto = new ImpostoModel();

        imposto.setIdiptu(2);
        imposto.setValor(999.99);
        LocalDate data = LocalDate.of(2023, 12, 15);
        imposto.setData(data);
        imposto.setIdimovel(20L);

        assertEquals(2, imposto.getIdiptu());
        assertEquals(999.99, imposto.getValor());
        assertEquals(data, imposto.getData());
        assertEquals(20L, imposto.getIdimovel());
    }
}

