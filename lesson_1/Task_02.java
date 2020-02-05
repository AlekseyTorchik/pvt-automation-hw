package test.automation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов массива: ");
        int[] array = new int[scanner.nextInt()];
        int i = 0;
        System.out.print("Массив элементов: ");
        while (i < array.length) {
            array[i] = (int) (Math.random() * 10);
            System.out.print(array[i] + " ");
            i++;
        }
        int multiple = 1;
        System.out.println();
        for (i = 0; i < array.length; i++) {
            multiple = multiple * array[i];
        }
        System.out.println("Произведение элементов массива: " + multiple);
      
    }
}