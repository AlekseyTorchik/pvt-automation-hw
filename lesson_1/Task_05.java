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
        System.out.print("\n" + "����� �������� ��������: ");
        for (i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                sum++;
                System.out.print(i + 1 + " ");
            }
        }
        if (sum == 0) {
            System.out.println("- ������� �������� �����������");
        }
    }
}