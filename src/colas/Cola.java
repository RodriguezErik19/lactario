package colas;
public class Cola<T>{
    //Atributo cabeza, que apunta al primer elemento de la cola
    private Nodo<T> cabeza;
    //Atributo cola, que apunta al último elemento de la cola
    private Nodo<T> cola;
    //Almacena el total de elementos de la cola
    private int tamanio;
    //Constructor defecto
    public Cola(){
        cabeza = null;
        cola = null;
        tamanio = 0;
    }
    //Devuelva el total de elementos de la cola
    public int getTananio(){
        return tamanio;
    }
    //verificar si la cola esta vacia
    public boolean esVacia(){
        return (cabeza == null);
    }
    //Encolar un elemento nuevo
    public void encolar(T valor){
        //crear un nuevo nodo
        Nodo<T> nuevo = new Nodo<T>();
        //fijal el valor dentro del Nodo
        nuevo.setValor(valor);
        if (esVacia()){
            //cabeza y cola apunt al nodo nuevo
            cabeza = nuevo;
            cola = nuevo;
        }else{
            //se enlaza el campo siguiente de la cola con el nuevo
            //elemento
            cola.setSiguiente(nuevo);
            //la nueva cola pasa a ser nuevo
            cola = nuevo;
        }
        //incrementa el tamanio porque hay un nuevo elemento en la cola
        tamanio++;
    }
    //Elimina el primer elementp de la cola
    public void desencolar(){
        //si no esta vacia
        if (!esVacia()){
            //verificar si hay un solo elemento en la cola
            if(cabeza == cola){
                cabeza = null;
                cola=null;
            }else {
                //se elimina el primer elemento de la cola
                //desplazando la cabeza al suguiente nodo
                cabeza = cabeza.getSiguiente();
            }
            //se disminuye el total de elementos
            tamanio--;
        }

    }
    //Devuelve el primer elemento de la cola
    public T frente(){
        if (!esVacia())
            return cabeza.getValor();
        else 
           return null;
        
    }
}
