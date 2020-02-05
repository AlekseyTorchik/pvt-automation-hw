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
        int y = 0;
        double[] array1 = new double[array.length];
        for (int j = 0; j < array.length - 1; j++, y++) {
            if (y == 0) {
                array1[j] = array[y + 1] * 0.5;
            }
            if (y > 0 && y < array.length) {
                array1[j] = (array[y - 1] + array[y + 1]) * 0.5;
            }
        }
        array1[array.length - 1] = array[array.length - 2] * 0.5;
        System.out.print("\n" + "Полученный массив элементов : ");
        for (double j : array1) {
            System.out.print(j + " ");
        }
    }
}