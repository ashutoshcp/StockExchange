package codes;

import java.util.Objects;

public class CustomQueue {
    // process names of users [100]
    // weightage for each name with initials letter of names [A-M]
    // Ashutosh, Abhyarth
    // Abhyarth, Veena
    private String name;
    private Integer weightage;

    public CustomQueue(String name) {
        this.name = name;
        this.weightage = (int) name.charAt(0);
    }

    public CustomQueue(String name, Integer weightage) {
        this.name = name;
        this.weightage = weightage;
    }

    public Integer getWeightage() {
        return weightage;
    }

    @Override
    public String toString() {
        return "name= " + name +  ", weightage" + " " + weightage;
    }
}
