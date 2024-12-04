package com.example.busqueda;

import java.util.Arrays;
import java.util.Random;

public class binaria {
    public static void main(String[] args) {
        // Generar un arreglo desordenado de 25,000 elementos únicos
        int[] arr = generateRandomArray(25000);

        // Ordenar el arreglo
        Arrays.sort(arr);

        // Imprimir el arreglo ordenado
        for (int i = 0; i < 25000; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Número a buscar
        int num = 25000;

        // Medir el tiempo de la búsqueda binaria
        long startTime = System.nanoTime(); // Tiempo inicial
        int result = binarySearchIterative(arr, num);
        long endTime = System.nanoTime(); // Tiempo final

        long duration = (endTime - startTime);

        if (result != -1) {
            System.out.println("Número encontrado en el índice: " + result);
        } else {
            System.out.println("Número no encontrado en el arreglo.");
        }

        System.out.println("Tiempo de ejecución de la búsqueda binaria: " + duration + " nanosegundos");
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

    // Implementación de búsqueda binaria iterativa
    public static int binarySearchIterative(int[] arr, int num) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == num) {
                return mid; // Devuelve el índice si se encuentra el número
            } else if (arr[mid] < num) {
                start = mid + 1; // Buscar en la mitad derecha
            } else {
                end = mid - 1; // Buscar en la mitad izquierda
            }
        }

        return -1; // Devuelve -1 si no se encuentra el número
    }
}
