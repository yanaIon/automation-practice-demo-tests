package tests;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$;
import static utils.RandomUtils.*;
import static utils.RandomUtils.getRandomItemFromArray;
import static utils.TestData.*;

public class PracticeTextBoxTestsWithFakerTests extends TestBase {

    @Test
    public void successfulRegistrationTest () {

        String userName = getRandomFirstName();
        String userLastName = getRandomLastName();
        String userEmail = getRandomEmail();
        String userGender = getRandomItemFromArray(genders);
        String userPhone = getRandomNumber();
        String userBirthDate = getRandomData(1, 29);
        String userBirthMonth = getRandomItemFromArray(month);
        String userBirthYear = getRandomData(1900, LocalDate.now().getYear());
        String subject = getRandomItemFromArray(subjects);
        String hobby = getRandomItemFromArray(hobbies);
        String pictures = "pictures/1.png";
        String address = getRandomAddress();
        String state = getRandomItemFromArray(stateList);
        String city = getRandomItemFromArray(stateCityMap.get(state));




        registrationPage.openPage()
                        .setFirstName(userName)
                        .setLastName(userLastName)
                        .setEmail(userEmail)
                        .setGender(userGender)
                        .setPhone(userPhone)
                        .setBirthDate(userBirthDate, userBirthMonth, userBirthYear)
                        .setSubjects(subject)
                        .setHobbies(hobby)
                        .uploadPictures(pictures)
                        .setAddress(address)
                        .setState(state)
                        .setCity(city)
                        .setSubmit();

            registrationPage.verifyModalAppears()
                            .verifyResult("Student Name", userName + " " + userLastName)
                            .verifyResult("Student Email", userEmail)
                            .verifyResult("Gender", userGender)
                            .verifyResult("Mobile", userPhone)
                            .verifyResult("Date of Birth", userBirthDate + " " +  userBirthMonth + "," + userBirthYear)
                            .verifyResult("Subjects", subject)
                            .verifyResult("Hobbies", hobby)
                            .verifyResult("Address", address)
                            .verifyResult("State and City", state + " " + city);

        $("#closeLargeModal").click();
    }
}