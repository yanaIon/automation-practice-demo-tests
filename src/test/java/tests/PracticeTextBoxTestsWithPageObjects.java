package tests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$;

public class PracticeTextBoxTestsWithPageObjects extends TestBase{

    @Test
    public void toolsQaForm () {
        String userName = "Ivan";
        String userLastName = "Ivanov";
        String userEmail = "IvanovIvan@mail.ru";
        String userGender = "Male";
        String userPhone = "9123456789";
        String userBirthDate = "25";
        String userBirthMonth = "July";
        String userBirthYear = "1992";
        String subject = "English";
        String hobbies = "Music";
        String pictures = "pictures/1.png";
        String address = "Sunset Blvd 123";
        String state = "Uttar Pradesh";
        String city = "Agra";




        registrationPage.openPage()
                        .setFirstName(userName)
                        .setLastName(userLastName)
                        .setEmail(userEmail)
                        .setGender(userGender)
                        .setPhone(userPhone)
                        .setBirthDate(userBirthDate, userBirthMonth, userBirthYear)
                        .setSubjects(subject)
                        .setHobbies(hobbies)
                        .uploadPictures(pictures)
                        .setAddress(address)
                        .setState(state)
                        .setCity(city)
                        .setSubmit();

            registrationPage.verifyModalAppears()
                            .verifyResult("Student Name", userName + " " + userLastName)
                            .verifyResult("Student Email", "IvanovIvan@mail.ru")
                            .verifyResult("Gender", "Male")
                            .verifyResult("Mobile", "9123456789")
                            .verifyResult("Date of Birth", "25 July,1992")
                            .verifyResult("Subjects", "English")
                            .verifyResult("Hobbies", "Music")
                            .verifyResult("Address", "Sunset Blvd 123")
                            .verifyResult("State and City", "Uttar Pradesh Agra");

        $("#closeLargeModal").click();
    }
}