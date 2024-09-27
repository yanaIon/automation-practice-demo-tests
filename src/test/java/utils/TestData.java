package utils;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    public static String[] genders = {"Male", "Female", "Other"};
    public static String[] hobbies = {"Sports", "Reading", "Music"};
    public static String[] subjects = {"Accounting", "Maths", "Arts", "Social Studies"};
    public static String[] month = {"January", "February", "March", "April", "May", "June",
                                    "July", "August", "September", "October", "November", "December"};
    public static String[] stateList = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

    // Map, связывающая штаты с соответствующими городами
    public static Map<String, String[]> stateCityMap = new HashMap<>();

    static {
        stateCityMap.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        stateCityMap.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        stateCityMap.put("Haryana", new String[]{"Karnal", "Panipat"});
        stateCityMap.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
    }
}
