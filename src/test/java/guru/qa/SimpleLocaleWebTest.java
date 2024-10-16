package guru.qa;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import guru.qa.data.Locale;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$$;

public class SimpleLocaleWebTest {
    //Это дата-провайдер-метод возвращает стрим аргументов, называем его как тест (если придумали свое название, то передает его в MethodSource в скобочках)
    // Arguments.of() - надо повторить столько раз, сколько раз запускается тест
    static Stream<Arguments> siteShouldContainsAllOfGivenButtonsForGivenLocale() {
        return Stream.of(
                Arguments.of(Locale.English, List.of("Women", "Men", "Kids", "Baby", "SALE %")),
                Arguments.of(Locale.ქართული, List.of("ქალი", "მამაკაცი", "ბავშვები", "ჩვილები", "SALE %"))
        );
    }
   @MethodSource()
    // 0 -порядковый номер аргумента в searchResultsShouldBeGreaterThan10ForWoman
    @ParameterizedTest (name = "Для локали {0} на сайте https://www.lcwaikiki.ge/ должен отоьбражаться список кнопок {1}")
    @Tag("BLOCKER")
    void siteShouldContainsAllOfGivenButtonsForGivenLocale(Locale locale, List<String> expectedButtons) {
        Selenide.open("https://www.lcwaikiki.ge/ ");
        $$(".header-top-languages div").find(Condition.text(locale.name())).click();
        $$(".menu-nav__lists li.menu-header-item").filter(Condition.visible).shouldHave(texts(expectedButtons));

    }

}
