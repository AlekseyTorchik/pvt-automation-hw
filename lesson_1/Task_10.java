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
        boolean check = true;
        for (i = 1; i < array.length; i++) {
            if (array[i - 1] >= array[i]) {
                check = false;
                break;
            }
        }
        if (check) {
            System.out.println("\n" + "������ �������� ������ ������������ �������������������");
        } else {
            System.out.println("\n" + "������ �� �������� ������ ������������ �������������������");
        }
    }
}