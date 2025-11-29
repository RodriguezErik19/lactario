package pilas;
public class Pila <T>{
    //Atributo cabeza apunta al tope de la pila
    private Nodo<T> cabeza;
    //Almacena el total de elementos de la pila
    private int tamanio;
    //constructor de defecto
    public Pila(){
        cabeza = null;
        tamanio = 0;
    }
    //Devuelde el total de elementos d ela pila
    public int getTamanio(){
        return tamanio;
    }
    //Verifica si la pila esta vacia
    public boolean esVacia(){
        return (cabeza == null);
    }
    public void apilar(T valor){
        //Crear un nuevo Nodo
        Nodo<T> nuevo = new Nodo<T>();
        //fijar el valor dentro de nodo
        nuevo.setValor(valor);
        if (esVacia()){
            //cabeza apunta al nuevo nodo
            cabeza =nuevo;
        }else{
            //se enlaza el campo siguiente de nuevo con la cabeza
            nuevo.setSiguiente(cabeza);
            //la nueva cabeza de la pila pasa a ser nuevo
            cabeza = nuevo;

        }
        //incrementar el tamaño porque hay un nuevo elementp en la pila
        tamanio++;

    }
    //Eliminar un elemento de la pila
    public void retirar(){
        if (!esVacia()){
            cabeza = cabeza.getSiguiente();
            tamanio--;
        }
    }
    //Devuelve el elemento almacenado en el tope de la cima
    public T cima(){
        if (!esVacia())
            return cabeza.getValor();
        else
           return null;

    }


    
}