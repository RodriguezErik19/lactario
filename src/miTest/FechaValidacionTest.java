package miTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import datos.Fecha;

public class FechaValidacionTest {
    @Test
    public void testEdadMadreCoincideConFechaNacimiento() {
        // Suponiendo que hoy es 23/11/2025
        Fecha fechaActual = new Fecha(23, 11, 2025);
        Fecha fechaNacimiento = new Fecha(23, 11, 2000);
        int edadCapturada = 25;
        int edadCalculada = fechaActual.calcularEdadAnios(fechaNacimiento);
        assertEquals(edadCapturada, edadCalculada,
            "La edad capturada debe coincidir con la calculada a partir de la fecha de nacimiento");
    }
}
