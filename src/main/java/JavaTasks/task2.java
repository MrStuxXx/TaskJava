package JavaTasks;//Определить матрицу (двумерный массив) и ее заполнить случайными значениями.
//8) значение элемента матрицы, не равное заданному значению


import java.util.Random;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            System.out.println("=== Поиск элементов матрицы, не равных заданному значению ===");
            System.out.print("Введите количество строк: ");
            int rows = scanner.nextInt();

            System.out.print("Введите количество столбцов: ");
            int cols = scanner.nextInt();


            System.out.print("Введите минимальное значение: ");
            int min = scanner.nextInt();

            System.out.print("Введите максимальное значение: ");
            int max = scanner.nextInt();

            System.out.print("Введите значение X (для поиска элементов != X): ");
            int x = scanner.nextInt();

            int[][] matrix = new int[rows][cols];

            System.out.println("\nСгенерированная матрица:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    matrix[i][j] = random.nextInt(max - min + 1) + min;
                    System.out.print(matrix[i][j] + "\t");
                }
                System.out.println();
            }

            System.out.println("\nЭлементы, НЕ равные " + x + ":"); // НЕ равные X

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] != x) {
                        System.out.printf("matrix[%d][%d] = %d%n", i, j, matrix[i][j]);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
}