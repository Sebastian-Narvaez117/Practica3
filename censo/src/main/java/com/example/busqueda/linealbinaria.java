package com.example.busqueda;

import java.util.Random;

public class linealbinaria {
    public static void main(String[] args) {
        // Generar un arreglo desordenado de 10,000 elementos únicos
        int[] arr = generateRandomArray(25000);

       
        
        for (int i = 0; i < 25000; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Número a buscar
        int num = 4213;

        // Medir el tiempo de la búsqueda lineal
        long startTime = System.nanoTime(); // Tiempo inicial
        int result = linearSearch(arr, num);
        long endTime = System.nanoTime(); // Tiempo final

        
        long duration = (endTime - startTime) ;

        
        if (result != -1) {
            System.out.println("Número encontrado en el índice: " + result);
        } else {
            System.out.println("Número no encontrado en el arreglo.");
        }

        System.out.println("Tiempo de ejecución de la búsqueda lineal: " + duration + " nanosegundos");
    }

    // Generar un arreglo desordenado con números únicos
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1; // Rellenar con valores únicos del 1 al size
        }

        
        Random random = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }

   
    public static int linearSearch(int[] arr, int num) {
        // Recorre el arreglo desde el inicio hasta el final
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) { // Si encuentra el valor, devuelve su índice
                return i;
            }
        }
        return -1; // Devuelve -1 si el número no está en el arreglo
    }
}
