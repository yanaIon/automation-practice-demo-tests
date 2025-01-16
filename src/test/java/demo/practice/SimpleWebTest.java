package demo.practice;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SimpleWebTest {

    @BeforeEach
    void setup() {
        Selenide.open("https://www.wildberries.ru/");
    }


//    @ValueSource(strings = {
//            "Платье женское","Брюки мужские"
//    })
//    // 0 -порядковый номер аргумента в searchResultsShouldBeGreaterThan10ForWoman
//    @ParameterizedTest (name = "В поисковой выдаче wildberries должно отображаться больше 10 результатов по запросу {0}") //т е тест запустится 2 раза, при первом разе возьмется первое "Платье...", потом "Брюки..."
//    @Tag("BLOCKER")
//    void searchResultsShouldBeGreaterThan10ForWoman(String testData) {
//        $("#searchInput").should(Condition.visible).setValue(testData).pressEnter();
//        $$(".product-card").filter(Condition.visible).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(10));
//    }


    @CsvSource(value = {
            "Платье женское, Платье",
            "Брюки мужские, Брюки"
    })
    // 0 -порядковый номер аргумента в searchResultsShouldBeGreaterThan10ForWoman
    @ParameterizedTest (name = "В первом результате выдачи для {0} должен отображаться текст {1}")
    @Tag("BLOCKER")
    void firstSearchResultsShouldContainExpectedText(String testData, String expectedText) {
        $("#searchInput").should(Condition.visible).setValue(testData).pressEnter();
        $$(".product-card__wrapper").filter(Condition.visible).first().find(".product-card__name").shouldHave(Condition.text(expectedText));
    }

}
