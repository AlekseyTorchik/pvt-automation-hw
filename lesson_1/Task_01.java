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
        System.out.println();
        System.out.print("������ ��������� � �������� �������: ");
        for (i = array.length-1; i >=0; i--) {
            System.out.print(array[i] + " ");
        }
    }
}