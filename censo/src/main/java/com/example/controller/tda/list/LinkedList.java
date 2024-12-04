package com.example.controller.tda.list;

import java.lang.reflect.Method;

import com.example.controller.exception.ListEmptyException;

public class LinkedList <E> {
    private Node <E> header;
    private Node <E> last;
    private Integer size ;
    public static Integer ASC = 1;
    public static Integer DESC = 0;

    public LinkedList(){
        this.header = null;
        this.last = null;
        this.size = 0;
    }


      

    public Boolean isEmpty(){
        return (this.header == null || this.size == 0);
            
    }

    public void addHeader(E dato) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(dato);
            this.header = help;
            this.last = help; // Inicializa last cuando se agrega el primer nodo
        } else {
            help = new Node<>(dato);
            help.setNext(this.header);
            this.header = help;
        }
        this.size++;
    }




    public void addLast(E info){
        Node<E> help;
        if(isEmpty()){
            addHeader(info);
        }else{
            help = new Node<>(info, null);
            last.setNext(help);
            last = help;
            this.size++;
        }
    }


    public void add(E info){
        addLast(info);
    }

    public Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if(isEmpty()){
            throw new ListEmptyException("Error, List empty");
        } else if(index.intValue()< 0 || index.intValue() >= this.size){
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if(index.intValue()== 0){
            return header;
        } else if(index.intValue()==(this.size -1)){
            return last;
        } else{
            Node<E> search = header;
            int cont = 0;
            while(cont < index.intValue()){
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    public E getFirst()throws ListEmptyException {
        if(isEmpty()){
            throw new ListEmptyException("Error, lista vacia");
        } 
         return header.getInfo();
    }


    public E getLast() throws ListEmptyException {
        if(isEmpty()){
            throw new ListEmptyException("Error, lista vacia");
        } 
         return last.getInfo();
    }


    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if(isEmpty()){
            throw new ListEmptyException("Error, List empity");
        } else if(index.intValue() < 0 || index.intValue() >= this.size.intValue()){
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if(index.intValue() == 0){
            return header.getInfo();
        } else if(index.intValue() == (this.size - 1)){
            return last.getInfo();
        } else{
            Node<E> search = header ;
            int cont = 0;
            while(cont < index.intValue()){ 
                cont++;
                search = search.getNext();
            }
            return search.getInfo();
        }
    }

    public void add(E info , Integer index ) throws ListEmptyException , IndexOutOfBoundsException {
        if(isEmpty()|| index.intValue()==  0){
            addHeader(info);
        }else if(index.intValue() == this.size.intValue()){
            addLast(info);
        }else{
            Node<E> search_preview = getNode(index-1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info , search);
            search_preview.setNext(help);
            this.size++;
        }
    }

      
    public void reset(){
        this.header = null;
        this.last = null; 
        this.size = 0;
    }


    public void update(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista está vacía, no se puede actualizar.");
        } 
        if (index < 0 || index >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites.");
        }
    
        // Caso especial: actualiza la cabeza de la lista
        if (index.intValue() == 0) {
            this.header.setInfo(info); 
        } 
        // Caso especial: actualiza el último nodo
        else if (index.intValue() == this.size.intValue() - 1) {
            this.last.setInfo(info); 
        } 
        // Caso general: actualiza un nodo intermedio
        else {
            Node<E> targetNode = getNode(index); 
            targetNode.setInfo(info); 
        }
    }



    public E deleteHeader() throws ListEmptyException{
        if(isEmpty()){
            throw new ListEmptyException("Error, lista vacia");
        } else {
            E info = header.getInfo();
            Node<E> aux = header.getNext();
            header = aux;
        if(size.intValue() == 1){
            last = null;
        }
            this.size--;
            return info;
        }
    }


    public E deleteLast() throws ListEmptyException{
        if(isEmpty()){
            throw new ListEmptyException("Error, lista vacia");
        } else {
            E element = last.getInfo();
            Node<E> aux = getNode(size - 2);
            if(aux == null){
                last = null;
                if( size == 2){
                    last = header;
                } else{
                    header = null;
                }
            } else{
                last = null;
                header = aux;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }




    public E delete(Integer post) throws ListEmptyException{
        if(isEmpty()){
            throw new ListEmptyException("Error, lista vacia");
        } else if(post.intValue() < 0 || post.intValue() >= this.size.intValue()){
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if(post.intValue() == 0){
            return deleteHeader();
        } else if(post.intValue() == (this.size - 1)){
            return deleteLast();
        } else{
            Node<E> search_preview = getNode(post - 1);
            Node<E> search = getNode(post);
            search_preview.setNext(search.getNext());
            this.size--;
            return search.getInfo();
        }
    }



  
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("List Data \n");
        try{

            Node<E> help = header;
            while(help != null){
                sb.append(help.getInfo()).append("->");
                help = help.getNext();
            }
        } catch(Exception e){
            sb.append(e.getMessage());
        }

        return sb.toString();
    }


    public Integer getSize() {
        return this.size;
    }



    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] matriz = null;
        if (!isEmpty()) {
            @SuppressWarnings("rawtypes")
            Class clazz = header.getInfo().getClass();
            matriz = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            Node<E> aux = header;
            for (int i = 0; i < this.size; i++) {
                matriz[i] = aux.getInfo();
                aux = aux.getNext();
            }
        }
        return matriz;
    }


    public LinkedList<E> toList(E[] matriz){
        reset();
        for(int i = 0; i < matriz.length; i++){
            this.add(matriz[i]);
        }
        return this;
    }   


    public LinkedList<E> order(String attribute, Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Object) {
                E[] lista = this.toArray();
                reset();
                for (int i = 1; i < lista.length; i++) {
                    E aux = lista[i]; // valor a ordenar
                    int j = i - 1; // indice anterior
                    while (j >= 0 && attribute_compare(attribute,lista[j], aux, type)) {
                        lista[j + 1] = lista[j--]; // desplazar los elementos hacia los derechos
                    }
                    lista[j + 1] = aux; // insertar el elemento en su lugar
                }
                this.toList(lista);
            } 
        }
        return this;
    }


    



    private boolean attribute_compare(String attribute, E a, E b, Integer type)throws Exception{
        return compare(exist_attribute(a, attribute), exist_attribute(b, attribute), type);
    }


    

    private Object exist_attribute(E a, String attribute) throws Exception {
        Method method = null;
        attribute = attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
        attribute = "get" + attribute;
        for (Method aux : a.getClass().getMethods()) {
            if (aux.getName().contains(attribute)) {
                method = aux;
                break;
            }
        }
        if (method == null) {
            for (Method aux : a.getClass().getSuperclass().getMethods()) {
                if (aux.getName().contains(attribute)) {
                    method = aux;
                    break;
                }
            }
        }
        if(method != null){
            return method.invoke(a);
        }
        return null;
    }


    private Boolean compare(Object a, Object b, Integer type) {
        switch (type) {
            case 0:
                if (a instanceof Number) {
                    Number a1 = (Number) a;
                    Number b1 = (Number) b;
                    return a1.doubleValue() > b1.doubleValue();
                } else {
                    return (a.toString()).compareTo(b.toString()) > 0;
                }


            default:
                if (a instanceof Number) {
                    Number a1 = (Number) a;
                    Number b1 = (Number) b;
                    return a1.doubleValue() < b1.doubleValue();
                } else {
                    return (a.toString()).compareTo(b.toString()) < 0;

                }
        }
    }





    

    
    //IMPLEMENTACION DE QUICKSORT

    public LinkedList<E> Quicksort(String attribute, Integer type) throws Exception {
        if (!isEmpty()) {
            E[] lista = this.toArray(); // Convierte la lista a un array para facilitar el trabajo
            quickSort(lista, 0, lista.length - 1, attribute, type);
            this.toList(lista); // Convierte el array ordenado de nuevo a la lista
        }
        return this;
    }
    
    private void quickSort(E[] vec, int inicio, int fin, String attribute, Integer type) throws Exception {
        if (inicio >= fin) return;
    
        // Usa el primer elemento como pivote
        E pivote = vec[inicio];
        int elemIzq = inicio + 1;
        int elemDer = fin;
    
        // Proceso de partición
        while (elemIzq <= elemDer) {
            // Encuentra elementos mayores o menores al pivote según el orden
            while (elemIzq <= fin && !attribute_compare(attribute, pivote, vec[elemIzq], type)) {
                elemIzq++;
            }
            while (elemDer > inicio && attribute_compare(attribute, pivote, vec[elemDer], type)) {
                elemDer--;
            }
            // Intercambia elementos si están desordenados
            if (elemIzq < elemDer) {
                E temp = vec[elemIzq];
                vec[elemIzq] = vec[elemDer];
                vec[elemDer] = temp;
            }
        }
    
        // Coloca el pivote en su posición correcta
        if (elemDer > inicio) {
            E temp = vec[inicio];
            vec[inicio] = vec[elemDer];
            vec[elemDer] = temp;
        }
    
        // Ordena las sublistas recursivamente
        quickSort(vec, inicio, elemDer - 1, attribute, type);
        quickSort(vec, elemDer + 1, fin, attribute, type);
    }
    








//IMPLEMENTACION DE MERGESORT


public LinkedList<E> mergeSort(String attribute, Integer type) throws Exception {
    if (!isEmpty()) {
        E[] lista = this.toArray(); // Convierte la lista enlazada a un array para facilitar el manejo
        mergeSortRecursive(lista, 0, lista.length - 1, attribute, type);
        this.toList(lista); // Convierte el array ordenado de vuelta a la lista enlazada
    }
    return this;
}

private void mergeSortRecursive(E[] arr, int left, int right, String attribute, Integer type) throws Exception {
    if (left < right) {
        int middle = (left + right) / 2;

        // Divide las mitades
        mergeSortRecursive(arr, left, middle, attribute, type);
        mergeSortRecursive(arr, middle + 1, right, attribute, type);

        // Une las mitades
        merge(arr, left, middle, right, attribute, type);
    }
}

private void merge(E[] arr, int left, int middle, int right, String attribute, Integer type) throws Exception {
    // Tamaños de los sub-arrays
    int n1 = middle - left + 1;
    int n2 = right - middle;

    // Arrays temporales
    @SuppressWarnings("unchecked")
    E[] leftArray = (E[]) new Object[n1];
    @SuppressWarnings("unchecked")
    E[] rightArray = (E[]) new Object[n2];

    // Copia los datos a los arrays temporales
    System.arraycopy(arr, left, leftArray, 0, n1);
    System.arraycopy(arr, middle + 1, rightArray, 0, n2);

    // Índices iniciales
    int i = 0, j = 0, k = left;

    // Fusiona los arrays temporales ordenados
    while (i < n1 && j < n2) {
        if (!attribute_compare(attribute, leftArray[i], rightArray[j], type)) {
            arr[k] = leftArray[i];
            i++;
        } else {
            arr[k] = rightArray[j];
            j++;
        }
        k++;
    }

    // Copia los elementos restantes de leftArray, si los hay
    while (i < n1) {
        arr[k] = leftArray[i];
        i++;
        k++;
    }

    // Copia los elementos restantes de rightArray, si los hay
    while (j < n2) {
        arr[k] = rightArray[j];
        j++;
        k++;
    }
}



//IMPLEMENTACION DE SHELLSORT

public LinkedList<E> shellSort(String attribute, Integer type) throws Exception {
    if (!isEmpty()) {
        E[] lista = this.toArray(); // Convierte la lista a un array para trabajar más fácilmente
        final int N = lista.length;
        int k = N / 2; // Inicializa el intervalo (gap)

        while (k >= 1) { // Mientras el intervalo sea válido
            for (int i = 0; i < k; i++) {
                // Recorre cada subgrupo creado por el intervalo
                for (int j = k + i; j < N; j += k) {
                    E v = lista[j];
                    int n = j - k;

                    // Compara los elementos en el subgrupo según el atributo
                    while (n >= 0 && attribute_compare(attribute, lista[n], v, type)) {
                        lista[n + k] = lista[n];
                        n -= k;
                    }
                    lista[n + k] = v;
                }
            }
            k /= 2; // Reduce el intervalo
        }
        this.toList(lista); // Convierte el array ordenado de nuevo a una lista
    }
    return this;
}





    

    
} 


