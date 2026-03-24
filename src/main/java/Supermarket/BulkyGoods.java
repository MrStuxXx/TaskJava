package Supermarket;

public class BulkyGoods extends Supermarket {

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

