package miTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import datos.Mama;
import datos.Fecha;

public class MamaInicialesTest {
    @Test
    public void testInicialesNombreCompleto() {
        Mama m1 = new Mama("1", "maria lourdes armenta lindoro", 30, new Fecha(1,1,1995));
        assertEquals("MLAL", m1.getIniciales(), "Las iniciales deben ser MLAL");

        Mama m2 = new Mama("2", "Juana Perez", 28, new Fecha(1,1,1997));
        assertEquals("JP", m2.getIniciales(), "Las iniciales deben ser JP");

        Mama m3 = new Mama("3", "Ema", 25, new Fecha(1,1,2000));
        assertEquals("E", m3.getIniciales(), "Las iniciales deben ser E");
    }
}
