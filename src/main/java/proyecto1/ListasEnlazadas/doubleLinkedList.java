package proyecto1.ListasEnlazadas;

import proyecto1.Enemigos.NaveEnemiga;

import java.io.Serializable;

//Declaracion lista doble
public class doubleLinkedList<T> implements Serializable,Lista<T>{

    //Atributos de la clase doubleLinkedList
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int largo;
    //Constructor lista doble
    public doubleLinkedList(){
        primero = null;
        ultimo = null;
        largo=0;
    }

    //Obtener largo de la lista Doble
    public int tamanoLista(){
        return largo;
    }

    //Agregar un nodo a la cabeza
    public void agregarPrimero(T dato) {
        if (this.primero == null) {
            this.primero = new Nodo<>();
            this.ultimo = this.primero;
        } else {
            Nodo<T> actual = new Nodo<>();
            actual.siguiente = this.primero;
            this.primero.anterior = actual;
            this.primero = actual;
        }
        largo++;
    }


    //Agregar un nodo a la cola
    public void agregarUltimo(T dato) {
        if (this.primero == null) {
            this.primero = new Nodo<>();
            this.ultimo = this.primero;
        } else {
            Nodo<T> actual = this.primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            Nodo<T> temporal = this.ultimo;
            Nodo<T> anterior = temporal;
            temporal.siguiente = new Nodo<>();
            temporal = temporal.siguiente;
            temporal.anterior = anterior;
            this.ultimo = temporal;
        }
        largo++;
    }

    //Borrar primero
    public void deletePrimero() {
        if (this.primero == null) {
            return;
        } else {
            Nodo<T> temporal = this.primero;
            this.primero = temporal.siguiente;
            this.primero.anterior = null;
            temporal.siguiente = null;
        }
        largo--;
    }

    //Borrar ultimo
    public void deleteUltimo() {
        if (this.primero == null) {
            return;
        } else {
            Nodo<T> temporal = this.ultimo;
            this.primero = temporal.anterior;
            this.ultimo.siguiente = null;
            temporal.siguiente = null;
        }
        largo--;
    }

    //Borrar dato en indice específico
    public void borrarDato(T t) {
        if (this.primero == null) {
            return;
        }
        Nodo<T> nodo = this.primero;
        if (nodo.getDato() == t) {
            this.primero = this.primero.siguiente;
        } else {
            boolean estaContenido = false;
            Nodo<T> temporal = this.primero;
            while (temporal.siguiente != null) {
                if (temporal.siguiente.getDato() == t) {
                    estaContenido = true;
                    Nodo<T> ante = temporal.siguiente.siguiente;
                    temporal.siguiente = temporal.siguiente.siguiente;
                    ante.anterior = ante.anterior.anterior;
                    break;
                } else {
                    temporal = temporal.siguiente;
                }
            }
        }largo--;
    }
    public T obtenerDato(int index){
        if (index >= largo){
            return null;
        }
        Nodo<T> actual= primero;
        for(int i = 0; i <= index; ++i){
            actual = actual.siguiente;
        }
        return actual.getDato();
    }

    public void bubbleSort(){
        Nodo<NaveEnemiga> actual = (Nodo<NaveEnemiga>) primero;
        Nodo<NaveEnemiga> index;
        NaveEnemiga temp;

        if (this.tamanoLista() == 0){
            System.out.println("Lista Vacia");
        }else{
            do{
                index = actual.siguiente;
                while(index != primero){
                    if (actual.valor.getVida() < index.valor.getVida()){
                        temp = actual.valor;
                        actual.valor = index.valor;
                        index.valor = temp;
                    }
                    index = index.siguiente;
                }
                actual = actual.siguiente;
            } while (actual != primero);
        }
    }
}





















