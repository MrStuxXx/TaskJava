package Supermarket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SupermarketStorage {

    private static final String SEPARATOR = "|";
    private static final String DEFAULT_FILE = "supermarket.txt";

    private List<Supermarket> products = new ArrayList<>();

    public void add(Supermarket product) {
        products.add(product);
    }


    public void printAll() {
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


    public void printSorted(Comparator<Supermarket> comparator, String title) {
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


    private void deleteProduct(Scanner sc) {
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

        String input = sc.nextLine().trim();
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
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("да") || confirm.equals("д") || confirm.equals("y") || confirm.equals("yes")) {
            products.remove(index - 1);
            System.out.println("   Товар «" + toDelete.getName() + "» удалён.");
        } else {
            System.out.println("  Удаление отменено.");
        }
    }


    private void saveToFile(Scanner sc) {
        if (products.isEmpty()) {
            System.out.println("\n  Коллекция пуста — нечего сохранять.");
            return;
        }
        System.out.print("\n  Имя файла (Enter = " + DEFAULT_FILE + "): ");
        String filename = sc.nextLine().trim();
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


    private void loadFromFile(Scanner sc) {
        System.out.print("\n  Имя файла (Enter = " + DEFAULT_FILE + "): ");
        String filename = sc.nextLine().trim();
        if (filename.isEmpty()) filename = DEFAULT_FILE;

        System.out.print("  Добавить к текущему списку или заменить? (д = добавить, з = заменить): ");
        String mode = sc.nextLine().trim().toLowerCase();
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
                            add(t);
                            loaded++;
                        }
                        case "FRUITS" -> {

                            Fruits fr = new Fruits(f[1], f[2], f[3], Double.parseDouble(f[4]), f[5],
                                    Integer.parseInt(f[6]), Double.parseDouble(f[7]));
                            add(fr);
                            loaded++;
                        }
                        case "BULKY" -> {

                            BulkyGoods b = new BulkyGoods(f[1], f[2], f[3], Double.parseDouble(f[4]), f[5],
                                    Double.parseDouble(f[6]), Double.parseDouble(f[7]), Double.parseDouble(f[8]));
                            add(b);
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


    private void addToy(Scanner sc) {
        System.out.println("\n--- Добавление: Игрушки ---");
        System.out.print("  Название отдела:       "); String dept     = sc.nextLine();
        System.out.print("  Наименование товара:   "); String name     = sc.nextLine();
        System.out.print("  Страна-производитель:  "); String country  = sc.nextLine();
        System.out.print("  Розничная цена (руб.): "); double price    = Double.parseDouble(sc.nextLine());
        System.out.print("  Поставщик:             "); String supplier = sc.nextLine();
        System.out.print("  Возрастная группа:     "); String ageGroup = sc.nextLine();
        System.out.print("  Тип игрушки:           "); String type     = sc.nextLine();
        add(new Toys(dept, name, country, price, supplier, ageGroup, type));
        System.out.println("   Игрушка добавлена.");
    }


    private void addFruit(Scanner sc) {
        System.out.println("\n--- Добавление: Фрукты ---");
        System.out.print("  Название отдела:             "); String dept     = sc.nextLine();
        System.out.print("  Наименование товара:         "); String name     = sc.nextLine();
        System.out.print("  Страна-производитель:        "); String country  = sc.nextLine();
        System.out.print("  Розничная цена (руб.):       "); double price    = Double.parseDouble(sc.nextLine());
        System.out.print("  Поставщик:                   "); String supplier = sc.nextLine();
        System.out.print("  Макс. время хранения (дней): "); int days        = Integer.parseInt(sc.nextLine().trim());
        System.out.print("  Температура хранения (°C):   "); double temp     = Double.parseDouble(sc.nextLine());
        add(new Fruits(dept, name, country, price, supplier, days, temp));
        System.out.println("   Фрукт добавлен.");
    }


    private void addBulky(Scanner sc) {
        System.out.println("\n--- Добавление: Габаритный товар ---");
        System.out.print("  Название отдела:       "); String dept     = sc.nextLine();
        System.out.print("  Наименование товара:   "); String name     = sc.nextLine();
        System.out.print("  Страна-производитель:  "); String country  = sc.nextLine();
        System.out.print("  Розничная цена (руб.): "); double price    = Double.parseDouble(sc.nextLine());
        System.out.print("  Поставщик:             "); String supplier = sc.nextLine();
        System.out.print("  Высота (см):           "); double h        = Double.parseDouble(sc.nextLine());
        System.out.print("  Ширина (см):           "); double w        = Double.parseDouble(sc.nextLine());
        System.out.print("  Длина  (см):           "); double l        = Double.parseDouble(sc.nextLine());
        add(new BulkyGoods(dept, name, country, price, supplier, h, w, l));
        System.out.println("   Габаритный товар добавлен.");
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
        System.out.println("║  0. Выход                        ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("  Ваш выбор: ");
    }


    public void run() {
        Scanner sc = new Scanner(System.in);

        // Демо-данные
        add(new Toys("Игрушки", "LEGO City", "Дания", 2499.00, "LEGO Group", "от 6 лет", "Конструктор"));
        add(new Fruits("Фрукты", "Бананы", "Эквадор", 89.90, "TropicFresh", 7, 13.5));
        add(new BulkyGoods("Техника", "Холодильник Samsung", "Юж. Корея", 54999.00, "Samsung", 185.0, 60.0, 65.0));

        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1"  -> addToy(sc);
                case "2"  -> addFruit(sc);
                case "3"  -> addBulky(sc);
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
                case "8"  -> deleteProduct(sc);
                case "9"  -> saveToFile(sc);
                case "10" -> loadFromFile(sc);
                case "0"  -> {
                    System.out.println("\n  До свидания!\n");
                    sc.close();
                    return;
                }
                default -> System.out.println("\n  Неверный ввод. Выберите пункт от 0 до 10.");
            }
        }
    }


    public static void main(String[] args) {
        new SupermarketStorage().run();
    }
}
