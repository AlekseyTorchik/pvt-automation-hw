package test.automation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("0. Действие ++i сначала будет увеличивать значение i, а затем присваивать его.");
        int i = 1;
        int j = ++i;
        System.out.print("   При i = 1, j=++i, j станет равным: " + j);
        System.out.println();
        System.out.println("   Действие i++ будет увеличивать значение i, но присвоит i, которое было до увеличения.");
        i = 1;
        int j1 = i++;
        System.out.println("   При i = 1, j=i++, j станет равным: " + j1);
        int a = 0;
        int b = 25;
        System.out.print("1. Числа от 0 до " + b + ": ");
        while (a < b) {
            System.out.print(a + " ");
            a++;
        }
        System.out.println();
        int c = 20;
        int d = 2;
        int border = 10;
        System.out.print("2. Чётные числа от " + d + " до " + c + " включительно: ");
        for (int e = d; e <= c; e++) {
            if (e % 2 == 0) {
                System.out.print(e + " ");
            }
        }
        System.out.println();
        System.out.print("3. Чётные числа от " + d + " до " + c + " и больше " + border + ": ");
        for (int e = d; e <= c; e++) {
            if (e % 2 == 0 && e > border) {
                System.out.print(e + " ");
            }
        }
    }
}