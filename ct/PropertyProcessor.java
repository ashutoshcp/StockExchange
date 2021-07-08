package ct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PropertyProcessor {
    private final AtomicInteger         propertyIdCounter = new AtomicInteger(0);
    //private Map<String, List<Property>> usersProperties;
    private       Map<String, Property> uniquePropertyMap;
    private static Map<Integer, Property> idPropertyMap;

    public String listProperty(Property pt, String userName) {
//        if (usersProperties == null || usersProperties.isEmpty()) {
//            usersProperties = new HashMap<>();
//        }
        if (uniquePropertyMap == null || uniquePropertyMap.isEmpty()) {
            uniquePropertyMap = new HashMap<>();
        }
        if (idPropertyMap == null || idPropertyMap.isEmpty()) {
            idPropertyMap = new HashMap<>();
        }
        User user = UserProcessor.getUser(userName);
        if (user == null) {
            System.out.println("First register user: " + userName);
            return "user not registered";
        }
        String key = userName + pt.getPropertyName() + pt.getLocation();
        if (uniquePropertyMap.containsKey(key)) {
            System.out.println("Already registered property: " + pt);
            return "Already registered property";
        } else {
            int i = propertyIdCounter.incrementAndGet();
            pt.setId(i);
            idPropertyMap.put(i, pt);
            uniquePropertyMap.put(key, pt);
            user.addListedProperty(i);
        }

        //List<Property> properties = usersProperties.get(userName);
//        if (properties==null || properties.isEmpty()) {
//            properties = new ArrayList<>();
//        }
//        properties.add(pt);
//        usersProperties.put(userName, properties);
        return "property successfully added";
    }

    public static boolean isValidProperty(Integer propertyId) {
        return idPropertyMap.containsKey(propertyId);
    }

    public Property getPropertyById(int pId) {
        return idPropertyMap.get(pId);
    }

    public List<Property> search(String[] search) {
        boolean filterLocation = true;
        boolean filterPrice = true;
        boolean filterType = true;
        boolean filterSize = true;
        boolean filterRoom = true;
        String location = search[0].trim();
        if (location.isBlank()) {
            filterLocation = false;
        }
        String[] priceRange = search[1].trim().split("-");
        if (priceRange.length == 0) {
            filterPrice = false;
        }
        String type = search[2].trim();
        if (type.isBlank()) {
            filterType = false;
        }
        Integer size = null;
        try {
            size = Integer.parseInt(search[3]);
        } catch (NumberFormatException ex) {

        }
        if (size == null) {
            filterSize = false;
        }
        Integer numberOfRooms = null;
        try {
            numberOfRooms = Integer.parseInt(search[4]);
        } catch (NumberFormatException ex) {

        }
        if (numberOfRooms == null) {
            filterRoom = false;
        }
        String sortOn = search[5].trim();
        List<Property> sortListed = (List<Property>) idPropertyMap.values();
        boolean finalFilterLocation = filterLocation;
        sortListed = sortListed.stream().filter(e -> finalFilterLocation && e.getLocation().equals(location)).collect(Collectors.toList());
        boolean finalFilterType = filterType;
        sortListed = sortListed.stream().filter(e -> finalFilterType && e.getType().equals(type)).collect(Collectors.toList());
        if (size != null) {
            boolean finalFilterSize = filterSize;
            Integer finalSize = size;
            sortListed = sortListed.stream().filter(e -> finalFilterSize && e.getSize().equals(finalSize)).collect(Collectors.toList());
        }
        if(numberOfRooms != null) {
            boolean finalFilterRoom = filterRoom;
            Integer finalNumberOfRooms = numberOfRooms;
            sortListed = sortListed.stream().filter(e -> finalFilterRoom && e.getRooms().equals(finalNumberOfRooms)).collect(Collectors.toList());
        }
        return sortListed;
    }
}
