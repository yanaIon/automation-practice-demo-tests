package utils;
import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
     static Faker faker = new Faker();


    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomItemFromArray(String[] values) {
        int index = getRandomInt(0, values.length - 1);

        return values[index];
    }

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String getRandomNumber() {
        return faker.numerify("##########");
    }

    public static String getRandomData(int min, int max) {
        return  String.valueOf(faker.number().numberBetween(min, max));
    }

}

