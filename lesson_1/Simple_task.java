package test.automation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("0. �������� ++i ������� ����� ����������� �������� i, � ����� ����������� ���.");
        int i = 1;
        int j = ++i;
        System.out.print("   ��� i = 1, j=++i, j ������ ������: " + j);
        System.out.println();
        System.out.println("   �������� i++ ����� ����������� �������� i, �� �������� i, ������� ���� �� ����������.");
        i = 1;
        int j1 = i++;
        System.out.println("   ��� i = 1, j=i++, j ������ ������: " + j1);
        int a = 0;
        int b = 25;
        System.out.print("1. ����� �� 0 �� " + b + ": ");
        while (a < b) {
            System.out.print(a + " ");
            a++;
        }
        System.out.println();
        int c = 20;
        int d = 2;
        int border = 10;
        System.out.print("2. ׸���� ����� �� " + d + " �� " + c + " ������������: ");
        for (int e = d; e <= c; e++) {
            if (e % 2 == 0) {
                System.out.print(e + " ");
            }
        }
        System.out.println();
        System.out.print("3. ׸���� ����� �� " + d + " �� " + c + " � ������ " + border + ": ");
        for (int e = d; e <= c; e++) {
            if (e % 2 == 0 && e > border) {
                System.out.print(e + " ");
            }
        }
    }
}