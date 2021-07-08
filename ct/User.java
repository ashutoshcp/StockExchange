package ct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class User {

    private final String       name;
    private final Set<Integer> shortlistedProperties = new TreeSet<>();
    private final Set<Integer> listedProperties      = new TreeSet<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProperty(Integer id) {
        this.shortlistedProperties.add(id);
    }

    public Set<Integer> getShortlistedProperties() {
        return shortlistedProperties;
    }

    public Set<Integer> getListedProperties() {
        return listedProperties;
    }

    public void addListedProperty(Integer id) {
        this.listedProperties.add(id);
    }
}
