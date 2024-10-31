package githab;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearch {
    @Test
    void shouldFindSelenideRepositoryAtTheTop() {
        open("https://github.com/");
        $("[data-target='qbsearch-input.inputButton']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
      //кликнуть на первый репозиторий из списка найденных
        $$("[data-testid='results-list'] div").first().$("a").click(); //1 $ ищет первый элем, удовл селектору, а 2 $$ - ищет все элементы
        //проверка selenide/selenide
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }
}
