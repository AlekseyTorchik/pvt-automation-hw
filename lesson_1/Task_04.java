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
        int sum = 0;
        for (i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                sum++;
            }
        }
        if (sum == 0) {
            System.out.println("- нулевые элементы отсутствуют");
        } else {
            System.out.println("\n" + "Количество нулевых элементов: " + sum);
        }
    }
}