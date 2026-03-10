//Определить матрицу (двумерный массив) и ее заполнить случайными значениями.
//8) значение элемента матрицы, не равное заданному значению

import java.util.Random;

public class task2 {
    public static void main(String[] args) {
        Random random = new Random();

        int size = 20;
        int maxValue = 10;

        int[] originalArray = new int[size];
        System.out.println("Исходный массив:");
        for (int i = 0; i < size; i++) {
            originalArray[i] = random.nextInt(maxValue + 1);
            System.out.print(originalArray[i] + " ");
        }

        int[] tempArray = new int[size];
        int uniqueCount = 0;

        for (int i = 0; i < size; i++) {
            boolean isUnique = true;

            for (int j = 0; j < uniqueCount; j++) {
                if (originalArray[i] == tempArray[j]) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                tempArray[uniqueCount] = originalArray[i];
                uniqueCount++;
            }
        }

        int[] uniqueArray = new int[uniqueCount];
        System.arraycopy(tempArray, 0, uniqueArray, 0, uniqueCount);

        System.out.println("\n\nМассив из неповторяющихся элементов:");
        System.out.println("Количество уникальных элементов: " + uniqueCount);
        for (int num : uniqueArray) {
            System.out.print(num + " ");
        }
    }
}
