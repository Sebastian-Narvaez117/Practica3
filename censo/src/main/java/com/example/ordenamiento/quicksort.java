package com.example.ordenamiento;

import java.util.Random;

public class quicksort {

     public static void main(String[] args) {
        int[] vec = generateRandomArray(20000);

        
        long startTime = System.nanoTime(); 
        System.out.println("Arreglo antes de ordenar:");
        imprimirVector(vec);

        
        ordenacionRapida(vec);

        
        System.out.println("\nArreglo después de ordenar:");
        imprimirVector(vec);

         long endTime = System.nanoTime(); 
         double duration = endTime - startTime; 
         System.out.println("Tiempo de ejecución en segundos:  " + duration / 1_000_000_000.0);
    }

  
    public static void ordenacionRapida(int vec[]) {
        final int N = vec.length;
        quickSort(vec, 0, N - 1);
    }

    
    public static void quickSort(int vec[], int inicio, int fin) {
        if (inicio >= fin) return;
        int pivote = vec[inicio];
        int elemIzq = inicio + 1;
        int elemDer = fin;
        while (elemIzq <= elemDer) {
            while (elemIzq <= fin && vec[elemIzq] < pivote) {
                elemIzq++;
            }
            while (elemDer > inicio && vec[elemDer] >= pivote) {
                elemDer--;
            }
            if (elemIzq < elemDer) {
                int temp = vec[elemIzq];
                vec[elemIzq] = vec[elemDer];
                vec[elemDer] = temp;
            }
        }

        if (elemDer > inicio) {
            int temp = vec[inicio];
            vec[inicio] = vec[elemDer];
            vec[elemDer] = temp;
        }
        quickSort(vec, inicio, elemDer - 1);
        quickSort(vec, elemDer + 1, fin);
    }

    
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(20000); 
        }
        return array;
    }

   
    public static void imprimirVector(int vec[]) {
        for (int i = 0; i < vec.length; i++) {
            System.out.print(vec[i] + " ");
        }
        System.out.println(); 
    }
    
}
