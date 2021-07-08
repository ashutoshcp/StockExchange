package ct;

import java.util.HashMap;
import java.util.Objects;

public class Property {
    private static final String DEFAULT_UNIT = "sqft";
    private       Integer id;
    private final String  propertyName;
    private final String  location;
    private final Double price;
    private final PropertyType type;
    private final Integer      size;
    private final String       unit;
    private final Integer rooms;
    private       String  status; // Available, Unavailable

    public Property(
            String propertyName,
            String location,
            Double price,
            PropertyType type,
            Integer size,
            String unit,
            Integer rooms) {
        this.propertyName = propertyName;
        this.location = location;
        this.price = price;
        this.type = type;
        this.size = size;
        this.unit = unit;
        this.rooms = rooms;
    }

    public Property(String[] input) {
        this.propertyName = input[1];
        this.location = input[2];
        this.price = Double.parseDouble(input[3].trim());
        this.type = PropertyType.valueOf(input[4]);
        String size = input[5].trim();
        if (size.contains(" ")) {
            String[] split = size.split(" ");
            this.size = Integer.parseInt(split[0].trim());
            this.unit = split[1];
        } else {
            this.size = Integer.valueOf(size);
            this.unit = DEFAULT_UNIT;
        }
        this.rooms = Integer.parseInt(input[6].trim());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getLocation() {
        return location;
    }

    public static String getDefaultUnit() {
        return DEFAULT_UNIT;
    }

    public Double getPrice() {
        return price;
    }

    public PropertyType getType() {
        return type;
    }

    public Integer getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getRooms() {
        return rooms;
    }

    public String getStatus() {
        return status;
    }
}
