package com.example.ordenamiento;

import java.util.Random;

public class shellsort {

    public static void main(String[] args) {
        // Generar un arreglo de tamaño 10,000 con valores aleatorios
        final int SIZE = 25000;
        int vec[] = new int[SIZE]; 
        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            vec[i] = random.nextInt(25000); // Valores entre 0 y 99,999 
        }

        System.out.println("Arreglo original:");
        imprimirPrimerosElementos(vec, 25000);
        long startTime = System.nanoTime(); 
        
        System.out.println("\nOrdenando el arreglo...");
        vec = ordenacionShell(vec);

        long endTime = System.nanoTime(); 
        double duration = endTime - startTime; 
        System.out.println("\nArreglo ordenado :");
        imprimirPrimerosElementos(vec, 25000);
        System.out.println("\nTiempo de ejecución en nanosegundos: " + duration);
        System.out.println("\nTiempo de ejecución en segundos: " + duration / 1_000_000_000.0);
    
    }

    public static int[] ordenacionShell(int vec[]) {
        
        final int N = vec.length;
        int k = N / 2;
        while (k >= 1) {
            for (int i = 0; i < k; i++) {
                // Para cada subvector recorremos sus elementos
                for (int j = k + i; j < N; j += k) {
                    int v = vec[j];
                    int n = j - k;
                    while (n >= 0 && vec[n] > v) {
                        vec[n + k] = vec[n];
                        n -= k;
                    }
                    vec[n + k] = v;
                }
            }
            // Obtenemos un nuevo salto
            k /= 2;
        }
        return vec;
    }

    public static void imprimirPrimerosElementos(int vec[], int cantidad) {
        for (int i = 0; i < cantidad && i < vec.length; i++) {
            System.out.print(vec[i] + " ");
        }
        System.out.println();
    }
}
