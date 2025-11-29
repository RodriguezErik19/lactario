package colas;
public class Nodo <T>{
   //Atributo valor de tipo T. Almacena la referencia al objeto que se
    // guarda en el Nodo
    private T valor;
    //Referencia al siguient Nodo Enlazado
    private Nodo<T> siguiente;
    //Constructor por defectp
    public Nodo(){
        valor = null;
        siguiente = null;
    }
    //Devuelve el valor
    public T getValor(){
        return valor;

    }
    //Modifica el valor
    public void setValor(T valor){
        this.valor = valor;
    }
    //Devuelve el atributo siguiente
    public Nodo<T> getSiguiente(){
        return siguiente;
    }
    //Modifica el atributo siguiente
    public void setSiguiente(Nodo<T> siguiente){
        this.siguiente = siguiente;
    }
}   