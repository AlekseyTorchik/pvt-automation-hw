package test.automation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("������� ���������� ��������� �������: ");
        int sizeBase = scanner.nextInt();
        System.out.print("���������� ������� ��� ������ ��������� ������ : ");
        int position = scanner.nextInt();
        int[] base = new int[sizeBase];
        int i = 0;
        System.out.println();
        System.out.print("������ ��������� �� ������: ");
        while (i < sizeBase) {
            base[i] = (int) (Math.random() * 15);
            System.out.print(base[i] + " ");
            i++;
        }
        System.out.println();
        System.out.print("������ ��������� ����� ������: ");
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