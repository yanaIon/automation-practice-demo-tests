package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();

    private String titleText = "Student Registration Form";
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput =  $("#lastName"),
            emailInput =  $("#userEmail"),
            genterWrapper = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            subjects =  $("#subjectsInput"),
            hobbies = $("#hobbies-checkbox-3"),
            pictures = $("#uploadPicture"),
            address = $("#currentAddress"),
            state = $("#state"),
            stateCityWrapper =  $("#stateCity-wrapper"),
            city = $("#city"),
            cityWrapper= $("#stateCity-wrapper"),
            submit = $("#submit");



    public RegistrationPage openPage() {
        open("/automation-practice-form");
        //Проверяем, что форма открылась
        $(".practice-form-wrapper").shouldHave(text(titleText));
        // Удалить элементы рекламы
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genterWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPhone(String value) {
        userNumber.setValue(value);

        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year)
    {
        System.out.println();
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjects.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbies.parent().$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPictures (String value) {
        pictures.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setAddress (String value) {
        address.setValue(value);

        return this;
    }
    public RegistrationPage setState (String value) {
        state.click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }
    public RegistrationPage setCity (String value) {
        city.click();
        cityWrapper.$(byText(value)).click();

        return this;
    }
    public RegistrationPage setSubmit () {
        submit.click();

        return this;
    }

    public RegistrationPage verifyModalAppears() {
        registrationResultsModal.verifyModalAppears();

        return this;
    }

    public RegistrationPage verifyResult(String key, String value) {
        registrationResultsModal.verifyResults(key, value);

        return this;
    }
}
