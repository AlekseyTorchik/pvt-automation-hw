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
        for (i = 2; i < array.length; i += 3) {
            array[i] = array[i] * 2;
        }
        System.out.print("\n" +"��������� ������ ���������: ");
        for (i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n" + "�������� ����� ������ ��������� 2-� ��������: ");
        for (i = 0; i < array.length; i++) {
            if ((i + 1) % 3 == 0) {
                array[i] = array[i] * 2;
            }
            System.out.print(array[i] + " ");
        }

    }
}