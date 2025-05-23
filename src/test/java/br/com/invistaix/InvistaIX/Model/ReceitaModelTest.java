package br.com.invistaix.InvistaIX.Model;

import br.com.invistaix.InvistaIX.model.ReceitaModel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ReceitaModelTest {

    @Test
    void testGettersAndSetters() {
        ReceitaModel receita = new ReceitaModel();

        receita.setId(10L);
        receita.setAluguel(1500.0);
        receita.setReceitaAvulsa(300.75);
        LocalDate data = LocalDate.of(2025, 5, 22);
        receita.setData(data);
        receita.setIdImovel(5L);

        assertEquals(10L, receita.getId());
        assertEquals(1500.0, receita.getAluguel());
        assertEquals(300.75, receita.getReceitaAvulsa());
        assertEquals(data, receita.getData());
        assertEquals(5L, receita.getIdImovel());
    }

    @Test
    void testConstructorWithArgs() {
        LocalDate data = LocalDate.of(2025, 5, 22);
        ReceitaModel receita = new ReceitaModel(1500.0, 300.75, data, 1L);

        assertEquals(1500.0, receita.getAluguel());
        assertEquals(300.75, receita.getReceitaAvulsa());
        assertEquals(data, receita.getData());
        assertEquals(1L, receita.getIdImovel());
    }
}
