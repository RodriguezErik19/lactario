package miTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import datos.Fecha;

public class HijoEdadValidacionTest {
    @Test
    public void testEdadHijoMenorA24Meses() {
        // Suponiendo que hoy es 23/11/2025
        Fecha fechaActual = new Fecha(23, 11, 2025);
        Fecha fechaNacimiento = new Fecha(1, 12, 2023); // Hijo de casi 24 meses
        int edadMeses = fechaActual.calcularEdadMeses(fechaNacimiento);
        assertTrue(edadMeses < 24, "La edad del hijo debe ser menor a 24 meses");
    }

    @Test
    public void testEdadHijoNoValido24OMasMeses() {
        // Suponiendo que hoy es 23/11/2025
        Fecha fechaActual = new Fecha(23, 11, 2025);
        Fecha fechaNacimiento = new Fecha(1, 10, 2023); // Hijo de más de 24 meses
        int edadMeses = fechaActual.calcularEdadMeses(fechaNacimiento);
        assertFalse(edadMeses < 24, "La edad del hijo no debe ser válida si es 24 meses o más");
    }
}
