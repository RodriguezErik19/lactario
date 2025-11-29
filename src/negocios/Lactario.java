package negocios;

import java.util.HashMap;
import java.util.Map;

import listaDoble.ListaDoblementeEnlazada;
import listaDoble.PosicionIlegalException;
import arbolAVL.ArbolAVL;
import arbolAVL.Nodo;
import pilas.Pila;
import datos.Mama;
import datos.Fecha;
import datos.Hijo;
import datos.LectorArchivo;
import datos.EscritorArchivo;

/**
 * Administra las estructuras del lactario: árbol AVL de mamás, lista de hijos,
 * hash de acceso rápido y pila para rastrear el último ID de mamá capturado.
 */
public class Lactario {

    private ListaDoblementeEnlazada<Hijo> listaHijos;
    private ArbolAVL abo = new ArbolAVL();
    private Map<String, Mama> ht;
    private Pila<String> pilaUltimosIds;

    /**
     * Constructor de Lactario.
     * Debes inicializar las estructuras principales:
     * - ht como un nuevo HashMap
     * - listaHijos como una nueva ListaDoblementeEnlazada
     * - pilaUltimosIds como una nueva Pila
     * Además, debes cargar los datos desde archivo usando LectorArchivo.
     * Si la pila queda vacía pero hay mamás cargadas, apila los IDs existentes.
     */
    public Lactario() {
        // TODO: Implementar la inicialización de estructuras y carga de datos
    }

    public ArbolAVL getAbo() {
        return abo;
    }

    public Pila<String> getPilaUltimosIds() {
        return pilaUltimosIds;
    }

    public String getUltimoIdCapturado() {
        if (!pilaUltimosIds.esVacia()) return pilaUltimosIds.cima();
        return null;
    }

    /** Obtiene el objeto Mama correspondiente al último ID capturado, o null si no hay. */
    public Mama getUltimaMama() {
        String id = getUltimoIdCapturado();
        if (id != null) {
            return buscarMama(id);
        } else {
            return null;
        }
    }

    /**
     * Agrega una nueva mamá al sistema.
     * Debes insertar el objeto Mama en el árbol AVL y en el HashMap (ht).
     * Además, registra el id en la pila de últimos IDs.
     * Si la mamá ya existe (por id), no debe agregarla y debe retornar false.
     * @param id Identificador único
     * @param nombre Nombre de la mamá
     * @param edad Edad
     * @param fecha Fecha de nacimiento
     * @param hijos Lista de hijos
     * @return true si se agregó correctamente, false si ya existe
     */
    public boolean agregar_mama(String id, String nombre, int edad, Fecha fecha, ListaDoblementeEnlazada<Hijo> hijos) throws Exception {
        // TODO: Implementar la lógica para agregar una mamá al arbolAVL y hashMamas
        return false;
    }

    public Mama buscarMama(String id) {
        return ht.get(id);
    }

    /**
     * Busca un hijo en la lista de hijos por su id.
     * Debes recorrer la listaHijos y comparar el id de cada Hijo.
     * Si lo encuentras, retorna el objeto Hijo; si no, retorna null.
     * @param id Identificador único del hijo
     * @return El objeto Hijo si existe, null si no
     */
    public Hijo buscaHijo(String id) throws PosicionIlegalException {
        // TODO: Implementar la búsqueda de hijo en listaHijos
        return null;
    }

    public boolean agregar_hijos(String Id, String nombre, int edadMeses, int edadDias, Fecha fecha, String idMama, String nombreMama) {
        listaHijos.agregar(new Hijo(Id, nombre, edadMeses, edadDias, fecha, idMama));
        return true;
    }

    public ListaDoblementeEnlazada<Hijo> getHijos(String idMadre) throws PosicionIlegalException {
        ListaDoblementeEnlazada<Hijo> hijos = new ListaDoblementeEnlazada<>();
        for (int i = 0; i < listaHijos.getTamanio(); i++) {
            if (listaHijos.getValor(i).getIdMama().equals(idMadre)) {
                hijos.agregar(listaHijos.getValor(i));
            }
        }
        return hijos;
    }

    public ListaDoblementeEnlazada<Mama> buscarMamaPorNombreParcial(String nombre) throws Exception {
        ListaDoblementeEnlazada<Mama> mamas = new ListaDoblementeEnlazada<>();
        for (String clave : ht.keySet()) {
            Mama mama = ht.get(clave);
            if (mama.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                mamas.agregar(mama);
            }
        }
        return mamas;
    }

    /**
     * Elimina una mamá del sistema por su id.
     * Debes eliminar el objeto Mama tanto del árbol AVL como del HashMap (ht).
     * Si la mamá no existe, retorna false.
     * @param id Identificador único de la mamá
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean eliminar_mama(String id) throws Exception {
        // TODO: Implementar la lógica para eliminar una mamá del arbolAVL y hashMamas
        return false;
    }

    /**
     * Elimina un hijo de la lista por su id.
     * Debes recorrer listaHijos y, si encuentras el id, eliminar ese hijo.
     * Retorna true si se eliminó, false si no existe.
     * @param id Identificador único del hijo
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminar_hijo(String id) throws PosicionIlegalException {
        // TODO: Implementar la eliminación de hijo en listaHijos
        return false;
    }

    /**
     * Elimina todos los hijos asociados a una mamá por su id.
     * Debes recorrer la lista de hijos y eliminar aquellos cuyo idMama coincida.
     * Retorna true si se eliminó al menos un hijo, false si no tenía hijos.
     * @param idMama Identificador único de la mamá
     * @return true si se eliminaron hijos, false si no tenía
     */
    public boolean eliminar_hijo_mama(String idMama) throws PosicionIlegalException {
        // TODO: Implementar la lógica para eliminar todos los hijos de una mamá
        return false;
    }

    public Mama buscar_mama(String id, String nombre, int edad, Fecha fecha) throws Exception {
        Nodo n = abo.buscar(new Mama(id, nombre, edad, fecha));
        if (n != null) {
            return (Mama) n.getValor();
        }
        return null;
    }

    public void recorrer_arbolAVL() {
        System.out.println("\n Recorrido en inorden");
        abo.inordenIterativo();
        System.out.println("\n Recorrido en inorden");
        abo.inorden();
    }

    public ListaDoblementeEnlazada<Hijo> obtenerListaLactantes() {
        return listaHijos;
    }

    public ListaDoblementeEnlazada<Mama> obtenerListaMamas() {
        ListaDoblementeEnlazada<Mama> listaMamas = new ListaDoblementeEnlazada<>();
        obtenerMamasInorden(abo.getRaiz(), listaMamas);
        return listaMamas;
    }

    private void obtenerMamasInorden(Nodo nodo, ListaDoblementeEnlazada<Mama> lista) {
        if (nodo != null) {
            obtenerMamasInorden(nodo.getIzquierdo(), lista);
            lista.agregar((Mama) nodo.getValor());
            obtenerMamasInorden(nodo.getDerecho(), lista);
        }
    }

    public void guardarDatos() {
        EscritorArchivo escritor = new EscritorArchivo();
        ListaDoblementeEnlazada<Mama> mamas = obtenerListaMamas();
        escritor.guardarArchivos(mamas, listaHijos);
    }
}
