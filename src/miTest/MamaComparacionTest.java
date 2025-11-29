package miTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import datos.Mama;
import datos.Fecha;

public class MamaComparacionTest {
    @Test
    public void testComparacionPorId() {
        Mama m1 = new Mama("A123", "Ana", 30, new Fecha(1,1,1995));
        Mama m2 = new Mama("B456", "Bertha", 28, new Fecha(1,1,1997));
        Mama m3 = new Mama("A123", "Ana", 30, new Fecha(1,1,1995));

        assertTrue(m1.esMenor(m2), "m1 debe ser menor que m2 por id");
        assertTrue(m2.esMayor(m1), "m2 debe ser mayor que m1 por id");
        assertTrue(m1.esIgual(m3), "m1 debe ser igual a m3 por id");
        assertFalse(m1.esMayor(m2), "m1 no debe ser mayor que m2 por id");
        assertFalse(m2.esMenor(m1), "m2 no debe ser menor que m1 por id");
        assertFalse(m1.esIgual(m2), "m1 no debe ser igual a m2 por id");
    }
}
