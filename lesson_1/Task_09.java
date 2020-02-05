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
        int idmax = 0;
        int idmin = 0;
        for (i = 0; i < array.length; i++) {
            if (array[i] > array[idmax]) {
                idmax = i;
            }
            if (array[i] < array[idmin]) {
                idmin = i;
            }
        }
        System.out.println("\n" + "����� ������������� ��������: " + (idmax + 1) + "\n" + "����� ������������ ��������: " + (idmin + 1));
    }
}