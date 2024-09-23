package pages.components;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {
    public void verifyModalAppears() {
        $(".modal-dialog").should(appear);
        $(".modal-open").shouldHave(text("Thanks for submitting the form"));
    }

    public void verifyResults(String key, String value) {
       // $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
            // Находим <td> с ключом (например, "Student Name"), поднимаемся к строке <tr>,
            // затем ищем следующий <td>, который должен содержать значение
            $(".table-responsive").$(byText(key)).closest("tr").find("td", 1).shouldHave(text(value));
    }
}
