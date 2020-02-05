package test.automation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов массива: ");
        int sizeBase = scanner.nextInt();
        System.out.print("Количество позиций для сдвига элементов вправо : ");
        int position = scanner.nextInt();
        int[] base = new int[sizeBase];
        int i = 0;
        System.out.println();
        System.out.print("Массив элементов до сдвига: ");
        while (i < sizeBase) {
            base[i] = (int) (Math.random() * 15);
            System.out.print(base[i] + " ");
            i++;
        }
        System.out.println();
        System.out.print("Массив элементов после сдвига: ");
        for (int j = 0; j < position; j++) {
            int tail = base[sizeBase - 1];
            for (int q = sizeBase - 1; q > 0; q--) {
                base[q] = base[q - 1];
            }
            base[0] = tail;
        }
        for (int j : base) {
            System.out.print(j + " ");
        }
    }
}