import java.io.*;
import java.util.*;

public class TaskManager {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "0":
                    task0(args);
                    break;
                case "1":
                    task1();
                    break;
                case "2":
                    task2();
                    break;
                case "3":
                    task3();
                    break;
                case "4":
                    task4();
                    break;
                case "5":
                    System.out.println("\nПрограмма завершена.");
                    return;
                default:
                    System.out.println("\nНеверный ввод. Выберите 0-5.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("МЕНЮ ЗАДАЧ:");
        System.out.println("=".repeat(60));
        System.out.println("0. TASK 0 - Произведение и тангенс (с аргументами)");
        System.out.println("1. TASK 1 - Уникальные элементы массива");
        System.out.println("2. TASK 2 - Поиск элементов матрицы != X");
        System.out.println("3. TASK 3 - Вычисление по формуле с max/min");
        System.out.println("4. TASK 4 - Супермаркет (управление товарами)");
        System.out.println("5. Выход");
        System.out.println("-".repeat(60));
        System.out.print("Выберите задачу: ");
    }

    private static void task0(String[] args) {
        System.out.println("\n--- TASK 0: Произведение и тангенс ---");

        double a, b;

        if (args.length >= 2) {
            try {
                a = Double.parseDouble(args[0]);
                b = Double.parseDouble(args[1]);
                System.out.println("Получены аргументы командной строки:");
                System.out.println("a = " + a + ", b = " + b);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: аргументы должны быть числами. Введите вручную.");
                a = getDoubleInput("Введите первое число (a): ");
                b = getDoubleInput("Введите второе число (b): ");
            }
        } else {
            System.out.println("Аргументы не переданы. Введите вручную:");
            a = getDoubleInput("Введите первое число (a): ");
            b = getDoubleInput("Введите второе число (b): ");
        }

        double product = a * b;
        double result;

        System.out.printf("\nПроизведение a * b = %.2f * %.2f = %.2f%n", a, b, product);

        if (product > 100) {
            result = 3 * Math.tan(b);
            System.out.printf("Условие: %.2f > 100%n", product);
            System.out.printf("Вычисляем утроенный тангенс второго числа: 3 * tan(%.2f) = %.4f%n", b, result);
        } else {
            result = a * 5;
            System.out.printf("Условие: %.2f <= 100%n", product);
            System.out.printf("Умножаем первое число на 5: %.2f * 5 = %.2f%n", a, result);
        }

        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }


    private static void task1() {
        System.out.println("\n--- TASK 1: Уникальные элементы массива ---");

        try {
            System.out.print("Введите размер массива: ");
            int size = Integer.parseInt(scanner.nextLine().trim());

            if (size <= 0) {
                System.out.println("Размер массива должен быть положительным числом!");
                return;
            }

            System.out.print("Введите максимальное значение элементов (от 0 до N): ");
            int maxValue = Integer.parseInt(scanner.nextLine().trim());

            Random random = new Random();
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

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректные числа.");
        }

        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }


    private static void task2() {
        System.out.println("\n--- TASK 2: Поиск элементов матрицы, не равных заданному значению ---");

        try {
            System.out.print("Введите количество строк: ");
            int rows = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите количество столбцов: ");
            int cols = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите минимальное значение: ");
            int min = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите максимальное значение: ");
            int max = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите значение X (для поиска элементов != X): ");
            int x = Integer.parseInt(scanner.nextLine().trim());

            Random random = new Random();
            int[][] matrix = new int[rows][cols];

            System.out.println("\nСгенерированная матрица:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = random.nextInt(max - min + 1) + min;
                    System.out.print(matrix[i][j] + "\t");
                }
                System.out.println();
            }

            System.out.println("\nЭлементы, НЕ равные " + x + ":");
            boolean found = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] != x) {
                        System.out.printf("matrix[%d][%d] = %d%n", i, j, matrix[i][j]);
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("Все элементы равны " + x);
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректные числа.");
        }

        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }

    private static void task3() {
        System.out.println("\n--- TASK 3: Вычисление по формуле ---");
        System.out.println("Формула: d = (max(a,b) - 2√min(a,b) + 4.2*min(a,b)) / (1 + max(a,b)/min(a,b))");

        try {
            System.out.print("Введите число a: ");
            double a = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));

            System.out.print("Введите число b: ");
            double b = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));

            double maxVal = Math.max(a, b);
            double minVal = Math.min(a, b);

            if (minVal <= 0) {
                System.out.println("\nОшибка: минимальное из чисел должно быть больше 0!");
                System.out.println("min(" + a + ", " + b + ") = " + minVal);
                return;
            }

            double result = calculateD(a, b);
            System.out.printf("\nРезультат: d = %.6f%n", result);

            calculateDDetailed(a, b);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректные числа.");
        }

        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }

    public static double calculateD(double a, double b) {
        double maxVal = Math.max(a, b);
        double minVal = Math.min(a, b);

        double sqrtMin = Math.sqrt(minVal);
        double numerator = maxVal - 2 * sqrtMin + 4.2 * minVal;
        double denominator = 1 + (maxVal / minVal);

        return numerator / denominator;
    }

    public static void calculateDDetailed(double a, double b) {
        double maxVal = Math.max(a, b);
        double minVal = Math.min(a, b);

        System.out.println("\n=== Подробные вычисления ===");
        System.out.printf("a = %.4f, b = %.4f%n", a, b);
        System.out.printf("max(a,b) = max(%.4f, %.4f) = %.4f%n", a, b, maxVal);
        System.out.printf("min(a,b) = min(%.4f, %.4f) = %.4f%n", a, b, minVal);

        double sqrtMin = Math.sqrt(minVal);
        System.out.printf("√min = √(%.4f) = %.4f%n", minVal, sqrtMin);

        double term2 = -2 * sqrtMin;
        double term3 = 4.2 * minVal;
        double numerator = maxVal + term2 + term3;

        System.out.println("\nЧислитель:");
        System.out.printf("max = %.4f%n", maxVal);
        System.out.printf("-2√min = -2 * %.4f = %.4f%n", sqrtMin, term2);
        System.out.printf("4.2*min = 4.2 * %.4f = %.4f%n", minVal, term3);
        System.out.printf("Итого: %.4f + (%.4f) + %.4f = %.4f%n",
                maxVal, term2, term3, numerator);

        double ratio = maxVal / minVal;
        double denominator = 1 + ratio;

        System.out.println("\nЗнаменатель:");
        System.out.printf("max/min = %.4f / %.4f = %.4f%n", maxVal, minVal, ratio);
        System.out.printf("1 + max/min = 1 + %.4f = %.4f%n", ratio, denominator);

        double result = numerator / denominator;
        System.out.printf("\nРезультат: d = %.4f / %.4f = %.4f%n",
                numerator, denominator, result);
    }

    private static void task4() {
        System.out.println("\n--- TASK 4: Супермаркет - Управление товарами ---");
        SupermarketStorage supermarket = new SupermarketStorage();
        supermarket.run();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Ошибка! Введите число: ");
            }
        }
    }
}


class Supermarket {
    private String nameotdela;
    private String name;
    private String cuntry;
    private double retailPrice;
    private String namesource;

    public Supermarket(String nameotdela, String name, String cuntry, double retailPrice, String namesource) {
        this.nameotdela = nameotdela;
        this.name = name;
        this.cuntry = cuntry;
        this.retailPrice = retailPrice;
        this.namesource = namesource;
    }

    public String getNameotdela() { return nameotdela; }
    public void setNameotdela(String nameotdela) { this.nameotdela = nameotdela; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCuntry() { return cuntry; }
    public void setCuntry(String cuntry) { this.cuntry = cuntry; }

    public double getRetailPrice() { return retailPrice; }
    public void setRetailPrice(double retailPrice) { this.retailPrice = retailPrice; }

    public String getNamesource() { return namesource; }
    public void setNamesource(String namesource) { this.namesource = namesource; }

    @Override
    public String toString() {
        return String.format(
                "  Отдел:           %s%n" +
                        "  Наименование:    %s%n" +
                        "  Страна:          %s%n" +
                        "  Цена:            %.2f руб.%n" +
                        "  Поставщик:       %s",
                nameotdela, name, cuntry, retailPrice, namesource
        );
    }
}

class Toys extends Supermarket {
    private String ageGroup;
    private String type;

    public Toys(String nameotdela, String name, String cuntry, double retailPrice, String namesource,
                String ageGroup, String type) {
        super(nameotdela, name, cuntry, retailPrice, namesource);
        this.ageGroup = ageGroup;
        this.type = type;
    }

    public String getAgeGroup() { return ageGroup; }
    public void setAgeGroup(String ageGroup) { this.ageGroup = ageGroup; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "[Игрушки]" + System.lineSeparator() +
                super.toString() + System.lineSeparator() +
                String.format(
                        "  Возрастная группа: %s%n" +
                                "  Тип:               %s",
                        ageGroup, type
                );
    }
}

class Fruits extends Supermarket {
    private int maxStorageTime;
    private double storageTemperature;

    public Fruits(String nameotdela, String name, String cuntry, double retailPrice, String namesource,
                  int maxStorageTime, double storageTemperature) {
        super(nameotdela, name, cuntry, retailPrice, namesource);
        this.maxStorageTime = maxStorageTime;
        this.storageTemperature = storageTemperature;
    }

    public int getMaxStorageTime() { return maxStorageTime; }
    public void setMaxStorageTime(int maxStorageTime) { this.maxStorageTime = maxStorageTime; }

    public double getStorageTemperature() { return storageTemperature; }
    public void setStorageTemperature(double storageTemperature) { this.storageTemperature = storageTemperature; }

    @Override
    public String toString() {
        return "[Фрукты]" + System.lineSeparator() +
                super.toString() + System.lineSeparator() +
                String.format(
                        "  Макс. время хранения: %d дней%n" +
                                "  Температура хранения: %.1f °C",
                        maxStorageTime, storageTemperature
                );
    }
}

class BulkyGoods extends Supermarket {
    private double height;
    private double width;
    private double length;

    public BulkyGoods(String nameotdela, String name, String cuntry, double retailPrice, String namesource,
                      double height, double width, double length) {
        super(nameotdela, name, cuntry, retailPrice, namesource);
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }

    @Override
    public String toString() {
        return "[Габаритный товар]" + System.lineSeparator() +
                super.toString() + System.lineSeparator() +
                String.format(
                        "  Высота:  %.2f см%n" +
                                "  Ширина:  %.2f см%n" +
                                "  Длина:   %.2f см",
                        height, width, length
                );
    }
}

class SupermarketStorage {
    private static final String SEPARATOR = "|";
    private static final String DEFAULT_FILE = "supermarket.txt";

    private List<Supermarket> products = new ArrayList<>();
    private Scanner scanner;

    public void run() {
        scanner = new Scanner(System.in);

        // Демо-данные
        products.add(new Toys("Игрушки", "LEGO City", "Дания", 2499.00, "LEGO Group", "от 6 лет", "Конструктор"));
        products.add(new Fruits("Фрукты", "Бананы", "Эквадор", 89.90, "TropicFresh", 7, 13.5));
        products.add(new BulkyGoods("Техника", "Холодильник Samsung", "Юж. Корея", 54999.00, "Samsung", 185.0, 60.0, 65.0));

        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1"  -> addToy();
                case "2"  -> addFruit();
                case "3"  -> addBulky();
                case "4"  -> printAll();
                case "5"  -> printSorted(
                        Comparator.comparingDouble(Supermarket::getRetailPrice),
                        "СОРТИРОВКА ПО ЦЕНЕ: дешевле → дороже");
                case "6"  -> printSorted(
                        Comparator.comparingDouble(Supermarket::getRetailPrice).reversed(),
                        "СОРТИРОВКА ПО ЦЕНЕ: дороже → дешевле");
                case "7"  -> printSorted(
                        Comparator.comparing(Supermarket::getCuntry),
                        "СОРТИРОВКА ПО СТРАНЕ-ПРОИЗВОДИТЕЛЮ (А → Я)");
                case "8"  -> deleteProduct();
                case "9"  -> saveToFile();
                case "10" -> loadFromFile();
                case "0"  -> {
                    System.out.println("\n  Возврат в главное меню...");
                    return;
                }
                default -> System.out.println("\n  Неверный ввод. Выберите пункт от 0 до 10.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║    СУПЕРМАРКЕТ — Управление      ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  1. Добавить Игрушку             ║");
        System.out.println("║  2. Добавить Фрукт               ║");
        System.out.println("║  3. Добавить Габаритный товар    ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  4. Показать все товары          ║");
        System.out.println("║  5. Сортировка по цене (↑)       ║");
        System.out.println("║  6. Сортировка по цене (↓)       ║");
        System.out.println("║  7. Сортировка по стране (А→Я)   ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  8. Удалить товар                ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  9. Сохранить в файл             ║");
        System.out.println("║ 10. Загрузить из файла           ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  0. Выход в главное меню         ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("  Ваш выбор: ");
    }

    private void addToy() {
        System.out.println("\n--- Добавление: Игрушки ---");
        System.out.print("  Название отдела:       "); String dept     = scanner.nextLine();
        System.out.print("  Наименование товара:   "); String name     = scanner.nextLine();
        System.out.print("  Страна-производитель:  "); String country  = scanner.nextLine();
        System.out.print("  Розничная цена (руб.): "); double price    = Double.parseDouble(scanner.nextLine());
        System.out.print("  Поставщик:             "); String supplier = scanner.nextLine();
        System.out.print("  Возрастная группа:     "); String ageGroup = scanner.nextLine();
        System.out.print("  Тип игрушки:           "); String type     = scanner.nextLine();
        products.add(new Toys(dept, name, country, price, supplier, ageGroup, type));
        System.out.println("   Игрушка добавлена.");
    }

    private void addFruit() {
        System.out.println("\n--- Добавление: Фрукты ---");
        System.out.print("  Название отдела:             "); String dept     = scanner.nextLine();
        System.out.print("  Наименование товара:         "); String name     = scanner.nextLine();
        System.out.print("  Страна-производитель:        "); String country  = scanner.nextLine();
        System.out.print("  Розничная цена (руб.):       "); double price    = Double.parseDouble(scanner.nextLine());
        System.out.print("  Поставщик:                   "); String supplier = scanner.nextLine();
        System.out.print("  Макс. время хранения (дней): "); int days        = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("  Температура хранения (°C):   "); double temp     = Double.parseDouble(scanner.nextLine());
        products.add(new Fruits(dept, name, country, price, supplier, days, temp));
        System.out.println("   Фрукт добавлен.");
    }

    private void addBulky() {
        System.out.println("\n--- Добавление: Габаритный товар ---");
        System.out.print("  Название отдела:       "); String dept     = scanner.nextLine();
        System.out.print("  Наименование товара:   "); String name     = scanner.nextLine();
        System.out.print("  Страна-производитель:  "); String country  = scanner.nextLine();
        System.out.print("  Розничная цена (руб.): "); double price    = Double.parseDouble(scanner.nextLine());
        System.out.print("  Поставщик:             "); String supplier = scanner.nextLine();
        System.out.print("  Высота (см):           "); double h        = Double.parseDouble(scanner.nextLine());
        System.out.print("  Ширина (см):           "); double w        = Double.parseDouble(scanner.nextLine());
        System.out.print("  Длина  (см):           "); double l        = Double.parseDouble(scanner.nextLine());
        products.add(new BulkyGoods(dept, name, country, price, supplier, h, w, l));
        System.out.println("   Габаритный товар добавлен.");
    }

    private void printAll() {
        if (products.isEmpty()) {
            System.out.println("\n  Список товаров пуст.");
            return;
        }
        String line = "=".repeat(52);
        System.out.println("\n" + line);
        System.out.println("     СПИСОК ТОВАРОВ  (всего: " + products.size() + ")");
        System.out.println(line);
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%n  № %d%n", i + 1);
            System.out.println("-".repeat(52));
            System.out.println(products.get(i));
        }
        System.out.println("\n" + line);
    }

    private void printSorted(Comparator<Supermarket> comparator, String title) {
        if (products.isEmpty()) {
            System.out.println("\n  Список товаров пуст.");
            return;
        }
        List<Supermarket> sorted = new ArrayList<>(products);
        sorted.sort(comparator);

        String line = "=".repeat(52);
        System.out.println("\n" + line);
        System.out.println("  " + title);
        System.out.println("  Всего: " + sorted.size() + " позиций");
        System.out.println(line);
        for (int i = 0; i < sorted.size(); i++) {
            System.out.printf("%n  № %d%n", i + 1);
            System.out.println("-".repeat(52));
            System.out.println(sorted.get(i));
        }
        System.out.println("\n" + line);
    }

    private void deleteProduct() {
        if (products.isEmpty()) {
            System.out.println("\n  Список товаров пуст — нечего удалять.");
            return;
        }
        System.out.println("\n--- Выберите товар для удаления ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("  [%d] %s — %.2f руб. (%s)%n",
                    i + 1,
                    products.get(i).getName(),
                    products.get(i).getRetailPrice(),
                    products.get(i).getCuntry());
        }
        System.out.println("  [0] Отмена");
        System.out.print("\n  Введите номер: ");

        String input = scanner.nextLine().trim();
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("   Неверный ввод.");
            return;
        }

        if (index == 0) { System.out.println("  Удаление отменено."); return; }
        if (index < 1 || index > products.size()) { System.out.println("   Номер вне диапазона."); return; }

        Supermarket toDelete = products.get(index - 1);
        System.out.printf("%n  Удалить «%s»? (Y / N): ", toDelete.getName());
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("да") || confirm.equals("д") || confirm.equals("y") || confirm.equals("yes")) {
            products.remove(index - 1);
            System.out.println("   Товар «" + toDelete.getName() + "» удалён.");
        } else {
            System.out.println("  Удаление отменено.");
        }
    }

    private void saveToFile() {
        if (products.isEmpty()) {
            System.out.println("\n  Коллекция пуста — нечего сохранять.");
            return;
        }
        System.out.print("\n  Имя файла (Enter = " + DEFAULT_FILE + "): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) filename = DEFAULT_FILE;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Supermarket p : products) {
                if (p instanceof Toys t) {
                    bw.write("TOYS" + SEPARATOR +
                            t.getNameotdela() + SEPARATOR +
                            t.getName()       + SEPARATOR +
                            t.getCuntry()     + SEPARATOR +
                            t.getRetailPrice()+ SEPARATOR +
                            t.getNamesource() + SEPARATOR +
                            t.getAgeGroup()   + SEPARATOR +
                            t.getType());
                } else if (p instanceof Fruits f) {
                    bw.write("FRUITS" + SEPARATOR +
                            f.getNameotdela()        + SEPARATOR +
                            f.getName()              + SEPARATOR +
                            f.getCuntry()            + SEPARATOR +
                            f.getRetailPrice()       + SEPARATOR +
                            f.getNamesource()        + SEPARATOR +
                            f.getMaxStorageTime()    + SEPARATOR +
                            f.getStorageTemperature());
                } else if (p instanceof BulkyGoods b) {
                    bw.write("BULKY" + SEPARATOR +
                            b.getNameotdela()  + SEPARATOR +
                            b.getName()        + SEPARATOR +
                            b.getCuntry()      + SEPARATOR +
                            b.getRetailPrice() + SEPARATOR +
                            b.getNamesource()  + SEPARATOR +
                            b.getHeight()      + SEPARATOR +
                            b.getWidth()       + SEPARATOR +
                            b.getLength());
                }
                bw.newLine();
            }
            System.out.println("   Сохранено " + products.size() + " товаров в файл: " + filename);
        } catch (IOException e) {
            System.out.println("   Ошибка записи файла: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        System.out.print("\n  Имя файла (Enter = " + DEFAULT_FILE + "): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) filename = DEFAULT_FILE;

        System.out.print("  Добавить к текущему списку или заменить? (д = добавить, з = заменить): ");
        String mode = scanner.nextLine().trim().toLowerCase();
        if (mode.equals("з") || mode.equals("z")) {
            products.clear();
            System.out.println("  Текущий список очищен.");
        }

        int loaded = 0;
        int errors = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] f = line.split("\\" + SEPARATOR, -1);

                try {
                    switch (f[0]) {
                        case "TOYS" -> {
                            Toys t = new Toys(f[1], f[2], f[3], Double.parseDouble(f[4]), f[5], f[6], f[7]);
                            products.add(t);
                            loaded++;
                        }
                        case "FRUITS" -> {
                            Fruits fr = new Fruits(f[1], f[2], f[3], Double.parseDouble(f[4]), f[5],
                                    Integer.parseInt(f[6]), Double.parseDouble(f[7]));
                            products.add(fr);
                            loaded++;
                        }
                        case "BULKY" -> {
                            BulkyGoods b = new BulkyGoods(f[1], f[2], f[3], Double.parseDouble(f[4]), f[5],
                                    Double.parseDouble(f[6]), Double.parseDouble(f[7]), Double.parseDouble(f[8]));
                            products.add(b);
                            loaded++;
                        }
                        default -> {
                            System.out.println("   Строка " + lineNum + ": неизвестный тип «" + f[0] + "» — пропущена.");
                            errors++;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("   Строка " + lineNum + ": ошибка разбора — " + e.getMessage());
                    errors++;
                }
            }

            System.out.printf("   Загружено: %d товаров.%s%n",
                    loaded,
                    errors > 0 ? " Пропущено строк с ошибками: " + errors : "");

        } catch (IOException e) {
            System.out.println("   Файл не найден или недоступен: " + filename);
        }
    }
}
