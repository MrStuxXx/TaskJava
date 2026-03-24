package Supermarket;

public class Toys extends Supermarket {

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
