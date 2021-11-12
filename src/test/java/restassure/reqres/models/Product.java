package restassure.reqres.models;

public class Product {

    private long id;
    private String name;
    private String year;
    private String color;
    private String pantone_value;

    public Product(String name, String year, String color, String pantone_value) {
        setName(name);
        setYear(year);
        setColor(color);
        setPantone_value(pantone_value);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPantone_value() {
        return pantone_value;
    }

    public void setPantone_value(String pantone_value) {
        this.pantone_value = pantone_value;
    }
}
