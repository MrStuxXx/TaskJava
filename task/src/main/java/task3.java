import java.util.Scanner;

public class task3 {

    public static double calculateD(double a, double b) {
        double maxVal = Math.max(a, b);
        double minVal = Math.min(a, b);

        if (minVal <= 0) {
            throw new IllegalArgumentException("Минимальное значение должно быть больше 0 (деление на ноль)");
        }

        double sqrtMin = Math.sqrt(minVal);
        double numerator = maxVal - 2 * sqrtMin + 4.2 * minVal;

        double denominator = 1 + (maxVal / minVal);

        return numerator / denominator;
    }
    public static double calculateDDetailed(double a, double b) {
        double maxVal = Math.max(a, b);
        double minVal = Math.min(a, b);

        System.out.println("\n=== Подробные вычисления ===");
        System.out.printf("a = %.4f, b = %.4f%n", a, b);
        System.out.printf("max(a,b) = max(%.4f, %.4f) = %.4f%n", a, b, maxVal);
        System.out.printf("min(a,b) = min(%.4f, %.4f) = %.4f%n", a, b, minVal);

        double sqrtMin = Math.sqrt(minVal);
        System.out.printf("√min = √(%.4f) = %.4f%n", minVal, sqrtMin);

        double term1 = maxVal;
        double term2 = -2 * sqrtMin;
        double term3 = 4.2 * minVal;
        double numerator = term1 + term2 + term3;

        System.out.println("\nЧислитель:");
        System.out.printf("max = %.4f%n", term1);
        System.out.printf("-2√min = -2 * %.4f = %.4f%n", sqrtMin, term2);
        System.out.printf("4.2*min = 4.2 * %.4f = %.4f%n", minVal, term3);
        System.out.printf("Итого: %.4f + (%.4f) + %.4f = %.4f%n",
                term1, term2, term3, numerator);

        double ratio = maxVal / minVal;
        double denominator = 1 + ratio;

        System.out.println("\nЗнаменатель:");
        System.out.printf("max/min = %.4f / %.4f = %.4f%n", maxVal, minVal, ratio);
        System.out.printf("1 + max/min = 1 + %.4f = %.4f%n", ratio, denominator);

        double result = numerator / denominator;
        System.out.printf("\nРезультат: d = %.4f / %.4f = %.4f%n",
                numerator, denominator, result);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Вычисление значения d по формуле ===");
        System.out.println("Формула: d = (max(a,b) - 2√min(a,b) + 4.2*min(a,b)) / (1 + max(a,b)/min(a,b))");

        try {
            System.out.print("\nВведите число a: ");
            double a = scanner.nextDouble();

            System.out.print("Введите число b: ");
            double b = scanner.nextDouble();

            double minVal = Math.min(a, b);
            if (minVal <= 0) {
                System.out.println("\nОшибка: минимальное из чисел должно быть больше 0!");
                System.out.println("min(" + a + ", " + b + ") = " + minVal);
                return;
            }

            double result = calculateD(a, b);
            System.out.printf("\nРезультат (простой расчет): d = %.6f%n", result);

            calculateDDetailed(a, b);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}