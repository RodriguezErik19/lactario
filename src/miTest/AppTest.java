package miTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import arbolAVL.*;
import datos.Fecha;
import datos.Hijo;
import datos.Mama;
import listaDoble.ListaDoblementeEnlazada;
import negocios.Lactario;


class AppTest {
    
    @Test
    public void testInsertar() throws Exception {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(new Mama("001", "Bertha", 20, new Fecha("01/01/2004")));
        arbol.insertar(new Mama("005","Zulema",25,new Fecha("30/04/1999")));
        arbol.insertar(new Mama("003","Ana",18,new Fecha("06/05/2006")));

        String mama = new Mama("003","Ana",18,new Fecha("06/05/2006")).getNombre();
        Mama raizMama = (Mama) (arbol.getRaiz().getValor());

        assertEquals(mama, raizMama.getNombre(), "El valor de la raíz debería ser 003 después de las inserciones");       
    }
    
    @Test
    public void testEliminar()  throws Exception {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(new Mama("001", "Bertha", 20, new Fecha("01/01/2004")));
        arbol.insertar(new Mama("003","Ana",18,new Fecha("06/05/2006")));
        arbol.insertar(new Mama("005","Zulema",25,new Fecha("30/04/1999")));

        arbol.eliminar(new Mama("003","Ana",18,new Fecha("06/05/2006")));

        String mama = new Mama("001", "Bertha", 20, new Fecha("01/01/2004")).getNombre();
        Mama raizMama = (Mama) (arbol.getRaiz().getValor());

        assertEquals(mama, raizMama.getNombre(), "El valor de la raíz debería ser 003 después de la supreción de Ana");
    }
    
    @Test
    public void testBuscar() throws Exception {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(new Mama("001", "Bertha", 20, new Fecha("01/01/2004")));
        arbol.insertar(new Mama("003","Ana",18,new Fecha("06/05/2006")));
        arbol.insertar(new Mama("005","Zulema",25,new Fecha("30/04/1999")));

        Nodo nodo = arbol.buscar(new Mama("003","Ana",18,new Fecha("06/05/2006")));
        assertNotNull(nodo, "La búsqueda de un valor existente debería devolver un nodo");

        nodo = arbol.buscar(new Mama("001", "Bertha", 20, new Fecha("01/01/2004")));
        assertNotNull(nodo, "La búsqueda de un valor existente debería devolver un nodo");

        String mama = new Mama("001", "Bertha", 20, new Fecha("01/01/2004")).getNombre();
        Nodo NodoBuscado = arbol.buscar(new Mama("001", "Bertha", 20, new Fecha("01/01/2004")));
        String mamaBuscada = ((Mama) NodoBuscado.getValor()).getNombre();
        assertEquals(mama, mamaBuscada, "El valor del nodo buscado debería ser Bertha");

        nodo = arbol.buscar(new Mama("006","Zulema",25,new Fecha("30/04/1999")));
        assertNull(nodo,"La búsqueda de un valor no existente debería devolver null");

    }
     @Test
    public void testAgregarMama() throws Exception {
        Lactario lactario = new Lactario();
        String id = "123";
        String nombre = "Ana";
        int edad = 30;
        Fecha fecha = new Fecha(1, 1, 2000);
        ListaDoblementeEnlazada<Hijo> hijos = new ListaDoblementeEnlazada<>();

        // Verificar que la mamá no existe antes de agregarla
        Mama mama = lactario.buscarMama(id);
        assertNull(mama, "La mamá no debería existir antes de agregarla");

        // Agregar la mamá
        lactario.agregar_mama(id, nombre, edad, fecha, hijos);

        // Verificar que la mamá se agregó correctamente
        mama = lactario.buscarMama(id);
        assertNotNull(mama, "La mamá debería existir después de agregarla");
        assertEquals(nombre, mama.getNombre(), "El nombre de la mamá debería ser " + nombre);
        assertEquals(edad, mama.getEdad(), "La edad de la mamá debería ser " + edad);
        assertEquals(fecha, mama.getFechaNacimiento(), "La fecha de la mamá debería ser " + fecha.toString());
        assertEquals(hijos, mama.getHijos(), "Los hijos de la mamá deberían ser los mismos que se agregaron");
    }

    @Test
    public void testBuscarMamaPorNombreParcial()  throws Exception{
        Lactario lactario = new Lactario();
        lactario.agregar_mama("123", "Ana", 30, new Fecha(1, 1, 2000), new ListaDoblementeEnlazada<>());
        lactario.agregar_mama("456", "Anabel", 25, new Fecha(1, 1, 2005), new ListaDoblementeEnlazada<>());
        lactario.agregar_mama("789", "Maria", 35, new Fecha(1, 1, 1995), new ListaDoblementeEnlazada<>());

        ListaDoblementeEnlazada<Mama> resultado = lactario.buscarMamaPorNombreParcial("Mar");


        assertTrue(resultado.getTamanio() == 1, "Debería haber 2 mamás con 'Ana' en su nombre");
        assertTrue(resultado.getValor(0).getNombre().contains("Mar"), "El nombre de la mamá debería contener 'Maria'");
    }
    @Test
    public void testEliminarMama() throws Exception {
        Lactario lactario = new Lactario();
        String id = "123";
        lactario.agregar_mama(id, "Ana", 30, new Fecha(1, 1, 2000), new ListaDoblementeEnlazada<>());

        // Verificar que la mamá existe antes de eliminarla
        Mama mama = lactario.buscarMama(id);
        assertNotNull(mama, "La mamá debería existir antes de eliminarla");

        // Eliminar la mamá
        boolean resultado = lactario.eliminar_mama(id);
        assertTrue(resultado, "Eliminar mamá debería devolver true cuando la mamá existe");

        // Verificar que la mamá se eliminó correctamente
        mama = lactario.buscarMama(id);
        assertNull(mama, "La mamá no debería existir después de eliminarla");
    }
    

	
}
