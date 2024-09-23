package pages.components;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDate(String day, String month, String year) {

        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").$(byText(month)).click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").$(byText(year)).click();

        // Корректное форматирование дня
        String formattedDay = day.length() == 1 ? "00" + day : "0" + day;
        $(".react-datepicker__day--" + formattedDay + ":not(.react-datepicker__day--outside-month)").click();
    }
}


