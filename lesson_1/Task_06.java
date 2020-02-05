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
        for (i = 1; i < array.length; i += 2) {
            int box = array[i - 1];
            array[i - 1] = array[i];
            array[i] = box;
        }
        System.out.print("\n" +"Изменённый массив элементов: ");
        for (i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}