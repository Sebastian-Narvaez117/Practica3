package com.example.ordenamiento;

import java.util.Random;

public class mergesort {

    public static void main(String[] args) {
        mergesort mergeSortExample = new mergesort();

        // Generar un arreglo de tamaño 10,000 con valores aleatorios
        int size = 10000;
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000); // Valores entre 0 y 99,999
        }

        
        long startTime = System.nanoTime(); 
        for (int i = 0; i < 10000; i++) { 
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nOrdenando el arreglo...");

        // Ordenar el arreglo
        mergeSortExample.sort(arr, 0, arr.length - 1);

        System.out.println("Arreglo ordenado (primeros 10 elementos):");
        for (int i = 0; i < 10000; i++) { // Mostrar solo los primeros 10 elementos
            System.out.print(arr[i] + " ");
        }
        long endTime = System.nanoTime(); 
        double duration = endTime - startTime; 
        System.out.println("\nTiempo de ejecución en segundos: " + duration / 1_000_000_000.0);
    }


    public void sort(int arr[], int left, int right) {
        if (left < right) {
            // Encuentra el punto medio del vector.
            int middle = (left + right) / 2;

            // Divide la primera y segunda mitad (llamada recursiva).
            sort(arr, left, middle);
            sort(arr, middle + 1, right);

            // Une las mitades.
            merge(arr, left, middle, right);
        }
    }

    public void merge(int arr[], int left, int middle, int right) {
        // Encuentra el tamaño de los sub-vectores para unirlos.
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Vectores temporales.
        int leftArray[] = new int[n1];
        int rightArray[] = new int[n2];

        // Copia los datos a los arrays temporales.
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[middle + j + 1];
        }
        /* Une los vectores temporales. */

        // Índices inicial del primer y segundo sub-vector.
        int i = 0, j = 0;

        // Índice inicial del sub-vector arr[].
        int k = left;

        // Ordenamiento.
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        } // Fin del while.

        /* Si quedan elementos por ordenar */
        // Copiar los elementos restantes de leftArray[].
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        // Copiar los elementos restantes de rightArray[].
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

}
