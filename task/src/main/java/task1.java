//Определить одномерный массив и заполнить его случайными значениями
// 8 Определить дополнительный массив, состоящий из неповторяющихся элементов исходного массива и вывести его на экран

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            System.out.println("=== Программа для поиска уникальных элементов массива ===");
            System.out.print("Введите размер массива: ");
            int size = scanner.nextInt();

            if (size <= 0) {
                System.out.println("Размер массива должен быть положительным числом!");
                return;
            }

            System.out.print("Введите максимальное значение элементов (от 0 до N): ");
            int maxValue = scanner.nextInt();

            int[] originalArray = new int[size];
            System.out.println("\nИсходный массив:");
            for (int i = 0; i < size; i++) {
                originalArray[i] = random.nextInt(maxValue + 1);
                System.out.print(originalArray[i] + " ");
            }

            LinkedHashSet<Integer> uniqueSet = new LinkedHashSet<>();
            for (int num : originalArray) {
                uniqueSet.add(num);
            }

            int[] uniqueArray = new int[uniqueSet.size()];
            int index = 0;
            for (int num : uniqueSet) {
                uniqueArray[index++] = num;
            }

            System.out.println("\n\nМассив уникальных элементов (сохранен порядок):");
            System.out.println("Количество уникальных элементов: " + uniqueArray.length);
            for (int num : uniqueArray) {
                System.out.print(num + " ");
            }

            System.out.println("\n\nСтатистика:");
            System.out.println("Всего элементов: " + size);
            System.out.println("Уникальных: " + uniqueArray.length);
            System.out.println("Повторяющихся: " + (size - uniqueArray.length));

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}