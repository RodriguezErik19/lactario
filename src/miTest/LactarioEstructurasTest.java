package miTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import negocios.Lactario;
import datos.Fecha;
import datos.Hijo;
import listaDoble.ListaDoblementeEnlazada;
import pilas.Pila;

/**
 * Pruebas enfocadas en el uso de las estructuras HashMap y Pila dentro de Lactario.
 */
public class LactarioEstructurasTest {

    @Test
    public void testHashMapAddRetrieveDuplicateAndRemove() throws Exception {
        Lactario lactario = new Lactario();
        String id = "HM100"; // Se asume no existe en data/mamas.txt
        ListaDoblementeEnlazada<Hijo> hijos = new ListaDoblementeEnlazada<>();

        // Antes de agregar no debe existir
        assertNull(lactario.buscarMama(id), "La mamá no debe existir antes de agregarla");

        // Agregar
        boolean agregado = lactario.agregar_mama(id, "Prueba Hash", 30, new Fecha(1,1,1995), hijos);
        assertTrue(agregado, "Debería agregar la mamá en HashMap y AVL");
        assertNotNull(lactario.buscarMama(id), "La mamá debe existir tras el agregado");

        // Intentar agregar duplicado
        boolean agregadoDuplicado = lactario.agregar_mama(id, "Prueba Hash", 30, new Fecha(1,1,1995), hijos);
        assertFalse(agregadoDuplicado, "No debe permitir agregar un ID duplicado al HashMap");

        // Eliminar
        boolean eliminado = lactario.eliminar_mama(id);
        assertTrue(eliminado, "Debe eliminar la mamá existente");
        assertNull(lactario.buscarMama(id), "La mamá no debe existir tras eliminación en HashMap y AVL");
    }

    @Test
    public void testPilaUltimoIdActualizado() throws Exception {
        Lactario lactario = new Lactario();
        String idInicial = lactario.getUltimoIdCapturado(); // Puede ser null si carga vacía

        // Agregar nuevos IDs secuencialmente
        lactario.agregar_mama("PILA200", "M1", 28, new Fecha(1,1,1997), new ListaDoblementeEnlazada<>());
        assertEquals("PILA200", lactario.getUltimoIdCapturado(), "La pila debe reflejar el último ID capturado");

        lactario.agregar_mama("PILA201", "M2", 29, new Fecha(1,1,1996), new ListaDoblementeEnlazada<>());
        assertEquals("PILA201", lactario.getUltimoIdCapturado(), "La pila debe actualizarse con el nuevo último ID");

        // Comprobar que la pila conserva orden (cima es el último)
        Pila<String> pila = lactario.getPilaUltimosIds();
        assertEquals("PILA201", pila.cima(), "La cima de la pila debe ser el último ID agregado");

        // Eliminar el último ID agregado NO modifica la pila (diseño actual)
        lactario.eliminar_mama("PILA201");
        assertEquals("PILA201", pila.cima(), "La pila conserva histórico incluso tras eliminar la mamá");

        // Eliminar el penúltimo tampoco debe modificar la cima
        lactario.eliminar_mama("PILA200");
        assertEquals("PILA201", pila.cima(), "La cima permanece como último ID insertado");

        // Si había un ID inicial previo cargado desde archivo, no se verifica su posición
        // porque la pila no expone métodos de iteración; solo validamos la cima.
        if (idInicial != null) {
            assertNotEquals(idInicial, pila.cima(), "La cima debe ser el último ID nuevo, no el inicial cargado");
        }
    }
}
