package Supermarket;

public class Fruits extends Supermarket {

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

