//Задание 1. Написать программу, получающую на вход в качестве аргумента два параметра - числа а и b.
// Если произведение двух чисел больше 100, то вычислить утроенный тангенс второго числа,
// в противном случае первое число умножить на 5. Вывести результат на экран.

import java.util.Scanner;

public class task0 {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите первое число (a): ");
            double a = scanner.nextDouble();

            System.out.print("Введите второе число (b): ");
            double b = scanner.nextDouble();

            double product = a * b;
            double result;

            if (product > 100) {
                result = 3 * Math.tan(b);
                System.out.printf("Произведение %.2f * %.2f = %.2f > 100%n", a, b, product);
                System.out.printf("Вычисляем утроенный тангенс второго числа: 3 * tan(%.2f) = %.2f%n", b, result);
            } else {
                result = a * 5;
                System.out.printf("Произведение %.2f * %.2f = %.2f <= 100%n", a, b, product);
                System.out.printf("Умножаем первое число на 5: %.2f * 5 = %.2f%n", a, result);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: введены некорректные данные.");
        }
    }
}
