package restassure.reqres.requestModel;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Product {
    private @NonNull String name;
    private @NonNull String year;
    private String color;
    private String pantone_value;

    /*public Product(String name, String year, String color, String pantone_value) {
        setName(name);
        setYear(year);
        setColor(color);
        setPantone_value(pantone_value);
    }

    public Product(String name, String year) {
        setName(name);
        setYear(year);
    }*/

    /*public long getId() {
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
    }*/
}
