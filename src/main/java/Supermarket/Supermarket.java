package Supermarket;

public class Supermarket {

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

