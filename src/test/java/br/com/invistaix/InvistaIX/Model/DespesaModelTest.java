package br.com.invistaix.InvistaIX.Model;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DespesaModelTest {

    @Test
    void testGettersAndSetters() {
        DespesaModel despesa = new DespesaModel();

        despesa.setId(10L);
        despesa.setManutencao(150.5);
        despesa.setDespesaAvulsa(300.75);
        despesa.setLuz(80.0);
        despesa.setAgua(60.0);
        LocalDate data = LocalDate.of(2025, 5, 22);
        despesa.setData(data);
        despesa.setIdImovel(5L);

        assertEquals(10L, despesa.getId());
        assertEquals(150.5, despesa.getManutencao());
        assertEquals(300.75, despesa.getDespesaAvulsa());
        assertEquals(80.0, despesa.getLuz());
        assertEquals(60.0, despesa.getAgua());
        assertEquals(data, despesa.getData());
        assertEquals(5L, despesa.getIdImovel());
    }

    @Test
    void testConstructorWithArgs() {
        LocalDate data = LocalDate.of(2025, 5, 22);
        DespesaModel despesa = new DespesaModel(100.0, 200.0, data, 1L);

        assertEquals(100.0, despesa.getManutencao());
        assertEquals(200.0, despesa.getDespesaAvulsa());
        assertEquals(data, despesa.getData());
        assertEquals(1L, despesa.getIdImovel());

        assertNull(despesa.getLuz());
        assertNull(despesa.getAgua());
    }
}
