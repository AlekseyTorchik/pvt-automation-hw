package test.automation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("������� ���������� ��������� �������: ");
        int[] array = new int[scanner.nextInt()];
        int i = 0;
        System.out.print("������ ���������: ");
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
            System.out.println("- ������� �������� �����������");
        } else {
            System.out.println("\n" + "���������� ������� ���������: " + sum);
        }
    }
}